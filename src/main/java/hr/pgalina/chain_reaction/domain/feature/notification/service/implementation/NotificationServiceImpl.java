package hr.pgalina.chain_reaction.domain.feature.notification.service.implementation;

import hr.pgalina.chain_reaction.domain.constant.DateTimeConstants;
import hr.pgalina.chain_reaction.domain.entity.Notification;
import hr.pgalina.chain_reaction.domain.entity.Rent;
import hr.pgalina.chain_reaction.domain.entity.User;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.constant.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.feature.notification.constant.NotificationConstants;
import hr.pgalina.chain_reaction.domain.feature.notification.dto.NotificationDto;
import hr.pgalina.chain_reaction.domain.feature.notification.service.NotificationService;
import hr.pgalina.chain_reaction.domain.feature.order.enumeration.DeliveryType;
import hr.pgalina.chain_reaction.domain.feature.rent.enumeration.Location;
import hr.pgalina.chain_reaction.domain.mapper.NotificationMapper;
import hr.pgalina.chain_reaction.domain.repository.NotificationRepository;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import hr.pgalina.chain_reaction.domain.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.NOTIFICATION_DOES_NOT_EXIST;
import static hr.pgalina.chain_reaction.domain.exception.constant.ExceptionMessages.USER_DOES_NOT_EXIST;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private static final String PRIVILEGED_TOKEN = "Basic Gts4RPPZfx35g1/jVeQlYp6HJAE=";
    private static final String WS_NOTIFICATIONS_UPDATE_PATH = "/ws/notifications/update?idUser={idUser}";
    private static final String USER_ID_PARAM = "idUser";

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private final RestTemplate restTemplate;

    @Value("${websocket-server.url}")
    private String webSocketServerUrl;

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> getAllNotificationsForUser(Long idUser) {
        log.info("Entered getAllNotificationsForUser in NotificationServiceImpl with idUser {}.", idUser);

        userRepository
            .existsByIdUser(idUser)
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST));

        List<Notification> notificationsForUser = notificationRepository.findNotificationsByUserIdUser(idUser);

        return notificationMapper.mapToDtos(notificationsForUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getNotificationsCountForUser(Long idUser) {
        log.info("Entered getNotificationsCountForUser in NotificationServiceImpl with idUser {}.", idUser);

        userRepository
                .existsByIdUser(idUser)
                .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST));

        return notificationRepository.findNotificationsCountByIdUser(idUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createNotificationForSuccessfullyCreatedRent(Long idUser, List<Rent> productRentals) {
        log.info("Entered createNotificationForSuccessfullyCreatedRent in NotificationServiceImpl with idUser {}.", idUser);

        Rent productRental = productRentals.get(0);

        createNotification(
            NotificationConstants.RENT_NOTIFICATION,
            String.format(
                NotificationConstants.RENT_SUCCESSFULLY_CREATED,
                productRental.getProduct().getName(),
                productRental.getProduct().getModel(),
                Location.findByIdLocation(productRental.getLocation()).getValue(),
                DateTimeUtils.formatDate(productRental.getDate(), DateTimeConstants.APP_LOCAL_DATE_FORMAT),
                DateTimeUtils.formatTime(productRental.getActiveFrom(), DateTimeConstants.APP_LOCAL_TIME_FORMAT),
                DateTimeUtils.formatTime(productRental.getActiveTo(), DateTimeConstants.APP_LOCAL_TIME_FORMAT)
            ),
            idUser
        );
        sendInformationAboutNotificationCountChange(idUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createNotificationForSuccessfullyCreatedOrder(Long idUser, DeliveryType deliveryType) {
        log.info("Entered createNotificationForSuccessfullyCreatedOrder in NotificationServiceImpl with idUser {} and deliveryType {}.", idUser, deliveryType.getValue());

        createNotification(
            NotificationConstants.ORDER_NOTIFICATION,
            String.format(
                NotificationConstants.ORDER_SUCCESSFULLY_CREATED,
                DateTimeUtils.formatDateWithLocale(LocalDate.now().plusDays(deliveryType.getMinimumArrivalDays()), DateTimeConstants.APP_LOCAL_DATE_WITH_DAY_AND_MONTH_NAME_FORMAT, Locale.ENGLISH),
                DateTimeUtils.formatDateWithLocale(LocalDate.now().plusDays(deliveryType.getMaximumArrivalDays()), DateTimeConstants.APP_LOCAL_DATE_WITH_DAY_AND_MONTH_NAME_FORMAT, Locale.ENGLISH)
            ),
            idUser
        );
        sendInformationAboutNotificationCountChange(idUser);
    }

    @Override
    @Transactional
    public void deleteNotificationsForUser(Long idUser) {
        log.info("Entered deleteNotificationsForUser in NotificationServiceImpl with idUser {}.", idUser);

        userRepository
            .existsByIdUser(idUser)
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST));

        notificationRepository.deleteAllByUserIdUser(idUser);

        sendInformationAboutNotificationCountChange(idUser);
    }

    @Override
    @Transactional
    public void deleteNotificationById(Long idNotification) {
        log.info("Entered deleteNotificationById in NotificationServiceImpl with idNotification {}.", idNotification);

        Notification notification = notificationRepository
            .findById(idNotification)
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, NOTIFICATION_DOES_NOT_EXIST));

        notificationRepository.delete(notification);

        sendInformationAboutNotificationCountChange(notification.getUser().getIdUser());
    }

    private void sendInformationAboutNotificationCountChange(Long idUser) {
        log.info("Entered sendInformationAboutNotificationCountChange in NotificationServiceImpl with idUser {}.", idUser);

        URI uri = createCompleteUri(idUser);

        HttpEntity<?> entity = new HttpEntity<>(createAuthHeader());

        restTemplate.postForEntity(uri, entity, Void.class);
    }

    private void createNotification(String title, String text, Long idUser) {
        Notification notification = new Notification();

        User user = userRepository
                .findById(idUser)
                .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST));

        notification.setUser(user);
        notification.setNotificationTitle(title);
        notification.setNotificationText(text);

        notificationRepository.save(notification);
    }

    private URI createCompleteUri(Long idUser) {
        Map<String, String> params = new HashMap<>();
        params.put(USER_ID_PARAM, String.valueOf(idUser));

        return UriComponentsBuilder.fromUriString(webSocketServerUrl + WS_NOTIFICATIONS_UPDATE_PATH)
            .buildAndExpand(params)
            .toUri();
    }

    private HttpHeaders createAuthHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", PRIVILEGED_TOKEN);

        return headers;
    }
}
