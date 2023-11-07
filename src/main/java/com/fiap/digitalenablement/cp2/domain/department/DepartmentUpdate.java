package com.fiap.digitalenablement.cp2.domain.department;

import com.fiap.digitalenablement.cp2.domain.employee.Employee;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record DepartmentUpdate(@NotNull
                               Long department_id,
                               String department_name,
                               List<Employee> employees) {
}
