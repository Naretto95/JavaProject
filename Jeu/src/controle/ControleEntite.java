package controle;

import Jeu.Carte;
import Jeu.Entité;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class ControleEntite {
	
	private Entité entite;
	protected Carte carte;
	protected GraphicsContext gc;
	protected Image feuilleDeSpriteEntite;
	
	protected boolean avanceD = false;
	protected boolean avanceG = false;
	protected boolean avanceH = false;
	protected boolean avanceB = false;
	protected double indiceSprite=0;

	protected int positionXPixel;
	protected int positionYPixel;
	protected int vitesse=1;
	protected int hauteurPixelEntite;
	protected int largeurPixelEntite;
	protected int deltaXHitBox=0;
	protected int deltaYHitBox=0;
	protected int paddingX;
	protected int paddingY;

	
	public ControleEntite(String feuilleDeSpriteEntite,Carte carte, GraphicsContext gc,Entité entite,int hauteurPixelEntite,int largeurPixelEntite) {
		this.feuilleDeSpriteEntite=new Image("file:"+feuilleDeSpriteEntite);
		this.hauteurPixelEntite=hauteurPixelEntite;
		this.largeurPixelEntite=largeurPixelEntite;
		this.entite=entite;
		this.carte = carte;
		this.gc = gc;
		this.carte.mettreEntite(entite, this.entite.getPositionY(), this.entite.getPositionX());
		this.positionXPixel=((this.entite.getPositionX())*this.carte.getLargeurCasePixel()) - this.carte.getFenetreEcran().getPosXPixelEcran();
		this.positionYPixel=((this.entite.getPositionY())*this.carte.getHauteurCasePixel()) - this.carte.getFenetreEcran().getPosYPixelEcran();
		this.paddingX=0;
		this.paddingY=0;
	}

	public int getPositionXPixel() {
		return positionXPixel;
	}

	public void setPositionXPixel(int positionXPixel) {
		this.positionXPixel = positionXPixel;
	}

	public int getPositionYPixel() {
		return positionYPixel;
	}

	public void setPositionYPixel(int positionYPixel) {
		this.positionYPixel = positionYPixel;
	}
}
