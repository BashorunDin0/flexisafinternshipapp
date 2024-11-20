package com.wale.flexisafbackendinternship.flexisafinternshipapp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.Service.EmployeeService;
import com.wale.flexisafbackendinternship.flexisafinternshipapp.dto.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mockMvc;
    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        mockEmployeeDto = new EmployeeDto(1L, "Wale", "Yemi7", "ibrahimolawale9.com", null, null, null, "Male", null, null);
    }

    @Test
    void createEmployeeSuccessfully() throws Exception {
        // Mock the service layer
        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(mockEmployeeDto);

        // Perform the request
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockEmployeeDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("ibrahimolawale9.com"));

        // Verify the service interaction
        verify(employeeService, Mockito.times(1)).createEmployee(any(EmployeeDto.class));

    }

    @Test
    void getEmployeeByIdSuccessfully() throws Exception{
        when(employeeService.getEmployeeById(1L)).thenReturn(mockEmployeeDto);

        mockMvc.perform(get("/api/employees/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));

        verify(employeeService, Mockito.times(1)).getEmployeeById(1L);
    }

    @Test
    void deleteEmployeeSuccessfully() throws Exception {
        Long employeeId = 1L;

        // Mock behavior
        doNothing().when(employeeService).deleteEmployeeById(employeeId);

        // Perform DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee with id: 1 was deleted successfully"));

        // Verify service method call
        verify(employeeService, Mockito.times(1)).deleteEmployeeById(employeeId);
    }
}