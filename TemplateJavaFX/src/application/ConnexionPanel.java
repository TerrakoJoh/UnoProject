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



/**
 * @author Johanne
 * ConnexionPanel is the screen where we can
 * connect and subscribe.
 */
public class ConnexionPanel extends Parent {
	private TextArea pseudo;
	private PasswordField password;
	private Text pseudoText;
	private Text passwordText;
	private Text invalidText;
	private Button buttonConnect;
	private Button buttonCreateAccount;
	private Group root;
	
	/**
	 * @param root
	 * Constructor
	 */
	public ConnexionPanel(Group root) {
		this.root = root;
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
		
		this.buttonCreateAccount.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				createAccount();				
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
	
	/**
	 * To connect to the server.
	 * Will receive and answer from Request and check 
	 * if pseudo and password are corrects.
	 */
	public void connexion() {

		Request req = new Request();
		if(req.canConnect(this.pseudo.getText(), this.password.getText())) {

			ClientPanel clientPanel = new ClientPanel(this.pseudo.getText());
			this.root.getChildren().clear();
			this.root.getChildren().add(clientPanel);
		} else {
			this.invalidText.setText("Pseudo ou mot de passe incorrect");
		}
	}
	
	/**
	 * Create account, verify if password / pseudo are blanks
	 * Verify if contains ' or spaces
	 * Verify if length of password >= 8
	 * Verify if pseudo is free
	 * 
	 */
	public void createAccount() {
		Request req = new Request();
		if(!this.pseudo.getText().isBlank() && !this.password.getText().isBlank()) {
		if(this.pseudo.getText().contains("'") || this.password.getText().contains("'") || this.pseudo.getText().contains(" ") || this.password.getText().contains(" ")) {
			this.invalidText.setText("Le pseudo et le mot de passe ne peuvent pas contenir d'apostrophes ' ni d'espaces.");
		} else {
			if(this.password.getText().length()<8) {
				this.invalidText.setText("Le mot de passe doit contenir au moins 8 caractères.");

			} else {
				if(req.isPseudoFree(this.pseudo.getText())) {
					if(req.isAccountCreated(this.pseudo.getText(), this.password.getText())) {
						this.invalidText.setText("Votre compte est créé !");
					} else {
						this.invalidText.setText("Erreur lors de la création de votre compte.");
					}
				} else {
					this.invalidText.setText("Ce pseudo existe déjà.");
				}
			}
		}
		} else {
			this.invalidText.setText("Le pseudo et le mot de passe ne peuvent pas être vides ou contenir seulement des espaces.");

		}
		
	}


}
