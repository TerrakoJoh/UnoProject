package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class ConnexionPanel extends Parent {
	private TextArea pseudo;
	private TextArea password;
	private Text pseudoText;
	private Text passwordText;
//	private JLabel Test;
	private Button buttonConnect;
	private Button buttonCreateAccount;
	
	public ConnexionPanel() {
		this.pseudo = new TextArea();
		this.password = new TextArea();
		this.pseudoText = new Text();
		this.passwordText = new Text();
		this.buttonConnect = new Button();
		this.buttonCreateAccount = new Button();
		
		this.buttonConnect.setText("Connexion");
		this.buttonCreateAccount.setText("Create account");
		this.pseudoText.setText("Pseudo");
		this.passwordText.setText("Password");
		
		this.buttonConnect.setLayoutX(50);
		this.buttonConnect.setLayoutY(350);
		
		this.buttonCreateAccount.setLayoutX(200);
		this.buttonCreateAccount.setLayoutY(350);
		
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
//				connexion();
				
			}
			
		});
		this.getChildren().add(this.pseudo);
		this.getChildren().add(this.password);
		this.getChildren().add(this.buttonConnect);
		this.getChildren().add(this.buttonCreateAccount);
		this.getChildren().add(this.passwordText);
		this.getChildren().add(this.pseudoText);
		
	
	}
	
	public void connexion(String pseudo, String password) {
		
	}

}
