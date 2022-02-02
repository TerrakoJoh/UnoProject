package server;

import java.util.ArrayList;

import common.Message;

public class Request {
	public DatabaseSingleton db = DatabaseSingleton.getInstance();
	
	public Request() {
		
	}
	
	public boolean canConnect(String pseudo, String password) {
		return this.db.canConnect(pseudo, password);
	}
	
	public boolean isAccountCreated(String pseudo, String password) {
		return this.db.createAccount(pseudo, password);
	}
	
	public ArrayList<Message> loadHisto() {
		return this.db.loadMessages();
	}
	
	public boolean isPseudoFree(String pseudo) {
		return this.db.isPseudoFree(pseudo);
	}

}
