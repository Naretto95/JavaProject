package controle;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Jeu.Carte;
import Jeu.Case;
import Jeu.Entité;
import Jeu.Joueur;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class ControleJoueur extends ControleEntite implements EventHandler<KeyEvent>,Observer,Runnable{
	
	
	private Joueur joueur;
	private int distMinBordEcranX=50;
	private int distMinBordEcranY=50;	
	

	public ControleJoueur(String feuilleDeSpriteEntite,Carte carte, Joueur joueur, GraphicsContext gc,int hauteurPixelEntite,int largeurPixelEntite){
		super(feuilleDeSpriteEntite,carte,gc,(Entité) joueur,hauteurPixelEntite,largeurPixelEntite);
		this.joueur = joueur;
		afficheCarte();
		afficheJoueur(KeyCode.RIGHT);
	}
	
	
	//gestion des input clavier pour le joueur 
	public void handle(KeyEvent event){
		//si on appuye sur une touche
		if (event.getEventType()==KeyEvent.KEY_PRESSED) {
			switch(event.getCode()) {
			case UP:
				avanceB=false;
				avanceH=true;
				break;
			case DOWN:
				avanceH=false;
				avanceB=true;
				break;
			case LEFT:
				avanceD=false;
				avanceG=true;
				break;
			case RIGHT:
				avanceG=false;
				avanceD=true;
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
				default:
					break;
		
			}
		}
	}
	
	
	public void deplacer(KeyCode kc) {

		switch (kc) {
		case UP:
			if (!detecteCollisionsMouvement(0,-vitesse)) {this.changerPositionPixel(0, -vitesse);}
			if(this.carte.getFenetreEcran().getPosYPixelEcran()+this.getPositionYPixel()+this.carte.getHauteurCasePixel()/2<(this.joueur.getPositionY())*this.carte.getHauteurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY()-1, this.joueur.getPositionX());
			}
			break;
		case DOWN:
			if (!detecteCollisionsMouvement(0,vitesse)) {this.changerPositionPixel(0, vitesse);}
			if(this.carte.getFenetreEcran().getPosYPixelEcran()+this.getPositionYPixel()+this.carte.getHauteurCasePixel()/2>(this.joueur.getPositionY()+1)*this.carte.getHauteurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY()+1, this.joueur.getPositionX());
			}
			break;
		case LEFT:
			if (!detecteCollisionsMouvement(-vitesse,0)) {this.changerPositionPixel(-vitesse, 0);}
			if(this.carte.getFenetreEcran().getPosXPixelEcran()+this.getPositionXPixel()+this.carte.getLargeurCasePixel()/2<(this.joueur.getPositionX())*this.carte.getLargeurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY(), this.joueur.getPositionX()-1);
			}
			break;
		case RIGHT:
			if (!detecteCollisionsMouvement(vitesse,0)) {this.changerPositionPixel(vitesse, 0);}
			if(this.carte.getFenetreEcran().getPosXPixelEcran()+this.getPositionXPixel()+this.carte.getLargeurCasePixel()/2>(this.joueur.getPositionX()+1)*this.carte.getLargeurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY(), this.joueur.getPositionX()+1);
			}
			break;
		default:
			break;
		}
		System.out.println("je suis en case :"+this.joueur.getPositionY()+" ,"+this.joueur.getPositionX());
	}
	
	
	public void afficheJoueur(KeyCode keycode) {
		//l'affichage des entites est variable pour chaque feuille de Sprite qui lui correspond
		indiceSprite=(indiceSprite+1)%10;//on change l'image affichée par celle qui suit dans la feuille
		switch(keycode) {
		//pour le joueur qui se déplace dans plusieurs directions il faut adapter l'image au sens du mouvement
		case RIGHT:
			gc.drawImage(this.feuilleDeSpriteEntite,indiceSprite*this.largeurPixelEntite,925,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel()+this.carte.getHauteurCasePixel()-this.hauteurPixelEntite,this.largeurPixelEntite,this.hauteurPixelEntite);
			break;
		case LEFT:
			gc.drawImage(this.feuilleDeSpriteEntite,indiceSprite*this.largeurPixelEntite,660,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel()+this.carte.getHauteurCasePixel()-this.hauteurPixelEntite,this.largeurPixelEntite,this.hauteurPixelEntite);
			break;
		case UP:
			gc.drawImage(this.feuilleDeSpriteEntite,indiceSprite*this.largeurPixelEntite,785,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel()+this.carte.getHauteurCasePixel()-this.hauteurPixelEntite,this.largeurPixelEntite,this.hauteurPixelEntite);
			break;
		case DOWN:
			gc.drawImage(this.feuilleDeSpriteEntite,indiceSprite*this.largeurPixelEntite,530,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel()+this.carte.getHauteurCasePixel()-this.hauteurPixelEntite,this.largeurPixelEntite,this.hauteurPixelEntite);
			break;
		default:
			break;
		}

	}
	
	public boolean detecteCollision(int rect1x,int rect1y, int rect1w, int rect1h,int rect2x,int rect2y, int rect2w, int rect2h) {
		return ((rect1x +paddingX< rect2x + rect2w) && (rect1x-paddingX + rect1w > rect2x) && ( rect1y+paddingY < rect2y + rect2h) && ( rect1h + rect1y > rect2y));
	}
	public boolean detecteCollisionCaseMouvement(int i, int j,int deltaX, int deltaY) {
		if(i>=0 && j>=0 && i<this.carte.getImagesCasesCarte().size() && j<this.carte.getImagesCasesCarte().get(i).size()) {
			return detecteCollision(this.getPositionXPixel()+deltaX,this.getPositionYPixel()+deltaY,this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel(),j*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran(),i*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel()) && (this.carte.getCase(i, j).getContenu()!=Case.VIDE);
		}
		return true;
	}
	public boolean detecteCollisionsMouvement(int deltaX,int deltaY) {
		//soit deltax est nul soit deltay est nul
		if (deltaX>0) {
			return (detecteCollisionCaseMouvement(this.joueur.getPositionY()-1,this.joueur.getPositionX()+1,deltaX,deltaY) ||detecteCollisionCaseMouvement(this.joueur.getPositionY(),this.joueur.getPositionX()+1,deltaX,deltaY)||detecteCollisionCaseMouvement(this.joueur.getPositionY()+1,this.joueur.getPositionX()+1,deltaX,deltaY));
		}
		if (deltaX<0) {
			return (detecteCollisionCaseMouvement(this.joueur.getPositionY()-1,this.joueur.getPositionX()-1,deltaX,deltaY) ||detecteCollisionCaseMouvement(this.joueur.getPositionY(),this.joueur.getPositionX()-1,deltaX,deltaY)||detecteCollisionCaseMouvement(this.joueur.getPositionY()+1,this.joueur.getPositionX()-1,deltaX,deltaY));
		}
		if (deltaY>0) {
			return (detecteCollisionCaseMouvement(this.joueur.getPositionY()+1,this.joueur.getPositionX()-1,deltaX,deltaY) ||detecteCollisionCaseMouvement(this.joueur.getPositionY()+1,this.joueur.getPositionX(),deltaX,deltaY)||detecteCollisionCaseMouvement(this.joueur.getPositionY()+1,this.joueur.getPositionX()+1,deltaX,deltaY));
		}
		if (deltaY<0) {
			return (detecteCollisionCaseMouvement(this.joueur.getPositionY()-1,this.joueur.getPositionX()-1,deltaX,deltaY) ||detecteCollisionCaseMouvement(this.joueur.getPositionY()-1,this.joueur.getPositionX(),deltaX,deltaY)||detecteCollisionCaseMouvement(this.joueur.getPositionY()-1,this.joueur.getPositionX()+1,deltaX,deltaY));
		}
		return true;
	}
	
	public void changerPositionPixel(int deltaXPixel, int deltaYPixel) {
		this.setPositionXPixel(this.getPositionXPixel()+deltaXPixel);
		this.setPositionYPixel(this.getPositionYPixel()+deltaYPixel);
		System.out.println("Ma nouvelle position est "+this.getPositionXPixel()+","+this.getPositionYPixel());
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
				afficheJoueur(KeyCode.RIGHT);
			}
			if(this.avanceH) {
				deplacer(KeyCode.UP);
				afficheCarte();
				afficheJoueur(KeyCode.UP);
			}
			if(this.avanceB) {
				deplacer(KeyCode.DOWN);
				afficheCarte();
				afficheJoueur(KeyCode.DOWN);
			}
			if(this.avanceG) {
				deplacer(KeyCode.LEFT);
				afficheCarte();
				afficheJoueur(KeyCode.LEFT);
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
