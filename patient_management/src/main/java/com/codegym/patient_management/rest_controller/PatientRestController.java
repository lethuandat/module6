package com.codegym.patient_management.rest_controller;

import com.codegym.patient_management.dto.PatientDto;
import com.codegym.patient_management.model.Patient;
import com.codegym.patient_management.model.Patienter;
import com.codegym.patient_management.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/patient")
public class PatientRestController {
    @Autowired
    PatientService patientService;

    @GetMapping("/list")
    public ResponseEntity<List<Patient>> findAllPagination(@RequestParam(value = "page", required = false) Integer page) {
        List<Patient> patients = patientService.findAllPagination(page);
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/list-no-pagination")
    public ResponseEntity<List<Patient>> findAllNoPagination() {
        List<Patient> patients = patientService.findAllNoPagination();
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Patient>> search(@RequestParam(value = "doctor", required = false, defaultValue = "") String doctor, @RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "reason", required = false, defaultValue = "") String reason, @RequestParam(value = "method", required = false, defaultValue = "") String method, @RequestParam(value = "page", required = false) Integer page) {
        List<Patient> patients = patientService.search(doctor, name, reason, method, page);
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
    public ResponseEntity add(@Validated @RequestBody PatientDto patientDto, BindingResult bindingResult) {
        if (!patientDto.getDayIn().equals("") && !patientDto.getDayOut().equals("")) {
            new PatientDto().validate(patientDto, bindingResult);
        }

        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_FOUND);
        }

        Patient patient = new Patient();

        BeanUtils.copyProperties(patientDto, patient);

        patient.setPatienter(new Patienter(Integer.parseInt(patientDto.getPatienter())));

        patient.setDayIn(LocalDate.parse(patientDto.getDayIn()));

        patient.setDayOut(LocalDate.parse(patientDto.getDayOut()));

        patientService.save(patient);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Integer id, @Validated @RequestBody PatientDto patientDto, BindingResult bindingResult) {
        Optional<Patient> currentPatient = patientService.findById(id);

        if (!patientDto.getDayIn().equals("") && !patientDto.getDayOut().equals("")) {
            new PatientDto().validate(patientDto, bindingResult);
        }

        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        if (!currentPatient.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentPatient.get().setId(patientDto.getId());

        currentPatient.get().setPatienter(new Patienter(Integer.parseInt(patientDto.getPatienter())));

        currentPatient.get().setDayIn(LocalDate.parse(patientDto.getDayIn()));

        currentPatient.get().setDayOut(LocalDate.parse(patientDto.getDayOut()));

        currentPatient.get().setReason(patientDto.getReason());

        currentPatient.get().setMethod(patientDto.getMethod());

        currentPatient.get().setDoctor(patientDto.getDoctor());

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
