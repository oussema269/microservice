package com.example.microservice.domaine.DTO;

import jakarta.persistence.Id;

import java.util.Date;

public class CourDTO {
    private String idCour;
    private String nomCour;
    private String description;
    private Date date;
    private Niveau niveau;
    private TypeCour typeCour;
    private String photo;
    private double prix;
    private int test;
    private String nomDomaine;


    public CourDTO() {
    }

    public CourDTO(String idCour, String nomCour, String description, Date date, Niveau niveau, TypeCour typeCour, String photo, double prix, int test, String nomDomaine) {
        this.idCour = idCour;
        this.nomCour = nomCour;
        this.description = description;
        this.date = date;
        this.niveau = niveau;
        this.typeCour = typeCour;
        this.photo = photo;
        this.prix = prix;
        this.test = test;
        this.nomDomaine = nomDomaine;
    }

    public CourDTO(String nomCour, String description, double prix) {
        this.nomCour = nomCour;
        this.description = description;
        this.prix = prix;
    }

    public String getIdCour() {
        return idCour;
    }

    public void setIdCour(String idCour) {
        this.idCour = idCour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomCour() {
        return nomCour;
    }

    public void setNomCour(String nomCour) {
        this.nomCour = nomCour;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public TypeCour getTypeCour() {
        return typeCour;
    }

    public void setTypeCour(TypeCour typeCour) {
        this.typeCour = typeCour;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public String getNomDomaine() {
        return nomDomaine;
    }

    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }
}
