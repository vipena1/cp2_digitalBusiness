package com.fiap.digitalenablement.cp2.domain.employee;

import com.fiap.digitalenablement.cp2.domain.department.Department;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EmployeeRegistration(@NotBlank
                                   String employee_name,
                                   @NotBlank
                                   String employee_title,
                                   @NotBlank @Min(0)
                                   Double employee_salary,
                                   @NotBlank
                                   String employee_manager,
                                   @Valid
                                   Department department) {
}
