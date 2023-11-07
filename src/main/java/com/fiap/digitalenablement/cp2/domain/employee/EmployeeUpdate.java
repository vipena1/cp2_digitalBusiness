package com.fiap.digitalenablement.cp2.domain.employee;

import com.fiap.digitalenablement.cp2.domain.department.Department;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EmployeeUpdate(@NotNull
                             Long employee_id,
                             String employee_name,
                             String employee_title,
                             Double employee_salary,
                             String employee_manager,
                             Department department) {
}
