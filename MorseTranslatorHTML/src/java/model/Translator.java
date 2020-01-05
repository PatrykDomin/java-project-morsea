/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exception.TypeOfInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Patryk Domin
 * @version 4.0
 */
public class Translator {
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
     * List of Strings contains translation hisotry from user
     */
    private List<String> translationHistory;
    /**
     * if true - translation from english to morse
     * id false - translatnion from morse to english
     */
    private boolean engToMorse;
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
     * translated text
     */
    private String translatedText;
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
     * set translated text
     * @param input - translated text
     */
    public void setTranslatedText(String input) {
        this.translatedText = input;
    }
    /**
     * set translate direction
     * true - from english to morse
     * false - from morse to english
     */
    public void setTranslateDirection() {
        this.engToMorse = !this.engToMorse;
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
     * get translated text
     * @return translated text
     */
    public String getTranslatedText() {
        return this.translatedText;
    }
    /**
     * get translate direction 
     * @return translate direction
     */
    public boolean getTranslateDirection() {
        return this.engToMorse;
    }
    /**
    * @return list of strings - history of translation
    */
    public List<String> getDecHistory() {
        return translationHistory;
    }
    
    /**
     * Constructor
     */
    public Translator() {
        userInput = "";
        userChoose = "";
        translatedText = "";
        translationHistory = new ArrayList<>();
        engToMorse = true;
    }
    
    /**
     * adding a new translation to a history
     * 
     * @return translation history
     */
    public String addNewTranslation() {
        String history = "";
        if (userInput != null) {
            history = userInput + " => " + translatedText;
            translationHistory.add(history);
        }
        return history;
    }
    /**
     * Clear history
     */
    public void clearHistory() {
        translationHistory.clear();
    }
    
    /**
     * checking if user choose correct type of text (morse or english)
     * @param choose - correct: english or morse
     * @throws TypeOfInputException  when user give choose different than morse or english
    */
    public void checkChoose (String choose) throws TypeOfInputException {
        if (!choose.equals("morse") && !(choose.equals("english"))) {
            throw new TypeOfInputException("CHOOSE ERROR");
        }
    }
    
    /**
     * get index of a sign in array
     * @param sign - a sign to get an index from
     * @return index as int, -1 if no sign will be found, 111 if sign is a spacebar
     */
    public int signIndexEnglish(char sign) {
        if (sign == ' ') {
            return 111;
        } else {
            int i = 0;
            for (String element : english) {
                if (element.equals(String.valueOf(sign))) {
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
        int i = 0;
        for (String element : morse) {
            if (element.equals(String.valueOf(sign))) {
                return i;
            }
            i++;
        } 
        return -1;
    }
    
    /**
     * Translate user input (to morse or english) 
     * @param input - user input
     * @return translated text or empty string if the input is wrong
     */
    public String morseToEnglish(String input) {
        String morseSign = "";
        String translated = "";
        for(int i = 0; i < input.length(); i++){
            if (input.charAt(i) == '/') {
                if (checkSingleSignMorse(morseSign)) {
                translated += changeSignFromMorse(signIndexMorse(morseSign));
                morseSign = "";
                } else {
                    return "";
                }
                if (i+1 == input.length()) {
                    return translated;
                } else if (input.charAt(i+1) == '/') {
                    translated += " ";
                    i++;
                }
                i++;
            }
            morseSign += String.valueOf(input.charAt(i));
        }
        return translated;
    }
    
    /**
     * replace one single character in english with its equivalent in morse 
     * @param index of en element from english array
     * @return "//" if index is 111, morse signt with the same index or null if index is valid
     */
    public String changeSignFromEnglish(int index) {
        if (index == 111) {
            return "/";
        } else if (index >= 0 && index < english.size()) { 
            return (morse.get(index) + "/");
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
    
    /**
     * function to check if the user input is correct
     * @param input - user input (text to translate)
     * @return true if input is correct
     */
    public boolean checkInputEnglish(String input) {
        char[] tmparr = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            if (signIndexEnglish(tmparr[i]) == -1) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * function to check if single morse sign is correct
     * @param input - user input (text to translate)
     * @return true if input is correct
     */
    public boolean checkSingleSignMorse (String input) {
        for (String element : morse ) {
            if (input.equals(element)) {
                return true;
            }
        }
        return false;
    }
}

