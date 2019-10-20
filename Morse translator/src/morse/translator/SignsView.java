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
public class SignsView {   
   /**
    * print user choose (correct: english or morse)
    */
   public void printUserChoose(String choose) {
       System.out.println("User choose: " + choose);
   }
   
   /**
    * print user input (text to translate)
    */
   public void printUserInput(String userInpt) {
       System.out.println("User input: " + userInpt);
   }
}
