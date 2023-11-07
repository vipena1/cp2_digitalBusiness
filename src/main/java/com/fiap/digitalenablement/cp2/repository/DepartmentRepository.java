package com.fiap.digitalenablement.cp2.repository;

import com.fiap.digitalenablement.cp2.domain.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Department findByNameEquals(String department_name);
}