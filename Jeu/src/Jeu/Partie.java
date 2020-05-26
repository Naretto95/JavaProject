package jeu;
import java.util.Timer;
public class Partie {
	private Timer Duree;
	private TypeIssus Issus;
	public Carte Carte;
	
	public boolean paused;
	public boolean win;
	
	//........................................................................
	
	public Partie() {
		this.Duree = new Timer();
		this.Carte= new Carte(null, null);
		Joueur heros = new Joueur("Lilian",1,0,0);
    	Ennemi monstre = new Ennemi(1,1,0,Categorie.Normal,Race.Humain);
    	monstre.setExperienceMonstre(110);
    	heros.Utiliser(monstre);
    	heros.setEtat(EtatEntité.Vivant);
    	heros.setExperience(0);
    	paused = false;
    	
    	while (!gameOver() && !paused && !win) {
    		jouer();
    	}
    	if(gameOver()) {System.out.println("GAME OVER");} // or show a "gameover" screen
    	else if (paused) {pause();}
    	else if (win) {System.out.println("CONGRATS");} // or show "congrats" screen
	}
		
	//..........................................................................
	
	public boolean gameOver() {
		if (Joueur.getEtat()==EtatEntité.Mort) {return true;}
		else {return false;}
	}
	
	
	public void pause() {
		this.Duree.cancel();
		//show pause screen
	}
		
	
	
	public void save() {
		
	}
	public void exit() {
		System.exit(0);
	}
	
	
	public void recommencer() {
		while (!(gameOver()) || !win )
		 {
		      if (!paused)
		      { jouer();}
		 }
	}
	
	public void jouer() {
		while (!win && !gameOver()) {
			//play and update the map
		}
		
	}
	
	
	//..................................................................................
	public Timer getDuree() {
		return Duree;
	}
	
	public void setDuree(Timer duree) {
		Duree = duree;
	}
	
	public TypeIssus getIssus() {
		return Issus;
	}
	
	public void setIssus(TypeIssus issus) {
		Issus = issus;
	}
	
	 public void setValue(Carte carte)
     {
       Carte = carte;
     }
    public Carte getCarte()
     {
      return Carte;
     }


}
