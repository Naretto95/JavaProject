package Jeu;

public class Main {
	
	
	
	
public static void main(String[] args) {
	Joueur heros = new Joueur("Lilian",1,0,0);
	heros.getInventaireItem().put(new Potion(Effet.GainDeVie,heros.getNiveau()),1);
	Ennemi monstre = new Ennemi(1,1,0,Categorie.Normal,Race.Humain);
	System.out.println(monstre.getInventaireItem());
	System.out.println(heros.getVie());
	monstre.Utiliser(heros);
	System.out.println(heros.getVie());

	//monstre.ChangerItem(new Arme(TypeArme.Main,monstre.getNiveau()));

	System.out.println("durabilite"+((Arme)monstre.getEnMain()).getDurabilité());
	System.out.println(monstre.getInventaireItem());
	heros.setEtat(EtatEntité.Vivant);
	heros.ChangerItem(new Potion(Effet.GainDeVie,heros.getNiveau()));
	heros.Utiliser(monstre);
	System.out.println(heros.getVie());
	System.out.println(heros.getInventaireItem());
}
}
