/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morse.translator;
import java.util.*;
/**
 *
 * @author Patryk Domin
 * @version 2.0
 * Model
 */

public class Signs {
    /**
     * List of characters used in translating user's text
     */
    private List<String> english = new LinkedList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                  "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", 
                  "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                  ",", ".", "?"));
    
    /**
     * List of characters used in translating user's text
     */
    private List<String> morse = new LinkedList<>(Arrays.asList(".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", 
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
                "-----", "--..--", ".-.-.-", "..--.."));
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
     * set user choose (morse or english)
     * @param choose - user choose (correct: morse or english)
     */
    public void setUserChoose(String choose) {
        this.userChoose = choose;
    }
    /**
     * set user input (text to translate)
     * @param input - user input (text to translate)
     */
    public void setUserInput(String input) {
        this.userInput = input;
    }
    
     /**
     * get user choose (morse or english)
     * @return user choose (correct: morse or english)
     */
    public String getUserChoose() {
        return this.userChoose;
    }
    /**
     * get user input (text to translate)
     * @return user input (text to translate)
     */
    public String getUserInput() {
        return this.userInput;
    }
    
    /**
     * get index of a sign in array
     * @param sign - a sign to get an index from
     * @return index as int, -1 if no sign will be found, 111 if sign is a spacebar
     */
    public int signIndexEnglish(String sign) {
        if (sign.equals(" ")) {
            return 111;
        } else {
            int i = 0;
            for (String element : english) {
                if (element.equals(sign)) {
                    return i;
                }
                i++;
            } 
            return -1;
        }
    }
    
    /**
     * get index of a sign in array
     * @param sign - a sign to get an index from (sign could be longer due to the morse notation
     * @return index as int, -1 if no sign will be found, 111 if sign is a '//' (spacebar in morse) or 222 if a sing is '/' (space between characters)
     */
    public int signIndexMorse(String sign) {
        switch (sign) {
            case "//":
                return 111;
            case "/":
                return 222;
            default:
                int i = 0;
                for (String element : morse) {
                if (element.equals(sign)) {
                    return i;
                }
                i++;
            } 
            return -1;
        }
    }
    
    /**
     * replace one single character in english with its equivalent in morse 
     * @param index of en element from english array
     * @return space if index is 111, morse signt with the same index or null if index is valid
     */
    public String changeSignFromEnglish(int index) {
        if (index == 111) {
            return " ";
        } else if (index >= 0 && index < english.size()) { 
            return morse.get(index);
        }
        return null;
    }
    
    /**
     * replace one single character in morse with its equivalent in english 
     * @param index of en element from morse array
     * @return space if index is 111, english sign with the same index or null if index is valid
     */
    public String changeSignFromMorse (int index) {
        if (index == 111) {
            return " ";
        } else if (index == 222) {
            return "";
        } else if (index >= 0 && index < morse.size()) { 
            return english.get(index);
        }
        return null;
    }
}
