package com;

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

import java.io.File;


public class MenuApp extends Application {
    String title="Beat the Zombies"; // pour pouvoir le changer partout d'un coup

    private Parent createContent() {
        Pane root = new Pane();

        ImageView imageView = new ImageView(new Image(getClass()
                .getResource("res/Fallout4_bg.jpg").toExternalForm()));

        //ImageView imgIcone = new ImageView(new Image(getClass().getResource("icon.png").toExternalForm())); sert plus a rien

        imageView.setFitWidth(1280);
        imageView.setFitHeight(720);

        SepiaTone tone = new SepiaTone(0.85);
        imageView.setEffect(tone);

        Rectangle masker = new Rectangle(1280, 720);
        masker.setOpacity(0);
        masker.setMouseTransparent(true);

        MenuBox menuBox = new MenuBox(250, 350);
        menuBox.setTranslateX(250);
        menuBox.setTranslateY(230);

        MenuBox menuBox2 = new MenuBox(510, 350);
        menuBox2.setTranslateX(250 + 20 + 250);
        menuBox2.setTranslateY(230);

        //menuBox.addItem(new MenuItem("CONTINUE", 250));

//---------------------------------Option Nouvelle Partie---------------------------------------------------------------


        MenuItem itemNew = new MenuItem("Nouvelle Partie", 250);
        itemNew.setOnAction(() -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(3), masker); // avec page de loading qui durera 3s
            ft.setToValue(1);

            ft.setOnFinished(e -> {
                root.getChildren().setAll(new LoadingScreen(1280, 720, () -> {
                    masker.setOpacity(0);
                    root.getChildren().setAll(imageView, menuBox, menuBox2, masker);
                }));
            });

            ft.play();
        });


//---------------------------------Option Astuces-----------------------------------------------------------------------

        menuBox.addItem(itemNew);
        menuBox.addItem(new MenuItem("Charger", 250));

            // je dois encore voir comment faire ça



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