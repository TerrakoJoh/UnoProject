package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.Message;
import common.Message;

/**
 * Class which represents a new connected client in the server side. It contains
 * a socket from which it creates an input and output stream to receive and send
 * messages to the client. Each ClientInServer has a unique Id
 *
 * @author Remi Watrigant
 *
 */
public class ConnectedClient implements Runnable {

	/*
	 * id counter (number of instances created)
	 */
	private static int idCounter = 0;

	/*
	 * id of the client
	 */
	private int id;

	/*
	 * socket used to communicate
	 */
	private Socket socket;

	/*
	 * output stream to send messages to the client
	 */
	private ObjectOutputStream out;

	/*
	 * input stream to receive messages from the client
	 */
	private ObjectInputStream in;

	/*
	 * a reference to the server
	 */
	Server server;

	/**
	 * initializes all attributes and creates the input and output streams using the
	 * socket
	 *
	 * @param server a reference to the server it belongs to
	 * @param socket the socket used to communcate
	 * @throws IOException
	 */
	public ConnectedClient(Server server, Socket socket) throws IOException {
		this.id = ++idCounter;
		this.server = server;
		this.socket = socket;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());

		System.out.println("Nouvelle connexion, id = " + id);
	}

	/**
	 * Sends a message to the client using the output stream
	 *
	 * @param m the message to be sent
	 */
	public void sendMessage(Message mess) {
		try {
			System.out.println("Le serveur envoie un message via le thread " + Thread.currentThread().getId() + " "
					+ Thread.currentThread().getName());
			this.out.writeObject(mess);
			this.out.flush();
		} catch (IOException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Reads messages from the client using the input stream if it receives null, it
	 * means the client disconnected in which case we tell it to the server
	 */
	@Override
	public void run() {

		String message = "";
		try {

			boolean isActive = true;
			while (isActive) {
				try {

					System.out.println("Ecoute de " + id);

					Message mess = (Message) in.readObject();
//					System.out.println("JE TESTE ENCORE !!" + mess);

					if (mess != null) {
//						mess.setId(id);
						System.out.println("Message de " + id + " : " + mess.toString());

						server.broadcastMessage(mess, id);
					} else {
						isActive = false;
						server.disconnectedClient(this);
					}
				} catch (ClassNotFoundException ex) {
					Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
				} catch (EOFException e) {
					isActive = false;
					server.disconnectedClient(this);
				}

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	/**
	 *
	 * @return the Id of the connected client
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Closes the input, output stream and the socket
	 *
	 * @throws IOException
	 */
	public void closeClient() throws IOException {
		System.out.println("Client " + id + " deconnecté");
		this.in.close();
		this.out.close();
		this.socket.close();
	}

}
