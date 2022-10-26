package vn.codegym.module6_exam.dto.projections;

import java.time.LocalDate;

public interface CampusProjection {
    Integer getId();

    String getName();

    String getAddress();

    LocalDate getDate();

    Integer getEmployee();
}
