package com.Ankit.CarbonCellBackendDevAssessment.repository;

import com.Ankit.CarbonCellBackendDevAssessment.model.JwtTokenManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JwtTokenManagementRepo extends JpaRepository<JwtTokenManagement,Long> {
    List<JwtTokenManagement> findByUsername(String userName);

    JwtTokenManagement findByToken(String token);
}
