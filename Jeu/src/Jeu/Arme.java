package Jeu;

public class Arme extends Item {
	
	private int Durabilité;
	private TypeArme Type;
	private int Degats;
	private int Portée;
	
	public Arme(TypeArme _Type, int _Niveau ){
		super(true,_Niveau,false);
		switch (_Type) {
		case EpéeLongue:
			this.setDegats(_Niveau * 5);
			this.setDurabilité(3);
			this.setType(_Type);
			this.setPortée(2);
			break;
			
		case EpéeCourte:
			this.setDegats(_Niveau * 3);
			this.setDurabilité(3);
			this.setType(_Type);
			this.setPortée(1);
			break;
		
		case Arc:
			this.setDegats(_Niveau * 2);
			this.setDurabilité(15);
			this.setType(_Type);
			this.setPortée(10);
			break;
		
		case Main:
			this.setRamassé(true);
			this.setDegats(_Niveau*1);
			this.setDurabilité(9999999);
			this.setType(_Type);
			this.setPortée(1);
			break;
			
		default:
			break;
		}
		
	}
	
	public void Ameliorer(Joueur _Joueur) {
		((Arme)_Joueur.getEnMain()).setDegats(((Arme)_Joueur.getEnMain()).getDegats()+10);
	}
	
	public int getDurabilité() {
		return Durabilité;
	}
	
	public void setDurabilité(int durabilité) {
		Durabilité = durabilité;
	}
	
	public TypeArme getType() {
		return Type;
	}
	
	public void setType(TypeArme type) {
		Type = type;
	}
	
	public int getPortée() {
		return Portée;
	}
	
	public void setPortée(int portée) {
		Portée = portée;
	}
	
	public int getDegats() {
		return Degats;
	}
	
	public void setDegats(int degats) {
		Degats = degats;
	}
	
	public String toString() {
		return ""+this.getType()+" "+this.getNiveau() +" "+this.getDurabilité();
	}
	

}
