package com.fiap.digitalenablement.cp2.domain.employee;

import com.fiap.digitalenablement.cp2.domain.department.Department;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;

    private String employee_name;
    private String employee_title;
    private Double employee_salary;
    private String employee_manager;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_name")
    private Department department;

    public Employee(EmployeeRegistration data) {
        this.employee_name = data.employee_name();
        this.employee_title = data.employee_title();
        this.employee_salary = data.employee_salary();
        this.employee_manager = data.employee_manager();
        this.department = data.department();
    }

    public void updateInformation(EmployeeUpdate data) {
        if (data.employee_name() != null) {
            this.employee_name = data.employee_name();
        }
        if (data.employee_title() != null) {
            this.employee_title = data.employee_title();
        }
        if (data.employee_salary() != null) {
            this.employee_salary = data.employee_salary();
        }
        if (data.employee_manager() != null) {
            this.employee_manager = data.employee_manager();
        }
    }

}
