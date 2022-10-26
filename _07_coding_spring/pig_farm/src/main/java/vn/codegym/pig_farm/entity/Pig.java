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
public class Pig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(10)")
    private String code;

    @Column(columnDefinition = "DATE")
    private LocalDate dateIn;

    @Column(columnDefinition = "DATE")
    private LocalDate dateOut;

    @Column(columnDefinition = "VARCHAR(20)")
    private String status;

    @Column(columnDefinition = "VARCHAR(20)")
    private String weight;

    @Column(columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "pig")
    private List<Treatment> treatments;

    @ManyToOne
    @JoinColumn(name = "pigsty_id", referencedColumnName = "id")
    private Pigsty pigsty;
}
