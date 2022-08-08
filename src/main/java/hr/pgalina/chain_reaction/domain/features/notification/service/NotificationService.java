package hr.pgalina.chain_reaction.domain.features.notification.service;

import hr.pgalina.chain_reaction.domain.entity.Rent;
import hr.pgalina.chain_reaction.domain.features.notification.dto.NotificationDto;

import java.util.List;

public interface NotificationService {

    List<NotificationDto> getAllNotificationsForUser(Long idUser);

    Long getNotificationsCountForUser(Long idUser);


    void createNotificationForSuccessfullyCreatedRent(Long idUser, List<Rent> productRentals);

    void deleteNotificationsForUser(Long idUser);
}
