package com.codegym.patient_management.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Patienter patienter;
    private LocalDate dayIn;
    private LocalDate dayOut;
    private String reason;
    private String method;
    private String doctor;

    public Patient() {
    }

    public Patient(Integer id, Patienter patienter, LocalDate dayIn, LocalDate dayOut, String reason, String method, String doctor) {
        this.id = id;
        this.patienter = patienter;
        this.dayIn = dayIn;
        this.dayOut = dayOut;
        this.reason = reason;
        this.method = method;
        this.doctor = doctor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patienter getPatienter() {
        return patienter;
    }

    public void setPatienter(Patienter patienter) {
        this.patienter = patienter;
    }

    public LocalDate getDayIn() {
        return dayIn;
    }

    public void setDayIn(LocalDate dayIn) {
        this.dayIn = dayIn;
    }

    public LocalDate getDayOut() {
        return dayOut;
    }

    public void setDayOut(LocalDate dayOut) {
        this.dayOut = dayOut;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
