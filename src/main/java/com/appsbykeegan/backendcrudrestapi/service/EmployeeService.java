package com.appsbykeegan.backendcrudrestapi.service;

import com.appsbykeegan.backendcrudrestapi.entity.models.records.EmployeeRequestBody;
import com.appsbykeegan.backendcrudrestapi.entity.models.records.ResponseTemplate;
import com.appsbykeegan.backendcrudrestapi.entity.tables.DepartmentEntity;
import com.appsbykeegan.backendcrudrestapi.entity.tables.EmployeeEntity;
import com.appsbykeegan.backendcrudrestapi.repository.DepartmentRepository;
import com.appsbykeegan.backendcrudrestapi.repository.EmployeeRepository;
import com.appsbykeegan.backendcrudrestapi.utility.MyUtilityClass;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final MyUtilityClass myUtilityClass;

    public ResponseTemplate createEmployeeEntry(EmployeeRequestBody employeeRequestBody) {

        Optional<DepartmentEntity> optionalDepartment;

        String emailOfEmployee = employeeRequestBody.emailAddress();
        String deptName = employeeRequestBody.departmentName();

        optionalDepartment = departmentRepository.findByDepartmentName(deptName);

        if (optionalDepartment.isEmpty()){

            throw new EntityExistsException("Department of name: "+deptName+" is not recognised");

        }

        EmployeeEntity employee = new EmployeeEntity(
                employeeRequestBody.employeeFirstName(),
                employeeRequestBody.employeeLastName(),
                employeeRequestBody.employeeGender(),
                myUtilityClass.getServerCurrentTime(),
                employeeRequestBody.employeeRole(),
                employeeRequestBody.emailAddress(),
                optionalDepartment.get()
        );

        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findByEmailAddress(emailOfEmployee);

        if (optionalEmployeeEntity.isPresent()){

            throw new EntityExistsException("Employee of email: "+emailOfEmployee+" already exists in the database");
        }

        Set<EmployeeEntity> employeeEntitySet = new HashSet<>();
        employeeEntitySet.add(employee);

        optionalDepartment.get().setEmployees(employeeEntitySet);
        departmentRepository.save(optionalDepartment.get());

        return new ResponseTemplate(HttpStatus.OK.value(),"Object Created",employee);
    }

    public ResponseTemplate retrieveEmployeeObject(String email, Boolean returnAll) {

        if (email != null) {
            EmployeeEntity employee = employeeRepository.findByEmailAddress(email)
                    .orElseThrow(NoSuchElementException::new);

            return new ResponseTemplate(HttpStatus.OK.value(),"returned object",employee);
        }
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        return new ResponseTemplate(HttpStatus.OK.value(),"returned entities",employeeEntities);
    }

    public ResponseTemplate updateEmployeeObject(EmployeeRequestBody employeeRequestBody) {

        EmployeeEntity employee = null;
        String email = employeeRequestBody.emailAddress();

        if (!email.isEmpty()) {

            employee = employeeRepository.findByEmailAddress(email)
                    .orElseThrow(NoSuchElementException::new);
        }

        if (employee == null) {

            throw new IllegalStateException("employee variable was not assigned correctly");
        }

        if (employeeRequestBody.employeeFirstName() != null) {
            employee.setEmployeeFirstName(employeeRequestBody.employeeFirstName());
        }
        if (employeeRequestBody.employeeLastName() != null) {
            employee.setEmployeeLastName(employeeRequestBody.employeeLastName());
        }
        if (employeeRequestBody.employeeRole() != null) {
            employee.setEmployeeRole(employeeRequestBody.employeeRole());
        }
        if (employeeRequestBody.employeeGender() != null) {
            employee.setEmployeeGender(employeeRequestBody.employeeGender());
        }

        employeeRepository.save(employee);

        return new ResponseTemplate(HttpStatus.OK.value(),"Object Updated",employee);
    }

    public ResponseTemplate deleteEmployeeObject(String email) {

        EmployeeEntity employee = null;

        if (!email.isEmpty()) {

            employee = employeeRepository.findByEmailAddress(email)
                    .orElseThrow(NoSuchElementException::new);
        }

        if (employee == null) {

            throw new NullPointerException("employee object is empty and not assigned properly");
        }

        employeeRepository.delete(employee);

        return new ResponseTemplate(HttpStatus.OK.value(),"Objected Deleted",null);
    }
}
