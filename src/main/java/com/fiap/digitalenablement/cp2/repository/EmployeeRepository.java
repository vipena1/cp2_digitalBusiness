package com.fiap.digitalenablement.cp2.repository;

import com.fiap.digitalenablement.cp2.domain.employee.Employee;

import org.hibernate.annotations.NamedQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value = "SELECT AVG(employee_salary) FROM employees WHERE department_name = ?1", nativeQuery = true)
    List<Employee> findByAverageEmployeeSalary(String department_name);
}