package vn.codegym.module6_exam.service;

import vn.codegym.module6_exam.entity.Placement;

import java.util.List;
import java.util.Optional;

public interface IPlacementService {
    List<Placement> findAll();

    Optional<Placement> findById(Integer id);
}
