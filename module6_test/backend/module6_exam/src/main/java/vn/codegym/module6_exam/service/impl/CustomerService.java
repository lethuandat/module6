package vn.codegym.module6_exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.module6_exam.entity.Customer;
import vn.codegym.module6_exam.repository.CustomerRepository;
import vn.codegym.module6_exam.service.ICustomerService;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageable, String keyword) {
        return customerRepository.findAll(pageable, keyword);
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Integer id) {
        customerRepository.delete(id);
    }
}
