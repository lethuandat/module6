package vn.codegym.module6_exam.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.module6_exam.entity.Campus;

import java.util.Optional;

public interface ICampusService {
    Page<Campus> findAll(Pageable pageable, String campusName, String employeeName);

    Optional<Campus> findById(Integer id);

    void save(Campus campus);

    void remove(Integer id);

    Boolean existsName(String name);
}
