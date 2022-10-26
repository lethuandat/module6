package vn.codegym.pig_farm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.codegym.pig_farm.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    /**
     * Created by: DatLT
     * Date created: 08/09/2022
     * Function: Display all news list by keyword with pagination
     * @param pageable pageable
     * @param keyword keyword
     * @return Page<Notification> notifications
     */
    @Query(value = "select * from notification where title like %:keyword% and content like %:keyword% and is_deleted = 0", nativeQuery = true)
    Page<Notification> findAll(Pageable pageable, @Param("keyword") String keyword);
}
