package com.company;

public class item extends Objet {

        private boolean etat;

    public item(String nom, boolean ramasse, boolean etat) {
        super(nom, ramasse);
        this.etat = etat;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
}
