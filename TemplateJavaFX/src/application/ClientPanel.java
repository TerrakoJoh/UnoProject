package application;

import java.io.IOException;
import java.net.UnknownHostException;

import client.Client;
import common.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;

public class ClientPanel extends Parent{
	private TextArea textToSend;
	private ScrollPane scrollReceivedText;
	private TextFlow receivedText;
	private Button sendBtn;
	private Button clearBtn;
	private Client client;
	
	public ClientPanel(String pseudo) {
		try {
			client = new Client(this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.textToSend = new TextArea();
		this.scrollReceivedText = new ScrollPane();
		this.sendBtn = new Button();
		this.clearBtn = new Button();
		this.receivedText = new TextFlow();
//		this.client = new Client(pseudo, 80, pseudo)
		
		this.sendBtn.setText("Send");
		this.sendBtn.setLayoutX(455);
		this.sendBtn.setLayoutY(405);
		this.sendBtn.setPrefHeight(30);
		this.sendBtn.setPrefWidth(60);
		this.sendBtn.setVisible(true);
		this.sendBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				printNewMessage(new Message(pseudo, textToSend.getText()));
				textToSend.clear();
				// TODO Auto-generated method stub
			}
		});
		
		
		this.clearBtn.setText("Clear");
		this.clearBtn.setLayoutX(455);
		this.clearBtn.setLayoutY(445);
		this.clearBtn.setPrefHeight(30);
		this.clearBtn.setPrefWidth(60);
		this.clearBtn.setVisible(true);
		this.clearBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				textToSend.clear();
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.textToSend.setLayoutX(50);
		this.textToSend.setLayoutY(405);
		this.textToSend.setPrefWidth(400);
		this.textToSend.setPrefHeight(70);
		
		this.scrollReceivedText.setLayoutX(50);
		this.scrollReceivedText.setLayoutY(50);
		this.scrollReceivedText.setPrefWidth(400);
		this.scrollReceivedText.setPrefHeight(350);
		
		this.receivedText.setVisible(true);
		//this.receivedText.setPrefHeight(this.scrollReceivedText.getHeight());
		this.receivedText.setPrefWidth(this.scrollReceivedText.getPrefWidth());
		this.scrollReceivedText.setContent(this.receivedText);
		this.scrollReceivedText.vvalueProperty().bind(this.receivedText.heightProperty());
		
		this.getChildren().add(this.textToSend);
		this.getChildren().add(this.scrollReceivedText);
		this.getChildren().add(this.sendBtn);
		this.getChildren().add(this.clearBtn);
		
		
	}
	public void printReceivedMessage(Message mess) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
		
			// TODO Auto-generated method stub
			Label text = new Label("\n" + mess.toString());
			text.setPrefWidth(receivedText.getPrefWidth()-20);
			text.setAlignment(Pos.CENTER_LEFT);
			receivedText.getChildren().add(text);	
		;}});
	}
	
	public void printNewMessage(Message mess) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Label text = new Label("\n" + mess.toString());
				text.setPrefWidth(receivedText.getPrefWidth()-20);
				text.setAlignment(Pos.CENTER_LEFT);
				receivedText.getChildren().add(text);
				try {
					client.sendMessage(mess);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
	
}
