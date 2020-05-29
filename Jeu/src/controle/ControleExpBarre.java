package controle;

import java.util.Observable;
import java.util.Observer;

import Jeu.Joueur;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ControleExpBarre  extends Group implements Observer{
	
	private Joueur joueur;
	private int largeurBarre=100;
	private int hauteurBarre=10;
	private Rectangle barBorder;
	private Rectangle unlife;
	private Rectangle life;
	
	public ControleExpBarre(Joueur joueur,int posX, int posY,int zoom) {
		this.joueur =joueur;
		hauteurBarre *= zoom;
		largeurBarre  *= zoom;
		barBorder = new Rectangle(posX,posY,largeurBarre,hauteurBarre);
		barBorder.setFill(Color.BLACK);
		unlife = new Rectangle(posX+5,posY+3,largeurBarre-10,hauteurBarre-6);
		unlife.setFill(Color.RED);
		life = new Rectangle(posX+5,posY+3,(joueur.getExperience()/(joueur.getNiveau()*100))*largeurBarre-10,hauteurBarre-6);
		life.setFill(Color.GREEN);
		joueur.addObserver(this);
		this.getChildren().add(barBorder);
		this.getChildren().add(unlife);
		this.getChildren().add(life);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if (arg1 instanceof Integer && arg1.equals(Joueur.GAIN_EXP)) {
			this.life.setWidth((joueur.getExperience()/(joueur.getNiveau()*100))*largeurBarre-10);
		}
	}
}
