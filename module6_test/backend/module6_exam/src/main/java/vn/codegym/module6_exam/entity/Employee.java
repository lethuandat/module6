package vn.codegym.module6_exam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDate birthDay;

    private Boolean gender;

    @ManyToOne
    @JoinColumn(name = "placement_id", referencedColumnName = "id")
    private Placement placement;

    @OneToOne(mappedBy = "employee")
    @JsonBackReference
    private Campus campus;
}
