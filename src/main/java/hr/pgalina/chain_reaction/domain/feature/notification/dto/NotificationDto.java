package hr.pgalina.chain_reaction.domain.feature.notification.dto;

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
