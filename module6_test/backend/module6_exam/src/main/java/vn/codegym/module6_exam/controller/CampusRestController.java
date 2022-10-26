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
import vn.codegym.module6_exam.dto.CampusDto;
import vn.codegym.module6_exam.entity.Campus;
import vn.codegym.module6_exam.entity.Customer;
import vn.codegym.module6_exam.service.ICampusService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/campus")
public class CampusRestController {
    @Autowired
    ICampusService iCampusService;

    @GetMapping("/list")
    public ResponseEntity<Page<Campus>> findAll(@PageableDefault(value = 10) Pageable pageable,
                                                @RequestParam Optional<String> campusName,
                                                @RequestParam Optional<String> employeeName) {
        Page<Campus> campuses = iCampusService.findAll(pageable, campusName.orElse(""), employeeName.orElse(""));
        if (campuses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(campuses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campus> findById(@PathVariable Integer id) {
        Optional<Campus> campus = iCampusService.findById(id);
        if (!campus.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(campus.orElse(null), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<List<FieldError>> create(@RequestBody @Valid CampusDto campusDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }

        Campus campus = new Campus();
        BeanUtils.copyProperties(campusDto, campus);
        this.iCampusService.save(campus);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Integer id) {
        Optional<Campus> campus = iCampusService.findById(id);

        if (!campus.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        iCampusService.remove(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/checkName/{name}")
    public ResponseEntity<Object> checkName(@PathVariable("name") String name) {
        return new ResponseEntity<>(iCampusService.existsName(name), HttpStatus.OK);
    }
}
