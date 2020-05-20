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
	private int distMinBordEcranX=20;
	private int distMinBordEcranY=20;	
	

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
			//si le perso n'atteint pas le bord supérieur de l'écran avec le prochain mouvement demandé
			if (this.getPositionYPixel()-vitesse-this.distMinBordEcranY>0) {
				
				//si le perso veut monter et qu'il est décalé sur la droite de sa case. Il faut vérifier que la case au dessus et celle au dessus à droite soient vide toutes les deux
				if (this.carte.getLargeurCasePixel()*(this.joueur.getPositionX()+1)-this.carte.getFenetreEcran().getPosXPixelEcran()<this.getPositionXPixel()+this.carte.getLargeurCasePixel()-this.paddingX) {
					if( this.carte.getCase(this.joueur.getPositionY()-1, this.joueur.getPositionX()).getContenu()==Case.VIDE && this.carte.getCase(this.joueur.getPositionY()-1, this.joueur.getPositionX()+1).getContenu()==Case.VIDE) {
						this.changerPositionPixel(0, -vitesse);
					}
				}
				
				//si le perso veut monter et qu'il est décalé sur la gauche de sa case. Il faut vérifier que la case au dessus et celle au dessus à gauche soient vide toutes les deux
				else if (this.carte.getLargeurCasePixel()*(this.joueur.getPositionX())-this.carte.getFenetreEcran().getPosXPixelEcran()>this.getPositionXPixel()+this.paddingX) {
					if( this.carte.getCase(this.joueur.getPositionY()-1, this.joueur.getPositionX()).getContenu()==Case.VIDE && this.carte.getCase(this.joueur.getPositionY()-1, this.joueur.getPositionX()-1).getContenu()==Case.VIDE) {
						this.changerPositionPixel(0, -vitesse);
					}
				}
				//si le perso veut monter et qu'il est exactement dans l'axe verticale qui passe par sa case, on regarde si la case au dessus est vide pour avancer
				else {
					if (this.carte.getCase(this.joueur.getPositionY()-1, this.joueur.getPositionX()).getContenu()==Case.VIDE)	{
						this.changerPositionPixel(0, -vitesse);
					}
				}
			}
			//si le milieu du carré mobile du joueur a dépassé celui le bord de la case du dessus, alors il est dans cette nouvelle case
			if(this.carte.getFenetreEcran().getPosYPixelEcran()+this.getPositionYPixel()+this.carte.getHauteurCasePixel()/2<(this.joueur.getPositionY())*this.carte.getHauteurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY()-1, this.joueur.getPositionX());
			}
			break;
		case DOWN:
			if (this.getPositionYPixel()+vitesse+this.distMinBordEcranY<this.carte.getFenetreEcran().getLargeurPixelEcran()) {
				if (this.carte.getLargeurCasePixel()*(this.joueur.getPositionX()+1)-this.carte.getFenetreEcran().getPosXPixelEcran()<this.getPositionXPixel()+this.carte.getLargeurCasePixel()-this.paddingX) {
					if( this.carte.getCase(this.joueur.getPositionY()+1, this.joueur.getPositionX()).getContenu()==Case.VIDE && this.carte.getCase(this.joueur.getPositionY()+1, this.joueur.getPositionX()+1).getContenu()==Case.VIDE) {
						this.changerPositionPixel(0, vitesse);
					}
				}
				else if (this.carte.getLargeurCasePixel()*(this.joueur.getPositionX())-this.carte.getFenetreEcran().getPosXPixelEcran()>this.getPositionXPixel()+this.paddingX) {
					if( this.carte.getCase(this.joueur.getPositionY()+1, this.joueur.getPositionX()).getContenu()==Case.VIDE && this.carte.getCase(this.joueur.getPositionY()+1, this.joueur.getPositionX()-1).getContenu()==Case.VIDE) {
						this.changerPositionPixel(0, vitesse);
					}
				}
				else {
					if (this.carte.getCase(this.joueur.getPositionY()+1, this.joueur.getPositionX()).getContenu()==Case.VIDE)	{
						this.changerPositionPixel(0, vitesse);
					}
				}
			}
			if(this.carte.getFenetreEcran().getPosYPixelEcran()+this.getPositionYPixel()+this.carte.getHauteurCasePixel()/2>(this.joueur.getPositionY()+1)*this.carte.getHauteurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY()+1, this.joueur.getPositionX());
			}
			break;
		case LEFT:
			//si on est encore à droite du bord de l'écran
			if (this.getPositionXPixel()-vitesse-this.distMinBordEcranY>0) {
				//si on ne rentre pas en collision avec la case de gauche
				if (this.getPositionXPixel()+this.carte.getFenetreEcran().getPosXPixelEcran()-vitesse-(this.joueur.getPositionX()*this.carte.getLargeurCasePixel())>0)	{
					this.changerPositionPixel(-vitesse, 0);
				}else {
					//si le personnage veut aller à gauche et qu'il est décalé sur le haut de sa case, on vérifie que la case de gauche et celle au dessus à gauche sont toutes les deux vides
					if (this.joueur.getPositionY()*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran()>this.getPositionYPixel()+this.paddingY) {
						System.out.println("coucou1");
						if( this.carte.getCase(this.joueur.getPositionY(), this.joueur.getPositionX()-1).getContenu()==Case.VIDE && this.carte.getCase(this.joueur.getPositionY()-1, this.joueur.getPositionX()-1).getContenu()==Case.VIDE) {
							this.changerPositionPixel(-vitesse, 0);
						}
					}
					//si le personnage veut aller à gauche et qu'il est décalé sur le bas de sa case, on vérifie que la case de gauche et celle en dessous à gauche sont toutes les deux vides
					else if ((this.joueur.getPositionY()+1)*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran()>this.getPositionYPixel()+this.carte.getHauteurCasePixel()+this.paddingY ){
						System.out.println("coucou2");
						if( this.carte.getCase(this.joueur.getPositionY()+1, this.joueur.getPositionX()-1).getContenu()==Case.VIDE && this.carte.getCase(this.joueur.getPositionY(), this.joueur.getPositionX()-1).getContenu()==Case.VIDE) {
							this.changerPositionPixel(-vitesse, 0);
						}
					}
					//sinon le perso est aligné dans sa case et on regarde simplement si la case de gauche est vide
					else {
						System.out.println("coucou3");
						if (  this.carte.getCase(this.joueur.getPositionY(), this.joueur.getPositionX()-1).getContenu()==Case.VIDE){
								this.changerPositionPixel(-vitesse, 0);
						}
					}
				}
			}
			if(this.carte.getFenetreEcran().getPosXPixelEcran()+this.getPositionXPixel()+this.carte.getLargeurCasePixel()/2<(this.joueur.getPositionX())*this.carte.getLargeurCasePixel()){
				this.carte.mettreEntite(this.joueur, this.joueur.getPositionY(), this.joueur.getPositionX()-1);
			}
			break;
		case RIGHT:
			//si on est encore à gauche du bord de l'écran
			if (this.getPositionXPixel()+vitesse+this.distMinBordEcranY<this.carte.getFenetreEcran().getLargeurPixelEcran()) {
				//sin on ne rentre pas en collision avec la case de droite
				if (this.getPositionXPixel()+this.carte.getFenetreEcran().getPosXPixelEcran()+this.carte.getLargeurCasePixel()+vitesse-((this.joueur.getPositionX()+1)*this.carte.getLargeurCasePixel())<1)	{
					this.changerPositionPixel(vitesse, 0);
				} else {
					//si le personnage veut aller à droite et qu'il est décalé sur le haut de sa case, on vérifie que la case de gauche et celle au dessus à droite sont toutes les deux vides
					if (this.joueur.getPositionY()*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran()>this.getPositionYPixel()+this.paddingY) {
						if( this.carte.getCase(this.joueur.getPositionY(), this.joueur.getPositionX()+1).getContenu()==Case.VIDE && this.carte.getCase(this.joueur.getPositionY()-1, this.joueur.getPositionX()+1).getContenu()==Case.VIDE) {
							this.changerPositionPixel(vitesse, 0);
						}
					}
					//si le personnage veut aller à droite et qu'il est décalé sur le bas de sa case, on vérifie que la case de gauche et celle en dessous à droite sont toutes les deux vides
					else if ((this.joueur.getPositionY()+1)*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran()>this.getPositionYPixel()+this.carte.getHauteurCasePixel()+this.paddingY ){
						if( this.carte.getCase(this.joueur.getPositionY()+1, this.joueur.getPositionX()+1).getContenu()==Case.VIDE && this.carte.getCase(this.joueur.getPositionY(), this.joueur.getPositionX()+1).getContenu()==Case.VIDE) {
							this.changerPositionPixel(vitesse, 0);
						}
					}
					//sinon le perso est aligné dans sa case et on regarde simplement si la case de droite est vide
					else {
						if (this.carte.getCase(this.joueur.getPositionY(), this.joueur.getPositionX()+1).getContenu()==Case.VIDE)	{
							this.changerPositionPixel(vitesse, 0);
						}
					}
				}
			}
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
			gc.drawImage(this.feuilleDeSpriteEntite,indiceSprite*this.largeurPixelEntite,925,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
			gc.fillRect(this.getPositionXPixel(), this.getPositionYPixel(), 50, 50);
			break;
		case LEFT:
			gc.drawImage(this.feuilleDeSpriteEntite,indiceSprite*this.largeurPixelEntite,660,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
			gc.fillRect(this.getPositionXPixel(), this.getPositionYPixel(), 50, 50);
			break;
		case UP:
			gc.drawImage(this.feuilleDeSpriteEntite,indiceSprite*this.largeurPixelEntite,785,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
			gc.fillRect(this.getPositionXPixel(), this.getPositionYPixel(), 50, 50);
			break;
		case DOWN:
			gc.drawImage(this.feuilleDeSpriteEntite,indiceSprite*this.largeurPixelEntite,530,this.largeurPixelEntite,this.hauteurPixelEntite,this.getPositionXPixel(),this.getPositionYPixel(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
			gc.fillRect(this.getPositionXPixel(), this.getPositionYPixel(), 50, 50);
			break;
		default:
			break;
		}

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
