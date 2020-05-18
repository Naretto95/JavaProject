package Jeu;

public class Main {
	
	
	
	
public static void main(String[] args) {
	Joueur heros = new Joueur("Lilian",1,0,0);
	Ennemi monstre = new Ennemi(1,1,0,Categorie.Normal,Race.Humain);
	System.out.println(monstre.getInventairePotion());
	System.out.println(monstre.getInventaireArme());
	monstre.ChangerItem(new Potion(Effet.GainDeVie,monstre.getNiveau()));
	System.out.println(monstre.getEnMain());
	monstre.Utiliser(heros);
	System.out.println(monstre.getVie());
	System.out.println(monstre.getEnMain());
	System.out.println(monstre.getInventairePotion());
	heros.Utiliser(monstre);
	System.out.println(monstre.getVie());
	System.out.println(monstre.getEnMain());
	System.out.println(monstre.getInventairePotion());
	monstre.setEtat(EtatEntité.Vivant);
	monstre.Utiliser(heros);
	System.out.println(monstre.getVie());
	System.out.println(monstre.getEnMain());
	System.out.println(monstre.getInventairePotion());
	
}
}
