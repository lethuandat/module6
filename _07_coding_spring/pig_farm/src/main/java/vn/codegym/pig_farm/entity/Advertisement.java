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
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String title;

    @Column(columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(columnDefinition = "DATE")
    private LocalDate submittedDate;

    @Column(columnDefinition = "VARCHAR(50)")
    private String timeExistence;


    @OneToMany(mappedBy = "advertisement")
    private List<Placement> placements;

    @Column(columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean isDeleted;

}
