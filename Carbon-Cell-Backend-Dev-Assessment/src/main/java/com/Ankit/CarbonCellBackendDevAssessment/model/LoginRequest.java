package com.Ankit.CarbonCellBackendDevAssessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    private String userName;
    private String password;
}
