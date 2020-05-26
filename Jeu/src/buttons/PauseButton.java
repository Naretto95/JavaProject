package buttons;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PauseButton extends Application {

	public static void main(String[] args) {
	    launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
	    primaryStage.setTitle("Pause");
	    Button PauseButton = new Button();
	    PauseButton.setText("Pause");
	    PauseButton.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	jeu.Partie.pause();;
	        }
	    });

	    Pane root = new Pane();
	    PauseButton.setLayoutX(0); //temporary
	    PauseButton.setLayoutY(0); //temporary
	    root.getChildren().add(PauseButton);
	    primaryStage.setScene(new Scene(root, 300, 250));
	    primaryStage.show();
	}
	
}
