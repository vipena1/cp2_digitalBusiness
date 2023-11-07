package com.fiap.digitalenablement.cp2.domain.department;

import com.fiap.digitalenablement.cp2.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Department")
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_id;

    private String department_name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Department(DepartmentRegistration data) {
        this.department_name = data.department_name();
        this.employees = data.employees();
    }

    public void updateInformation(DepartmentUpdate data) {
        if (data.department_name() != null) {
            this.department_name = data.department_name();
        }
        if (data.employees() != null) {
            this.employees = data.employees();
        }
    }
}
