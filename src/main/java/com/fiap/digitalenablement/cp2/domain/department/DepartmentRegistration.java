package com.fiap.digitalenablement.cp2.domain.department;

import com.fiap.digitalenablement.cp2.domain.employee.Employee;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record DepartmentRegistration(@NotBlank
                                     String department_name,
                                     @Valid
                                     List<Employee> employees) {
}
