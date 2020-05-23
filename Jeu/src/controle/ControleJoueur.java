package controle;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Jeu.Arme;
import Jeu.Carte;
import Jeu.Ennemi;
import Jeu.Entité;
import Jeu.Item;
import Jeu.Joueur;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class ControleJoueur extends ControleEntite implements EventHandler<KeyEvent>,Observer,Runnable{
	
	
	private Joueur joueur;
	private boolean attaque = false;
	private KeyCode lastDirection=KeyCode.DOWN;
	private int distMinBordEcranX=20;
	private int distMinBordEcranY=20;	
	private int speed=10;
	private int distance = 0;
	

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
			case SPACE:
				attaque=true;
				this.distance = this.attaque();
				break;
			case Z:
				avanceB=false;
				avanceH=true;
				this.lastDirection=KeyCode.UP;
				break;
			case S:
				avanceH=false;
				avanceB=true;
				this.lastDirection=KeyCode.DOWN;
				break;
			case Q:
				avanceD=false;
				avanceG=true;
				this.lastDirection=KeyCode.LEFT;
				break;
			case D:
				avanceG=false;
				avanceD=true;
				this.lastDirection=KeyCode.RIGHT;
				break;
			case SHIFT:
				vitesse=speed;
				break;
			default:
				break;
			
			}
		}
		//si on relache la touche
		if (event.getEventType()==KeyEvent.KEY_RELEASED) {
			switch (event.getCode()) {
				case SPACE:
					attaque=false;
					break;
				case Z:
					avanceH=false;
					break;
				case S:
					avanceB=false;
					break;
				case Q:
					avanceG=false;
					break;
				case D:
					avanceD=false;
					break;
				case SHIFT:
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
			if (!detecteCollisionsMouvement(0,vitesse) && this.getPositionYPixel()+this.carte.getHauteurCasePixel()+this.distMinBordEcranY+vitesse<this.carte.getFenetreEcran().getHauteurPixelEcran()) {this.changerPositionPixel(0, vitesse);}
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
			if (!detecteCollisionsMouvement(vitesse,0) && this.getPositionXPixel()+this.carte.getLargeurCasePixel()+this.distMinBordEcranX+vitesse<this.carte.getFenetreEcran().getLargeurPixelEcran()) {this.changerPositionPixel(vitesse, 0);}
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
	
	public int attaque() {
		Item itemEnMain = this.joueur.getEnMain();
			int portee=0;
			if (itemEnMain instanceof Arme) {portee= ((Arme) itemEnMain).getPortée();}
			int distance = 0;
			while (distance <= portee) {
				switch(this.lastDirection) {
				case UP:
					if (this.joueur.getPositionY()-1-distance>-1) {
					Object objet1 = this.carte.getCase(this.joueur.getPositionY()-1-distance, this.joueur.getPositionX()).getContenu();
					if (objet1 instanceof Ennemi) {
						this.joueur.Utiliser((Entité) objet1);
						return distance;
					}
					else {distance++;}
					}
					else {return -1;}
					break;
				case DOWN:
					if (this.joueur.getPositionY()+1+distance<this.carte.getImagesCasesCarte().size()) {
					Object objet2 = this.carte.getCase(this.joueur.getPositionY()+1+distance, this.joueur.getPositionX()).getContenu();
					if (objet2 instanceof Ennemi) {
						this.joueur.Utiliser((Entité) objet2);
						return distance;
					}
					else {distance++;}
					}
					else {return -1;}
					break;
				case RIGHT:
					if (this.joueur.getPositionX()+1+distance<this.carte.getImagesCasesCarte().get(this.joueur.getPositionY()).size()) {
					Object objet3 = this.carte.getCase(this.joueur.getPositionY(), this.joueur.getPositionX()+1+distance).getContenu();
					if (objet3 instanceof Ennemi) {
						this.joueur.Utiliser((Entité) objet3);
						return distance; 
					}
					else {distance++;}
					}
					else {return -1;}
					break;
				case LEFT:
					if (this.joueur.getPositionX()-1-distance>-1) {
					Object objet4 = this.carte.getCase(this.joueur.getPositionY(), this.joueur.getPositionX()-1-distance).getContenu();
					if (objet4 instanceof Ennemi) {
						this.joueur.Utiliser((Entité) objet4);
						return distance; 
					}
					else {distance++;}
					}else  {return -1;}
					break;
				default:
					break;
				}
			}
		return -1;
	}
	
	public void afficheAttaque() {
		if (this.distance>-1) {
		switch (this.lastDirection) {
		case UP:
			gc.drawImage(this.feuilleDeSpriteEntite,879,1402,237,222,(this.joueur.getPositionX())*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran(),(this.joueur.getPositionY()-1-distance)*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
			break;
		case DOWN:
			gc.drawImage(this.feuilleDeSpriteEntite,879,1402,237,222,(this.joueur.getPositionX())*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran(),(this.joueur.getPositionY()+1+distance)*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
			break;
		case LEFT:
			gc.drawImage(this.feuilleDeSpriteEntite,879,1402,237,222,(this.joueur.getPositionX()-1-distance)*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran(),(this.joueur.getPositionY())*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
			break;
		case RIGHT:
			gc.drawImage(this.feuilleDeSpriteEntite,879,1402,237,222,(this.joueur.getPositionX()+1+distance)*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran()+20,(this.joueur.getPositionY())*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
			break;
		default:
			break;
		}
		}
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
			if (this.attaque) {
				afficheCarte();
				afficheJoueur(this.lastDirection,0);
				afficheAttaque();
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
