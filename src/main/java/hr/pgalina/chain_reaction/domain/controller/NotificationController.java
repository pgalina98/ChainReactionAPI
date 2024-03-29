package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.feature.notification.dto.NotificationDto;
import hr.pgalina.chain_reaction.domain.feature.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> fetchAllNotificationsForUser(@RequestParam Long idUser) {
        log.info("Entered '/api/notifications' with user ID {} [GET].", idUser);

        return new ResponseEntity<>(notificationService.getAllNotificationsForUser(idUser), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> fetchNotificationsCountForUser(@RequestParam Long idUser) {
        log.info("Entered '/api/notifications/count' with user ID {} [GET].", idUser);

        return new ResponseEntity<>(notificationService.getNotificationsCountForUser(idUser), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> removeNotificationsForUser(@RequestParam Long idUser) {
        log.info("Entered '/api/notifications' with user ID {} [DELETE].", idUser);

        notificationService.deleteNotificationsForUser(idUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idNotification}")
    public ResponseEntity<?> removeNotificationById(@PathVariable Long idNotification) {
        log.info("Entered '/api/notifications/{idNotification}' with notification ID {} [DELETE].", idNotification);

        notificationService.deleteNotificationById(idNotification);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
