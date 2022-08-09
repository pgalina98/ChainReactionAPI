package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.entity.Notification;
import hr.pgalina.chain_reaction.domain.features.notification.dto.NotificationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationMapper {

    public NotificationDto mapToDto(Notification notification) {
        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setIdNotification(notification.getIdNotification());
        notificationDto.setNotificationTitle(notification.getNotificationTitle());
        notificationDto.setNotificationText(notification.getNotificationText());
        notificationDto.setCreatedAt(notification.getCreatedTimestamp());

        return notificationDto;
    }

    public List<NotificationDto> mapToDtos(List<Notification> notifications) {
        return notifications
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
}