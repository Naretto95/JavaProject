package com.company;

import java.sql.Time;

public class Partie {

    private Time duree;
    private boolean issu;

    public Partie(Time duree, boolean issu) {
        this.duree = duree;
        this.issu = issu;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public boolean isIssu() {
        return issu;
    }

    public void setIssu(boolean issu) {
        this.issu = issu;
    }
    /*gameover()
pause()
save()
Exit()
Recommencer()
jouer()*/
}
