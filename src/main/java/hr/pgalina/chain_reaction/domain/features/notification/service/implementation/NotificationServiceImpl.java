package hr.pgalina.chain_reaction.domain.features.notification.service.implementation;

import hr.pgalina.chain_reaction.domain.entity.Notification;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.notification.dto.NotificationDto;
import hr.pgalina.chain_reaction.domain.features.notification.service.NotificationService;
import hr.pgalina.chain_reaction.domain.mapper.NotificationMapper;
import hr.pgalina.chain_reaction.domain.repository.NotificationRepository;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.USER_DOES_NOT_EXIST;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    @Override
    public List<NotificationDto> getAllNotificationsForUser(Long idUser) {
        log.info("Entered getAllNotificationsForUser in NotificationServiceImpl with idUser {}.", idUser);

        userRepository
            .existsByIdUser(idUser)
            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST));

        List<Notification> notificationsForUser = notificationRepository.findNotificationsByUserIdUser(idUser);

        return notificationMapper.mapToDtos(notificationsForUser);
    }

    @Override
    public Long getNotificationsCountForUser(Long idUser) {
        log.info("Entered getNotificationsCountForUser in NotificationServiceImpl with idUser {}.", idUser);

        userRepository
                .existsByIdUser(idUser)
                .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST));

        return notificationRepository.findNotificationsCountByIdUser(idUser);
    }
}
