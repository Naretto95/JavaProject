package Jeu;

import java.util.Map.Entry;

public class Ennemi extends Entit� {
	
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
			this.setEnMain(new Arme(TypeArme.Ep�eLongue,this.getNiveau()));
			this.getInventaireArme().add(new Arme(TypeArme.Ep�eLongue,this.getNiveau()));
			this.getInventairePotion().add(new Potion(Effet.Poison,this.getNiveau()));
			this.getInventaireRessource().put(new Ressource(TypeRessource.Fer),1);
			this.getInventaireRessource().put(new Ressource(TypeRessource.Or),1);
			break;
			
		case Humain:
			this.setEnMain(new Arme(TypeArme.Ep�eCourte,this.getNiveau()));
			this.getInventaireArme().add(new Arme(TypeArme.Ep�eCourte,this.getNiveau()));
			this.getInventaireRessource().put(new Ressource(TypeRessource.Bois),2);
			this.getInventairePotion().add(new Potion(Effet.GainDeVie,this.getNiveau()));
			break;
			
		case Dragon:
			this.setEnMain(new Arme(TypeArme.Ep�eLongue,this.getNiveau()));
			this.getInventaireArme().add(new Arme(TypeArme.Ep�eLongue,this.getNiveau()));
			this.getInventairePotion().add(new Potion(Effet.GainDegats,this.getNiveau()));
			this.getInventaireRessource().put(new Ressource(TypeRessource.Or),2);
			
			break;
			
		case Nain:
			this.setEnMain(new Arme(TypeArme.Arc,this.getNiveau()));
			this.getInventaireArme().add(new Arme(TypeArme.Arc,this.getNiveau()));
			this.getInventairePotion().add(new Potion(Effet.Etourdissement,this.getNiveau()));
			this.getInventaireRessource().put(new Ressource(TypeRessource.Fer),2);
			
			break;

		default:
			break;
		}
	}
	
	public void Jeter() {
		for (int i = 0; i < this.getInventaireArme().size(); i++) {
			if (this.getInventaireArme().get(i).getType()!=TypeArme.Main) {
				this.getInventaireArme().get(i).setRamass�(false);
				//AJOUTER A LA CASE
			}
		}
		for (int i = 0; i < this.getInventairePotion().size(); i++) {
			this.getInventairePotion().get(i).setRamass�(false);
			//AJOUTER A LA CASE
		}
		for(Entry<Ressource, Integer> entry : this.getInventaireRessource().entrySet()) {
			Ressource cle = entry.getKey();
			Integer valeur = entry.getValue();
			/*while (valeur>0) {
				this.getInventaireRessource().put(cle,this.getInventaireRessource().get(cle)-1);
				cle.setRamass�(false);
				// AJOUTER A LA CASE
				
			}*/
			
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
