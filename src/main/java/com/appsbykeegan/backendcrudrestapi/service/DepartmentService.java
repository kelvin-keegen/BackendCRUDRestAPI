package com.appsbykeegan.backendcrudrestapi.service;

import com.appsbykeegan.backendcrudrestapi.entity.models.records.DepartmentRequestBody;
import com.appsbykeegan.backendcrudrestapi.entity.models.records.ResponseTemplate;
import com.appsbykeegan.backendcrudrestapi.entity.tables.DepartmentEntity;
import com.appsbykeegan.backendcrudrestapi.repository.DepartmentRepository;
import com.appsbykeegan.backendcrudrestapi.utility.MyUtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final MyUtilityClass myUtilityClass;


    public ResponseTemplate createDepartmentEntry(DepartmentRequestBody departmentRequestBody) {

        DepartmentEntity department = new DepartmentEntity(
                departmentRequestBody.departmentName(),
                departmentRequestBody.departmentFloorNumber(),
                departmentRequestBody.departmentDescription(),
                departmentRequestBody.departmentBudget(),
                myUtilityClass.getServerCurrentTime()
        );

        departmentRepository.save(department);

        return new ResponseTemplate(HttpStatus.OK.hashCode(),"Object created",department);
    }

    public ResponseTemplate updateDepartmentObject(String departmentName, int departmentFloorNumber, String departmentDescription, BigDecimal departmentBudget, Long id) {

        DepartmentEntity department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(NoSuchElementException::new);

        department.setDepartmentName(departmentName);
        department.setDepartmentFloorNumber(departmentFloorNumber);
        department.setDepartmentDescription(departmentDescription);
        department.setDepartmentBudget(departmentBudget);

        departmentRepository.save(department);

        return new ResponseTemplate(HttpStatus.OK.hashCode(),"Updated Object",department);
    }

    public ResponseTemplate retrieveDepartmentObject(String name, Long id, Boolean returnAll) {

        List<DepartmentEntity> departmentObjects = departmentRepository.findAll();

        return new ResponseTemplate(HttpStatus.OK.hashCode(),"returned list",departmentObjects);
    }


    public ResponseTemplate deleteDepartmentObject(String name, Long id) {

        DepartmentEntity department = departmentRepository.findByDepartmentName(name)
                .orElseThrow(NoSuchElementException::new);

        departmentRepository.delete(department);

        return new ResponseTemplate(HttpStatus.OK.hashCode(),"Object Deleted",null);
    }


}
