package com.appsbykeegan.backendcrudrestapi.entity.models.records;

import com.appsbykeegan.backendcrudrestapi.entity.models.enums.EmployeeGender;
import com.appsbykeegan.backendcrudrestapi.entity.models.enums.EmployeeRole;
import com.appsbykeegan.backendcrudrestapi.entity.tables.DepartmentEntity;

public record EmployeeGenericTemplate(
        String employeeFirstName,
        String employeeLastName,
        EmployeeGender employeeGender,
        String startDate,
        EmployeeRole employeeRole,
        String emailAddress,
        DepartmentEntity departmentEntity
) {
}
