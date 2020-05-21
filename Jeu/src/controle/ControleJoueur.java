package controle;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Jeu.Carte;
import Jeu.Entité;
import Jeu.Joueur;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class ControleJoueur extends ControleEntite implements EventHandler<KeyEvent>,Observer,Runnable{
	
	
	private Joueur joueur;
	private KeyCode lastDirection=KeyCode.DOWN;
	private int distMinBordEcranX=50;
	private int distMinBordEcranY=50;	
	private int speed=10;
	

	public ControleJoueur(String feuilleDeSpriteEntite,Carte carte, Joueur joueur, GraphicsContext gc,int hauteurPixelEntite,int largeurPixelEntite){
		super(feuilleDeSpriteEntite,carte,gc,(Entité) joueur,hauteurPixelEntite,largeurPixelEntite);
		this.joueur = joueur;
		afficheCarte();
		afficheJoueur(KeyCode.DOWN,0);
	}
	
	
	//gestion des input clavier pour le joueur
	public void handle(KeyEvent event){
		//si on appuye sur une touche
		if (event.getEventType()==KeyEvent.KEY_PRESSED) {
			switch(event.getCode()) {
			case UP:
				avanceB=false;
				avanceH=true;
				this.lastDirection=KeyCode.UP;
				break;
			case DOWN:
				avanceH=false;
				avanceB=true;
				this.lastDirection=KeyCode.DOWN;
				break;
			case LEFT:
				avanceD=false;
				avanceG=true;
				this.lastDirection=KeyCode.LEFT;
				break;
			case RIGHT:
				avanceG=false;
				avanceD=true;
				this.lastDirection=KeyCode.RIGHT;
				break;
			case S:
				vitesse=speed;
				break;
			default:
				break;
			
			}
		}
		//si on relache la touche
		if (event.getEventType()==KeyEvent.KEY_RELEASED) {
			switch (event.getCode()) {
				case UP:
					avanceH=false;
					break;
				case DOWN:
					avanceB=false;
					break;
				case LEFT:
					avanceG=false;
					break;
				case RIGHT:
					avanceD=false;
					break;
				case S:
					vitesse=5;
				default:
					break;
		
			}
			if (!(this.avanceG||this.avanceD||this.avanceH||this.avanceB)) {
				afficheCarte();
				afficheJoueur(this.lastDirection,0);
			}
		}
	}
	
	
	public void deplacer(KeyCode kc) {

		switch (kc) {
		case UP:
			if (!detecteCollisionsMouvement(0,-vitesse) && this.getPositionYPixel()-this.distMinBordEcranY-vitesse>0) {this.changerPositionPixel(0, -vitesse);}
			if(this.carte.getFenetreEcran().getPosYPixelEcran()+this.getPositionYPixel()+this.carte.getHauteurCasePixel()/2<(this.joueur.getPositionY())*this.carte.getHauteurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY()-1, this.joueur.getPositionX());
			}
			break;
		case DOWN:
			if (!detecteCollisionsMouvement(0,vitesse) && this.getPositionYPixel()+this.distMinBordEcranY+vitesse<this.carte.getFenetreEcran().getHauteurPixelEcran()) {this.changerPositionPixel(0, vitesse);}
			if(this.carte.getFenetreEcran().getPosYPixelEcran()+this.getPositionYPixel()+this.carte.getHauteurCasePixel()/2>(this.joueur.getPositionY()+1)*this.carte.getHauteurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY()+1, this.joueur.getPositionX());
			}
			break;
		case LEFT:
			if (!detecteCollisionsMouvement(-vitesse,0) && this.getPositionXPixel()-this.distMinBordEcranX-vitesse>0) {this.changerPositionPixel(-vitesse, 0);}
			if(this.carte.getFenetreEcran().getPosXPixelEcran()+this.getPositionXPixel()+this.carte.getLargeurCasePixel()/2<(this.joueur.getPositionX())*this.carte.getLargeurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY(), this.joueur.getPositionX()-1);
			}
			break;
		case RIGHT:
			if (!detecteCollisionsMouvement(vitesse,0) && this.getPositionXPixel()+this.distMinBordEcranX+vitesse<this.carte.getFenetreEcran().getLargeurPixelEcran()) {this.changerPositionPixel(vitesse, 0);}
			if(this.carte.getFenetreEcran().getPosXPixelEcran()+this.getPositionXPixel()+this.carte.getLargeurCasePixel()/2>(this.joueur.getPositionX()+1)*this.carte.getLargeurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY(), this.joueur.getPositionX()+1);
			}
			break;
		default:
			break;
		}
		System.out.println("je suis en case :"+this.joueur.getPositionY()+" ,"+this.joueur.getPositionX());
		System.out.println("Ma nouvelle position est "+this.getPositionXPixel()+","+this.getPositionYPixel());
	}
	
	
	public void afficheJoueur(KeyCode keycode,int bouge) {
		//l'affichage des entites est variable pour chaque feuille de Sprite qui lui correspond
		indiceSprite=(indiceSprite+1);//on change l'image affichée par celle qui suit dans la feuille
		switch(keycode) {
		//pour le joueur qui se déplace dans plusieurs directions il faut adapter l'image au sens du mouvement
		case RIGHT:
			gc.drawImage(this.feuilleDeSpriteEntite,(((indiceSprite%10)*bouge+4)%10)*this.largeurPixelEntite,925,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel()+this.carte.getHauteurCasePixel()-this.hauteurPixelEntite/this.facteurTaille,this.largeurPixelEntite/this.facteurTaille,this.hauteurPixelEntite/this.facteurTaille);
			break;
		case LEFT:
			gc.drawImage(this.feuilleDeSpriteEntite,(indiceSprite%10)*bouge*this.largeurPixelEntite,660,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel()+this.carte.getHauteurCasePixel()-this.hauteurPixelEntite/this.facteurTaille,this.largeurPixelEntite/this.facteurTaille,this.hauteurPixelEntite/this.facteurTaille);
			break;
		case UP:
			gc.drawImage(this.feuilleDeSpriteEntite,(indiceSprite%10)*bouge*this.largeurPixelEntite,785,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel()+this.carte.getHauteurCasePixel()-this.hauteurPixelEntite/this.facteurTaille,this.largeurPixelEntite/this.facteurTaille,this.hauteurPixelEntite/this.facteurTaille);
			break;
		case DOWN:
			gc.drawImage(this.feuilleDeSpriteEntite,(indiceSprite%10)*bouge*this.largeurPixelEntite,530,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel()+this.carte.getHauteurCasePixel()-this.hauteurPixelEntite/this.facteurTaille,this.largeurPixelEntite/this.facteurTaille,this.hauteurPixelEntite/this.facteurTaille);
			break;
		default:
			break;
		}

	}

	public void update(Observable o, Object arg) {
	}
	
	public void run() {
		//le thread qui execute les mouvements en fonction de l'état des variables : avanceH,avanceB,avanceG,avanceD
		while(true) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(this.avanceD) {
				deplacer(KeyCode.RIGHT);
				afficheCarte();
				afficheJoueur(KeyCode.RIGHT,1);
			}
			if(this.avanceH) {
				deplacer(KeyCode.UP);
				afficheCarte();
				afficheJoueur(KeyCode.UP,1);
			}
			if(this.avanceB) {
				deplacer(KeyCode.DOWN);
				afficheCarte();
				afficheJoueur(KeyCode.DOWN,1);
			}
			if(this.avanceG) {
				deplacer(KeyCode.LEFT);
				afficheCarte();
				afficheJoueur(KeyCode.LEFT,1);
			}
			
		}
	}
	
	
	public void afficheCarte() {
		ArrayList<ArrayList<Image>> imagesCasesCarte = carte.getImagesCasesCarte();
		for (int i = 0 ; i < imagesCasesCarte.size();i++) {
			for (int j = 0 ; j < imagesCasesCarte.get(i).size();j++) {
				gc.drawImage(imagesCasesCarte.get(i).get(j), this.carte.getHauteurCasePixel()*j, this.carte.getLargeurCasePixel()*i);
			}
		}
	}
}
