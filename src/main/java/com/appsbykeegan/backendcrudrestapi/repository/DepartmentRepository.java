package com.appsbykeegan.backendcrudrestapi.repository;


import com.appsbykeegan.backendcrudrestapi.entity.tables.DepartmentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentTable,Long> {

    Optional<DepartmentTable> findByDepartmentName(String departmentName);

    Optional<DepartmentTable> findByDepartmentId(Long departmentId);
}
