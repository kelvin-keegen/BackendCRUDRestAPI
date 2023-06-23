package com.appsbykeegan.backendcrudrestapi.repository;

import com.appsbykeegan.backendcrudrestapi.entity.tables.EmployeeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeTable,Long> {

    Optional<EmployeeTable> findByEmployeeId(Long employeeId);

    Optional<EmployeeTable> findByEmployeeFirstNameAndEmployeeLastName(String firstName,String lastName);


}
