package com.example.blogamine.Entite;

import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InteractionsDTO {
    private String id;

    private String commentaire;

    private UserDTO user;

    private Blog blog;

    List<InteractionsDTO> replay;

    InteractionsDTO parentInteraction;
}
