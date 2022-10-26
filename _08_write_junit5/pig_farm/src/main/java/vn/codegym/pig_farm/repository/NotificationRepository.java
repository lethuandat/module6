package vn.codegym.pig_farm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.codegym.pig_farm.entity.Notification;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    /**
     * Created by: DatLT
     * Date created: 08/09/2022
     * Function: Display all news list by keyword with pagination
     *
     * @param pageable pageable
     * @param keyword  keyword
     * @return Page<Notification> notifications
     */
    @Query(value = "select * from notification where title like %:keyword% and content like %:keyword% and is_deleted = 0", nativeQuery = true)
    Page<Notification> findAll(Pageable pageable, @Param("keyword") String keyword);


    /**
     * Created by: DatLT
     * Date created: 10/09/2022
     * Function: Display one news by id
     *
     * @param id must not be {@literal null}.
     * @return Notification object
     */

    @Query(value = "select * from notification where id = :id", nativeQuery = true)
    Optional<Notification> findById(@Param("id") Integer id);
}
