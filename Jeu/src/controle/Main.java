package controle;

import Jeu.Arme;
import Jeu.Carte;
import Jeu.Entité;
import Jeu.Joueur;
import Jeu.Ressource;
import Jeu.TypeArme;
import Jeu.TypeRessource;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		StackPane fenetreJeu = new StackPane();
		Group fenetreJeuBis = new Group();
		
		
		Canvas canvas = new Canvas(1000,1000);
		fenetreJeuBis.getChildren().add(canvas);
		fenetreJeu.getChildren().add(fenetreJeuBis);
		
		String[][] saisieMain = 
			{{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","tiles.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","tiles.png","tiles.png","tiles.png","tiles.png","door.png","tiles.png","tiles.png","tiles.png","tiles.png","tiles.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","water.png","water.png","water.png","brique.png","brique.png","water.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"},
			{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png"}};
		
		Carte carte = new Carte("images",saisieMain,canvas.getWidth(),canvas.getHeight(),fenetreJeu);
		carte.getCase(6, 6).addRessource(new Ressource(TypeRessource.Cle));
		Joueur joueur = new Joueur("Bob",50,6,3);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		ControleJoueur ctlJoueur = new ControleJoueur("link2.png",carte,joueur,gc,120,120);
		ControleOuverturePorte ctlOP = new ControleOuverturePorte((ControleEntite)ctlJoueur);
		ControleInventaireItemsEntite ctlInventaireJoueur = new ControleInventaireItemsEntite((Entité)joueur);
		ControleBarreDeVie ctlbdv = new ControleBarreDeVie(ctlJoueur,15);
		ControleBarreDeVie ctlbdv2 = new ControleBarreDeVie(ctlJoueur,50,950,100);
		ControleStatsRessourcesEntite ctlSRE = new ControleStatsRessourcesEntite(joueur,50,50,100);
		joueur.Ramasser(new Arme(TypeArme.EpéeCourte,1));
		fenetreJeuBis.getChildren().add(ctlbdv);
		fenetreJeuBis.getChildren().add(ctlbdv2);
		fenetreJeu.getChildren().add(ctlSRE);
		fenetreJeu.getChildren().add(ctlInventaireJoueur);
		FlowPane bouton = new FlowPane();
		bouton.getChildren().add(ctlOP);
		bouton.setAlignment(Pos.CENTER_RIGHT);
		fenetreJeu.getChildren().add(bouton);

        new Thread(ctlJoueur).start();
        Scene scene = new Scene(fenetreJeu);
        scene.getStylesheets().add("file:css/styles.css");
		arg0.addEventFilter(KeyEvent.ANY,ctlJoueur);
        arg0.setScene(scene);
        arg0.show();
	}
}
