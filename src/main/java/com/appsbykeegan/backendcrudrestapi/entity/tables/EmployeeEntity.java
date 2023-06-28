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
public class EmployeeEntity {

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
    @Enumerated(EnumType.STRING)
    private EmployeeGender employeeGender;
    private String startDate;
    @Enumerated(EnumType.STRING)
    private EmployeeRole employeeRole;
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "departmentId",nullable = false)
    private DepartmentEntity department;

    public EmployeeEntity(String employeeFirstName, String employeeLastName, EmployeeGender employeeGender, String startDate, EmployeeRole employeeRole, String emailAddress, DepartmentEntity department) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeGender = employeeGender;
        this.startDate = startDate;
        this.employeeRole = employeeRole;
        this.emailAddress = emailAddress;
        this.department = department;
    }
}
