package Jeu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class Carte {
	
	private ArrayList<ArrayList<Image>> imagesCasesCarte;
	private Map<String,Image> listeImagesElementsJeu;
	private ArrayList<ArrayList<Case>> casesCarte;
	private FenetreEcran fenetreEcran;
	private int largeurCasePixel=50;
	private int hauteurCasePixel=50;
	private int hauteurFenetreJeu=1000;
	private int largeurFenetreJeu=1000;
	
	public Carte(String repertoireImages, String[][] saisieCarte) {
		int nbLigne = saisieCarte.length;
		int nbColonne = saisieCarte[0].length;
		this.fenetreEcran=new FenetreEcran(0,0,this.largeurFenetreJeu,this.hauteurFenetreJeu,nbColonne*this.largeurCasePixel-this.largeurFenetreJeu,nbLigne*this.hauteurCasePixel-this.hauteurFenetreJeu);
		this.listeImagesElementsJeu= new HashMap<String,Image>(); 
		File repImages = new File(repertoireImages);
		for (String s : repImages.list()) {
			this.listeImagesElementsJeu.put(s,new Image("file:" + repertoireImages + "/" + s,largeurCasePixel,hauteurCasePixel,false,true));
		}
		this.imagesCasesCarte = new ArrayList<ArrayList<Image>>();
		for (int i =0;i<nbLigne;i++) {
			imagesCasesCarte.add(new ArrayList<Image>());
			casesCarte.add(new ArrayList<Case>());
			for(int j = 0 ; j<nbColonne;j++) {
				imagesCasesCarte.get(i).add(this.listeImagesElementsJeu.get(saisieCarte[i][j]));
				switch(saisieCarte[i][j]) {
				case "mur":
					casesCarte.get(i).add(new Case(Case.OBSTACLE));
					break;
				case "porte":
					casesCarte.get(i).add(new Case(Case.PORTE));
					break;
				default:
					casesCarte.get(i).add(new Case());
					break;				
				}
			}
		}
		
	}
	
	public Case getCase(int i, int j) {
		if (i<this.casesCarte.size() && j<this.casesCarte.get(i).size()) {
			return this.casesCarte.get(i).get(j);
		}
		return null;
	}

	public FenetreEcran getFenetreEcran() {
		return fenetreEcran;
	}
	
}
