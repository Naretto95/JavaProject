package controle;

import java.io.File;
import java.io.Serializable;
import java.util.Observable;
import Jeu.Arme;
import Jeu.Carte;
import Jeu.Carte.Porte;
import Jeu.Case;
import Jeu.Entité;
import Jeu.Item;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public abstract class ControleEntite extends Observable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Integer A_BOUGE = new Integer(10);
	public static Image imageAttaque = new Image ("file:imagesitems/attaque.png");
	
	private Media sonEpee;
	private MediaPlayer mediaPlayer;
	protected Entité entite;
	protected Carte carte;
	protected GraphicsContext gc;
	protected Image feuilleDeSpriteEntite;
	
	protected boolean avanceD = false;
	protected boolean avanceG = false;
	protected boolean avanceH = false;
	protected boolean avanceB = false;
	protected boolean attaqueEnCours = false;
	protected int indiceSprite=0;
	private KeyCode lastDirection=KeyCode.DOWN;	
	private int speed=10;
	private int distance = 0;
	private int bouge=0;

	protected int positionXPixel;
	protected int positionYPixel;
	private int vitesse=5;
	protected int hauteurPixelEntite;
	protected int largeurPixelEntite;
	protected int deltaXHitBox=0;
	protected int deltaYHitBox=0;
	protected int paddingX;
	protected int paddingY;
	protected int facteurTaille=2;

	
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
		this.paddingX=5;
		this.paddingY=20;
		try {
			File file = new File("musiques/song3.mp3");
			this.sonEpee = new Media(file.toURI().toString());
	        this.mediaPlayer = new MediaPlayer(sonEpee);
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
	
	public boolean detecteCollision(int rect1x,int rect1y, int rect1w, int rect1h,int rect2x,int rect2y, int rect2w, int rect2h) {
		return ((rect1x +paddingX< rect2x + rect2w) && (rect1x-paddingX + rect1w > rect2x) && ( rect1y+paddingY < rect2y + rect2h) && ( rect1h + rect1y > rect2y));
	}
	
	public boolean detecteCollisionCaseMouvement(int i, int j,int deltaX, int deltaY) {
		if(i>=0 && j>=0 && i<this.carte.getImagesCasesCarte().size() && j<this.carte.getImagesCasesCarte().get(i).size()) {
			Case caseR = this.carte.getCase(i, j);
			if (caseR.getContenu() instanceof Porte) {this.setChanged();this.notifyObservers(caseR.getContenu());}
			return detecteCollision(this.getPositionXPixel()+deltaX,this.getPositionYPixel()+deltaY,this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel(),j*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran(),i*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel()) && (caseR.getContenu()!=Case.VIDE);
		}
		return true;
	}
	
	public boolean detecteCollisionsMouvement(int deltaX,int deltaY) {
		//soit deltax est nul soit deltay est nul
		if (deltaX>0) {
			return (detecteCollisionCaseMouvement(this.entite.getPositionY()-1,this.entite.getPositionX()+1,deltaX,deltaY) ||detecteCollisionCaseMouvement(this.entite.getPositionY(),this.entite.getPositionX()+1,deltaX,deltaY)||detecteCollisionCaseMouvement(this.entite.getPositionY()+1,this.entite.getPositionX()+1,deltaX,deltaY));
		}
		if (deltaX<0) {
			return (detecteCollisionCaseMouvement(this.entite.getPositionY()-1,this.entite.getPositionX()-1,deltaX,deltaY) ||detecteCollisionCaseMouvement(this.entite.getPositionY(),this.entite.getPositionX()-1,deltaX,deltaY)||detecteCollisionCaseMouvement(this.entite.getPositionY()+1,this.entite.getPositionX()-1,deltaX,deltaY));
		}
		if (deltaY>0) {
			return (detecteCollisionCaseMouvement(this.entite.getPositionY()+1,this.entite.getPositionX()-1,deltaX,deltaY) ||detecteCollisionCaseMouvement(this.entite.getPositionY()+1,this.entite.getPositionX(),deltaX,deltaY)||detecteCollisionCaseMouvement(this.entite.getPositionY()+1,this.entite.getPositionX()+1,deltaX,deltaY));
		}
		if (deltaY<0) {
			return (detecteCollisionCaseMouvement(this.entite.getPositionY()-1,this.entite.getPositionX()-1,deltaX,deltaY) ||detecteCollisionCaseMouvement(this.entite.getPositionY()-1,this.entite.getPositionX(),deltaX,deltaY)||detecteCollisionCaseMouvement(this.entite.getPositionY()-1,this.entite.getPositionX()+1,deltaX,deltaY));
		}
		return true;
	}
	public int attaque() {
		if (this.mediaPlayer!=null) {this.mediaPlayer.seek(Duration.ZERO);this.mediaPlayer.play();}
		Item itemEnMain = this.getEntite().getEnMain();
			int portee=0;
			if (itemEnMain instanceof Arme) {portee= ((Arme) itemEnMain).getPortée();}
			int distance = 0;
			while (distance <= portee) {
				switch(this.lastDirection) {
				case UP:
					if (this.getEntite().getPositionY()-1-distance>-1) {
					Object objet1 = this.carte.getCase(this.getEntite().getPositionY()-1-distance, this.getEntite().getPositionX()).getContenu();
					if (objet1 instanceof Entité) {
						this.getEntite().Utiliser((Entité) objet1);
						return distance;
					}
					else {distance++;}
					}
					else {return -1;}
					break;
				case DOWN:
					if (this.getEntite().getPositionY()+1+distance<this.carte.getImagesCasesCarte().size()) {
					Object objet2 = this.carte.getCase(this.getEntite().getPositionY()+1+distance, this.getEntite().getPositionX()).getContenu();
					if (objet2 instanceof Entité) {
						this.getEntite().Utiliser((Entité) objet2);
						return distance;
					}
					else {distance++;}
					}
					else {return -1;}
					break;
				case RIGHT:
					if (this.getEntite().getPositionX()+1+distance<this.carte.getImagesCasesCarte().get(this.getEntite().getPositionY()).size()) {
					Object objet3 = this.carte.getCase(this.getEntite().getPositionY(), this.getEntite().getPositionX()+1+distance).getContenu();
					if (objet3 instanceof Entité) {
						this.getEntite().Utiliser((Entité) objet3);
						return distance; 
					}
					else {distance++;}
					}
					else {return -1;}
					break;
				case LEFT:
					if (this.getEntite().getPositionX()-1-distance>-1) {
					Object objet4 = this.carte.getCase(this.getEntite().getPositionY(), this.getEntite().getPositionX()-1-distance).getContenu();
					if (objet4 instanceof Entité) {
						this.getEntite().Utiliser((Entité) objet4);
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
	

	
	
	public void deplacer(KeyCode kc, int distMinBordEcranX, int distMinBordEcranY, boolean canMoveScreen) {

		switch (kc) {
		case UP:
			if (!detecteCollisionsMouvement(0,-this.getVitesse()) && this.getPositionYPixel()-distMinBordEcranY-this.getVitesse()>0) {this.changerPositionPixel(0, -this.getVitesse());}
			else if (!detecteCollisionsMouvement(0,-this.getVitesse()) && canMoveScreen) {this.carte.deplaceFenetre(0, -this.getVitesse());}
			if(this.carte.getFenetreEcran().getPosYPixelEcran()+this.getPositionYPixel()+this.carte.getHauteurCasePixel()/2<(this.getEntite().getPositionY())*this.carte.getHauteurCasePixel()){
				this.carte.mettreEntite(this.getEntite(), this.getEntite().getPositionY()-1, this.getEntite().getPositionX());
			}
			break;
		case DOWN:
			if (!detecteCollisionsMouvement(0,this.getVitesse()) && this.getPositionYPixel()+this.carte.getHauteurCasePixel()+distMinBordEcranY+this.getVitesse()<this.carte.getFenetreEcran().getHauteurPixelEcran()) {this.changerPositionPixel(0, this.getVitesse());}
			else if (!detecteCollisionsMouvement(0,this.getVitesse()) && canMoveScreen) {this.carte.deplaceFenetre(0, this.getVitesse());}
			if(this.carte.getFenetreEcran().getPosYPixelEcran()+this.getPositionYPixel()+this.carte.getHauteurCasePixel()/2>(this.getEntite().getPositionY()+1)*this.carte.getHauteurCasePixel()){
				this.carte.mettreEntite(this.getEntite(), this.getEntite().getPositionY()+1, this.getEntite().getPositionX());
			}
			break;
		case LEFT:
			if (!detecteCollisionsMouvement(-this.getVitesse(),0) && this.getPositionXPixel()-distMinBordEcranX-this.getVitesse()>0) {this.changerPositionPixel(-this.getVitesse(), 0);}
			else if (!detecteCollisionsMouvement(-this.getVitesse(),0) && canMoveScreen) {this.carte.deplaceFenetre(-this.getVitesse(), 0);}
			if(this.carte.getFenetreEcran().getPosXPixelEcran()+this.getPositionXPixel()+this.carte.getLargeurCasePixel()/2<(this.getEntite().getPositionX())*this.carte.getLargeurCasePixel()){
				this.carte.mettreEntite(this.getEntite(), this.getEntite().getPositionY(), this.getEntite().getPositionX()-1);
			}
			break;
		case RIGHT:
			if (!detecteCollisionsMouvement(this.getVitesse(),0) && this.getPositionXPixel()+this.carte.getLargeurCasePixel()+distMinBordEcranX+this.getVitesse()<this.carte.getFenetreEcran().getLargeurPixelEcran()) {this.changerPositionPixel(this.getVitesse(), 0);}
			else if (!detecteCollisionsMouvement(this.getVitesse(),0) && canMoveScreen) {this.carte.deplaceFenetre(this.getVitesse(), 0);}
			if(this.carte.getFenetreEcran().getPosXPixelEcran()+this.getPositionXPixel()+this.carte.getLargeurCasePixel()/2>(this.getEntite().getPositionX()+1)*this.carte.getLargeurCasePixel()){
				this.carte.mettreEntite(this.getEntite(), this.getEntite().getPositionY(), this.getEntite().getPositionX()+1);
			}
			break;
		default:
			break;
		}
	}
	
	public void changerPositionPixel(int deltaXPixel, int deltaYPixel) {
		this.setPositionXPixel(this.getPositionXPixel()+deltaXPixel);
		this.setPositionYPixel(this.getPositionYPixel()+deltaYPixel);
		this.setChanged();
		this.notifyObservers(ControleEntite.A_BOUGE);
	}
	

	
	public void afficheAttaque() {
		if (this.getDistance()>-1 && this.attaqueEnCours) {
			switch (this.getLastDirection()) {
			case UP:
				gc.drawImage(ControleEntite.imageAttaque,91,49,240,222,(this.getEntite().getPositionX())*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran(),(this.getEntite().getPositionY()-1-this.getDistance())*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
				break;
			case DOWN:
				gc.drawImage(ControleEntite.imageAttaque,91,49,240,222,(this.getEntite().getPositionX())*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran(),(this.getEntite().getPositionY()+1+this.getDistance())*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
				break;
			case LEFT:
				gc.drawImage(ControleEntite.imageAttaque,91,49,240,222,(this.getEntite().getPositionX()-1-this.getDistance())*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran(),(this.getEntite().getPositionY())*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
				break;
			case RIGHT:
				gc.drawImage(ControleEntite.imageAttaque,91,49,240,222,(this.getEntite().getPositionX()+1+this.getDistance())*this.carte.getLargeurCasePixel()-this.carte.getFenetreEcran().getPosXPixelEcran()+20,(this.getEntite().getPositionY())*this.carte.getHauteurCasePixel()-this.carte.getFenetreEcran().getPosYPixelEcran(),this.carte.getLargeurCasePixel(),this.carte.getHauteurCasePixel());
				break;
			default:
				break;
			}
		}
	}
	
	public static class AttaqueService extends Service<Object>{
		
		private ControleEntite ctlEntite;

		public AttaqueService(ControleEntite ctlEntite) {
			this.ctlEntite = ctlEntite;
		}
		@Override
		protected Task<Object> createTask() {
			// TODO Auto-generated method stub
			return new Task<Object>() {

				@Override
				protected Object call() throws Exception {
					// TODO Auto-generated method stub
					Thread.sleep(100);
					ctlEntite.attaqueEnCours=false;
					return null;
				}
				
			};
		}
		
	}
	
	public int getPositionXPixel() {
		return positionXPixel;
	}

	public boolean isAvanceD() {
		return avanceD;
	}

	public boolean isAvanceG() {
		return avanceG;
	}

	public boolean isAvanceH() {
		return avanceH;
	}

	public boolean isAvanceB() {
		return avanceB;
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

	public Entité getEntite() {
		return entite;
	}

	public KeyCode getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(KeyCode lastDirection) {
		this.lastDirection = lastDirection;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getBouge() {
		return bouge;
	}

	public void setBouge(int bouge) {
		this.bouge = bouge;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
}
