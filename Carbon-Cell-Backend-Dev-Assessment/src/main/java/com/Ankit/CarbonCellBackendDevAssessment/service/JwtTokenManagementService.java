package com.Ankit.CarbonCellBackendDevAssessment.service;

import com.Ankit.CarbonCellBackendDevAssessment.model.JwtTokenManagement;
import com.Ankit.CarbonCellBackendDevAssessment.repository.JwtTokenManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtTokenManagementService {
    @Autowired
    private JwtTokenManagementRepo jwtTokenManagementRepo;
    public void savedata(JwtTokenManagement jwtTokenManagement) {
        jwtTokenManagementRepo.save(jwtTokenManagement);
    }

    public List<JwtTokenManagement> getAlTokenByUserName(String userName) {
        return jwtTokenManagementRepo.findByUsername(userName);
    }
}
