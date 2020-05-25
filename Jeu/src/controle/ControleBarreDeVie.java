package controle;

import java.util.Observable;
import java.util.Observer;

import Jeu.Carte;
import Jeu.Entité;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ControleBarreDeVie  extends Group implements Observer{
	
	private ControleEntite ctlEntite;
	private boolean isDynamic=false;
	private int vie;
	private int vieMax;
	private int zoom; 
	private Rectangle barBorder;
	private Rectangle unlife;
	private Rectangle life;
	
	public ControleBarreDeVie(ControleEntite ctlEntite,int posX, int posY, int zoom) {
		this.ctlEntite = ctlEntite;
		this.vie=ctlEntite.getEntite().getVie();
		this.vieMax=this.vie;
		this.zoom=zoom;
		barBorder = new Rectangle(posX,posY,zoom*vieMax+10,(zoom*vieMax+10)/10+6);
		barBorder.setFill(Color.BLACK);
		unlife = new Rectangle(posX+5,posY+3,zoom*(vieMax),(zoom*vieMax+10)/10);
		unlife.setFill(Color.RED);
		life = new Rectangle(posX+5,posY+3,zoom*vie,(zoom*vieMax+10)/10);
		life.setFill(Color.GREEN);
		ctlEntite.addObserver(this);
		ctlEntite.getEntite().addObserver(this);
		this.getChildren().add(barBorder);
		this.getChildren().add(unlife);
		this.getChildren().add(life);
	}
	
	public ControleBarreDeVie(ControleEntite ctlEntite, int zoom) {
		this.isDynamic=true;
		this.ctlEntite = ctlEntite;
		this.ctlEntite.carte.addObserver(this);
		this.vie=ctlEntite.getEntite().getVie();
		this.vieMax=this.vie;
		this.zoom=zoom;
		barBorder = new Rectangle(this.ctlEntite.getPositionXPixel(),this.ctlEntite.getPositionYPixel(),zoom*vieMax+10,(zoom*vieMax+10)/10+6);
		barBorder.setFill(Color.BLACK);
		unlife = new Rectangle(this.ctlEntite.getPositionXPixel()+5,this.ctlEntite.getPositionYPixel()+3,zoom*(vieMax),(zoom*vieMax+10)/10);
		unlife.setFill(Color.RED);
		life = new Rectangle(this.ctlEntite.getPositionXPixel()+5,this.ctlEntite.getPositionYPixel()+3,zoom*vie,(zoom*vieMax+10)/10);
		life.setFill(Color.GREEN);
		ctlEntite.addObserver(this);
		ctlEntite.getEntite().addObserver(this);
		this.getChildren().add(barBorder);
		this.getChildren().add(unlife);
		this.getChildren().add(life);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg.equals(Entité.VIE_MODIFIEE)) {
			life.setWidth(zoom*this.ctlEntite.getEntite().getVie());
		}
		if ((arg.equals(ControleEntite.A_BOUGE)||arg.equals(Carte.CARTE_QUI_BOUGE)) && (this.isDynamic)) {
			if (this.ctlEntite.detecteCollision(ctlEntite.getPositionXPixel(), ctlEntite.getPositionYPixel(), ctlEntite.largeurPixelEntite, ctlEntite.hauteurPixelEntite, ctlEntite.carte.getFenetreEcran().getPosXPixelEcran(), ctlEntite.carte.getFenetreEcran().getPosYPixelEcran(), ctlEntite.carte.getFenetreEcran().getLargeurPixelEcran(), ctlEntite.carte.getFenetreEcran().getHauteurPixelEcran())) {this.setVisible(true);}
			else {this.setVisible(false);}
			barBorder.setX(this.ctlEntite.getPositionXPixel());
			barBorder.setY(this.ctlEntite.getPositionYPixel());
			unlife.setX(this.ctlEntite.getPositionXPixel()+5);
			unlife.setY(this.ctlEntite.getPositionYPixel()+3);
			life.setX(this.ctlEntite.getPositionXPixel()+5);
			life.setY(this.ctlEntite.getPositionYPixel()+3);
		}
		if (arg.equals(Entité.EST_MORT)) {
			
			this.ctlEntite.getEntite().deleteObservers();
			this.ctlEntite.deleteObservers();
			this.ctlEntite.carte.deleteObserver(this);
			this.getChildren().removeAll(this.life,this.barBorder,this.unlife);
		}
	}

}
