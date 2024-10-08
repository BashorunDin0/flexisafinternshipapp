package com.wale.flexisafbackendinternship.flexisafinternshipapp.dto;


import lombok.*;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private byte[] profilePicture;
    private String address;
    private String gender;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
