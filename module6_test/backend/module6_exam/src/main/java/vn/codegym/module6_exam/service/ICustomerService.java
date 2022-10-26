package vn.codegym.module6_exam.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.module6_exam.entity.Customer;

import java.util.Optional;

public interface ICustomerService {
    Page<Customer> findAll(Pageable pageable, String keyword);

    Optional<Customer> findById(Integer id);

    void save(Customer customer);
    void remove(Integer id);
}
