package Jeu;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Carte {
	
	private ArrayList<ArrayList<Image>> imagesCasesCartes;
	
	private ArrayList<ArrayList<Case>> casesCarte;
	
	public Carte() {}
	
	public Case getCase(int i, int j) {
		if (i<this.casesCarte.size() && j<this.casesCarte.get(i).size()) {
			return this.casesCarte.get(i).get(j);
		}
		return null;
	}
	
}
