package com.codegym.patient_management.rest_controller;

import com.codegym.patient_management.model.Patienter;
import com.codegym.patient_management.service.PatienterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/patienter")
public class PatienterRestController {

    @Autowired
    PatienterService patienterService;

    @GetMapping()
    public ResponseEntity<List<Patienter>> getList() {
        List<Patienter> patienters = patienterService.findAll();
        if (patienters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patienters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patienter> findById(@PathVariable Integer id) {
        Optional<Patienter> patienter = patienterService.findById(id);
        if (!patienter.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patienter.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Patienter patienter) {
        patienterService.save(patienter);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patienter> update(@PathVariable Integer id, @RequestBody Patienter patienter) {
        Optional<Patienter> currentPatienter = patienterService.findById(id);

        if (!currentPatienter.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BeanUtils.copyProperties(patienter, currentPatienter.get());

        patienterService.update(currentPatienter.get());

        return new ResponseEntity(currentPatienter.get(), HttpStatus.OK);
    }

}
