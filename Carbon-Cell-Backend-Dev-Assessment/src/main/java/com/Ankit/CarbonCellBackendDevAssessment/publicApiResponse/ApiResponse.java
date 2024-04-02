package com.Ankit.CarbonCellBackendDevAssessment.publicApiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse {
    private Integer entriesCount;
    private String message;
    private List<HashMap<String ,String>> entries;

}
