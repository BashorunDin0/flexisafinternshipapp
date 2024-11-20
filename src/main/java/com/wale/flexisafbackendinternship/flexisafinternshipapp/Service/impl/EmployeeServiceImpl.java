package com.wale.flexisafbackendinternship.flexisafinternshipapp.Service.impl;

import com.wale.flexisafbackendinternship.flexisafinternshipapp.Entity.Employee;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.Exception.EmailAlreadyExistsException;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.Exception.EmployeeNotFoundException;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.Mapper.EmployeeMapper;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.Repository.EmployeeRepo;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.Service.EmployeeService;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        if (employeeRepo.existsByEmail(employeeDto.getEmail())) {
            try {
                throw new EmailAlreadyExistsException("An employee with this email already exists: " + employeeDto.getEmail());
            } catch (EmailAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
        }

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepo.save(employee);

        return EmployeeMapper.mapToEmpDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee with the given id:" + employeeId + " does not exist."));
        return EmployeeMapper.mapToEmpDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmpDto(employee))
                .toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(
                () -> new EmployeeNotFoundException("Employee with the given id:" + employeeId + " does not exist."));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeOby = employeeRepo.save(employee);
        return EmployeeMapper.mapToEmpDto(updatedEmployeeOby);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee with the given id:" + employeeId + " does not exist."));
        employeeRepo.deleteById(employeeId);
    }
}
