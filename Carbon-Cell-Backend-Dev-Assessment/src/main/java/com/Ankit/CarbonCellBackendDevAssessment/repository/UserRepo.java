package com.Ankit.CarbonCellBackendDevAssessment.repository;

import com.Ankit.CarbonCellBackendDevAssessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUserName(String username);
}
