
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author remi
 */
public class Connection implements Runnable {
	/*
	 * a reference to the server it belongs to
	 */
	private Server server;

	/*
	 * the server socket used to accept new connexions
	 */
	private ServerSocket serverSocket;

	/**
	 * creates a new ServerSocket
	 *
	 * @param server a reference to the server
	 * @param port   the port the server listens to
	 * @throws IOException
	 */
	public Connection(Server server) throws IOException {
		
		this.server = server;
		
		
		System.out.println("creating serverSocket");

		this.serverSocket = new ServerSocket(server.getPort());

	}

	/**
	 * Wait for new client connexions using the SocketServer. When a new client
	 * connects, it creates a new thread with a new ClientInServer object, and adds
	 * this new ClientInServer to the server's list of connected clients
	 */
	@Override
	public void run() {

		while (true) {

			try {

				if (serverSocket.isClosed() == false) {

					System.out.println("Attente de nouveaux clients...");
					Socket sockNewClient = serverSocket.accept();

					if (sockNewClient != null) {

						ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
						server.addClient(newClient);
						Thread threadNewClient = new Thread(newClient);
						threadNewClient.start();
					} else {
						this.close();
					}
				}
			} catch (IOException e) {
				System.out.println("here");
				// e.printStackTrace();
				this.close();
			}

		}

	}

	public void close() {
		try {
			System.out.println("HeRE");
			System.out.flush();
			if (this.serverSocket.isClosed() == false) {
				this.serverSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
