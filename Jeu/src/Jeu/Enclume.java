package Jeu;

public class Enclume {
	private TypeEnclume Type;
	private Joueur Joueur;
	
	public Enclume(TypeEnclume _Type,Joueur _Joueur) {
		this.setJoueur(_Joueur);
		this.setType(_Type);
	}
	
	public void Utiliser() {
		switch (this.getType()) {
		case EnclumeReparer:
			this.getJoueur().Ameliorer();
			break;
			
		case EnclumeAmeliorer:
			this.getJoueur().Reparer();
			break;			
			
		default:
			break;
		}
	}
	
	public TypeEnclume getType() {
		return Type;
	}

	public void setType(TypeEnclume type) {
		Type = type;
	}

	public Joueur getJoueur() {
		return Joueur;
	}

	public void setJoueur(Joueur joueur) {
		Joueur = joueur;
	}
	
}
