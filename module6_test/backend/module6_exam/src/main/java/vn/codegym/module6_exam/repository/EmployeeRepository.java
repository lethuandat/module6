package vn.codegym.module6_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.codegym.module6_exam.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "select * " +
            "from employee " +
            "where placement_id = 1", nativeQuery = true)
    List<Employee> findAll();
}
