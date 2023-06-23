package com.appsbykeegan.backendcrudrestapi.controller;

import com.appsbykeegan.backendcrudrestapi.entity.models.records.DepartmentGenericTemplate;
import com.appsbykeegan.backendcrudrestapi.entity.models.records.EmployeeGenericTemplate;
import com.appsbykeegan.backendcrudrestapi.entity.models.records.ResponseTemplate;
import com.appsbykeegan.backendcrudrestapi.service.DepartmentService;
import com.appsbykeegan.backendcrudrestapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RestAPIController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @GetMapping(path = "/")
    public ResponseTemplate appStatusChecker() {

        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String stringOfTime = dateTimeFormatter.format(timeNow);

        return new ResponseTemplate(HttpStatus.OK,"health is Ok", "Server Time: "+stringOfTime);
    }

    // ### Department endpoints ###

    @PostMapping(path = "/department/create")
    public ResponseTemplate createDepartment() {

        return null;
    }

    @GetMapping(path = "/department/retrieve")
    public ResponseTemplate retrieveDepartment() {

        return null;
    }

    @PutMapping(path = "/department/update")
    public ResponseTemplate updateDepartmentDetails() {

        return null;
    }

    @DeleteMapping(path = "/department/delete")
    public ResponseTemplate deleteDepartment() {

        return null;
    }

    // ### Employee endpoints ###

    @PostMapping(path = "/employee/create")
    public ResponseTemplate createEmployee() {

        return null;
    }

    @GetMapping(path = "/employee/retrieve")
    public ResponseTemplate retrieveEmployee() {

        return null;
    }

    @PutMapping(path = "/employee/update")
    public ResponseTemplate updateEmployee() {

        return null;
    }

    @DeleteMapping(path = "/employee/delete")
    public ResponseTemplate deleteEmployee() {

        return null;
    }

    // ### Generic endpoints ###

    // empty json object of Department
    @GetMapping(path = "/department/retrieve_mock")
    public ResponseTemplate retrieveDepartment_mock() {

        return new ResponseTemplate(HttpStatus.OK,"", new DepartmentGenericTemplate(null,0,null,null,null));
    }

    // empty json object of Employee
    @GetMapping(path = "/employee/retrieve_mock")
    public ResponseTemplate retrieveEmployee_mock() {

        return new ResponseTemplate(HttpStatus.OK,"", new EmployeeGenericTemplate(null,null,null,null,null,null,null));
    }
}
