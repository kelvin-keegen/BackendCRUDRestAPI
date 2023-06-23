package com.appsbykeegan.backendcrudrestapi.entity.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "department")
public class FacultyDepartmentClass {

    @Id
    private Long departmentId;
    private String departmentName;
    private int departmentFloorNumber;
    private String departmentDescription;
    private BigDecimal departmentBudget;
    private String departmentCreationDate;

}
