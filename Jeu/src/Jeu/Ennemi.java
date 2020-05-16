package Jeu;

public class Ennemi extends Entité {
	
	private Categorie Categorie;
	private Race Race;
	private int ExperienceMonstre;
	
	public Ennemi( int _Niveau, int _PositionX, int PositionY,Categorie _Categorie, Race _Race) {
		super(_Niveau, _PositionX,PositionY);
		this.setExperienceMonstre(_Niveau*10);
		this.setCategorie(_Categorie);
		this.setRace(_Race);
		if (this.getCategorie()==Jeu.Categorie.Boss) {
			this.setVie(this.getVie()*2);	
		}
	}
	
	public Categorie getCategorie() {
		return Categorie;
	}
	
	public void setCategorie(Categorie categorie) {
		Categorie = categorie;
	}
	
	public Race getRace() {
		return Race;
	}
	
	public void setRace(Race race) {
		Race = race;
	}

	public int getExperienceMonstre() {
		return ExperienceMonstre;
	}

	public void setExperienceMonstre(int experienceMonstre) {
		ExperienceMonstre = experienceMonstre;
	}
	
}
