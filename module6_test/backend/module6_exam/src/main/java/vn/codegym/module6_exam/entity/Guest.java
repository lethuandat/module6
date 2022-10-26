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
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String idCard;

    private LocalDate birthDay;

    private Boolean gender;

    private LocalDate registerDay;

    private String expiry;

    @ManyToOne
    @JoinColumn(name = "campus_id", referencedColumnName = "id")
    private Campus campus;
}
