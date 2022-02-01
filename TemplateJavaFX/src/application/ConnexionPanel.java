package application;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.Request;



public class ConnexionPanel extends Parent {
	private TextArea pseudo;
	private PasswordField password;
	private Text pseudoText;
	private Text passwordText;
	private Text invalidText;
//	private JLabel Test;
	private Button buttonConnect;
	private Button buttonCreateAccount;
	private Group root;
	
	public ConnexionPanel(Group root) {
		this.root = root;
		//init components
		this.pseudo = new TextArea();
		this.password = new PasswordField();
		this.pseudoText = new Text();
		this.passwordText = new Text();
		this.buttonConnect = new Button();
		this.buttonCreateAccount = new Button();
		this.invalidText = new Text();

		//set texts
		this.buttonConnect.setText("Connexion");
		this.buttonCreateAccount.setText("Create account");
		this.pseudoText.setText("Pseudo");
		this.passwordText.setText("Password");
		
		//Set UI
		this.buttonConnect.setLayoutX(50);
		this.buttonConnect.setLayoutY(350);
		
		this.buttonCreateAccount.setLayoutX(200);
		this.buttonCreateAccount.setLayoutY(350);
		
		this.invalidText.setLayoutX(50);
		this.invalidText.setLayoutY(150);
		
		this.pseudo.setLayoutX(100);
		this.pseudo.setLayoutY(25);
		this.pseudo.setPrefWidth(100);
		this.pseudo.setPrefHeight(25);
		
		this.pseudoText.setLayoutX(40);
		this.pseudoText.setLayoutY(50);

		this.password.setLayoutX(100);
		this.password.setLayoutY(100);
		this.password.setPrefWidth(100);
		this.password.setPrefHeight(25);

		this.passwordText.setLayoutX(40);
		this.passwordText.setLayoutY(125);
		
		this.buttonConnect.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				connexion();
			}
			
		});
		
		//add in UI
		this.getChildren().add(this.pseudo);
		this.getChildren().add(this.password);
		this.getChildren().add(this.buttonConnect);
		this.getChildren().add(this.buttonCreateAccount);
		this.getChildren().add(this.passwordText);
		this.getChildren().add(this.pseudoText);
		this.getChildren().add(this.invalidText);
	}
	
	public void connexion() {
		//check in db
		System.out.println(this.pseudo.getText());
		System.out.println(this.password.getText());
		
		Request req = new Request();
		if(req.canConnect(this.pseudo.getText(), this.password.getText())) {

			ClientPanel clientPanel = new ClientPanel(this.pseudo.getText(), this.root);
			this.root.getChildren().clear();
			this.root.getChildren().add(clientPanel);
		} else {
			this.invalidText.setText("Pseudo ou mot de passe incorrect");

		}
			

	}


}
