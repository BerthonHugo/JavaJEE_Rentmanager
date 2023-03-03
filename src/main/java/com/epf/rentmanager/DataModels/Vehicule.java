package com.epf.rentmanager.DataModels;

public class Vehicule {

    //class attributes
    private int id;
    private String constructeur;
    private String modele;
    private int nb_places;
    //constructors

    public Vehicule(int id, String constructeur, String modele, int nb_places) {
        this.id = id;
        this.constructeur = constructeur;
        this.modele = modele;
        this.nb_places = nb_places;
    }

    public Vehicule(int id) {
        this.id = id;
    }

    public Vehicule() {

    }

    public Vehicule(Vehicule vehicule) {

    }

    public Vehicule(int vehiculeId, String constructeur, int nbPlaces) {
        this.id = vehiculeId;
        this.constructeur = constructeur;
        this.nb_places = nbPlaces;
    }

    //methods

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", constructeur='" + constructeur + '\'' +
                ", modele='" + modele + '\'' +
                ", nb_places=" + nb_places +
                '}';
    }

    //Getters and setters

    public int getId() {
        return id;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }
}
