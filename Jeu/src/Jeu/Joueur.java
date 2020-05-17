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
	
	public void levelup() {
		if (this.getExperience()>this.getNiveau()*100) {
			this.setNiveau(this.getNiveau()+1);
			this.setExperience(this.getExperience()-this.getNiveau()*100);
			for(Entry<Item, Integer> entry : this.getInventaireItem().entrySet()) {
				Item cle = entry.getKey();
			    if (cle instanceof Arme) {
					if (((Arme) cle).getType()==TypeArme.Main) {
						((Arme) cle).setDegats(this.getNiveau() * 1);
					}
				}
			   
			}   
		}
	}
	
	public void Ameliorer() {
		if (this.getEnMain() instanceof Arme) {
			AmeliorerArme((Arme)this.getEnMain());
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
	
	public void Jeter() {
		for(Entry<Item, Integer> entry : this.getInventaireItem().entrySet()) {
			Item cle = entry.getKey();
		    Integer valeur = entry.getValue();
		    if (cle instanceof Arme && this.getEnMain() instanceof Arme && valeur>0 && ((Arme) cle).getType()==((Arme)this.getEnMain()).getType()) {
		    	if (valeur>1) {
		    		this.getInventaireItem().put(cle,this.getInventaireItem().get(cle)-1);
		    		this.setEnMain(new Arme(((Arme)this.getEnMain()).getType(),((Arme)this.getEnMain()).getNiveau()));
		    		cle.setRamassé(false);
		    		//TOMBE SUR LA CASE
				}else {
						this.getInventaireItem().put(cle,this.getInventaireItem().get(cle)-1);
						this.setEnMain(new Arme(TypeArme.Main, this.getNiveau()));
						cle.setRamassé(false);
						//TOMBE SUR LA CASE
				}
			}
		    if (cle instanceof Potion && this.getEnMain() instanceof Potion && valeur>0 && ((Potion) cle).getEffet()==((Potion)this.getEnMain()).getEffet()) {
		    	if (valeur>1) {
		    		this.getInventaireItem().put(cle,this.getInventaireItem().get(cle)-1);
		    		this.setEnMain(new Potion(((Potion)this.getEnMain()).getEffet(),((Potion)this.getEnMain()).getNiveau()));
		    		cle.setRamassé(false);
		    		//TOMBE SUR LA CASE
				}else {
						this.getInventaireItem().put(cle,this.getInventaireItem().get(cle)-1);
						this.setEnMain(new Arme(TypeArme.Main, this.getNiveau()));
						cle.setRamassé(false);
						//TOMBE SUR LA CASE
				}
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
				}				
			}
		}
	}
	
	public void Bouger(int guauche, int droite, int bas, int haut){
		if (this.getEtat()==EtatEntité.Vivant) {
			this.setPositionX(this.getPositionX()+droite);
			this.setPositionX(this.getPositionX()-guauche);
			this.setPositionY(this.getPositionY()+haut);
			this.setPositionY(this.getPositionY()-bas);
		}	
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
