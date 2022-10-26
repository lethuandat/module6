package vn.codegym.pig_farm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import vn.codegym.pig_farm.entity.Notification;

import java.util.Optional;

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


    /**
     * Created by: DatLT
     * Date created: 10/09/2022
     * Function: Display one news by id
     *
     * @param id must not be {@literal null}.
     * @return Notification object
     */
    Optional<Notification> findById(Integer id);
}
