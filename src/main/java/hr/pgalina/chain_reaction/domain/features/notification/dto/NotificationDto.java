package hr.pgalina.chain_reaction.domain.features.notification.dto;

import hr.pgalina.chain_reaction.domain.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class NotificationDto implements Serializable {

    private Long idNotification;

    private String notificationTitle;

    private String notificationText;

    private LocalDateTime createdAt;
}
