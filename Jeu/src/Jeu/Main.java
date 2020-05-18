package Jeu;

public class Main {
	
	
	
	
public static void main(String[] args) {
	Joueur heros = new Joueur("Lilian",1,0,0);
	Ennemi monstre = new Ennemi(1,1,0,Categorie.Normal,Race.Humain);
	System.out.println(heros.getInventaireArme());
	heros.Ramasser(new Arme(TypeArme.Arc,1));
	heros.getInventaireArme().add(new Arme(TypeArme.Arc,5));
	System.out.println(heros.getInventaireArme());
	heros.ChangerItem(new Arme(TypeArme.Arc,1));
	heros.Jeter();
	System.out.println(heros.getInventaireArme());
	System.out.println(heros.getEnMain());
	heros.Jeter();
	System.out.println(heros.getInventaireArme());
	System.out.println(heros.getEnMain());
}
}
