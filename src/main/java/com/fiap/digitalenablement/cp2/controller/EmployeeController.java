package com.fiap.digitalenablement.cp2.controller;

import com.fiap.digitalenablement.cp2.domain.employee.Employee;
import com.fiap.digitalenablement.cp2.domain.employee.EmployeeListing;
import com.fiap.digitalenablement.cp2.domain.employee.EmployeeRegistration;
import com.fiap.digitalenablement.cp2.domain.employee.EmployeeUpdate;
import com.fiap.digitalenablement.cp2.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/employee")
@Tag(name = "Employee",description = "Employee CRUD.")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Employee create", description = "Endpoint for the registration of a new employee.")
    public ResponseEntity registration(@RequestBody @Valid EmployeeRegistration data, UriComponentsBuilder uriBuilder) {
        var employee = new Employee(data);
        repository.save(employee);
        var uri = uriBuilder.path("/employee/{employee_id}").buildAndExpand(employee.getEmployee_id()).toUri();
        return ResponseEntity.created(uri).body(new EmployeeListing(employee));
    }

    @GetMapping
    @Operation(summary = "Employee list", description = "Endpoint for reading multiple employees.")
    public ResponseEntity<Page<EmployeeListing>> listing(@PageableDefault(size = 10, sort = {"employee_name"}) Pageable pagination) {
        var page = repository.findAll(pagination).map(EmployeeListing::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{employee_id}")
    @Operation(summary = "Employee display", description = "Endpoint for reading a single employee.")
    public ResponseEntity display(@PathVariable Long employee_id) {
        var employee = repository.getReferenceById(employee_id);
        return ResponseEntity.ok(new EmployeeListing(employee));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Employee update", description = "Endpoint for updating a employee.")
    public ResponseEntity update(@RequestBody @Valid EmployeeUpdate data) {
        var employee = repository.getReferenceById(data.employee_id());
        employee.updateInformation(data);
        return ResponseEntity.ok(new EmployeeListing(employee));
    }

    @DeleteMapping("/{employee_id}")
    @Transactional
    @Operation(summary = "Employee delete", description = "Endpoint for deleting a employee.")
    public ResponseEntity delete(@PathVariable Long employee_id) {
        repository.deleteById(employee_id);
        return  ResponseEntity.ok().body("Employee " + employee_id + " deleted.");
    }

    @GetMapping("/average_salary/{department_name}")
    @Operation(summary = "Employee average salary.", description = "Endpoint for reading the average employee salary by department.")
    public ResponseEntity averageSalary(@PathVariable String department_name) {
        var averageSalary = repository.findByAverageEmployeeSalary(department_name);
        return ResponseEntity.ok().body("The department average salary is " + averageSalary);
    }
}
