package com.Ankit.CarbonCellBackendDevAssessment.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true,nullable = false)
    private String userName;
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;
    private String email;
    @Column(nullable = false)
    private String role;
}
