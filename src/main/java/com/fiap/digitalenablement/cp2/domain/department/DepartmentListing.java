package com.fiap.digitalenablement.cp2.domain.department;

import com.fiap.digitalenablement.cp2.domain.employee.Employee;
import java.util.List;

public record DepartmentListing(Long department_id,
                                String department_name,
                                List<Employee> employees) {

    public DepartmentListing(Department department) {
        this(department.getDepartment_id(), department.getDepartment_name(), department.getEmployees());
    }
}
