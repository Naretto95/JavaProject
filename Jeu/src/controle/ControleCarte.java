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
	

	
	
	public void afficheCarte() {
		ArrayList<ArrayList<Image>> imagesCasesCarte = carte.getImagesCasesCarte();
		for (int i = 0 ; i < imagesCasesCarte.size();i++) {
			for (int j = 0 ; j < imagesCasesCarte.get(i).size();j++) {
				gc.drawImage(imagesCasesCarte.get(i).get(j), this.carte.getHauteurCasePixel()*j, this.carte.getLargeurCasePixel()*i);
				if (this.carte.getCase(i, j).hasStaff()) {gc.drawImage(ControleCarte.staff, this.carte.getHauteurCasePixel()*j, this.carte.getLargeurCasePixel()*i);}
			}
		}
	}
}
