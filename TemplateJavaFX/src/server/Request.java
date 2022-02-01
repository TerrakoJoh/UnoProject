package server;

public class Request {
	public DatabaseSingleton db = DatabaseSingleton.getInstance();
	
	public Request() {
		
	}
	
	public boolean canConnect(String pseudo, String password) {
		return this.db.canConnect(pseudo, password);
	}
	

}
