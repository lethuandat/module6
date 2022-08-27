package com.codegym.patient_management.dto;

import com.codegym.patient_management.model.Patienter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class PatientDto implements Validator {
    private Integer id;

    @NotNull(message = "*Không được để trống!")
    private Patienter patienter;

    @NotBlank(message = "*Không được để trống!")
    private String dayIn;

    @NotBlank(message = "*Không được để trống!")
    private String dayOut;

    @NotBlank(message = "*Không được để trống!")
    private String reason;

    @NotBlank(message = "*Không được để trống!")
    private String method;

    @NotBlank(message = "*Không được để trống!")
    private String doctor;

    public PatientDto() {
    }

    public PatientDto(Integer id, Patienter patienter, String dayIn, String dayOut, String reason, String method, String doctor) {
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

    public String getDayIn() {
        return dayIn;
    }

    public void setDayIn(String dayIn) {
        this.dayIn = dayIn;
    }

    public String getDayOut() {
        return dayOut;
    }

    public void setDayOut(String dayOut) {
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PatientDto patientDto = (PatientDto) target;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (Period.between(LocalDate.parse(patientDto.getDayIn(), formatter), LocalDate.now()).getDays() < 0) {
            errors.rejectValue("dayIn", "dayIn.fail");
        }

        if (Period.between(LocalDate.parse(patientDto.getDayOut(), formatter), LocalDate.now()).getDays() < 0) {
            errors.rejectValue("dayOut", "dayOut.fail");
        }

        if (Period.between(LocalDate.parse(patientDto.getDayOut(), formatter), LocalDate.parse(patientDto.getDayOut(), formatter)).getDays() < 0) {
            errors.rejectValue("dayOut", "minusDay.fail");
        }
    }
}
