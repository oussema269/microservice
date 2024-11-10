package com.example.microservice.domaine;

import com.example.microservice.domaine.DTO.CourDTO;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("Domaine")

public class domaine {
    @Id
    private String id;
    private String nom;
    private String description;
    private String photo;
    @ElementCollection
    private List<String> list=new ArrayList<String>();


    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
//@DBRef
    // Faculte faculte;
}
