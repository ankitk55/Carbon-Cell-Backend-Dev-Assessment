package com.Ankit.CarbonCellBackendDevAssessment.controller;

import com.Ankit.CarbonCellBackendDevAssessment.Exceptionhandling.Exceptions.InvalidCredentialsException;
import com.Ankit.CarbonCellBackendDevAssessment.JwtUtils.JwtService;
import com.Ankit.CarbonCellBackendDevAssessment.model.JwtTokenManagement;
import com.Ankit.CarbonCellBackendDevAssessment.model.LoginRequest;
import com.Ankit.CarbonCellBackendDevAssessment.model.User;
import com.Ankit.CarbonCellBackendDevAssessment.service.JwtTokenManagementService;
import com.Ankit.CarbonCellBackendDevAssessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtTokenManagementService jwtTokenManagementService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> AddUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody LoginRequest authRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                String token =jwtService.generateToken(authRequest.getUserName());

                SetAllOldTokenExpired(authRequest.getUserName());
                JwtTokenManagement jwtTokenManagement =JwtTokenManagement.builder().token(token).username(authRequest.getUserName())
                        .tokenExpired(false).build();
                jwtTokenManagementService.savedata(jwtTokenManagement);
                return "token "+token;
            }
        }catch (Exception e){

        throw new InvalidCredentialsException();}
       return null;
    }

    private List<JwtTokenManagement>AllExistTokenByUserName(String userName){
       return jwtTokenManagementService.getAlTokenByUserName(userName);
    }
    private void SetAllOldTokenExpired(String userName){
        List<JwtTokenManagement>list =AllExistTokenByUserName(userName);
        if(!list.isEmpty()){
           for(JwtTokenManagement token:list){
               token.setTokenExpired(true);
               jwtTokenManagementService.savedata(token);
           }

        }
    }
}
