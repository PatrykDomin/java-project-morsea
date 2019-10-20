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
 */
public class SignsController {
    private Signs model;
    private SignsView view;
    Scanner scanner = new Scanner(System.in);
    
    public SignsController(Signs model, SignsView view) {
        this.model = model;
        this.view = view;
    }
    
    public String[] getEnglishSequence() {
        return model.getEnglishSequence();
    }
    
    public String[] getMorseSequence() {
        return model.getMorseSequence();
    }
    
    public void setUserChoose(String choose) {
        model.setUserChoose(choose);
    }
    
    public void setUserInput(String userInpt) {
        model.setUserInput(userInpt);
    }
    
    public void getUserChoose() {
        model.getUserChoose();
    }
    
    public void getUserInput() {
        model.getUserInput();
    }
    
    public void printUserInput() {
        view.printUserInput(model.getUserInput());
    }
    
    public void printUserChoose() {
        view.printUserChoose(model.getUserChoose());
    }
    
    public void checkChoose (String choose) throws TypeOfInputException {
        if (!choose.equals("morse") && !(choose.equals("english"))) {
            throw new TypeOfInputException("You can choose only 'morse' or 'english'");
        }
    }
    
    public void checkInput(String input) {
        // TODO regex 
   }
    
    public void scanForEverything() {
        try {
            System.out.println("What type of text do you want to translate (morse or english)?");
            String type = scanner.nextLine();
            this.checkChoose(type);
            setUserChoose(type);
            System.out.println("Write your text to translate");
            String userText = scanner.nextLine();
            setUserInput(userText); 
        }
        catch (TypeOfInputException myExc) {
            System.out.println(myExc.getMessage());
        }   
    }
}


