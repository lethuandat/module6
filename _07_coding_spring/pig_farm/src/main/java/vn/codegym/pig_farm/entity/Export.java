package vn.codegym.pig_farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Export {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String company;

    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    private Integer amount;

    private Double kilogram;

    private Double price;

    private Double totalMoney;

    @Column(columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "pigsty_id", referencedColumnName = "id")
    private Pigsty pigsty;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}
