/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morse.translator;

/**
 *
 * @author Patryk Domin
 * @version 2.0
 */
public class MorseTranslator {

    /**
     * enum with two values (correct user choose inputs)
     */
    public enum typeOfTranslation {
        english, morse
    }
       
    
    /**
     * @param args the command line arguments
     * 1st - morse or english (you choose what type of text do you want to translate)
     * 2nd - text to translate (text should be between " " signs)
     * Example args: english "text to translate"
     */
    public static void main(String[] args) {
        Signs model = new Signs();
        SignsView view = new SignsView();
        SignsController controller = new SignsController(model, view);
        
        
        switch (args.length) {
            case 0:
                controller.scanForEverything();
                break;
            case 2:
                try {
                    controller.checkChoose(args[0]); 
                    controller.setUserChoose(args[0]);
                    controller.setUserInput(args[1]);
                    controller.printUserChoose();
                    controller.printUserInput();
                }
                catch (TypeOfInputException myExc) {
                    System.out.println(myExc.getMessage());
                }
                break;
            default:
                System.err.println("There should be 0 or 2 parameter");
                break;
        }
        
        typeOfTranslation tot = typeOfTranslation.valueOf(controller.getUserChoose());
        
        switch (tot) {
            case english: 
                System.out.println("You choose english");
                break;
            case morse:
                System.out.println("You choose morse");
                break;
            default:
                break;
        }
        
    }
}