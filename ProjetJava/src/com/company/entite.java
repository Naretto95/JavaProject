package com.company;

import java.util.HashMap;

public class entite {
    private int vie;
    private int positionX;
    private int positionY;
    private EtatEntite etat;
    private HashMap<item,Integer> inventaireItem;
    private int niveau;
    private item enMain;

    public entite(int vie, int positionX, int positionY, EtatEntite etat, HashMap<item, Integer> inventaireItem, int niveau, item enMain) {
        this.vie = vie;
        this.positionX = positionX;
        this.positionY = positionY;
        this.etat = etat;
        this.inventaireItem = inventaireItem;
        this.niveau = niveau;
        this.enMain = enMain;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public EtatEntite getEtat() {
        return etat;
    }

    public void setEtat(EtatEntite etat) {
        this.etat = etat;
    }

    public HashMap<item, Integer> getInventaireItem() {
        return inventaireItem;
    }

    public void setInventaireItem(HashMap<item, Integer> inventaireItem) {
        this.inventaireItem = inventaireItem;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public item getEnMain() {
        return enMain;
    }

    public void setEnMain(item enMain) {
        this.enMain = enMain;
    }

   /* public void degatsrecus(arme);
    public void attaque(entite,arme);
    public void empoisonner(entite,potion);
    */







}
