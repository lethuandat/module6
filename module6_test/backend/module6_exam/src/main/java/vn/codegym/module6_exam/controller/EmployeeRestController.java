package vn.codegym.module6_exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.module6_exam.entity.Employee;
import vn.codegym.module6_exam.service.IEmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/employee")
public class EmployeeRestController {
    @Autowired
    IEmployeeService employeeService;

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employeeList = employeeService.findAll();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.orElse(null), HttpStatus.OK);
    }
}
