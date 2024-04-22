package com.appsbykeegan.backendcrudrestapi.controller;

import com.appsbykeegan.backendcrudrestapi.entity.models.records.*;
import com.appsbykeegan.backendcrudrestapi.service.DepartmentService;
import com.appsbykeegan.backendcrudrestapi.service.EmployeeService;
import com.appsbykeegan.backendcrudrestapi.utility.MyUtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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
            @RequestParam(required = false) Boolean returnAll) {

        return departmentService.retrieveDepartmentObject(name,returnAll);
    }

    @PutMapping(path = "/department/update")
    public ResponseTemplate updateDepartmentDetails(@RequestBody DepartmentRequestBody departmentRequestBody) {

        return departmentService.updateDepartmentObject(departmentRequestBody);
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
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean returnAll) {

        return employeeService.retrieveEmployeeObject(email,returnAll);
    }

    @PutMapping(path = "/employee/update")
    public ResponseTemplate updateEmployee(
            @RequestBody EmployeeRequestBody employeeRequestBody) {

        return employeeService.updateEmployeeObject(employeeRequestBody);
    }

    @DeleteMapping(path = "/employee/delete")
    public ResponseTemplate deleteEmployee(
            @RequestParam String email) {

        return employeeService.deleteEmployeeObject(email);
    }

}
