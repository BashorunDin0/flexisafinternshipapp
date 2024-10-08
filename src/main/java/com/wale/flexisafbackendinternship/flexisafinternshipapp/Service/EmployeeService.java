package com.wale.flexisafbackendinternship.flexisafinternshipapp.Service;

import com.wale.flexisafbackendinternship.flexisafinternshipapp.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees ();

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeD);

    void deleteEmployeeById(Long employeeId);
}
