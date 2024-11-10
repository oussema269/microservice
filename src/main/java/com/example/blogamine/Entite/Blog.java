package com.example.blogamine.Entite;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Blog {

    @Id
    private String blogCode;


    private String titreBlog;

    private LocalDate dateBlog;


    private String domaine;


    private String contenu;

    private Boolean status;

    private String photo;

    private String iduser;
    @ElementCollection
    private List<String> idinteractions = new ArrayList<>();}
