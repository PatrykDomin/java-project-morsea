/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;


/**
 * @author Patryk Domin
 * @version 3.0
 */
public class MorseTranslatorClient {
    /**
     * The main application method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("port", "8888");
        properties.setProperty("ip", "127.0.0.1");
        
        try (FileOutputStream out = new FileOutputStream(".properties")) {
            properties.store(out, "--Konfiguracja--");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        try (FileInputStream in = new FileInputStream(".properties")) {
            properties.load(in);
            int port = parseInt(properties.getProperty("port"));
            Client client = new Client(properties.getProperty("ip"), port);
            boolean end = false;
            while(!end) {
                client.help();
                end = client.clientRealize();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
}

class Client {
    /**
     * socket
     */
    private Socket socket;
    /**
     * input to read from server
     */
    private BufferedReader input;
    /**
     * output to write to server
     */
    private PrintWriter output;
    
    /**
     * Client constructor
     * @param ip - ip adress 
     * @param port - port adress
     * @throws IOException 
     */
    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        output = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                this.socket.getOutputStream())), true);
        input = new BufferedReader(
                new InputStreamReader(
                        this.socket.getInputStream()));
    }
    /**
     * send user choose to the server
     * @return true if client wants to close the app 
     */
    public boolean clientRealize() throws IOException {
        Scanner scanner = new Scanner(System.in);
        MorseTranslatorView mtv = new MorseTranslatorView();
        String str = scanner.next();
        output.println(str);
        switch(input.readLine()) {
            case "STARTTRANSLATOR":
                translate(mtv);
                break;
            case "HELP":
                break; 
            case "QUIT":
                return true;
            default:
                System.out.println("\nWrong input");
                break;
        }
        return false;
    }
    
    /**
     * translate function - getting choose and text to translate from the user
     * @param mtv - project view
     * @throws IOException 
     */
    private void translate(MorseTranslatorView mtv) throws IOException {
        String choose, userInput;
        Scanner scanner = new Scanner(System.in);
        mtv.printQuestion();
        choose = scanner.nextLine();
        output.println(choose);
        mtv.printAskForInput();
        userInput = scanner.nextLine();
        output.println(userInput);
        String serverResponse = input.readLine();
        if (serverResponse.equals("CHOOSE ERROR")) {
            System.out.println("\nYou can choose only 'english' or 'morse' as input type");
        } else {
            System.out.println("Translated text: " + serverResponse);
        }
    }
    
    /**
     * print help for the user (from the server)
     * @throws IOException 
     */
    public void help() throws IOException {
        System.out.println("\n" + input.readLine());
        System.out.println(input.readLine());
        System.out.println(input.readLine());
        System.out.println("Yout choice: ");
    }
}