package vn.codegym.module6_exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.module6_exam.entity.CustomerType;
import vn.codegym.module6_exam.repository.CustomerTypeRepository;
import vn.codegym.module6_exam.service.ICustomerTypeService;

import java.util.List;

@Service
public class CustomerTypeService implements ICustomerTypeService {
    @Autowired
    CustomerTypeRepository customerTypeRepository;

    @Override
    public List<CustomerType> findAll() {
        return customerTypeRepository.findAll();
    }
}
