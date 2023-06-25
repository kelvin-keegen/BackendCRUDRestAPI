package com.appsbykeegan.backendcrudrestapi.service;

import com.appsbykeegan.backendcrudrestapi.entity.models.enums.EmployeeRole;
import com.appsbykeegan.backendcrudrestapi.entity.models.records.DepartmentRequestBody;
import com.appsbykeegan.backendcrudrestapi.entity.models.records.EmployeeRequestBody;
import com.appsbykeegan.backendcrudrestapi.entity.models.records.ResponseTemplate;
import com.appsbykeegan.backendcrudrestapi.entity.tables.DepartmentEntity;
import com.appsbykeegan.backendcrudrestapi.entity.tables.EmployeeEntity;
import com.appsbykeegan.backendcrudrestapi.repository.DepartmentRepository;
import com.appsbykeegan.backendcrudrestapi.repository.EmployeeRepository;
import com.appsbykeegan.backendcrudrestapi.utility.MyUtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final MyUtilityClass myUtilityClass;

    public ResponseTemplate createEmployeeEntry(EmployeeRequestBody employeeRequestBody) {

        DepartmentEntity departmentEntity = new DepartmentEntity(
                employeeRequestBody.departmentRequestBody().departmentName(),
                employeeRequestBody.departmentRequestBody().departmentFloorNumber(),
                employeeRequestBody.departmentRequestBody().departmentDescription(),
                employeeRequestBody.departmentRequestBody().departmentBudget(),
                myUtilityClass.getServerCurrentTime()
        );

        EmployeeEntity employee = new EmployeeEntity(
                employeeRequestBody.employeeFirstName(),
                employeeRequestBody.employeeLastName(),
                employeeRequestBody.employeeGender(),
                myUtilityClass.getServerCurrentTime(),
                employeeRequestBody.employeeRole(),
                employeeRequestBody.emailAddress(),
                departmentEntity
        );

        Set<EmployeeEntity> employeeEntitySet = new HashSet<>();
        employeeEntitySet.add(employee);

        departmentEntity.setEmployees(employeeEntitySet);
        departmentRepository.save(departmentEntity);

        return new ResponseTemplate(HttpStatus.OK.value(),"Object Created",employee);
    }

    public ResponseTemplate retrieveEmployeeObject(String firstName, String lastName, Boolean returnAll, Long id) {

        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        return new ResponseTemplate(HttpStatus.OK.value(),"returned entities",employeeEntities);
    }

    public ResponseTemplate updateEmployeeObject(String firstName, String lastName, EmployeeRole employeeRole,
                                                 String emailAddress, DepartmentRequestBody department, Long id) {

        EmployeeEntity employee = null;

        if (!firstName.isEmpty() && !lastName.isEmpty()) {

            employee = employeeRepository.findByEmployeeFirstNameAndEmployeeLastName(firstName,lastName)
                    .orElseThrow(NoSuchElementException::new);
        }

        if (employee == null) {

            throw new IllegalStateException("employee variable was not assigned correctly");
        }

        if (employeeRole != null) {
            employee.setEmployeeRole(employeeRole);
        }
        if (emailAddress != null) {
            employee.setEmailAddress(emailAddress);
        }
        if (department != null) {

            DepartmentEntity departmentEntity = new DepartmentEntity(
                    department.departmentName(),
                    department.departmentFloorNumber(),
                    department.departmentDescription(),
                    department.departmentBudget(),
                    myUtilityClass.getServerCurrentTime()
            );

            employee.setDepartmentEntity(departmentEntity);
        }

        employeeRepository.save(employee);

        return new ResponseTemplate(HttpStatus.OK.value(),"Object Updated",employee);
    }

    public ResponseTemplate deleteEmployeeObject(String firstName, String lastName, Long id) {

        EmployeeEntity employee = null;

        if (!firstName.isEmpty() && !lastName.isEmpty()) {

            employee = employeeRepository.findByEmployeeFirstNameAndEmployeeLastName(firstName,lastName)
                    .orElseThrow(NoSuchElementException::new);
        }

        employeeRepository.delete(employee);

        return new ResponseTemplate(HttpStatus.OK.value(),"Objected Deleted",null);
    }
}
