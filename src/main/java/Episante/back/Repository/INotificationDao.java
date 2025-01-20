package Episante.back.Repository;

import Episante.back.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationDao extends JpaRepository<Notification, Long> {
}
