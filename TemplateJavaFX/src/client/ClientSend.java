package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.Message;

/**
 * Thread in charge of sending messages to the server
 *
 * @author Remi Watrigant
 *
 */
public class ClientSend implements Runnable {

	/**
	 * a reference to the output stream
	 */
	private ObjectOutputStream out;

	private Socket socket;
	
	private String pseudo;

	/**
	 *
	 * @param client the client object
	 * @param out    the output stream
	 */
	public ClientSend(String pseudo, Socket socket, ObjectOutputStream out) {
		this.pseudo = pseudo;
		this.socket = socket;
		this.out = out;
	}

	/**
	 * Loop that read messages from the standard input (keyboard) and send it to the
	 * server using the output stream
	 */
	@Override
	public void run() {

		Scanner sc = new Scanner(System.in);

		while (true) {

			try {
				System.out.println("Votre message >> ");
				String m = sc.nextLine();

				Message mess = new Message(this.pseudo, m);

				out.writeObject(mess);
				out.flush();

				System.out.println("message envoyé");
			} catch (IOException ex) {
				Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
			}

		}

	}

}
