package com.example.poleservice;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Pole")
public class Pole implements Serializable {

    @Id
    private String codePole;

    private String nom;

    private String adresse;

    private String description;

    private String photoUrl;

    // Store only the IDs of facultes and user to avoid direct references
    @JsonIgnore
    private List<String> faculteIds = new ArrayList<>(); // List of Faculte IDs

    private String userId; // ID of associated User
}
