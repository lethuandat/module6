package vn.codegym.module6_exam.service;

import vn.codegym.module6_exam.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> findAll();

    Optional<Employee> findById(Integer id);
}
