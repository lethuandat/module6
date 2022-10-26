package vn.codegym.module6_exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.module6_exam.entity.Placement;
import vn.codegym.module6_exam.repository.PlacementRepository;
import vn.codegym.module6_exam.service.IPlacementService;

import java.util.List;
import java.util.Optional;

@Service
public class PlacementService implements IPlacementService {
    @Autowired
    PlacementRepository placementRepository;

    @Override
    public List<Placement> findAll() {
        return placementRepository.findAll();
    }

    @Override
    public Optional<Placement> findById(Integer id) {
        return placementRepository.findById(id);
    }
}
