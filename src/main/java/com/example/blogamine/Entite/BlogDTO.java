package com.example.blogamine.Entite;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlogDTO {


    private String blogCode;

    private String titreBlog;

    private LocalDate dateBlog;

    private String domaine;

    private String contenu;
    private Boolean status ;
    private String  photo;


    UserDTO user;

    List<InteractionsDTO> interactions;
}
