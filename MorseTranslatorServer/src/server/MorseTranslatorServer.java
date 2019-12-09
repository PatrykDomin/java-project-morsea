/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 *
 * @author Patryk Domin
 * @version 3.0
 */
public class MorseTranslatorServer implements Closeable {
   
    /**
     * port number
     */
    private static int PORT;

    /**
     * server socket
     */
    private ServerSocket serverSocket;

    /**
     * Creates the server socket
     *
     * @throws IOException when prot is already bind
     */
    MorseTranslatorServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    /**
     * The main application method
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        Properties properties = new Properties();
        properties.setProperty("port", "8888");
       
        try (FileOutputStream out = new FileOutputStream(".properties")) {
            properties.store(out, "--Konfiguracja--");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        try (FileInputStream in = new FileInputStream(".properties")) {
            properties.load(in);
            PORT = parseInt(properties.getProperty("port"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        try (MorseTranslatorServer tcpServer = new MorseTranslatorServer()) {
            System.out.println("Server started");
            while (true) {
                Socket socket = tcpServer.serverSocket.accept();
                try (SingleService singleService = new SingleService(socket)) {
                    singleService.realize();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
    }
}

/**
 * The server class servicing a single connection
 */
class SingleService implements Closeable {

    /**
     * socket
     */
    private Socket socket;
    /**
     * input to read from client
     */
    private BufferedReader input;
    /**
     * output to write to client
     */
    private PrintWriter output;

    /**
     * enum with two values (correct user choose inputs)
     */
    public enum typeOfTranslation {
        english, morse
    }
    
    /**
     * The constructor of instance of the SingleService class.
     *
     * @param socket socket representing connection to the client
     */
    public SingleService(Socket socket) throws IOException {
        this.socket = socket;
        output = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                this.socket.getOutputStream())), true);
        input = new BufferedReader(
                new InputStreamReader(
                        this.socket.getInputStream()));
    }
    /**
     * Realizes the service
     */
    public void realize() {
        try {
            help();
            MorseTranslatorModel mtm = new MorseTranslatorModel();
            boolean server = true;
            while (server) {
                String str = input.readLine();
                System.out.println("Clinet sent: " + str);
                switch(str.toUpperCase()) {
                    case "STARTTRANSLATOR":
                        output.println("STARTTRANSLATOR");
                        translate(mtm);
                        help();
                        break;
                    case "HELP":
                        output.println("HELP");
                        help();
                        break;
                    case "QUIT":
                        output.println("QUIT");
                        server = !server;
                        break;
                    default:
                        output.println("");
                        help();
                        break;
                }
            }
            System.out.println("closing...");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    /**
    * handle translation of the user input
    * @param mtm - project model
    */
    public void translate(MorseTranslatorModel mtm) {
        try {
            String choose = input.readLine();
            System.out.println("Client wrote choose");
            mtm.checkChoose(choose);
            mtm.setUserChoose(choose);
            String userInpt = input.readLine();
            System.out.println("Client wrote text to translate");
            typeOfTranslation tot = typeOfTranslation.valueOf(mtm.getUserChoose());
            boolean correct = false;
            switch (tot) {
                case english:
                    if (mtm.checkInputEnglish(userInpt)) {
                        correct = !correct;
                    }
                    break;
                case morse:
                    if (mtm.checkInputMorse(userInpt)) {
                        correct = !correct;
                    }
                    break;
            }
            if(correct) {
                mtm.setUserInput(userInpt);
                output.println(mtm.getUserInput());
            } else {
                output.println("Wrong input");
            }
            System.out.println("Output has been sent to client");
            
        } catch (IOException e) {
            
        } catch (TypeOfInputException toie) {
            output.println(toie.getMessage());
        } 
    } 
    /**
     * output help instructions to the client
     */
    public void help() {
        output.println("STARTTRANSLATOR");
        output.println("HELP");
        output.println("QUIT");
    }
    /**
     * closing socket method
     * @throws IOException 
     */
    @Override
    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }
}
