package vn.codegym.module6_exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.codegym.module6_exam.entity.CustomerType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Integer id;


    private CustomerType customerType;


    @NotBlank(message = "Vui lòng không để trống.")
    private String name;


    @NotNull(message = "Vui lòng không để trống")
    private LocalDate birthDay;


    private Boolean gender;


    @NotBlank(message = "Vui lòng không để trống")
    private String idCard;


    @NotBlank(message = "Vui lòng không để trống")
    private String phone;


    @NotBlank(message = "Vui lòng không để trống")
    private String email;


    @NotBlank(message = "Vui lòng không để trống")
    private String address;


    private Boolean isDeleted;
}
