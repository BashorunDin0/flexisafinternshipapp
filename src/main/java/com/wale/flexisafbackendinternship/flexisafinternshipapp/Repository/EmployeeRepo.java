package com.wale.flexisafbackendinternship.flexisafinternshipapp.Repository;

import com.wale.flexisafbackendinternship.flexisafinternshipapp.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
        boolean existsByEmail(String email);
    }

