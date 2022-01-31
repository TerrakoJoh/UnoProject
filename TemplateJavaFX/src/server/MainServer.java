package server;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.ClientSend;
import common.Message;

/**
 * start a server. Reads the server's port from the command line argument
 *
 * @author Remi Watrigant
 *
 */
public class MainServer {

    /**
     * creates a new server
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
//                Integer port = new Integer(args[0]);
                Server server = new Server();

                Scanner sc = new Scanner(System.in);

                while(true) {
                	
                	System.out.print("Saisir exit pour quitter >> ");
					String m = sc.nextLine();

					if ("exit".equals(m)) {
						System.out.println("au revoir");
						server.close();
						System.exit(0);
					}
                	
                }
                
            
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static void printUsage() {
        System.out.println("java server.Server <port>");
        System.out.println("\t<port>: server's port");
    }
}
