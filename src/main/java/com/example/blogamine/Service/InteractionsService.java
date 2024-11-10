package com.example.blogamine.Service;

import com.example.blogamine.Entite.InteractionsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class InteractionsService {
    private final RestTemplate restTemplate;
    public InteractionsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public InteractionsDTO getInteractionsDTOById(String id) {
        String url = "http://localhost:5000/api/auth/contacts" + id;  // Replace with your actual URL

        ResponseEntity<InteractionsDTO> response = restTemplate.getForEntity(url, InteractionsDTO.class);

        return response.getBody(); // Returns the contact data if successful
    }
}
