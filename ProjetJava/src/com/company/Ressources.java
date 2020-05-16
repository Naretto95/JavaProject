package com.company;

public class Ressources extends Objet {
    private TypeRessources type;

    public Ressources(String nom, boolean ramasse, TypeRessources type) {
        super(nom, ramasse);
        this.type = type;
    }

    public TypeRessources getType() {
        return type;
    }

    public void setType(TypeRessources type) {
        this.type = type;
    }
}
