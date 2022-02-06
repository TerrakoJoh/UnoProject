package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import common.Message;

/**
 * @author Johanne
 * Singleton Database to access database. Just one ope
 *
 */
public class DatabaseSingleton {
	
	/**
	 * Instance of database. Only one
	 */
	static DatabaseSingleton databaseInstance;
	/**
	 * url to connect to db.
	 */
	String url;
	/**
	 * user to connect to db.
	 */
	String user;
	/**
	 * password to connect db.
	 */
	String passwd;
	
	/**
	 * Creation of DatabaseSingleton.
	 * Use Properties file (more secure).
	 */
	private DatabaseSingleton() {
		Properties prop = new Properties();
		try {
			FileInputStream input = new FileInputStream("src/properties/config.properties");
			prop.load(input);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.url = prop.getProperty("url");
		this.user = prop.getProperty("user");
		this.passwd = prop.getProperty("passwd");
		
		
	}
	
	/**
	 * @return the instance (created if never created, or just returned).
	 */
	public static DatabaseSingleton getInstance() {
		if(databaseInstance == null) {
			databaseInstance = new DatabaseSingleton();
		}
		return databaseInstance;
	}
	
	

	
	/**
	 * @param pseudo
	 * @param password
	 * @return true if pseudo AND password are corrects. False if not.
	 */
	private boolean areIdsCorrect(String pseudo, String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement stmt = conn.createStatement();
//			Statement stmt = (Statement) conn.createStatement();
			String query = "SELECT pseudoUser FROM UNOUSER" + " WHERE pseudoUser LIKE '" + pseudo +  "'" + " AND pwdUser LIKE '" + password + "'";
			
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			
//			
			if(rs.next()) {
				stmt.close();

				return true;
			}
			
			stmt.close();

		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}
	
	/**
	 * @param m the message to save
	 * Save the message in the db.
	 */
	public void saveMessage(Message m) {
		   
		   try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection(url, user, passwd);
				Statement stmt = conn.createStatement();
				String test = m.getContent().replace("'", "''");
				String query = "insert into unomessage(contentmess, datesend, pseudouser) VALUES('" + test 
				+ "', '" + m.getDate()
				+ "', '" + m.getSender() 
				+ "')";
				ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);

				query = "commit";
				rs = ((java.sql.Statement) stmt).executeQuery(query);


				stmt.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		   
	}
	
	/**
	 * @return ArrayList<Message> the messages saved.
	 */
	public ArrayList<Message> loadMessages() {
		ArrayList<Message> histo = new ArrayList<Message>();
		
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection(url, user, passwd);
				Statement stmt = conn.createStatement();
				String query = "select * from unomessage order by idmess"; 
			
				ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);

				while(rs.next()) {
					String sender = rs.getString("pseudoUser");
					String content = rs.getString("contentMess");
					String date = rs.getString("dateSend");
					if(!sender.isEmpty() && !content.isEmpty() && !date.isEmpty()) {
						histo.add(new Message(sender, content, date));
					}
					
				}
				stmt.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		 
		 return histo;
	}
	
	/**
	 * @param pseudo
	 * @return true if pseudo is free, false else.
	 */
	public boolean isPseudoFree(String pseudo) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement stmt = conn.createStatement();

			
			String query = "SELECT pseudoUser FROM UNOUSER" + " WHERE pseudoUser LIKE '" + pseudo +  "'";
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			
			if(rs.next()) {
				stmt.close();
				return false;
			}
			stmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * @param pseudo
	 * @param password
	 * @return true if account is created (no duplicates), else false.
	 */
	public boolean createAccount(String pseudo, String password) {
		  try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection(url, user, passwd);
				Statement stmt = conn.createStatement();

				String query = "insert into unouser(pseudoUser, pwdUser) VALUES('" + pseudo 
				+ "', '" + password
				+ "')";
				
				ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
				query = "commit";
				rs = ((java.sql.Statement) stmt).executeQuery(query);
				query = "SELECT pseudoUser FROM UNOUSER" + " WHERE pseudoUser LIKE '" + pseudo +  "'";
				rs = ((java.sql.Statement) stmt).executeQuery(query);
				
				if(rs.next()) {
					stmt.close();

					return true;
				}
				stmt.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			return false;
	}
	
	/**
	 * @param pseudo
	 * @param password
	 * @return true if canConnect, else false.
	 */
	public boolean canConnect(String pseudo, String password) {
		return areIdsCorrect(pseudo, password);
	}
}
