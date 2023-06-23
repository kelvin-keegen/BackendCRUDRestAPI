package com.appsbykeegan.backendcrudrestapi.entity.tables;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "department")
public class DepartmentTable {

    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    private Long departmentId;
    private String departmentName;
    private int departmentFloorNumber;
    private String departmentDescription;
    private BigDecimal departmentBudget;
    private String departmentCreationDate;

    @OneToMany(mappedBy = "departmentTable",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<EmployeeTable> employees;

    public DepartmentTable(String departmentName, int departmentFloorNumber, String departmentDescription, BigDecimal departmentBudget, String departmentCreationDate) {
        this.departmentName = departmentName;
        this.departmentFloorNumber = departmentFloorNumber;
        this.departmentDescription = departmentDescription;
        this.departmentBudget = departmentBudget;
        this.departmentCreationDate = departmentCreationDate;
    }
}
