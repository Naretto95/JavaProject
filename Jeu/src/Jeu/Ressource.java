package Jeu;

public class Ressource extends Objet {
	
	private TypeRessource Type;
	
	public Ressource(TypeRessource _Type) {
		this.setRamassť(true);
		this.setType(_Type);
	}
	
	public TypeRessource getType() {
		return Type;
	}

	public void setType(TypeRessource type) {
		Type = type;
	}

}
