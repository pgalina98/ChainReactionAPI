package hr.pgalina.chain_reaction.domain.feature.notification.service;

import hr.pgalina.chain_reaction.domain.entity.Order;
import hr.pgalina.chain_reaction.domain.entity.Rent;
import hr.pgalina.chain_reaction.domain.feature.notification.dto.NotificationDto;
import hr.pgalina.chain_reaction.domain.feature.order.enumeration.DeliveryType;

import java.util.List;

public interface NotificationService {

    List<NotificationDto> getAllNotificationsForUser(Long idUser);

    Long getNotificationsCountForUser(Long idUser);

    void createNotificationForSuccessfullyCreatedRent(Long idUser, List<Rent> productRentals);

    void createNotificationForSuccessfullyCreatedOrder(Long idUser, DeliveryType deliveryType);

    void deleteNotificationsForUser(Long idUser);

    void deleteNotificationById(Long idNotification);
}
