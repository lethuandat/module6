package com.codegym.patient_management.repository;

import com.codegym.patient_management.model.Patienter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatienterRepository extends JpaRepository<Patienter, Integer> {
    @Query(value = "select * from patienter", nativeQuery = true)
    List<Patienter> findAll();

    @Query(value = "select * from patienter where id = :id", nativeQuery = true)
    Optional<Patienter> findById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "insert into patienter(name) values (:name);", nativeQuery = true)
    void save(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "update patienter set name = :name where id = :id", nativeQuery = true)
    void update(@Param("name") String name, @Param("id") Integer id);
}
