package Episante.back.DAO;

import Episante.back.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationDao extends JpaRepository<Notification, Long> {
}
