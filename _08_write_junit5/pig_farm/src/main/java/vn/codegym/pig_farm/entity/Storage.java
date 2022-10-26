package vn.codegym.pig_farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "storage")
    private List<Food> foods;

    private Integer amount;

    @Column(columnDefinition = "VARCHAR(15)")
    private String unit;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @Column(columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean isDeleted;
}
