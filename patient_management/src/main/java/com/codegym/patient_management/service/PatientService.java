package com.codegym.patient_management.service;

import com.codegym.patient_management.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> findAll();
    Optional<Patient> findById(Integer id);
    void save(Patient patient);
    void update(Patient patient);
    void delete(Integer id);
}
