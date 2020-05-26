package controle;

import java.util.Observable;

import Jeu.Carte;
import Jeu.Entité;
import Jeu.EtatEntité;
import Jeu.Joueur;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class ControleJoueur extends ControleEntite implements EventHandler<KeyEvent>{
	
	
	private Joueur joueur;
	private int distMinBordEcranX=150;
	private int distMinBordEcranY=200;

	public ControleJoueur(String feuilleDeSpriteEntite,Carte carte, Joueur joueur, GraphicsContext gc,int hauteurPixelEntite,int largeurPixelEntite){
		super(feuilleDeSpriteEntite,carte,gc,(Entité) joueur,hauteurPixelEntite,largeurPixelEntite);
		this.setJoueur(joueur);
	}
	
	
	//gestion des input clavier pour le joueur
	public void handle(KeyEvent event){
		//si on appuye sur une touche
		if (event.getEventType()==KeyEvent.KEY_PRESSED) {
			switch(event.getCode()) {
			case SPACE:
				if (!this.attaqueEnCours) {
					this.setBouge(0);;
					this.attaqueEnCours=true;
					avanceB=false;
					avanceD=false;
					avanceG=false;
					avanceH=false;
					this.setDistance(this.attaque());
					(new AttaqueService(this)).start();
				}
				break;
			case Z:
				this.setBouge(1);
				avanceB=false;
				avanceH=true;
				this.setLastDirection(KeyCode.UP);
				break;
			case S:
				this.setBouge(1);
				avanceH=false;
				avanceB=true;
				this.setLastDirection(KeyCode.DOWN);
				break;
			case Q:
				this.setBouge(1);
				avanceD=false;
				avanceG=true;
				this.setLastDirection(KeyCode.LEFT);
				break;
			case D:
				this.setBouge(1);
				avanceG=false;
				avanceD=true;
				this.setLastDirection(KeyCode.RIGHT);
				break;
			case SHIFT:
				this.setVitesse(this.getSpeed());
				break;
			default:
				break;
			
			}
		}
		//si on relache la touche
		if (event.getEventType()==KeyEvent.KEY_RELEASED) {
			switch (event.getCode()) {
				case SPACE:
					this.setBouge(1);
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
					this.setVitesse(5);
				default:
					break;
		
			}
			if (!(this.avanceG||this.avanceD||this.avanceH||this.avanceB)) {
				this.setBouge(0);
			}
		}
	}
	
	public void deplacerJoueur(KeyCode kc) {
		deplacer(kc,this.distMinBordEcranX,this.distMinBordEcranY,false);
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
	
	public static class RunService extends Service<Integer>{

		private ControleJoueur ctlJoueur;
		
		public RunService(ControleJoueur ctlJoueur) {
			this.ctlJoueur=ctlJoueur;
		}
		@Override
		protected Task<Integer> createTask() {
			// TODO Auto-generated method stub
			return new Task<Integer>() {
				
				@Override
				protected Integer call() throws Exception {
					// TODO Auto-generated method stub
					while(ctlJoueur.getJoueur().getEtat()!=EtatEntité.Mort) {
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(ctlJoueur.avanceD) {
							ctlJoueur.deplacerJoueur(KeyCode.RIGHT);
						}
						if(ctlJoueur.avanceH) {
							ctlJoueur.deplacerJoueur(KeyCode.UP);
						}
						if(ctlJoueur.avanceB) {
							ctlJoueur.deplacerJoueur(KeyCode.DOWN);
						}
						if(ctlJoueur.avanceG) {
							ctlJoueur.deplacerJoueur(KeyCode.LEFT);
						}
					}
					return null;
				}
				
			};
		}
		
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}
