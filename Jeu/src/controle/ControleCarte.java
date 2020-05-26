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
		int hauteur = this.carte.getHauteurCasePixel();
		int largeur = this.carte.getLargeurCasePixel();
		for (int i = 0 ; i < 20;i++) {
			for (int j = 0 ; j < 20;j++) {
				int y =hauteur*j;
				int x = largeur*i;
				gc.drawImage(imagesCasesCarte.get(i).get(j), y, x);
				if (this.carte.getCase(i, j).hasStaff()) {gc.drawImage(ControleCarte.staff, y, x);}
			}
		}
	}
	
}
