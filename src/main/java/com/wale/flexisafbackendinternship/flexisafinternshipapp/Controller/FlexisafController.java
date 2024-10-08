package com.wale.flexisafbackendinternship.flexisafinternshipapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/welcome")
public class FlexisafController {

    @GetMapping("/intern")
    public String welcomeMessage() {
      return  "Welcome to Flexisaf Advance Backend Internship";
    }
}
