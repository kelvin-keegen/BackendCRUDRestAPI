package com.appsbykeegan.backendcrudrestapi.repository;

import com.appsbykeegan.backendcrudrestapi.entity.tables.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

    Optional<EmployeeEntity> findByEmployeeId(Long employeeId);

    Optional<EmployeeEntity> findByEmployeeFirstNameAndEmployeeLastName(String firstName, String lastName);


}
