package server;

import java.util.ArrayList;

import common.Message;

/**
 * @author Johanne
 * Request class permits to make the link between db and client. 
 * It is not a direct access to db.
 *
 */
public class Request {
	/**
	 * SingletonDB
	 */
	public DatabaseSingleton db = DatabaseSingleton.getInstance();
	
	/**
	 * Constructor
	 */
	public Request() {
		
	}
	
	/**
	 * @param pseudo
	 * @param password
	 * @return true if can connect, else false.
	 */
	public boolean canConnect(String pseudo, String password) {
		return this.db.canConnect(pseudo, password);
	}
	
	/**
	 * @param pseudo
	 * @param password
	 * @return true if account well created, else false.
	 */
	public boolean isAccountCreated(String pseudo, String password) {
		return this.db.createAccount(pseudo, password);
	}
	
	/**
	 * @return list of all messages written before connexion.
	 */
	public ArrayList<Message> loadHisto() {
		return this.db.loadMessages();
	}
	
	/**
	 * @param pseudo
	 * @return true if pseudo is free, else false.
	 */
	public boolean isPseudoFree(String pseudo) {
		return this.db.isPseudoFree(pseudo);
	}

}
