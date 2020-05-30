package Jeu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
/**
 * 
 * @author Lilian Naretto
 *
 */
public class Entit� extends Observable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static Integer VIE_MODIFIEE = new Integer(0);
	public static Integer EST_MORT = new Integer(1);
	public static Integer RESSOURCES_MODIFIEES = new Integer(2);
	public static Integer CHANGEMENT_ITEM = new Integer(3);
	
	private int Vie;
	private int PositionX;
	private int PositionY;
	private int Niveau;
	private int Etourdissement;
	private Item enMain;
	private EtatEntit� Etat;
	private List<Arme> InventaireArme;
	private List<Potion> InventairePotion;
	private Map<Ressource, Integer> InventaireRessource;
	
	public Entit�(int _Niveau, int _PositionX, int PositionY) {
		List<Arme> _InventaireArme = new ArrayList<>();
		List<Potion> _InventairePotion = new ArrayList<>();
		Map<Ressource, Integer> _InventaireRessource= new HashMap<>();
		this.setInventaireArme(_InventaireArme);
		this.setInventairePotion(_InventairePotion);
		this.setEtat(EtatEntit�.Vivant);
		this.setEtourdissement(0);
		this.setNiveau(_Niveau);
		this.setInventaireRessource(_InventaireRessource);
		this.setPositionX(_PositionX);
		this.setPositionY(PositionY);
		this.setVie(_Niveau*100);
		this.getInventaireArme().add(new Arme(TypeArme.Main,this.getNiveau()));
		this.getInventaireRessource().put(new Ressource(TypeRessource.Cle),0);
		this.getInventaireRessource().put(new Ressource(TypeRessource.Bois),0);
		this.getInventaireRessource().put(new Ressource(TypeRessource.Fer),0);
		this.getInventaireRessource().put(new Ressource(TypeRessource.Or),0);
	}
	
	public void DegatsRe�ues(Arme _Arme){
		this.setVie(this.getVie()-_Arme.getDegats());
		if (this.getVie()<=0) {
			this.setEtat(EtatEntit�.Mort);
			this.setChanged();
			this.notifyObservers(Entit�.EST_MORT);
		}
	}
	
	public void Attaque(Entit� _Entit�, Arme _Arme){
		if (_Arme.getDurabilit�()>0) {
			boolean rater = new Random().nextInt(5)==0;
				if (rater==false) {
					boolean etourdis = new Random().nextInt(5)==0;
					if (Math.abs(_Entit�.getPositionX()-this.getPositionX())<= _Arme.getPort�e() && _Entit�.getPositionY()==this.getPositionY() || Math.abs(_Entit�.getPositionY()-this.getPositionY())<= _Arme.getPort�e() && _Entit�.getPositionX()==this.getPositionX() ) {
						if (etourdis==true) {
							_Entit�.setEtat(EtatEntit�.Etourdis);
						}_Entit�.DegatsRe�ues(_Arme);
						
						_Arme.setDurabilit�(_Arme.getDurabilit�()-1);
						if (_Arme.getDurabilit�()<=0) {
							_Arme.setEtat(false);
						}
						this.ActualiserInventaire();
					}
				}
		}
	}
	
	public void ChangerItem(Item _Item) {
		if (this.getEtat()==EtatEntit�.Vivant) {
			if (_Item instanceof Arme) {
				for (int i = 0; i < this.getInventaireArme().size(); i++) {
					if (this.enMain instanceof Arme) {
						if (this.getInventaireArme().get(i).getType()==((Arme)this.enMain).getType()) {
							for (int j = 0; j < this.getInventaireArme().size(); j++) {
								if (this.getInventaireArme().get(j).getType()==((Arme)this.enMain).getType() && this.getInventaireArme().get(j)!=(Arme)this.enMain && ((Arme)_Item).getType()==((Arme)this.enMain).getType()) {
									this.enMain=this.getInventaireArme().get(j);
									this.getInventaireArme().set(j,this.getInventaireArme().get(i));
									this.getInventaireArme().set(i,(Arme)this.enMain);
								}
								else {
									if (this.getInventaireArme().get(j).getType()==((Arme)_Item).getType() && this.getInventaireArme().get(j)!=(Arme)this.enMain) {
										this.enMain=this.getInventaireArme().get(j);
										break;
									}
								}
							}
							break;
						}
					}else {
						for (int j = 0; j < this.getInventaireArme().size(); j++) {
							if (this.getInventaireArme().get(j).getType()==((Arme)_Item).getType()) {
								this.enMain=this.getInventaireArme().get(j);
							}
						}
					}
				}
			}
			if (_Item instanceof Potion) {
				for (int i = 0; i < this.getInventairePotion().size(); i++) {
					if (this.enMain instanceof Potion) {
						if (this.getInventairePotion().get(i).getEffet()==((Potion)this.enMain).getEffet()) {
							for (int j = 0; j < this.getInventairePotion().size(); j++) {
								if (this.getInventairePotion().get(j).getEffet()==((Potion)this.enMain).getEffet() && this.getInventairePotion().get(j)!=(Potion)this.enMain && ((Potion)_Item).getEffet()==((Potion)this.enMain).getEffet()) {
									this.enMain=this.getInventairePotion().get(j);
									this.getInventairePotion().set(j,this.getInventairePotion().get(i));
									this.getInventairePotion().set(i,(Potion)this.enMain);
								}
								else {
									if (this.getInventairePotion().get(j).getEffet()==((Potion)_Item).getEffet() && this.getInventairePotion().get(j)!=(Potion)this.enMain) {
										this.enMain=this.getInventairePotion().get(j);
										break;
									}
								}
							}
							break;
						}
					}else {
						for (int j = 0; j < this.getInventairePotion().size(); j++) {
							if (this.getInventairePotion().get(j).getEffet()==((Potion)_Item).getEffet()) {
								this.enMain=this.getInventairePotion().get(j);
							}
						}
					}
				}
			}
		}else {
			if (this.getEtat()==EtatEntit�.Etourdis) {
				this.setEtourdissement(this.getEtourdissement()+1);
				if (this.getEtourdissement()==5) {
					this.setEtourdissement(0);
					this.setEtat(EtatEntit�.Vivant);
				}
			}
		}
		this.setChanged();
		this.notifyObservers(CHANGEMENT_ITEM);
	}
	
	public void ActualiserInventaire(){
		if (this.enMain instanceof Arme) {
			for (int i = 0; i < this.getInventaireArme().size(); i++) {
					if (((Arme)this.enMain)==this.getInventaireArme().get(i)) {
						if (this.enMain.isEtat()==false) {
							for (int j = 0; j < this.getInventaireArme().size(); j++) {
								if (this.getInventaireArme().get(j).getType()==this.getInventaireArme().get(i).getType() && this.getInventaireArme().get(j)!=(Arme)this.enMain) {
										this.enMain=this.getInventaireArme().get(j);
								}
							}
							if ((Arme)this.enMain==this.getInventaireArme().get(i)) {
								this.enMain=this.getInventaireArme().get(0);
							}
							this.getInventaireArme().remove(i);
						}else {
							this.getInventaireArme().set(i,(Arme)this.enMain);
							break;
						}
				}
			}
		}
		if (this.enMain instanceof Potion) {
			for (int i = 0; i < this.getInventairePotion().size(); i++) {
					if (((Potion)this.enMain)==this.getInventairePotion().get(i)) {
						if (this.enMain.isEtat()==false) {
							for (int j = i+1; j < this.getInventairePotion().size(); j++) {
								if (this.getInventairePotion().get(j).getEffet()==this.getInventairePotion().get(i).getEffet()&& this.getInventairePotion().get(j)!=(Potion)this.enMain) {
										this.enMain=this.getInventairePotion().get(j);
								}
							}
							if ((Potion)this.enMain==this.getInventairePotion().get(i)) {
								this.enMain=this.getInventaireArme().get(0);
							}
							this.getInventairePotion().remove(i);
						}else {
							this.getInventairePotion().set(i,(Potion)this.enMain);
							break;
						}
				}
			}
		}
	}
	
	public void Utiliser(Entit� _Entit�){
		if (this.getEtat()==EtatEntit�.Vivant) {
			if (this.enMain instanceof Arme) {
				if (_Entit�.getEtat()==EtatEntit�.Vivant || _Entit�.getEtat()==EtatEntit�.Etourdis) {
					Attaque(_Entit�,(Arme)this.enMain);
					if (this instanceof Joueur && _Entit� instanceof Ennemi) {
						if (_Entit�.getEtat()==EtatEntit�.Mort) {
							((Joueur)this).setExperience(((Joueur)this).getExperience()+((Ennemi)_Entit�).getExperienceMonstre());
							((Joueur)this).levelup();
						}
					}
				}
			}else {
				if (this.enMain instanceof Potion) {
					switch (((Potion)this.enMain).getEffet()) {
					case Etourdissement:
						if (_Entit�.getPositionX() == this.getPositionX()) {
							this.Empoisonner(_Entit�);
						}else {
							if (_Entit�.getPositionY() == this.getPositionY()) {
								this.Empoisonner(_Entit�);
							}
						}
						break;
						
					case Poison:
						if (_Entit�.getEtat()==EtatEntit�.Vivant || _Entit�.getEtat()==EtatEntit�.Etourdis) {
							if (Math.abs(_Entit�.getPositionX()-this.getPositionX())<= 3 && _Entit�.getPositionY()==this.getPositionY() || Math.abs(_Entit�.getPositionY()-this.getPositionY())<= 3 && _Entit�.getPositionX()==this.getPositionX() ) {
								this.Empoisonner(_Entit�);
							}
						}
						break;
						
					case GainDeVie :
							if (this.getVie()<this.getNiveau()*100) {
								if (((Potion)this.enMain).getNiveau()+this.getVie() <= this.getNiveau()*100) {
									this.setVie(((Potion)this.enMain).getNiveau()+this.getVie());
									((Potion)this.enMain).setEtat(false);
									this.ActualiserInventaire();								
								}else {
									this.setVie(this.getNiveau()*100);
									((Potion)this.enMain).setEtat(false);
									this.ActualiserInventaire();
								}
						}
						break;
						
					case GainDegats :
						for (int i = 0; i < this.getInventaireArme().size(); i++) {
							if (this.getInventaireArme().get(i).getType()!=TypeArme.Main && this.getInventaireArme().get(i).isDegatsUp()==false) {
								this.getInventaireArme().get(i).setDegatsUp(true);
								this.getInventaireArme().get(i).setDegats(this.getInventaireArme().get(i).getDegats()+((Potion)this.enMain).getNiveau());
								((Potion)this.enMain).setEtat(false);
								this.ActualiserInventaire();
								break;
							}
						}
						break;
						
					default:
						break;
					}
				}
			}
		}else {
			if (this.getEtat()==EtatEntit�.Etourdis) {
				this.setEtourdissement(this.getEtourdissement()+1);
				if (this.getEtourdissement()==5) {
					this.setEtourdissement(0);
					this.setEtat(EtatEntit�.Vivant);
				}
			}
		}
		this.ActualiserInventaire();
		this.setChanged();
		this.notifyObservers(CHANGEMENT_ITEM);
	}
	
	public void Empoisonner(Entit� _Entit�) {
		_Entit�.Empoisonnement((Potion)this.enMain);
		((Potion)this.enMain).setEtat(false);
		this.ActualiserInventaire();
		if (this instanceof Joueur && _Entit� instanceof Ennemi) {
			if (_Entit�.getEtat()==EtatEntit�.Mort) {
				((Joueur)this).setExperience(((Joueur)this).getExperience()+((Ennemi)_Entit�).getExperienceMonstre());
				((Joueur)this).levelup();
				((Ennemi)_Entit�).Jeter();
				_Entit�=null;
			}
		}
	}
	
	
	public void Empoisonnement(Potion _Potion){
		switch (_Potion.getEffet()) {
		case Etourdissement:
			this.setEtat(EtatEntit�.Etourdis);
			break;
		case Poison:
			this.setVie(this.getVie()-_Potion.getNiveau()*10);
			if (this.getVie()<=0) {
				this.setEtat(EtatEntit�.Mort);
			}
			break;

		default:
			break;
		}
	}
	
	public int getVie() {
		return Vie;
	}
	
	public void setVie(int vie) {
		Vie = vie;
		this.setChanged();
		this.notifyObservers(Entit�.VIE_MODIFIEE);
	}

	public int getPositionX() {
		return PositionX;
	}

	public void setPositionX(int positionX) {
		PositionX = positionX;
	}

	public int getPositionY() {
		return PositionY;
	}

	public void setPositionY(int positionY) {
		PositionY = positionY;
	}

	public int getNiveau() {
		return Niveau;
	}

	public void setNiveau(int niveau) {
		Niveau = niveau;
	}

	public Item getEnMain() {
		return enMain;
	}

	public void setEnMain(Item enMain) {
		this.enMain = enMain;
	}

	public EtatEntit� getEtat() {
		return Etat;
	}

	public void setEtat(EtatEntit� etat) {
		Etat = etat;
	}

	public Map<Ressource, Integer> getInventaireRessource() {
		return InventaireRessource;
	}

	public void setInventaireRessource(Map<Ressource, Integer> inventaireRessource) {
		InventaireRessource = inventaireRessource;
		this.setChanged();
		this.notifyObservers(RESSOURCES_MODIFIEES);
	}

	public List<Arme> getInventaireArme() {
		return InventaireArme;
	}

	public void setInventaireArme(List<Arme> inventaireArme) {
		InventaireArme = inventaireArme;
	}
	
	public List<Potion> getInventairePotion() {
		return InventairePotion;
	}

	public void setInventairePotion(List<Potion> inventairePotion) {
		InventairePotion = inventairePotion;
	}

	public int getEtourdissement() {
		return Etourdissement;
	}

	public void setEtourdissement(int etourdissement) {
		Etourdissement = etourdissement;
	}
	
	
	
	
}
