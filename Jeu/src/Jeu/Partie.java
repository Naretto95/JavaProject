package Jeu;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import javax.swing.JFileChooser;

import controle.ControleBarreDeVie;
import controle.ControleCarte;
import controle.ControleEnnemi;
import controle.ControleEntite;
import controle.ControleInventaireItemsEntite;
import controle.ControleJoueur;
import controle.ControleOuverturePorte;
import controle.ControleStatsRessourcesEntite;
import javafx.concurrent.Service;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import saveAndLoad.RessourceManager;
import threadService.AffichageService;
import threadService.RunJoueurService;

public class Partie implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//les composants graphiques
	private StackPane MainPane;//le composant graphique principal
	private Group GroupCanvasEtLifeBar;//le composant qui contient le canvas et les barres de vie des persos
	private Canvas canvas; // le canvas
	private GraphicsContext gc;//son GraphicsContext
	private FlowPane fp;//le FlowPane qui contient le bouton des portes et la barre d'inventaire du joueur
	
	//le dur de la partie
	private Carte carte;//la carte de cette partie
	private Joueur joueur;//le joueur de la partie 
	private ArrayList<Ennemi> listeEnnemi;//la liste des ennemis de la partie
	
	//les controleurs de la partie
	private ControleCarte ctlCarte;
	private ControleJoueur ctlJoueur;
	private ControleBarreDeVie ctlBdvJoueur;//le controle de la barre de vie du joueur
	private ControleInventaireItemsEntite ctlInventaireJoueur;
	private ControleStatsRessourcesEntite ctlSRE;//le controle des ressources du joueur
	private ControleOuverturePorte ctlOP;
	private ArrayList<ControleEnnemi> listeControleEnnemi;
	private ArrayList<ControleBarreDeVie> listeCtlBdvEnnemi;
	
	
	//les threads de la partie
	private RunJoueurService threadJoueur;
	private Service<Integer> threadAffichageJeu;

	
	
	private Timer Duree;
	private TypeIssus Issus;
	public boolean paused;
	public boolean win;
	
	//la carte par défaut rentrée à la main
	String[][] saisieMain = 
		{{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","tiles.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","tiles.png","tiles.png","tiles.png","tiles.png","door.png","tiles.png","tiles.png","tiles.png","tiles.png","tiles.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","water.png","water.png","water.png","brique.png","brique.png","water.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","plancher.jpg","plancher.jpg","tiles.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","tiles.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","tiles.png","tiles.png","tiles.png","tiles.png","door.png","tiles.png","tiles.png","tiles.png","tiles.png","tiles.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","water.png","water.png","water.png","brique.png","brique.png","water.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","water.png","water.png","water.png","water.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"},
		{"brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","brique.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","tiles.png","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","plancher.jpg","brique.png","brique.png","brique.png","brique.png","brique.png"}};
	
	//........................................................................
	
	public Partie() {
		
		//on crée les composants 
		this.MainPane = new StackPane();
		this.GroupCanvasEtLifeBar = new Group();
		this.canvas = new Canvas(1000,1000);
		this.gc = canvas.getGraphicsContext2D();
		this.fp = new FlowPane();
		fp.setAlignment(Pos.BOTTOM_RIGHT);
		
		this.Duree = new Timer();
    	this.paused = false;
	}
		
	//..........................................................................
	
	public Scene nouvellePartie() {
		
		//on crée le dur du jeu (carte , joueur , ennemis)
		this.carte = new Carte("images",saisieMain,canvas.getWidth(),canvas.getHeight());
		carte.getCase(6, 6).addRessource(new Ressource(TypeRessource.Cle));
		this.joueur = new Joueur("Bob",21,7,7);
		joueur.Ramasser(new Arme(TypeArme.EpéeCourte,20));
		this.listeEnnemi = new ArrayList<Ennemi>();
		this.listeEnnemi.add(new Ennemi(20,16,5,Categorie.Boss,Race.Nain));
		//Joueur heros = new Joueur("Lilian",1,0,0);
    	//Ennemi monstre = new Ennemi(1,1,0,Categorie.Normal,Race.Humain);
		
		
		this.preparerPartie();
		
		return new Scene(this.MainPane);
		
	}
	
	public Scene choisirPartie() {
		
		JFileChooser dialogue = new JFileChooser(new File("sauvegardes"));
		File fichier;
		
		if (dialogue.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
		    fichier = dialogue.getSelectedFile();
			if (fichier!=null) {
				try {
					Partie partieChoisie = (Partie) RessourceManager.load(fichier.getCanonicalPath().toString());
					
					this.carte = partieChoisie.getCarte();
					this.joueur = partieChoisie.getJoueur();
					this.listeEnnemi = partieChoisie.getListeEnnemi();
							
					this.preparerPartie();
					return new Scene(this.MainPane);
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Il y a eu une erreur lors de la sélection du fichier");
		return null;
	}
	
	public void preparerPartie() {
		
		/* Les observables ne sont pas serialisables, ils faut donc les recharger à chaque chargement de partie, de même que pour les liaisons entre les composants graphiques */
		
		//on crée les controles du jeu qui se souscrivent automatiquement auprès des observables qui les concernent
		this.ctlCarte = new ControleCarte(carte,gc);
		this.ctlJoueur = new ControleJoueur("link2.png",carte,joueur,gc,120,120);
		this.ctlOP = new ControleOuverturePorte((ControleEntite)ctlJoueur);
		this.ctlInventaireJoueur = new ControleInventaireItemsEntite((Entité)joueur);
		this.ctlSRE = new ControleStatsRessourcesEntite(joueur,50,50,100);
		this.ctlBdvJoueur = new ControleBarreDeVie(ctlJoueur,50,950,3);
		this.listeControleEnnemi = new ArrayList<ControleEnnemi>();
		this.listeCtlBdvEnnemi = new ArrayList<ControleBarreDeVie>();
		for (int i =0;i<this.listeEnnemi.size();i++) {
			this.listeControleEnnemi.add(new ControleEnnemi("imagesmonstres/zombie.png",carte,gc,this.listeEnnemi.get(i),32,32));
			this.listeCtlBdvEnnemi.add(new ControleBarreDeVie(this.listeControleEnnemi.get(this.listeControleEnnemi.size()-1) ,1));
		}
				
		
		//on relie les controles de jeu aux composants graphiques du jeu
		GroupCanvasEtLifeBar.getChildren().add(canvas);
		this.fp.getChildren().addAll(ctlOP,ctlInventaireJoueur);
		for (int i =0 ; i<this.listeCtlBdvEnnemi.size();i++) {
			this.GroupCanvasEtLifeBar.getChildren().add(this.listeCtlBdvEnnemi.get(i));
		}
		this.GroupCanvasEtLifeBar.getChildren().add(this.ctlBdvJoueur);
		this.MainPane.getChildren().add(GroupCanvasEtLifeBar);
		this.MainPane.getChildren().add(ctlSRE);
		MainPane.getChildren().add(fp);
		

		//on crée les threads du jeu
		this.threadJoueur = new RunJoueurService(this);
		this.threadAffichageJeu = new AffichageService(this);
		
		//on lance les threads du jeu pour lancer la partie
		this.threadJoueur.start();
		this.threadAffichageJeu.start();
		
	}
	
	public boolean gameOver() {
		return (this.joueur.getEtat()==EtatEntité.Mort);
	}
	
	public void pause() {
		this.paused=true;
		this.Duree.cancel();
		
		//show pause screen
	}
		
	public void save() {
		
		DateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date date = new Date();
		try {
			RessourceManager.save(this, "sauvegardes/save_"+format.format(date));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void exit() {
		System.exit(0);
	}
	
	public Scene recommencer() {
		return this.nouvellePartie();
	}
	
	public void jouer() {
		/*la fonction permet de relancer les threads de la partie après une pause*/
		
		this.threadJoueur.restart();
		this.threadAffichageJeu.restart();
		
		
	}
	
	
	//..................................................................................
	public Timer getDuree() {
		return Duree;
	}
	
	public void setDuree(Timer duree) {
		Duree = duree;
	}
	
	public TypeIssus getIssus() {
		return Issus;
	}
	
	public void setIssus(TypeIssus issus) {
		Issus = issus;
	}

	public Carte getCarte() {
		return carte;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public ControleJoueur getCtlJoueur() {
		return ctlJoueur;
	}

	public ArrayList<Ennemi> getListeEnnemi() {
		return listeEnnemi;
	}

	public ControleCarte getCtlCarte() {
		return ctlCarte;
	}

	public ArrayList<ControleEnnemi> getListeControleEnnemi() {
		return listeControleEnnemi;
	}

	public boolean isPaused() {
		return paused;
	}

	public ControleBarreDeVie getCtlBdvJoueur() {
		return ctlBdvJoueur;
	}


}