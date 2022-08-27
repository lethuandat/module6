package com.codegym.patient_management.service.impl;

import com.codegym.patient_management.model.Patient;
import com.codegym.patient_management.repository.PatientRepository;
import com.codegym.patient_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(Integer id) {
        return patientRepository.findById(id);
    }

    @Override
    public void save(Patient patient) {
        patientRepository.save(patient.getPatienter().getId(), patient.getDayIn(), patient.getDayOut(), patient.getReason(), patient.getMethod(), patient.getDoctor());
    }

    @Override
    public void update(Patient patient) {
        patientRepository.update(patient.getDayIn(), patient.getDayOut(), patient.getReason(), patient.getMethod(), patient.getDoctor(), patient.getId());
    }

    @Override
    public void delete(Integer id) {
        patientRepository.delete(id);
    }
}
