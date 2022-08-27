package com.codegym.patient_management.service.impl;

import com.codegym.patient_management.model.Patienter;
import com.codegym.patient_management.repository.PatienterRepository;
import com.codegym.patient_management.service.PatienterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatienterServiceImpl implements PatienterService {
    @Autowired
    PatienterRepository patienterRepository;

    @Override
    public List<Patienter> findAll() {
        return patienterRepository.findAll();
    }

    @Override
    public Optional<Patienter> findById(Integer id) {
        return patienterRepository.findById(id);
    }
    @Override
    public void save(Patienter patienter) {
        patienterRepository.save(patienter.getName());
    }

    @Override
    public void update(Patienter patienter) {
        patienterRepository.update(patienter.getName(), patienter.getId());
    }
}
