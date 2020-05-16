package com.company;

public class Arme extends item {

    private int durabilite;
    private int niveau;
    private TypeArme typeArme;
    private int degats;
    private int portee;
    private int ameliore;


    public Arme(String nom, boolean ramasse, boolean etat, int durabilite, int niveau, TypeArme typeArme, int degats, int portee, int ameliore) {
        super(nom, ramasse, etat);
        this.durabilite = durabilite;
        this.niveau = niveau;
        this.typeArme = typeArme;
        this.degats = degats;
        this.portee = portee;
        this.ameliore = ameliore;
    }

    public int getDurabilite() {
        return durabilite;
    }

    public void setDurabilite(int durabilite) {
        this.durabilite = durabilite;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public TypeArme getTypeArme() {
        return typeArme;
    }

    public void setTypeArme(TypeArme typeArme) {
        this.typeArme = typeArme;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getPortee() {
        return portee;
    }

    public void setPortee(int portee) {
        this.portee = portee;
    }

    public int getAmeliore() {
        return ameliore;
    }

    public void setAmeliore(int ameliore) {
        this.ameliore = ameliore;
    }
}
