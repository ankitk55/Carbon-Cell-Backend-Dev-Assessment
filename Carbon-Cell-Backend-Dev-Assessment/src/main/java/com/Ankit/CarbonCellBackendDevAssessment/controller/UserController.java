package com.Ankit.CarbonCellBackendDevAssessment.controller;

import com.Ankit.CarbonCellBackendDevAssessment.model.User;
import com.Ankit.CarbonCellBackendDevAssessment.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
  private UserService userService;


    @GetMapping("/user/users")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<User>>getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/public/home")
    public String homePage(){
        return "Hii This is non Secure Endpoint";
    }


    @GetMapping("/admin/dashboard")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminDashBoard(){
        return " This is Admin DashBoard only Admin can Access This";
    }

    @GetMapping("/private/secured")
    @SecurityRequirement(name = "Bearer Authentication")
    public String hasAnygtrwwwRolehm(){

        return "This Endpoint accessible Any Authenticated User..";
    }


}
