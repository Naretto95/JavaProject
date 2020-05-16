package com.company;

public class Objet {
    private String nom;
    private boolean ramasse;

    public Objet(String nom, boolean ramasse) {
        this.nom = nom;
        this.ramasse = ramasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isRamasse() {
        return ramasse;
    }

    public void setRamasse(boolean ramasse) {
        this.ramasse = ramasse;
    }
}
