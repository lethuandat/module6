package com.codegym.patient_management.service;

import com.codegym.patient_management.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> findAllPagination(Integer page);
    List<Patient> findAllNoPagination();
    List<Patient> search(String doctor, String name, String reason, String method, String dayIn, String dayOut, Integer page);
    Optional<Patient> findById(Integer id);
    void save(Patient patient);
    void update(Patient patient);
    void delete(Integer id);
}
