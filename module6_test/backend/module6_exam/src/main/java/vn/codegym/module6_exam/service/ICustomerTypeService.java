package vn.codegym.module6_exam.service;

import vn.codegym.module6_exam.entity.CustomerType;

import java.util.List;
public interface ICustomerTypeService {
    List<CustomerType> findAll();
}
