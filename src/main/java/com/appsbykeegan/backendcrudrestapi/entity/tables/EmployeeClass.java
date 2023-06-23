package com.appsbykeegan.backendcrudrestapi.entity.tables;

import com.appsbykeegan.backendcrudrestapi.entity.models.enums.EmployeeGender;
import com.appsbykeegan.backendcrudrestapi.entity.models.enums.EmployeeRole;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
public class EmployeeClass {

    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private EmployeeGender employeeGender;
    private String StartDate;
    private EmployeeRole employeeRole;
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "departmentId",nullable = false)
    private DepartmentClass departmentClass;
}
