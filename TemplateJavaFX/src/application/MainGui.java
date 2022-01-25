package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


public class MainGui extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		ClientPanel clientPanel = new ClientPanel();
		ConnexionPanel connexionPanel = new ConnexionPanel();
		Group root = new Group();
//		root.getChildren().add(clientPanel);
		root.getChildren().add(connexionPanel);
		Scene scene = new Scene(root, 600, 500);
		stage.setTitle("Ma super app");

	
		stage.setScene(scene);
		stage.show();
	}

}
