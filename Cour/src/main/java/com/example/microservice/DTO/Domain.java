package com.example.microservice.DTO;

public class Domain {
    private String id;

    public Domain() {

    }

    public Domain(String id, String nom, String description, String photo) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String nom;
    private String description;
    private String photo;
}
