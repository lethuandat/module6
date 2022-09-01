package com.codegym.patient_management.repository;

import com.codegym.patient_management.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query(value = "select * from patient limit :page, 5", nativeQuery = true)
    List<Patient> findAllPagination(@Param("page") Integer page);

    @Query(value = "select * from patient", nativeQuery = true)
    List<Patient> findAllNoPagination();

    @Query(value = "select patient.* from patient join patienter on patient.patienter_id = patienter.id where patient.doctor like %:doctor% and patienter.name like %:name% and patient.reason like %:reason% and patient.method like %:method% and day_in between :dayIn and :dayOut and day_out between :dayIn and :dayOut limit :page, 5", nativeQuery = true)
    List<Patient> search(@Param("doctor") String doctor, @Param("name") String name, @Param("reason") String reason, @Param("method") String method, @Param("dayIn") String dayIn, @Param("dayOut") String dayOut, @Param("page") Integer page);

    @Query(value = "select * from patient where id = :id", nativeQuery = true)
    Optional<Patient> findById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into patient(patienter_id, day_in, day_out, reason, method, doctor) values (:patienterId, :dayIn, :dayOut, :reason, :method, :doctor );", nativeQuery = true)
    void save(@Param("patienterId") Integer patienterId, @Param("dayIn") LocalDate dayIn, @Param("dayOut") LocalDate dayOut, @Param("reason") String reason, @Param("method") String method, @Param("doctor") String doctor);

    @Transactional
    @Modifying
    @Query(value = "update patient set day_in = :dayIn, day_out = :dayOut, reason = :reason, method = :method, doctor = :doctor where id = :id", nativeQuery = true)
    void update(@Param("dayIn") LocalDate dayIn, @Param("dayOut") LocalDate dayOut, @Param("reason") String reason, @Param("method") String method, @Param("doctor") String doctor, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "delete from patient where id = :id", nativeQuery = true)
    void delete(@Param("id") Integer id);
}
