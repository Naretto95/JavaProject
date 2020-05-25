package controle;

import java.util.ArrayList;

import Jeu.Carte;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ControleCarte {
	
	public static Image staff; 
	private Carte carte;
	private GraphicsContext gc;
	
	public ControleCarte(Carte carte, GraphicsContext gc) {
		this.carte = carte;
		this.gc = gc;
		ControleCarte.staff= new Image("file:imagesitems/staff.png",this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel(),false,true);	
	}
	

	
	/*
	public void afficheCarte() {
		ArrayList<ArrayList<Image>> imagesCasesCarte = carte.getImagesCasesCarte();
		for (int i = 0 ; i < imagesCasesCarte.size();i++) {
			for (int j = 0 ; j < imagesCasesCarte.get(i).size();j++) {
				gc.drawImage(imagesCasesCarte.get(i).get(j), this.carte.getHauteurCasePixel()*j, this.carte.getLargeurCasePixel()*i);
				if (this.carte.getCase(i, j).hasStaff()) {gc.drawImage(ControleCarte.staff, this.carte.getHauteurCasePixel()*j, this.carte.getLargeurCasePixel()*i);}
			}
		}
	}
	
	*/
	public void afficheCarte() {
		ArrayList<ArrayList<Image>> imagesCasesCarte = carte.getImagesCasesCarte();
		int numLignePremiereCaseEcran = this.carte.getFenetreEcran().getPosYPixelEcran()/this.carte.getHauteurCasePixel();
		int numColonnePremiereCaseEcran = this.carte.getFenetreEcran().getPosXPixelEcran()/this.carte.getLargeurCasePixel();
		int varTempPourX = (numColonnePremiereCaseEcran)*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran();
		int varTempPourY = (numLignePremiereCaseEcran)*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran();
		int indiceLigneTempCase = numLignePremiereCaseEcran;
		int indiceColonneTempCase = numColonnePremiereCaseEcran;
		while(varTempPourY<this.carte.getFenetreEcran().getHauteurPixelEcran()) {
			while(varTempPourX<this.carte.getFenetreEcran().getLargeurPixelEcran()) {
				gc.drawImage(imagesCasesCarte.get(indiceLigneTempCase).get(indiceColonneTempCase), varTempPourX, varTempPourY);
				if (this.carte.getCase(indiceLigneTempCase, indiceColonneTempCase).hasStaff()) {gc.drawImage(ControleCarte.staff, varTempPourX, varTempPourY);}
				varTempPourX+=this.carte.getLargeurCasePixel();
				indiceColonneTempCase++;
			}
			varTempPourX=(numColonnePremiereCaseEcran)*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran();
			varTempPourY+=this.carte.getHauteurCasePixel();
			indiceColonneTempCase=numColonnePremiereCaseEcran;
			indiceLigneTempCase++;
		}
	}
	
}
