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
			this.getInventaireRessource().put(new Ressource(TypeRessource.Cle),1);
		}
		switch (this.getRace()) {
		case Orc:
			this.setEnMain(new Arme(TypeArme.EpéeLongue,this.getNiveau()));
			this.getInventaireItem().put(new Arme(TypeArme.EpéeLongue,this.getNiveau()),1);
			this.getInventaireItem().put(new Potion(Effet.Poison,this.getNiveau()),1);
			this.getInventaireRessource().put(new Ressource(TypeRessource.Fer),1);
			this.getInventaireRessource().put(new Ressource(TypeRessource.Or),1);
			break;
			
		case Humain:
			this.setEnMain(new Arme(TypeArme.EpéeCourte,this.getNiveau()));
			this.getInventaireItem().put(new Arme(TypeArme.EpéeCourte,this.getNiveau()),1);
			this.getInventaireRessource().put(new Ressource(TypeRessource.Bois),2);
			this.getInventaireItem().put(new Potion(Effet.GainDeVie,this.getNiveau()),1);
			break;
			
		case Dragon:
			this.setEnMain(new Arme(TypeArme.EpéeLongue,this.getNiveau()));
			this.getInventaireItem().put(new Arme(TypeArme.EpéeLongue,this.getNiveau()),1);
			this.getInventaireItem().put(new Potion(Effet.GainDegats,this.getNiveau()),1);
			this.getInventaireRessource().put(new Ressource(TypeRessource.Or),2);
			
			break;
			
		case Nain:
			this.setEnMain(new Arme(TypeArme.Arc,this.getNiveau()));
			this.getInventaireItem().put(new Arme(TypeArme.Arc,this.getNiveau()),1);
			this.getInventaireItem().put(new Potion(Effet.Etourdissement,this.getNiveau()),1);
			this.getInventaireRessource().put(new Ressource(TypeRessource.Fer),2);
			
			break;

		default:
			break;
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
