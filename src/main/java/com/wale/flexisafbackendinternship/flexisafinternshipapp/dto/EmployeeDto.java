package com.wale.flexisafbackendinternship.flexisafinternshipapp.dto;


import com.wale.flexisafbackendinternship.flexisafinternshipapp.Controller.EmployeeController;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto extends RepresentationModel<EmployeeDto> {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private byte[] profilePicture;
    private String address;
    private String gender;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
