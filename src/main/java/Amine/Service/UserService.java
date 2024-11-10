package Amine.Service;

import Amine.Entite.UserDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserService {
    private final RestTemplate restTemplate;
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UserDTO getUserDTOById(String id) {
        String url = "http://localhoast:82820/UserManagement_Ms/api/user/ROLE_FORMATEUR" ;  // Replace with your actual URL

        // Perform a GET request
        ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);

        return response.getBody(); // Returns the contact data if successful
    }
}

//////




