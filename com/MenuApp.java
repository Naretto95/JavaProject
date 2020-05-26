package com;

import Jeu.Joueur;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import saveAndLoad.RessourceManager;

import java.io.File;


public class MenuApp extends Application {
    String title="Beat the Zombies"; // pour pouvoir le changer partout d'un coup


    private Parent createContent() {
        Pane root = new Pane();

        ImageView imageView = new ImageView(new Image(getClass()
                .getResource("res/background.jpg").toExternalForm()));

        //ImageView imgIcone = new ImageView(new Image(getClass().getResource("icon.png").toExternalForm())); sert plus a rien

        imageView.setFitWidth(1280); //1280
        imageView.setFitHeight(720); //720

        SepiaTone tone = new SepiaTone(0.85);
        imageView.setEffect(tone);

        Rectangle masker = new Rectangle(1280, 720); // 1280, 720
        masker.setOpacity(0);
        masker.setMouseTransparent(true);

        MenuBox menuBox = new MenuBox(250, 350);
        menuBox.setTranslateX(250);
        menuBox.setTranslateY(230);

        MenuBox menuBox2 = new MenuBox(510, 350);
        menuBox2.setTranslateX(250 + 20 + 250);
        menuBox2.setTranslateY(230);

       /* MenuBox menuBox3 = new MenuBox(5, 5);
        menuBox3.setTranslateX(250 + 20 + 250 + 5);
        menuBox3.setTranslateY(230);

        */

        //menuBox2menuBox.addItem(new MenuItem("CONTINUE", 250));

//---------------------------------Option Nouvelle Partie---------------------------------------------------------------


        MenuItem itemNew = new MenuItem("Nouvelle Partie", 250);
        itemNew.setOnAction(() -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(3), masker); // avec page de loading qui durera 3s
            ft.setToValue(1);

            ft.setOnFinished(e -> {
                root.getChildren().setAll(new LoadingScreen(1280, 720, () -> {
                    masker.setOpacity(0);
                    root.getChildren().setAll(imageView, menuBox, menuBox2,masker);
                }));
            });

            ft.play();
        });
        menuBox.addItem(itemNew);

//---------------------------------Option Load-----------------------------------------------------------------------


       /* menuBox.addItem(new MenuItem("Charger", 250));

        */

        MenuItem load = new MenuItem("Charger", 250);
        load.setOnAction(() -> {
            Joueur j = new Joueur("Hassan",10,20,30); // recuperer ses infos de la barre de vie, barre inventaire...etc là j'ai crée un joueur just pour tester

            try {
                Joueur data = (Joueur) RessourceManager.load("1.save");
                j.setExperience(data.getExperience());
                j.setNom(data.getNom());
                j.setEnMain(data.getEnMain());
                j.setEtat(data.getEtat());
                j.setInventaireArme(data.getInventaireArme());
                j.setInventairePotion(data.getInventairePotion());
                j.setInventaireRessource(data.getInventaireRessource());
                j.setNiveau(data.getNiveau());
                j.setPositionX(data.getPositionX());
                j.setPositionY(data.getPositionY());
                j.setVie(data.getVie());
            }

            catch (Exception e) {


                /*
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur de chargement");
                alert.setHeaderText("Votre fichier semble étre introuvable");
                alert.setContentText("Message erreur : " +e.getMessage());
                alert.showAndWait();
                // j'ai essayé de faire afficher une fenetre de dialogue avec l'erreur, mais ça marche pas
                 */

               System.out.println("Erreur de chargement : " +e.getMessage());

            }

        });
        menuBox.addItem(load);

                ///////////////////////// Sauvegarder ////////////////////////////////////

         // à ajouter dans affichage/carte et faire apparaitre le boutton
        /*
        Button btnSave = new Button("Sauvegarder");
        btnSave.setOnAction(event -> {

            Joueur data = new Joueur( ); // mettre les parametres
            data.setExperience(exp);
            data.setVie(_);
            data.setPositionX(_);
            data.setPositionY(_);
            data.setNiveau(_);
            data.setInventaireRessource(_);
            data.setInventairePotion(_);
            data.setInventaireArme(_);
            data.setEnMain(_);
            data.setNom(_);
            data.setEtat(_);
            try {
                RessourceManager.save(data,"1.save"); // il faut :  implements java.io.Serializabl : Important
            }
            catch (Exception e) {
                System.out.println("Sauvegarde impossible : " + e.getMessage());
            }




                }







                );*/




//---------------------------------Option Astuces-----------------------------------------------------------------------

        MenuItem astuce = new MenuItem("Astuces", 250);
        astuce.setOnAction(() -> {
            menuBox2.addItems(
                    new MenuItem("Recuperer et jeter des Items", 400),
                    new MenuItem("Changer d'Arme", 510),
                    new MenuItem("blablabla", 510),
                    new MenuItem("blablabla", 510));
        });
        menuBox.addItem(astuce);

//---------------------------------Option parametres-----------------------------------------------------------------------


        MenuItem settings = new MenuItem("Parametres", 250);
        settings.setOnAction(() -> {

            menuBox2.addItems(
                    new MenuItem("Graphique", 400),
                    new MenuItem("Son", 510),
                    new MenuItem("Difficulté", 510),
                    new MenuItem("blablabla", 510));
        });


        menuBox.addItem(settings);

        //menuBox.addItem(new MenuItem("Credits", 250));

//---------------------------------Option Credits-----------------------------------------------------------------------

        File file = new File("H:\\Desktop\\cotegraph\\src\\com\\res\\test.pdf");
        HostServices hostServices = getHostServices();
        MenuItem Credits = new MenuItem("Credits", 250);
        Credits.setOnAction(() -> hostServices.showDocument(file.getAbsolutePath()));
        menuBox.addItem(Credits);



//---------------------------------Option Quitter-----------------------------------------------------------------------

        MenuItem itemExit = new MenuItem("Quitter", 250);
        itemExit.setOnAction(() -> System.exit(0));
        menuBox.addItem(itemExit);

        root.getChildren().addAll(imageView, menuBox, menuBox2, masker);
        return root;
    }

   /* MediaPlayer mediaPlayer;
    public void music(){
        String backMusic = "H:\\Desktop\\cotegraph\\src\\com\\res\\song.mp3";
        Media hit = new Media(Paths.get(backMusic).toUri().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }
            // je voulais ajouter un son en background quand le jeu se lance mais ça marche pas trop

    */



    @Override
    public void start(Stage primaryStage) throws Exception {
       //music();
        Scene scene = new Scene(createContent());
        primaryStage.setTitle(title);


        Image Imgicon = new Image(getClass().getResourceAsStream("res/icon.png"));
        primaryStage.getIcons().add(Imgicon);


        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}