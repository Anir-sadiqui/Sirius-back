package Episante.back.Service;

import Episante.back.DAO.INotificationDao;
import Episante.back.Entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private INotificationDao notificationDao;

    public List<Notification> getAllNotifications() {
        return notificationDao.findAll();
    }

    public Notification saveNotification(Notification notification) {
        return notificationDao.save(notification);
    }
}
