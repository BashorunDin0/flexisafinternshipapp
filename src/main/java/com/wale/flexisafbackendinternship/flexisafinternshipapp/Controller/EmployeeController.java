package com.wale.flexisafbackendinternship.flexisafinternshipapp.Controller;

import com.wale.flexisafbackendinternship.flexisafinternshipapp.Service.EmployeeService;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.dto.EmployeeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Create a new employee", description = "Add a new employee to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully",
                    content = @Content(schema = @Schema(implementation = EmployeeDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(
            @Parameter(description = "Employee data to create") @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        addLinks(savedEmployee);  // Add HATEOAS links
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @Operation(summary = "Get employee by ID", description = "Retrieve employee details by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found",
                    content = @Content(schema = @Schema(implementation = EmployeeDto.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @Parameter(description = "ID of the employee to retrieve") @PathVariable("id") Long employeeId) {
        EmployeeDto emp = employeeService.getEmployeeById(employeeId);
        addLinks(emp);  // Add HATEOAS links
        return ResponseEntity.ok(emp);
    }

    @Operation(summary = "Get all employees", description = "Retrieve all employees in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of employees retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        employees.forEach(this::addLinks);  // Add HATEOAS links to each employee
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Update employee by ID", description = "Update an existing employee's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully",
                    content = @Content(schema = @Schema(implementation = EmployeeDto.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @Parameter(description = "ID of the employee to update") @PathVariable("id") Long employeeId,
            @Parameter(description = "Updated employee data") @RequestBody EmployeeDto updatedEmployee) {
        EmployeeDto emp = employeeService.updateEmployee(employeeId, updatedEmployee);
        addLinks(emp);  // Add HATEOAS links
        return ResponseEntity.ok(emp);
    }

    @Operation(summary = "Delete employee by ID", description = "Remove an employee from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @Parameter(description = "ID of the employee to delete") @PathVariable("id") Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok("Employee with id: " + employeeId + " was deleted successfully");
    }

    // Method to add HATEOAS links to an EmployeeDto
    private void addLinks(EmployeeDto employee) {
        Link selfLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(EmployeeController.class).getEmployeeById(employee.getId()))
                .withSelfRel();

        Link allEmployeesLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(EmployeeController.class).getAllEmployees())
                .withRel("all-employees");

        Link updateEmployeeLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(EmployeeController.class).updateEmployee(employee.getId(), employee))
                .withRel("update-employee");

        Link deleteEmployeeLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(EmployeeController.class).deleteEmployee(employee.getId()))
                .withRel("delete-employee");

        employee.add(selfLink, allEmployeesLink, updateEmployeeLink, deleteEmployeeLink);
    }
}
