package com.epf.rentmanager.DataModels;

import java.time.LocalDate;

public class Client {
    //class Attributes
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private LocalDate naissance;


    //Constructors
    public Client(int id, String nom, String prenom, String mail, LocalDate naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.naissance = naissance;
    }
    public Client(String nom, String prenom, String mail, LocalDate naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.naissance = naissance;
    }

    public Client(int id) {
        this.id = id;
    }
    public Client(Client client){

    }
    public Client() {
    }

    //methods

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", naissance=" + naissance +
                '}';
    }

    //Getters and setters


    public int getId() {
        return id;
    }

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }


}
