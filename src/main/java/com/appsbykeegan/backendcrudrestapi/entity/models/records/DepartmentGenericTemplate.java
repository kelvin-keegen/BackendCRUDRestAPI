package com.appsbykeegan.backendcrudrestapi.entity.models.records;

import java.math.BigDecimal;

public record DepartmentGenericTemplate(
        String departmentName,
        int departmentFloorNumber,
        String departmentDescription,
        BigDecimal departmentBudget,
        String departmentCreationDate

) {
}
