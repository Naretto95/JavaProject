package com.company;

import java.util.HashMap;

public class Joueur extends entite{
    private int experience;
    private String nom;

    public Joueur(int vie, int positionX, int positionY, EtatEntite etat, HashMap<item, Integer> inventaireItem, int niveau, item enMain, int experience, String nom) {
        super(vie, positionX, positionY, etat, inventaireItem, niveau, enMain);
        this.experience = experience;
        this.nom = nom;
    }

/*
    public void utilise(Potion) {

    }

    public void bouger(int, int, int, int) {

    }

    public void ameliorer(Arme) {

    }

    public void ramasser(item) {

    }

    public void levelup() {

    }*/


}
