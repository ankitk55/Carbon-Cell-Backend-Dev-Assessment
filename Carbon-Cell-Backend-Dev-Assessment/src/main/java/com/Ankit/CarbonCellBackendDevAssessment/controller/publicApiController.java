package com.Ankit.CarbonCellBackendDevAssessment.controller;

import com.Ankit.CarbonCellBackendDevAssessment.publicApiResponse.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class publicApiController {
    private static final String API_URL = "https://api.publicapis.org/entries";

    @GetMapping("/filterByCategory")
    public ResponseEntity<ApiResponse>  filterByCategory(@RequestParam String category) {
        try {
            // Retrieve data from the API
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);
            String responseBody = responseEntity.getBody();

            JSONObject jsonAPIResponse = new JSONObject(responseBody);

            JSONArray entriesArray = jsonAPIResponse.getJSONArray("entries");

            // Store it as a JSON array
            JSONArray jsonArray = entriesArray;

            List<HashMap<String ,String>>ans =new ArrayList<>();

            // Iterate over the entries array
            for (int i = 0; i < entriesArray.length(); i++) {
                HashMap<String, String> hashMap = new HashMap<>();
                try {
                    // Get the current entry object
                    JSONObject entry = jsonArray.getJSONObject(i);
                    // Check if the category matches the provided category
                    if (entry.getString("Category").equalsIgnoreCase(category)) {
//
                        for (String key : entry.keySet()) {
                            Object value = entry.get(key);

                            // Convert the value to string
                            String valueStr;
                            if (value instanceof Boolean) {
                                // Convert boolean to string representation
                                valueStr = String.valueOf(value);
                            } else {
                                // Convert other types to string
                                valueStr = value.toString();
                            }

                            // Store the key-value pair in the HashMap
                            hashMap.put(key, valueStr);
                        }
                        ans.add(hashMap);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return  ResponseEntity.status(500).body(ApiResponse.builder().message("Error occurred while Iterating the JSONArray.").build());
                }
            }

            if(ans.size()==0){
                return ResponseEntity.status(404).body(ApiResponse.builder().entriesCount(0).message("No entry found for category : "+category).build());
            }

            return ResponseEntity.ok( ApiResponse.builder().entries(ans).entriesCount(ans.size()).message("Data Fetch Successfully..").build()) ;

            //  return ResponseEntity.ok(ans);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(ApiResponse.builder().message("Error occurred while processing the request.").build());
        }

    }

    @GetMapping("filterByCount/{ccount}")
    public ResponseEntity<ApiResponse>  filterByCount(@PathVariable String ccount) {

        if(!StringUtils.isNumeric(ccount)){
            return ResponseEntity.status(400).body(ApiResponse.builder().message("Message : provide valid input...").build());
        }
       int count = Integer.parseInt(ccount);
        if(count<0){
            return ResponseEntity.status(400).body(ApiResponse.builder().message("Message : provide valid count...").build());
        }
        try {
            // Retrieve data from the API
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);
            String responseBody = responseEntity.getBody();

            JSONObject jsonAPIResponse = new JSONObject(responseBody);

            JSONArray entriesArray = jsonAPIResponse.getJSONArray("entries");

            // Store it as a JSON array
            JSONArray jsonArray = entriesArray;

            List<HashMap<String ,String>>ans =new ArrayList<>();

            // Iterate over the entries array
            int coun =0;
            for (int i = 0; i < entriesArray.length(); i++) {
                HashMap<String, String> hashMap = new HashMap<>();
                if(coun ==count){
                    break;
                }
                try {
                    // Get the current entry object
                    JSONObject entry = jsonArray.getJSONObject(i);

                    for (String key : entry.keySet()) {
                        Object value = entry.get(key);

                        // Convert the value to string
                        String valueStr;
                        if (value instanceof Boolean) {
                            // Convert boolean to string representation
                            valueStr = String.valueOf(value);
                        } else {
                            // Convert other types to string
                            valueStr = value.toString();
                        }

                        // Store the key-value pair in the HashMap
                        hashMap.put(key, valueStr);
                    }
                    ans.add(hashMap);
                    coun++;



                } catch (JSONException e) {
                    e.printStackTrace();
                    return  ResponseEntity.status(500).body(ApiResponse.builder().message("Error occurred while Iterating the JSONArray.").build());
                }
            }

//            if(ans.size()==0){
//                return ResponseEntity.status(404).body(ApiResponse.builder().entriesCount(0).message("No entry found for category : "+category).build());
//            }
            if(jsonArray.length()<count){
                return ResponseEntity.ok( ApiResponse.builder().message("Your Api has only - "+jsonArray.length()+" results").entries(ans).entriesCount(ans.size()).build()) ;
            }

            return ResponseEntity.ok( ApiResponse.builder().message("Data Fetch Successfully..").entries(ans).entriesCount(ans.size()).build()) ;

            //  return ResponseEntity.ok(ans);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(ApiResponse.builder().message("Error occurred while processing the request.").build());
        }

    }
}
