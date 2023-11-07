package com.fiap.digitalenablement.cp2.controller;

import com.fiap.digitalenablement.cp2.domain.department.Department;
import com.fiap.digitalenablement.cp2.domain.department.DepartmentListing;
import com.fiap.digitalenablement.cp2.domain.department.DepartmentRegistration;
import com.fiap.digitalenablement.cp2.domain.department.DepartmentUpdate;
import com.fiap.digitalenablement.cp2.repository.DepartmentRepository;
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
@RequestMapping("/department")
@Tag(name = "Department",description = "Department CRUD.")
public class DepartmentController {

    @Autowired
    private DepartmentRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Department create", description = "Endpoint for the creation of a new department.")
    public ResponseEntity registration(@RequestBody @Valid DepartmentRegistration data, UriComponentsBuilder uriBuilder) {
        var department = new Department(data);
        repository.save(department);
        var uri = uriBuilder.path("/department/{id}").buildAndExpand(department.getDepartment_id()).toUri();
        return ResponseEntity.created(uri).body(new DepartmentListing(department));
    }

    @GetMapping
    @Operation(summary = "Department list", description = "Endpoint for reading multiple departments.")
    public ResponseEntity<Page<DepartmentListing>> listing(@PageableDefault(size = 10, sort = {"department_name"}) Pageable pagination) {
        var page = repository.findAll(pagination).map(DepartmentListing::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{department_id}")
    @Operation(summary = "Department display", description = "Endpoint for reading a single department.")
    public ResponseEntity display(@PathVariable Long department_id) {
        var department = repository.getReferenceById(department_id);
        return ResponseEntity.ok(new DepartmentListing(department));
    }

    @GetMapping("/{department_name}")
    @Operation(summary = "Department display by name", description = "Endpoint for reading a single department by name.")
    public ResponseEntity displayByName(@PathVariable String department_name) {
        var department = repository.findByNameEquals(department_name);
        return ResponseEntity.ok(new DepartmentListing(department));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Department update", description = "Endpoint for updating a department.")
    public ResponseEntity update(@RequestBody @Valid DepartmentUpdate data) {
        var department = repository.getReferenceById(data.department_id());
        department.updateInformation(data);
        return ResponseEntity.ok(new DepartmentListing(department));
    }

    @DeleteMapping("/{department_id}")
    @Transactional
    @Operation(summary = "Department delete", description = "Endpoint for deleting a department.")
    public ResponseEntity delete(@PathVariable Long department_id) {
        repository.deleteById(department_id);
        return  ResponseEntity.ok().body("Department " + department_id + " deleted.");
    }
}
