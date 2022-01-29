package application;
import gameuno.Game;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;


public class MainGui extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
//		ClientPanel clientPanel = new ClientPanel();
		Group root = new Group();
		ConnexionPanel connexionPanel = new ConnexionPanel(root);
		root.getChildren().add(connexionPanel);
//		root.getChildren().clear();
//		root.getChildren().add(clientPanel);
//		root.getChildren().add
		Scene scene = new Scene(root, 600, 500);
		stage.setTitle("Ma super app");

		Game g = new Game();
		
	
		stage.setScene(scene);
		stage.show();
	}

}
