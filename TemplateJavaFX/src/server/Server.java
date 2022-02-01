package server;

import common.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implementing a simple Chat server. Contains one thread accepting new
 * clients, and, for each connected client, one thread receiving new messages.
 * When a message is received from a client, it is broadcasted to every other
 * clients. Relies on TCP connexions.
 *
 * @author Remi Watrigant
 *
 */
public class Server {

    /*
	 * The list of connected clients
     */
    private List<ConnectedClient> clients;

    /*
	 * the port the server listens to
     */
    private int port;

    private Connection conn;
    private DatabaseSingleton database;
    
    /**
     * Starts a thread which accepts new clients
     *
     * @param port the port the server listens to
     * @throws IOException
     */
    public Server() throws IOException {
        this.port = 1885;
        this.database = DatabaseSingleton.getInstance();
        this.clients = new ArrayList<ConnectedClient>();
        
        conn = new Connection(this);

        Thread threadConnection = new Thread(conn);
        threadConnection.start();
    }

    /**
     * add a new client to the list of connected clients
     *
     * @param newClient the new client
     */
    public void addClient(ConnectedClient newClient) {
    	
    	Message mess = new Message("server", "Quelqu'un vient de se connecter !");
    	broadcastMessage(mess, newClient.getId());
        this.clients.add(newClient);
    }

    /**
     * Broadcasts a message to all connected clients but the one who sent it
     *
     * @param message the message to send
     * @param id the client's id who sent the message
     */
    public void broadcastMessage(Message mess, int id) {
        for (ConnectedClient client : clients) {
            if (client.getId() != id) {
                client.sendMessage(mess);
            }
        }

    }

    /**
     * Remove a client from the list of connected clients and invoke the
     * closeClient() method on it
     *
     * @param id if of the client that just disconnected
     * @throws IOException
     */
    public void disconnectedClient(ConnectedClient discClient) throws IOException {

        clients.remove(discClient);
        discClient.closeClient();
        
        Message mess = new Message("server", "Le client " + discClient.getId() + " nous a quitté");
        broadcastMessage(mess, discClient.getId());
    }

    /**
     * getter for the port of the server
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }
	private DatabaseSingleton getDatabase() {
		return this.database;
	}
	public void close() {
		for (ConnectedClient client : this.clients) {
			try {
				client.closeClient();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.conn.close();
		
	}

}
