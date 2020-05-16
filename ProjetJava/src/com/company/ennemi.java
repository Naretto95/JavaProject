package com.company;

import java.util.HashMap;

public class ennemi extends entite {
    private Categorie categorie;
    private Race race;

    public ennemi(int vie, int positionX, int positionY, EtatEntite etat, HashMap<item, Integer> inventaireItem, int niveau, item enMain, Categorie categorie, Race race) {
        super(vie, positionX, positionY, etat, inventaireItem, niveau, enMain);
        this.categorie = categorie;
        this.race = race;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
