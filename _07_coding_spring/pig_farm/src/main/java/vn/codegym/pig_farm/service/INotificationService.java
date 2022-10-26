package vn.codegym.pig_farm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import vn.codegym.pig_farm.entity.Notification;

public interface INotificationService {

    /**
     * Created by: DatLT
     * Date created: 08/09/2022
     * Function: Display all news list by keyword with pagination
     * @param pageable pageable
     * @param keyword keyword
     * @return Page<Notification>
     */

    Page<Notification> findAll(Pageable pageable, @Param("keyword") String keyword);
}
