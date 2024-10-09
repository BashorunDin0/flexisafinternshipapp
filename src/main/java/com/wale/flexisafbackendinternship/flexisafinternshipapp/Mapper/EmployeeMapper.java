package com.wale.flexisafbackendinternship.flexisafinternshipapp.Mapper;

import com.wale.flexisafbackendinternship.flexisafinternshipapp.Entity.Employee;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.dto.EmployeeDto;

public class EmployeeMapper {

    public static EmployeeDto mapToEmpDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getUsername(),
                employee.getProfilePicture(),
                employee.getAddress(),
                employee.getGender(),
                employee.getCreatedDate(),
                employee.getUpdatedDate()

        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getUsername(),
                employeeDto.getProfilePicture(),
                employeeDto.getAddress(),
                employeeDto.getGender(),
                employeeDto.getCreatedDate(),
                employeeDto.getUpdatedDate()
        );
    }
}
