package Amine;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
}
