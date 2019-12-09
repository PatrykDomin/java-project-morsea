/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Scanner;

/**
 * @author Patryk Domin
 * @version 3.0
 */
public class Controller {
    /**
     * view
     */
    //private Signs model;
    private MorseTranslatorView view;
    
    /**
     * private scanner
     */
    private Scanner scanner = new Scanner(System.in);
    
    /*public SignsController(Signs model, SignsView view) {
        this.model = model;
        this.view = view;
    }*/
    /**
     * Constructor
     */
    public Controller() {}
    
    /**
     * set user choose (morse or english)
     * @param choose - user choose (correct: morse or english)
     */
//    public void setUserChoose(String choose) {
//        model.setUserChoose(choose);
//    }
    
    /**
     * set user input (text to translate)
     * @param userInpt - user input (text to translate)
     */
//    public void setUserInput(String userInpt) {
//        model.setUserInput(userInpt);
//    }
    
    /**
     * get user choose (morse or english)
     * @return user choose (correct: morse or english)
     */
//    public String getUserChoose() {
//        return model.getUserChoose();
//    }
    
    /**
     * get user input (text to translate)
     * @return user input (text to translate)
     */
//    public String getUserInput() {
//        return model.getUserInput();
//    }
    
    /**
    * print user choose 
    */
//    public void printUserInput() {
//        view.printUserInput(model.getUserInput());
//    }
    
    /**
    * print user input
    */
//    public void printUserChoose() {
//        view.printUserChoose(model.getUserChoose());
//    }
    
     /**
    * question to the user about type of text to translate
    */
//   public void printQuestion() {
//       view.printQuestion();
//   }
    
    /**
     * get index of a sign in array
     * @param sign - a sign to get an index from
     * @return index as int, -1 if no sign will be found, 111 if sign is a spacebar
     */
//    public int signIndexEnglish(String sign) {
//        return model.signIndexEnglish(sign);
//    }
    
    /**
     * get index of a sign in array
     * @param sign - a sign to get an index from (sign could be longer due to the morse notation
     * @return index as int, -1 if no sign will be found, 111 if sign is a '//' (spacebar in morse) or 222 if a sing is '/' (space between characters)
     */
//    public int signIndexMorse(String sign) {
//        return model.signIndexMorse(sign);
//    }
    
    /**
     * replace one single character in english with its equivalent in morse 
     * @param index of en element from english array
     * @return space if index is 111, morse signt with the same index or null if index is valid
     */
//    public String changeSignFromEnglish(int index) {
//        return model.changeSignFromEnglish(index);
//    }
    
    /**
     * replace one single character in morse with its equivalent in english 
     * @param index of en element from morse array
     * @return space if index is 111, english sign with the same index or null if index is valid
     */
//    public String changeSignFromMorse (int index) {
//        return model.changeSignFromMorse(index);
//    }
    
    /**
     * checking if user choose correct type of text (morse or english)
     * @param choose - correct: english or morse
     * @throws TypeOfInputException  when user give choose different than morse or english
    */
//    public void checkChoose (String choose) throws TypeOfInputException {
//        if (!choose.equals("morse") && !(choose.equals("english"))) {
//            throw new TypeOfInputException("You can choose only 'morse' or 'english'");
//        }
//    }
    
    /**
     * function to check if the user input is correct
     * @param input - user input (text to translate)
     * @return true if input is correct
     */
//    public boolean checkInputEnglish(String input) {
//        char[] tmparr = input.toCharArray();
//        for (int i = 0; i < input.length(); i++) {
//            String sign = String.valueOf(tmparr[i]);
//            if (model.signIndexEnglish(sign) == -1) {
//                return false;
//            }
//        }
//        return true;
//    }
    
    /**
     * TODO: function to check if the user input is correct
     * @param input - user input (text to translate)
     * @return true if input is correct
     */
//    public boolean checkInputMorse (String input) {
//        char[] temparr = input.toCharArray();
//        String signSequence = "";
//        for (int i=0; i<input.length(); i++) {
//             if (temparr[i] == '/') {
//                 signSequence = "";
//                 if (i + 1 == input.length()) {
//                     return true;
//                 }
//             }
//            signSequence += String.valueOf(temparr[i]);
//        }
//        return false;
//    }
    
    /**
     * Scan inputs in case user don't give any args. 
    */
    public void scanForEverything() {
        view.printQuestion();
        String type = scanner.nextLine();
//        try {
            //this.checkChoose(type);
            //setUserChoose(type);
            //asking user for text to translate until the text is correct
                view.printAskForInput();
                switch (type) {
                    case "english":
                        System.out.println("You can use signs like : '.', ',' or '?', all characters and numbers");
                        break;
                    case "morse":
                        System.out.println("Use '/' sign as a space between characters and '//' as a space between words. You can use all characters, ");
                        break;
                    default:
                        break;
                }
                String userInput = scanner.nextLine().toLowerCase();
                //setUserInput(userText);
//        }
//        catch (TypeOfInputException myExc) {
//            System.out.println(myExc.getMessage());
//        }   
    }
}
