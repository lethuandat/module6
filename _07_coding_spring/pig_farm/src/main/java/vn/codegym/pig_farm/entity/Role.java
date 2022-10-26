package vn.codegym.pig_farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(45)")
    private String name;

    @Column(columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean isDeleted;


    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;
}
