/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;

/**
 * Creates view as a html page to servlet request
 * @author Patryk Domin
 * @version 2.0
 */
public class View {
    
    /**
     * Contains error message
     */
    private String errorMsg;
    
    /**
     * Contains user input
     */
    private String userInput;
    /**
     * Contains translated text
     */
    private String translatedText;
    /**
     * Contains english or morse label text
     */
    private String label;

    /**
     * Single element Contains history of translation
     */
    private ArrayList<String> history;
    
    /**
     * Contains number of translations
     */
    private int translationNumber;
    
    /**
     * Contains html page (response from servlets)
     */
    private String response;
    
    /**
     * Constructor
     */
    public View() {
        errorMsg = "";
        userInput = "";
        label = "English";
        translatedText = "";
        history = new ArrayList<>();
        translationNumber = 0;
    }
    
    /**
     * Set new error message
     * @param value of the error message 
     */
    public void setErrorMsg (String value) {
        errorMsg = value;
    }
    
    /**
     * Set new userInput field value
     * @param value of the user input 
     */
    public void setUserInput (String value) {
        userInput = value;
    }
    
    /**
     * Set Label
     * @param value of the label 
     */
    public void setLabel (String value) {
        label = value;
    }
    
     /**
     * Set number of translations
     * @param value new translation number 
     */
    public void setTranslationNumber (int value) {
        translationNumber = value;
    }
    
    /**
     * Set translated text
     * @param input tramslated text
     */
    public void setTranslatedText(String input) {
        translatedText = "Translated: <br> " + input;
    }
    
    /**
     * Adds new line to the history of conversions.
     *
     * @param newTranslation String, containing newest translation.
     */
    public void addNewTranslation (String newTranslation) {
//        if (history.size() >= 10) {
//            history.remove(0);
//        }
        history.add("<li> " + newTranslation + "</li>");
    }
    
    /**
     * Clears history of translations
     */
    public void clearHistory() {
        history.clear();
    }
    
     /**
     * Creates response
     */
    public void createResponse() {
        String historyHTML = "";
        for (String s : history) {
            historyHTML += s;
        }
        response = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Morse Translator</title> \n"
                + "        <link rel=\"stylesheet\" href=\"style.css\"> \n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "         <div class=\"container\">\n" 
                + "             <h1 id=\"header\">Welcome to the Morse Translator</h1>\n" 
                + "             <h2 id=\"label1\">" + label + "</h2>\n" 
                + "             <form  action=\"Translate\" method=\"POST\">\n" 
                + "                <input id=\"text1\" value=\"" + userInput +"\" type=\"text\" name=\"text1\" />\n" 
                + "                <input class=\"button\" id=\"translate\" type=\"submit\" value=\"Translate\" />\n" 
                + "            </form>\n"
                + "            <form action=\"Change\" method=\"POST\">\n" 
                + "                <input class=\"button\" id=\"change\" type=\"submit\" value=\"Change\" />\n" 
                + "            </form>\n" 
                + "            <div id=\"translated\"> <h3>" + translatedText + "</h3>\n" 
                + "            </div>\n" 
                + "            <form action=\"Clear\" method=\"POST\">\n"
                + "                <input class=\"button\" id=\"clear\" type=\"submit\" value=\"Clear History\" />\n" 
                + "            </form>\n" 
                + "            <div id=\"error\">" + errorMsg +  "</div>\n"
                + "            <form  action=\"Select\" method=\"POST\">"
                + "                <input class=\"button\" id=\"select\" type=\"submit\" value=\"Update and show history\">"
                + "            \n"
                + "            <h4 id=\"historyLabel\">History of translations:</h4>\n" 
                + "            <div class=\"historyDiv\">" 
                +               historyHTML 
                + "            </div>\n"
                + "            <h5 id=\"cookie\"> You made " + translationNumber + " translations </h5>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>\n";
    }
    /**
     * Get response (html page)
     *
     * @return servlet response
     */
    public String getResponse() {
        return response;
    }
}



























