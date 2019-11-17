/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morse.translator;
import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern; //to use in checking user input
/**
 *
 * @author Patryk Domin
 * @version 2.0
 * Controller
 */
public class SignsController {
    /**
     * connecting model and view in the controller
     */
    private Signs model;
    private SignsView view;
    Scanner scanner = new Scanner(System.in);
    
    public SignsController(Signs model, SignsView view) {
        this.model = model;
        this.view = view;
    }
    
    public void setUserChoose(String choose) {
        model.setUserChoose(choose);
    }
    
    public void setUserInput(String userInpt) {
        model.setUserInput(userInpt);
    }
    
    public String getUserChoose() {
        return model.getUserChoose();
    }
    
    public String getUserInput() {
        return model.getUserInput();
    }
    
    public void printUserInput() {
        view.printUserInput(model.getUserInput());
    }
    
    public void printUserChoose() {
        view.printUserChoose(model.getUserChoose());
    }
    
    public int signIndexEnglish(String sign) {
        return model.signIndexEnglish(sign);
    }
    
    public int signIndexMorse(String sign) {
        return model.signIndexMorse(sign);
    }
    
    public String changeSignFromEnglish(int index) {
        return model.changeSignFromEnglish(index);
    }
    
    public String changeSignFromMorse (int index) {
        return model.changeSignFromMorse(index);
    }
    
    /**
     * checking if user choose correct type of text (morse or english)
     * @param choose - correct: english or morse
     * @throws TypeOfInputException  when user give choose different than morse or english
    */
    public void checkChoose (String choose) throws TypeOfInputException {
        if (!choose.equals("morse") && !(choose.equals("english"))) {
            throw new TypeOfInputException("You can choose only 'morse' or 'english'");
        }
    }
    
    /**
     * function to check if the user input is correct
     * @param input - user input (text to translate)
     * @return true if input is correct
     */
    public boolean checkInputEnglish(String input) {
        char[] tmparr = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            String sign = String.valueOf(tmparr[i]);
            if (model.signIndexEnglish(sign) == -1) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * TODO: function to check if the user input is correct
     * @param input - user input (text to translate)
     * @return true if input is correct
     */
    public boolean checkInputMorse (String input) {
        return true;
    }
    
    /**
     * Scan inputs in case user don't give any args. 
    */
    public void scanForEverything() {
        try {
            System.out.println("What type of text do you want to translate (morse or english)?");
            String type = scanner.nextLine();
            this.checkChoose(type);
            setUserChoose(type);
            System.out.println("Write your text to translate (if you want to write morse text use '/' sign as a space between characters and '//' as a space between words");
            System.out.println("You can use signs like : '.', ',' or '?' ");
            String userText = scanner.nextLine();
            setUserInput(userText);
        }
        catch (TypeOfInputException myExc) {
            System.out.println(myExc.getMessage());
        }   
    }
}


