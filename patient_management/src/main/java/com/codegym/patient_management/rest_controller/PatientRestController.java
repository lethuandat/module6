package com.codegym.patient_management.rest_controller;

import com.codegym.patient_management.model.Patient;
import com.codegym.patient_management.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/patient")
public class PatientRestController {
    @Autowired
    PatientService patientService;

    @GetMapping()
    public ResponseEntity<List<Patient>> getList() {
        List<Patient> patients = patientService.findAll();
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Integer id) {
        Optional<Patient> patient = patientService.findById(id);
        if (!patient.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Patient patient) {
        patientService.save(patient);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Integer id, @RequestBody Patient patient) {
        Optional<Patient> currentPatient = patientService.findById(id);

        if (!currentPatient.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BeanUtils.copyProperties(patient, currentPatient.get());

        patientService.update(currentPatient.get());

        return new ResponseEntity(currentPatient.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> delete(@PathVariable Integer id) {
        Optional<Patient> patient = patientService.findById(id);

        if (!patient.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        patientService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
