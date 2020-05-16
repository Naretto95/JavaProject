package com.company;

public class Potion extends item {
    private Effet effet;

    public Potion(String nom, boolean ramasse, boolean etat, Effet effet) {
        super(nom, ramasse, etat);
        this.effet = effet;
    }

    public Effet getEffet() {
        return effet;
    }

    public void setEffet(Effet effet) {
        this.effet = effet;
    }
}
