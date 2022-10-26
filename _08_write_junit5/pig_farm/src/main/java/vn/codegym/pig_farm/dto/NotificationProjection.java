package vn.codegym.pig_farm.dto;

import java.time.LocalDate;

public interface NotificationProjection {

    Integer getId();

    String getTitle();

    String getContent();

    LocalDate getSubmittedDate();

    String getImage();
}
