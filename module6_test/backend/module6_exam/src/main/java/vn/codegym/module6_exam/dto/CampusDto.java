package vn.codegym.module6_exam.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.codegym.module6_exam.entity.Employee;
import vn.codegym.module6_exam.entity.Guest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampusDto {
    private Integer id;

    @NotBlank(message = "Vui lòng không để trống.")
    private String name;

    @NotNull(message = "Vui lòng không để trống")
    private LocalDate date;

    @NotBlank(message = "Vui lòng không để trống.")
    private String address;

    private Boolean isDeleted;

    @NotNull(message = "Vui lòng không để trống.")
    private Employee employee;

    private List<Guest> guest;
}
