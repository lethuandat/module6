package com.codegym.patient_management.service;

import com.codegym.patient_management.model.Patienter;

import java.util.List;
import java.util.Optional;

public interface PatienterService {
    List<Patienter> findAll();
    Optional<Patienter> findById(Integer id);
    void save(Patienter patienter);

    void update(Patienter patienter);
}
