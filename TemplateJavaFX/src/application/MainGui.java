package application;
import gameuno.Game;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;


/**
 * @author Johanne
 * Load ClientPanel
 */
public class MainGui extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {

		Group root = new Group();
		ConnexionPanel connexionPanel = new ConnexionPanel(root);
		root.getChildren().add(connexionPanel);

		Scene scene = new Scene(root, 600, 500);
		stage.setTitle("UnoProject");
		
	
		stage.setScene(scene);
		stage.show();
	}

}
