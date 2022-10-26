package vn.codegym.module6_exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.codegym.module6_exam.entity.CustomerType;
import vn.codegym.module6_exam.service.impl.CustomerTypeService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/customerType")
public class CustomerTypeRestController {
    @Autowired
    CustomerTypeService customerTypeService;

    @GetMapping()
    public ResponseEntity<List<CustomerType>> findAll() {
        List<CustomerType> customerTypeList = customerTypeService.findAll();
        if (customerTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerTypeList, HttpStatus.OK);
    }
}
