/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morse.translator;

/**
 *
 * @author Patryk Domin
 */

public class Signs {
    /**
     * Arrays of characters used in translating user's text
     */
    private String[] english = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                  "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", 
                  "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                  ",", ".", "?" };
    private String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", 
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
                "-----", "--..--", ".-.-.-", "..--.." };
    /**
     * user text to translate
     */
    private String userInput; 
    
    /**
     * user choose type of his input
     * morse
     * or
     * english
     */
    private String userChoose; 
    
    /**
    * getrer to get whole english or morse sequence (map)
    */
    public String[] getEnglishSequence() {
        return this.english;
    }
    
    public String[] getMorseSequence() {
        return this.morse;
    }
        
    /**
     * set user input (text to translate) and set user choose (morse or english)
     */
    public void setUserChoose(String choose) {
        this.userChoose = choose;
    }
    
    public void setUserInput(String input) {
        this.userInput = input;
    }
    
    /**
     * get user input (text to translate)
     */
    public String getUserInput() {
        return this.userInput;
    }
    
    public String getUserChoose() {
        return this.userChoose;
    }
}
