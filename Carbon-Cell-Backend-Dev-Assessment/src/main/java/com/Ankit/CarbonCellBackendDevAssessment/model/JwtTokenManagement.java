package com.Ankit.CarbonCellBackendDevAssessment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JwtTokenManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;
    private String username;
    private boolean tokenExpired;


    public boolean tokenExpired() {
        return tokenExpired;
    }
}
