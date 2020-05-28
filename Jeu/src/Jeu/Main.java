package Jeu;

public class Main {
	
	
	
	
public static void main(String[] args) {
	Joueur heros = new Joueur("Lilian",1,0,0);
	Ennemi monstre = new Ennemi(1,1,0,Categorie.Normal,Race.Humain);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	heros.Utiliser(monstre);
	System.out.println(monstre.getEtat());
	monstre.Utiliser(heros);
	monstre.Utiliser(heros);
	monstre.Utiliser(heros);
	monstre.Utiliser(heros);
	System.out.println(monstre.getEtat());
}
}
