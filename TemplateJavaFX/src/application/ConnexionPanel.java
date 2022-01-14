package application;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ConnexionPanel extends Parent {
	private TextArea pseudo;
	private TextArea password;
	private Button buttonConnect;
	private Button buttonCreateAccount;
	
	public ConnexionPanel() {
		this.pseudo = new TextArea();
		this.password = new TextArea();
		this.buttonConnect = new Button();
		this.buttonCreateAccount = new Button();
		
		this.buttonConnect.setText("Connexion");
		
	
	}
	
	public void connexion() {
		
	}

}
