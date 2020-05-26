package threadService;

import java.util.ArrayList;

import Jeu.Partie;
import controle.ControleCarte;
import controle.ControleEnnemi;
import controle.ControleJoueur;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class AffichageService extends Service<Integer>{
	
	private Partie partie;
	private ControleCarte ctlCarte;
	private ControleJoueur ctlJoueur;
	private ArrayList<ControleEnnemi> listeCtlEnnemi;
	
	public AffichageService(Partie partie) {
		this.partie = partie;
		this.ctlCarte=partie.getCtlCarte();
		this.ctlJoueur = partie.getCtlJoueur();
		this.listeCtlEnnemi=partie.getListeControleEnnemi();
	}

	protected Task<Integer> createTask() {
		return new Task<Integer>() {

			protected Integer call() throws Exception {
				while(!partie.isPaused() && !partie.gameOver()) {

					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					ctlCarte.afficheCarte();
					for (int i = 0 ;i< listeCtlEnnemi.size();i++) {
						listeCtlEnnemi.get(i).afficheEnnemi();
					}
					ctlJoueur.afficheJoueur(ctlJoueur.getLastDirection(), ctlJoueur.getBouge());
					for (int i = 0 ;i< listeCtlEnnemi.size();i++) {
						listeCtlEnnemi.get(i).afficheAttaque();
					}
					ctlJoueur.afficheAttaque();
				}
				return null;
			}
		};
	}
}
