package vn.codegym.module6_exam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.codegym.module6_exam.entity.Campus;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface CampusRepository extends JpaRepository<Campus, Integer> {
    @Query(value = "select campus.* " +
            "from campus " +
            "join employee on campus.employee_id = employee.id " +
            "where campus.name like %:campusName% " +
            "and employee.name like %:employeeName% " +
            "and is_deleted = false", nativeQuery = true)
    Page<Campus> findAll(Pageable pageable, @Param("campusName") String campusName, @Param("employeeName") String employeeName);

    @Transactional
    @Modifying
    @Query(value = "update campus " +
            "set is_deleted = 1 " +
            "where id =:id", nativeQuery = true)
    void delete(@Param("id") Integer id);


    @Modifying
    @Transactional
    @Query(value = "insert into campus (address, date, is_deleted, `name`, employee_id)" +
            " values (:address, :date, :is_deleted, :name, :employee_id );", nativeQuery = true)
    void save(@Param("address") String address, @Param("date") LocalDate date, @Param("is_deleted") Boolean isDeleted, @Param("name") String name, @Param("employee_id") Integer employeeId);


    @Query(value = "select `name` " +
            "from campus ", nativeQuery = true)
    List<String> checkExistsName(@Param("name") String name);
}
