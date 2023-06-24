package com.appsbykeegan.backendcrudrestapi.entity.models.records;

import com.appsbykeegan.backendcrudrestapi.entity.models.enums.EmployeeGender;
import com.appsbykeegan.backendcrudrestapi.entity.models.enums.EmployeeRole;
import com.appsbykeegan.backendcrudrestapi.entity.tables.DepartmentEntity;

public record EmployeeRequestBody(
        String employeeFirstName,
        String employeeLastName,
        EmployeeGender employeeGender,
        EmployeeRole employeeRole,
        String emailAddress,
        DepartmentEntity departmentEntity
) {
}
