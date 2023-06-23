package com.appsbykeegan.backendcrudrestapi.entity.tables;

import com.appsbykeegan.backendcrudrestapi.entity.models.enums.EmployeeGender;
import com.appsbykeegan.backendcrudrestapi.entity.models.enums.EmployeeRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
public class EmployeeClass {


    @Id
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private EmployeeGender employeeGender;
    private String StartDate;
    private EmployeeRole employeeRole;
    private String emailAddress;

}
