package hr.pgalina.chain_reaction.domain.repository;

import hr.pgalina.chain_reaction.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findNotificationsByUserIdUser(Long idUser);

    @Query(value = "SELECT count(notification.*) FROM Notification notification WHERE notification.id_user =:idUser", nativeQuery = true)
    Long findNotificationsCountByIdUser(Long idUser);
}
