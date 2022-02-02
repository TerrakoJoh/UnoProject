package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Properties;

import common.Message;

public class DatabaseSingleton {
	
	static DatabaseSingleton databaseInstance;
	String url;
	String user;
	String passwd;
	
	private DatabaseSingleton() {
		Properties prop = new Properties();
		try {
			FileInputStream input = new FileInputStream("src/properties/config.properties");
			prop.load(input);
//			prop.load(getClass().getResourceAsStream("src/properties/config.properties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.url = prop.getProperty("url");
		this.user = prop.getProperty("user");
		this.passwd = prop.getProperty("passwd");
		
		
	}
	
	public static DatabaseSingleton getInstance() {
		if(databaseInstance == null) {
			databaseInstance = new DatabaseSingleton();
		}
		return databaseInstance;
	}
	
	

	
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
	
	public void saveMessage(Message m) {
		   
		   try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection(url, user, passwd);
				Statement stmt = conn.createStatement();
//				Statement stmt = (Statement) conn.createStatement();
				String query = "insert into unomessage(contentmess, datesend, pseudouser) VALUES('" + m.getContent() 
				+ "', '" + m.getDate()
				+ "', '" + m.getSender() 
				+ "')";
				ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
				query = "commit";
				rs = ((java.sql.Statement) stmt).executeQuery(query);

//				
//				while(rs.next()) {
//					int id = rs.getInt("no_adherent");
//					String name = rs.getString("nom_adherent");
//					System.out.println("Pr�nom " + id + " Nom " + name);
//				}
				stmt.close();
//				((Connection) stmt).close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		   
	}
	
	
	public boolean canConnect(String pseudo, String password) {
		return areIdsCorrect(pseudo, password);
	}
}