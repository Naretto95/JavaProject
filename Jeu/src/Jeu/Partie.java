package jeu;
import java.util.HashMap;

public class Partie {
	private int Duree;
	private TypeIssus Issus;
	public HashMap<Coordonnee,Case> Carte;
	
	
	public Partie() {
		
	}
	
	public void Gameover() {
		
	}
	
	public void pause() {
		
	}
	
	public void save() {
		
	}
	
	public void exit() {
		
	}
	
	public void recommencer() {
		
	}
	
	public void jouer() {
		
	}
	
	
	public int getDuree() {
		return Duree;
	}
	
	public void setDuree(int duree) {
		Duree = duree;
	}
	
	public TypeIssus getIssus() {
		return Issus;
	}
	
	public void setIssus(TypeIssus issus) {
		Issus = issus;
	}
	
	 public void setValue(HashMap<Coordonnee,Case> carte)
     {
       Carte = carte;
     }
    public HashMap<Coordonnee,Case> getCarte()
     {
      return Carte;
     }
	


}
