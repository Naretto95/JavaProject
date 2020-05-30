package Jeu;

import java.io.Serializable;
/**
 * 
 * @author Lilian Naretto
 *
 */

public class Arme extends Item implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Durabilit�;
	private TypeArme Type;
	private boolean DegatsUp;
	private int Degats;
	private int Port�e;
	
	public Arme(TypeArme _Type, int _Niveau ){
		super(true,_Niveau,false);
		this.setDegatsUp(false);
		switch (_Type) {
		case Ep�eLongue:
			this.setDegats(_Niveau * 5);
			this.setDurabilit�(20);
			this.setType(_Type);
			this.setPort�e(2);
			break;
			
		case Ep�eCourte:
			this.setDegats(_Niveau * 3);
			this.setDurabilit�(10);
			this.setType(_Type);
			this.setPort�e(1);
			break;
		
		case Arc:
			this.setDegats(_Niveau * 2);
			this.setDurabilit�(15);
			this.setType(_Type);
			this.setPort�e(10);
			break;
		
		case Main:
			this.setRamass�(true);
			this.setDegats(_Niveau*1);
			this.setDurabilit�(9999999);
			this.setType(_Type);
			this.setPort�e(1);
			break;
			
		default:
			break;
		}
		
	}
	
	public void Ameliorer(Joueur _Joueur) {
		((Arme)_Joueur.getEnMain()).setDegats(_Joueur.getNiveau()*3);
		switch (((Arme)_Joueur.getEnMain()).getType()) {
		case Ep�eLongue:
			((Arme)_Joueur.getEnMain()).setDegats(_Joueur.getNiveau()*5);
			((Arme)_Joueur.getEnMain()).setNiveau(_Joueur.getNiveau());
			break;
			
		case Ep�eCourte:
			((Arme)_Joueur.getEnMain()).setDegats(_Joueur.getNiveau()*3);
			((Arme)_Joueur.getEnMain()).setNiveau(_Joueur.getNiveau());
			break;
		
		case Arc:
			((Arme)_Joueur.getEnMain()).setDegats(_Joueur.getNiveau()*2);
			((Arme)_Joueur.getEnMain()).setNiveau(_Joueur.getNiveau());
			break;
			
		default:
			break;
		}
	}
	
	public void Reparer(Entit� _Entit�) {
		switch (((Arme)_Entit�.getEnMain()).getType()) {
		case Ep�eLongue:
			((Arme)_Entit�.getEnMain()).setDurabilit�(20);
			break;
			
		case Ep�eCourte:
			((Arme)_Entit�.getEnMain()).setDurabilit�(10);
			break;
		
		case Arc:
			((Arme)_Entit�.getEnMain()).setDurabilit�(15);
			break;
			
		default:
			break;
		}
	}
	
	public int getDurabilit�() {
		return Durabilit�;
	}
	
	public void setDurabilit�(int durabilit�) {
		Durabilit� = durabilit�;
	}
	
	public TypeArme getType() {
		return Type;
	}
	
	public void setType(TypeArme type) {
		Type = type;
	}
	
	public int getPort�e() {
		return Port�e;
	}
	
	public void setPort�e(int port�e) {
		Port�e = port�e;
	}
	
	public int getDegats() {
		return Degats;
	}
	
	public void setDegats(int degats) {
		Degats = degats;
	}
	
	public String toString() {
		return "Type:"+this.getType()+" Niveau:"+this.getNiveau() +" Durabilit�:"+this.getDurabilit�();
	}

	public boolean isDegatsUp() {
		return DegatsUp;
	}

	public void setDegatsUp(boolean degatsUp) {
		DegatsUp = degatsUp;
	}
	

}
