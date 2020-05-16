package Jeu;

import java.util.Map.Entry;

public class Joueur extends Entité {
	
	private int Experience;
	private String Nom;
	
	public Joueur(String _Nom, int _Niveau, int _PositionX, int PositionY) {
		super(_Niveau, _PositionX,PositionY);
		this.setEnMain(new Arme(TypeArme.Main,_Niveau));
		this.setNom(_Nom);
		this.setExperience(0);
	}
	
	public void Utiliser(Entité _Entité,Potion _Potion){
		if (this.getEtat()==EtatEntité.Vivant) {
			switch (_Potion.getEffet()) {
			case Etourdissement:
				if (_Entité.getPositionX() == this.getPositionX()) {
					this.Empoisonner(_Entité, _Potion);
				}else {
					if (_Entité.getPositionY() == this.getPositionY()) {
						this.Empoisonner(_Entité, _Potion);
					}
				}
				break;
				
			case Poison:
				if (_Entité.getPositionX() == this.getPositionX()) {
					this.Empoisonner(_Entité, _Potion);
				}else {
					if (_Entité.getPositionY() == this.getPositionY()) {
						this.Empoisonner(_Entité, _Potion);
					}
				}
				break;
				
			case GainDeVie :
				if (_Entité.getPositionX() == this.getPositionX() && _Entité.getPositionY() == this.getPositionY()) {
					if (this.getVie()<this.getNiveau()*100) {
						this.setVie(this.getNiveau()*100);
					}
				}
				break;
				
			case GainExperience:
				if (_Entité.getPositionX() == this.getPositionX() && _Entité.getPositionY() == this.getPositionY()) {
					this.setExperience(this.getExperience()+_Potion.getNiveau()*10);
					this.levelup();
				}
				break;
			default:
				break;
			}
		}
	}
	
	public void levelup() {
		if (this.getExperience()>this.getNiveau()*100) {
			this.setNiveau(this.getNiveau()+1);
			this.setExperience(this.getExperience()-this.getNiveau()*100);
		}
	}
	
	public void AmeliorerArme(Arme _Arme) {
		for(Entry<Ressource, Integer> entry : this.getInventaireRessource().entrySet()) {
		    Ressource cle = entry.getKey();
		    Integer valeur = entry.getValue();
		    switch (_Arme.getType()) {
			case EpéeLongue:
				if (cle.getType()==TypeRessource.Fer) {
					if (valeur>=5) {
						_Arme.Ameliorer();
						this.getInventaireRessource().put(cle,this.getInventaireRessource().get(cle)-5);
					}			
				}
				break;
				
			case EpéeCourte:
				if (cle.getType()==TypeRessource.Fer) {
					if (valeur>=3) {
						_Arme.Ameliorer();
						this.getInventaireRessource().put(cle,this.getInventaireRessource().get(cle)-3);
					}			
				}
				break;
				
			case Arc:
				if (cle.getType()==TypeRessource.Bois) {
					if (valeur>=3) {
						_Arme.Ameliorer();
						this.getInventaireRessource().put(cle,this.getInventaireRessource().get(cle)-3);
					}			
				}
				break;

			default:
				break;
			}
		}
	}
	
	public void Ramasser(Item _Item) {
		for(Entry<Item, Integer> entry : this.getInventaireItem().entrySet()) {
			Item cle = entry.getKey();
		    Integer valeur = entry.getValue();
		    if (cle==_Item){
		    	if (valeur<3) {
					this.getInventaireItem().put(cle,this.getInventaireItem().get(cle)+1);
					cle.setRamassé(true);
				}				
			}
		}
	}
	
	public void Bouger(int guauche, int droite, int bas, int haut){
		this.setPositionX(this.getPositionX()+droite);
		this.setPositionX(this.getPositionX()-guauche);
		this.setPositionY(this.getPositionY()+haut);
		this.setPositionY(this.getPositionY()-bas);
		
	}
	
	public int getExperience() {
		return Experience;
	}
	
	public void setExperience(int experience) {
		Experience = experience;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}
	
	
	
}
