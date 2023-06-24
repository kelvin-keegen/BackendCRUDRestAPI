package com.appsbykeegan.backendcrudrestapi.repository;


import com.appsbykeegan.backendcrudrestapi.entity.tables.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {

    Optional<DepartmentEntity> findByDepartmentName(String departmentName);

    Optional<DepartmentEntity> findByDepartmentId(Long departmentId);
}
