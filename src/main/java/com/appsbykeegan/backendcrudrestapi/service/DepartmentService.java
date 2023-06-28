package com.appsbykeegan.backendcrudrestapi.service;

import com.appsbykeegan.backendcrudrestapi.entity.models.records.DepartmentRequestBody;
import com.appsbykeegan.backendcrudrestapi.entity.models.records.ResponseTemplate;
import com.appsbykeegan.backendcrudrestapi.entity.tables.DepartmentEntity;
import com.appsbykeegan.backendcrudrestapi.entity.tables.EmployeeEntity;
import com.appsbykeegan.backendcrudrestapi.repository.DepartmentRepository;
import com.appsbykeegan.backendcrudrestapi.utility.MyUtilityClass;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

        String deptName = departmentRequestBody.departmentName();

        Optional<DepartmentEntity> optionalDepartment = departmentRepository.findByDepartmentName(deptName);

        if (optionalDepartment.isPresent()){

            throw new EntityExistsException("Department of name: "+deptName+" already exists in the database");

        }

        departmentRepository.save(department);

        return new ResponseTemplate(HttpStatus.OK.value(),"Object created",department);
    }

    public ResponseTemplate retrieveDepartmentObject(String name,Boolean returnAll) {

        if (name != null) {

            DepartmentEntity department = departmentRepository.findByDepartmentName(name)
                    .orElseThrow(NoSuchElementException::new);

            return new ResponseTemplate(HttpStatus.OK.value(),"returned Object",department);
        }

        List<DepartmentEntity> departmentObjects = departmentRepository.findAll();

        return new ResponseTemplate(HttpStatus.OK.value(),"returned list",departmentObjects);
    }

    public ResponseTemplate updateDepartmentObject(DepartmentRequestBody departmentRequestBody) {

        DepartmentEntity department = departmentRepository.findByDepartmentName(departmentRequestBody.departmentName())
                .orElseThrow(NoSuchElementException::new);

        if (departmentRequestBody.departmentName() != null) {

            department.setDepartmentName(departmentRequestBody.departmentName());
        }
        if (departmentRequestBody.departmentFloorNumber() != 0) {

            department.setDepartmentFloorNumber(departmentRequestBody.departmentFloorNumber());
        }
        if (departmentRequestBody.departmentDescription() != null) {

            department.setDepartmentDescription(departmentRequestBody.departmentDescription());
        }
        if (departmentRequestBody.departmentBudget() != null) {

            department.setDepartmentBudget(departmentRequestBody.departmentBudget());
        }

        departmentRepository.save(department);

        return new ResponseTemplate(HttpStatus.OK.value(),"Updated Object",department);
    }

    public ResponseTemplate deleteDepartmentObject(String name, Long id) {

        DepartmentEntity department = departmentRepository.findByDepartmentName(name)
                .orElseThrow(NoSuchElementException::new);

        departmentRepository.delete(department);

        return new ResponseTemplate(HttpStatus.OK.value(),"Object Deleted",null);
    }

}
