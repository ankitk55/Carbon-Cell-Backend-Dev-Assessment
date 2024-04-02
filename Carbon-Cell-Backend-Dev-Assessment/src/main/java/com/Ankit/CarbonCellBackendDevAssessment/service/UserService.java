package com.Ankit.CarbonCellBackendDevAssessment.service;

import com.Ankit.CarbonCellBackendDevAssessment.Exceptionhandling.Exceptions.NullFieldException;
import com.Ankit.CarbonCellBackendDevAssessment.Exceptionhandling.Exceptions.UserExistsException;
import com.Ankit.CarbonCellBackendDevAssessment.model.User;
import com.Ankit.CarbonCellBackendDevAssessment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    public ResponseEntity<String> addUser(User user) {
        String userName =user.getUserName();
        String role =user.getRole();
        if(userName==null){
            throw new NullFieldException("User Name can't be null ");
        }
        if(role ==null){
            throw new NullFieldException("User Role  can't be null ");
        }
        User user1 =userRepo.findByUserName(userName);
        if(user1 !=null){
            throw new UserExistsException("User Already exists with user Name : "+userName);
        }
        System.out.print("pass---->>>>"+user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role.toUpperCase());

        userRepo.save(user);
        return new ResponseEntity<>("User registered Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user =userRepo.findAll();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
