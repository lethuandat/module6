package vn.codegym.module6_exam.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import vn.codegym.module6_exam.dto.CustomerDto;
import vn.codegym.module6_exam.entity.Customer;
import vn.codegym.module6_exam.service.ICustomerService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/customer")
public class CustomerRestController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> findAll(@PageableDefault(value = 5) Pageable pageable, @RequestParam Optional<String> keyword) {
        Page<Customer> customers = customerService.findAll(pageable, keyword.orElse(""));
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customerService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer.orElse(null), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<List<FieldError>> create(@RequestBody @Valid CustomerDto customerDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        this.customerService.save(customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> update(@PathVariable Integer id, @Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
        Optional<Customer> currentCustomer = customerService.findById(id);

        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        if (!currentCustomer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentCustomer.get().setId(customerDto.getId());
        currentCustomer.get().setCustomerType(customerDto.getCustomerType());
        currentCustomer.get().setName(customerDto.getName());
        currentCustomer.get().setBirthDay(customerDto.getBirthDay());
        currentCustomer.get().setGender(customerDto.getGender());
        currentCustomer.get().setIdCard(customerDto.getIdCard());
        currentCustomer.get().setPhone(customerDto.getPhone());
        currentCustomer.get().setEmail(customerDto.getEmail());
        currentCustomer.get().setAddress(customerDto.getAddress());

        customerService.save(currentCustomer.get());

        return new ResponseEntity<>(currentCustomer.get(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Integer id) {
        Optional<Customer> customer = customerService.findById(id);

        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customerService.remove(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
