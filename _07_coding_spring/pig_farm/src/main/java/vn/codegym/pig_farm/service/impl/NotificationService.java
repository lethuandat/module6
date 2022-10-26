package vn.codegym.pig_farm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.pig_farm.entity.Notification;
import vn.codegym.pig_farm.repository.NotificationRepository;
import vn.codegym.pig_farm.service.INotificationService;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    /**
     * Created by: DatLT
     * Date created: 08/09/2022
     * Function: Display all news list by keyword with pagination
     * @param pageable pageable
     * @param keyword keyword
     * @return Page<Notification> notifications
     */

    @Override
    public Page<Notification> findAll(Pageable pageable, String keyword) {
        return notificationRepository.findAll(pageable, keyword);
    }
}
