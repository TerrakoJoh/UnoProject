package client;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * starts a client. Reads the address and port from the command line argument
 *
 * @author Remi Watrigant
 *
 */
public class MainClient {

    /**
     * construct a new client
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
//                String address = args[0];
        	String address = "127.0.0.1";
//                Integer port = new Integer(args[1]);
        	Integer port = 1885;
                Client c = new Client(address, port);
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private static void printUsage() {

        System.out.println("java client.Client <address> <port>");
        System.out.println("\t<address>: server's ip address");
        System.out.println("\t<port>: server's port");
    }
}
