package affichage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ExitButton extends Application {

	public static void main(String[] args) {
	    launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
	    primaryStage.setTitle("Exit");
	    Button ExitButton = new Button();
	    ExitButton.setText("Exit");
	    ExitButton.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	jeu.Partie.exit();;
	        }
	    });

	    Pane root = new Pane();
	    ExitButton.setLayoutX(250);
	    ExitButton.setLayoutY(220);
	    root.getChildren().add(ExitButton);
	    primaryStage.setScene(new Scene(root, 300, 250));
	    primaryStage.show();
	}
	
}

