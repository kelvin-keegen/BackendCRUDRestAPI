package com.appsbykeegan.backendcrudrestapi.controller;

import com.appsbykeegan.backendcrudrestapi.entity.models.enums.EmployeeRole;
import com.appsbykeegan.backendcrudrestapi.entity.models.records.*;
import com.appsbykeegan.backendcrudrestapi.entity.tables.DepartmentEntity;
import com.appsbykeegan.backendcrudrestapi.service.DepartmentService;
import com.appsbykeegan.backendcrudrestapi.service.EmployeeService;
import com.appsbykeegan.backendcrudrestapi.utility.MyUtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RestAPIController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final MyUtilityClass myUtilityClass;

    @GetMapping(path = "/")
    public ResponseTemplate appStatusChecker() {

        return new ResponseTemplate(HttpStatus.OK.value(),"health is Ok", "Server Time: "+myUtilityClass.getServerCurrentTime());
    }

    // ### Department endpoints ###

    @PostMapping(path = "/department/create")
    public ResponseTemplate createDepartment(@RequestBody DepartmentRequestBody departmentRequestBody) {

        return departmentService.createDepartmentEntry(departmentRequestBody);
    }

    @GetMapping(path = "/department/retrieve")
    public ResponseTemplate retrieveDepartment(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Boolean returnAll) {

        return departmentService.retrieveDepartmentObject(name,id,returnAll);
    }

    @PutMapping(path = "/department/update")
    public ResponseTemplate updateDepartmentDetails(
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) int departmentFloorNumber,
            @RequestParam(required = false) String departmentDescription,
            @RequestParam(required = false) BigDecimal departmentBudget,
            @RequestParam(required = false) Long id) {

        return departmentService.updateDepartmentObject(departmentName, departmentFloorNumber, departmentDescription, departmentBudget, id);
    }

    @DeleteMapping(path = "/department/delete")
    public ResponseTemplate deleteDepartment(
            @RequestParam String name,
            @RequestParam(required = false) Long id) {

        return departmentService.deleteDepartmentObject(name,id);
    }

    // ### Employee endpoints ###

    @PostMapping(path = "/employee/create")
    public ResponseTemplate createEmployee(@RequestBody EmployeeRequestBody employeeRequestBody) {

        return employeeService.createEmployeeEntry(employeeRequestBody);
    }

    @GetMapping(path = "/employee/retrieve")
    public ResponseTemplate retrieveEmployee(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Boolean returnAll) {

        return employeeService.retrieveEmployeeObject(firstName,lastName,returnAll,id);
    }

    @PutMapping(path = "/employee/update")
    public ResponseTemplate updateEmployee(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) EmployeeRole employeeRole,
            @RequestParam(required = false) String emailAddress,
            @RequestParam(required = false) DepartmentRequestBody department,
            @RequestParam(required = false) Long id) {

        return employeeService.updateEmployeeObject(firstName,lastName,employeeRole,emailAddress,department,id);
    }

    @DeleteMapping(path = "/employee/delete")
    public ResponseTemplate deleteEmployee(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Long id) {

        return employeeService.deleteEmployeeObject(firstName,lastName,id);
    }

    // ### Generic endpoints ###

    // empty json object of Department
    @GetMapping(path = "/department/retrieve_mock")
    public ResponseTemplate retrieveDepartment_mock() {

        return new ResponseTemplate(HttpStatus.OK.value(),"", new DepartmentGenericTemplate(null,0,null,null,null));
    }

    // empty json object of Employee
    @GetMapping(path = "/employee/retrieve_mock")
    public ResponseTemplate retrieveEmployee_mock() {

        return new ResponseTemplate(HttpStatus.OK.value(),"", new EmployeeGenericTemplate(null,null,null,null,null,null,null));
    }
}
