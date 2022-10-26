package vn.codegym.module6_exam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.codegym.module6_exam.entity.Customer;

import javax.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select * " +
            "from customer " +
            "where name like %:keyword% " +
            "and address like %:keyword% " +
            "and email like %:keyword% " +
            "and is_deleted = 0", nativeQuery = true)
    Page<Customer> findAll(Pageable pageable, @Param("keyword") String keyword);

    @Transactional
    @Modifying
    @Query(value = "update customer " +
            "set is_deleted = 1 " +
            "where id =:id", nativeQuery = true)
    void delete(@Param("id") Integer id);
}
