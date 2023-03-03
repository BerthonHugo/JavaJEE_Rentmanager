package com.epf.rentmanager.DataModels;

import java.time.LocalDate;


public class Reservation {

    //Class Attributes
     private int id;
     private int client_id;
     private int vehicule_id;
     private LocalDate debut;
     private LocalDate fin;

     //Constructors

    public Reservation(int id, int client_id, int vehicule_id, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.client_id = client_id;
        this.vehicule_id = vehicule_id;
        this.debut = debut;
        this.fin = fin;
    }

    public Reservation(int id) {
        this.id = id;
    }

    public Reservation() {
    }

    public Reservation(Reservation reservation) {
    }

    public Reservation(int id, int clientId, LocalDate debut, LocalDate fin) {
    }

    //methods

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", vehicule_id=" + vehicule_id +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }

    //Getters and setters

    public int getId() {
        return id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getvehicule_id() {
        return vehicule_id;
    }

    public void setvehicule_id(int vehicule_id) {
        this.vehicule_id = vehicule_id;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }
}
