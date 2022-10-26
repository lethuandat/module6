package vn.codegym.module6_exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_type_id", referencedColumnName = "id")
    private CustomerType customerType;

    private String name;

    private LocalDate birthDay;

    private Boolean gender;

    @Column(unique = true)
    private String idCard;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    private String address;

    private Boolean isDeleted;
}
