package com.wale.flexisafbackendinternship.flexisafinternshipapp.Service.impl;

import com.wale.flexisafbackendinternship.flexisafinternshipapp.Entity.Employee;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.Exception.EmployeeNotFoundException;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.Repository.EmployeeRepo;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.dto.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.delete;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee mockEmployee;
    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockEmployee = new Employee(1L, "Wale", "Yemi7", "olawale9@gmail.com", null,
                null, null, null, null, null);
        mockEmployeeDto = new EmployeeDto(1L, "Wale", "Yemi7", "olawale9@gmail.com", null,
                null, null, null, null, null);
    }

    @Test
    void createEmployeeSuccessfully() {
        when(employeeRepo.existsByEmail(mockEmployeeDto.getEmail())).thenReturn(false);
        when(employeeRepo.save(eq(mockEmployee))).thenReturn(mockEmployee);

        EmployeeDto createdEmployee = employeeService.createEmployee(mockEmployeeDto);

        assertNotNull(createdEmployee);
        assertEquals(mockEmployeeDto.getEmail(), createdEmployee.getEmail());
        verify(employeeRepo, times(1)).save(eq(mockEmployee));
    }
    @Test
    void testCreateEmployee_EmailExists_ThrowsException() {
        when(employeeRepo.existsByEmail(mockEmployeeDto.getEmail())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> employeeService.createEmployee(mockEmployeeDto));
        verify(employeeRepo, never()).save(eq(mockEmployee));
    }

    @Test
    void getEmployeeByIdSuccessfully() {
        when(employeeRepo.findById(1L)).thenReturn(Optional.of(mockEmployee));

        EmployeeDto foundEmployee = employeeService.getEmployeeById(1L);

        assertNotNull(foundEmployee);
        assertEquals("Wale", foundEmployee.getFirstName());
        verify(employeeRepo, times(1)).findById(1L);

    }

    @Test
    void testGetEmployeeById_NotFound_ThrowsException() {
        when(employeeRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(1L));
        verify(employeeRepo, times(1)).findById(1L);
    }
    @Test
    void testDeleteEmployeeById_Success() {
        // Mock the repository to find the employee
        when(employeeRepo.findById(mockEmployee.getId())).thenReturn(Optional.of(mockEmployee));

        // Call the delete method
        employeeService.deleteEmployeeById(mockEmployee.getId());

        // Verify that the repository's deleteById method was called
        verify(employeeRepo, times(1)).deleteById(mockEmployee.getId());
    }

    @Test
    void testDeleteEmployeeById_NotFound() {
        // Mock the repository to return an empty Optional
        when(employeeRepo.findById(mockEmployee.getId())).thenReturn(Optional.empty());

        // Verify that an exception is thrown when trying to delete a non-existent employee
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployeeById(mockEmployee.getId()));

        // Verify that the deleteById method was never called
        verify(employeeRepo, never()).deleteById(mockEmployee.getId());
    }

}