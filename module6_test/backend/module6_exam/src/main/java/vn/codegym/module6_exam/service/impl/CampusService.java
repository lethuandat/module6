package vn.codegym.module6_exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.module6_exam.entity.Campus;
import vn.codegym.module6_exam.repository.CampusRepository;
import vn.codegym.module6_exam.service.ICampusService;

import java.util.List;
import java.util.Optional;

@Service
public class CampusService implements ICampusService {
    @Autowired
    CampusRepository campusRepository;

    @Override
    public Page<Campus> findAll(Pageable pageable, String campusName, String employeeName) {
        return campusRepository.findAll(pageable, campusName, employeeName);
    }

    @Override
    public Optional<Campus> findById(Integer id) {
        return campusRepository.findById(id);
    }

    @Override
    public void save(Campus campus) {
        campusRepository.save(campus.getAddress(), campus.getDate(), campus.getIsDeleted(), campus.getName(), campus.getEmployee().getId());
    }

    @Override
    public void remove(Integer id) {
        campusRepository.delete(id);
    }

    @Override
    public Boolean existsName(String name) {
        return name.equals(campusRepository.checkExistsName(name));
    }
}
