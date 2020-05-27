package screens;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import javafx.application.Application;
import javafx.geometry.Pos;

public class PauseScreen extends Application {
	public void start(Stage primaryStage) throws Exception{
		Group g1 = new Group();
        Group g2 = new Group();

        Scene pause = new Scene(g1,490,300,Color.GREEN);
        primaryStage.setOpacity(0.5);
        primaryStage.initStyle(StageStyle.DECORATED); // to make the screen see-through 
        Scene game = new Scene(g2,490,300,Color.YELLOW);

        Label t1 = new Label("pause screen");
        Button b1 = new Button("Recommencer");
        b1.setAlignment(Pos.CENTER);
        Label t2 = new Label("game");
        t1.setTranslateY(15);
        t2.setTranslateY(15);
        b1.setTranslateY(200);
        b1.setTranslateX(170);
        g1.getChildren().addAll(t1, b1);

        b1.setOnMouseClicked(e -> { primaryStage.setScene(game); });

        primaryStage.setScene(pause);
        primaryStage.show();
        
    }
	public static void main(String[] args) {
	    launch(args);
	}
}
