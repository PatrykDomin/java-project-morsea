/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Patryk Domin
 * @version 3.0
 */
public class MorseTranslatorView {
    /**
    * print user choose 
    * @param choose - correct: english or morse
    */
   public void printUserChoose(String choose) {
       System.out.println("User choose: " + choose);
   }
   
   /**
    * print user input
    * @param userInpt - text to translate
    */
   public void printUserInput(String userInpt) {
       System.out.println("User input: " + userInpt);
   }
   
   /**
    * question to the user about type of text to translate
    */
   public void printQuestion() {
       System.out.println("What type of text do you want to translate (morse or english)?");
   }
   
   /**
    * ask user to write a text to translate
    */
   public void printAskForInput() {
       System.out.println("Write your text to translate");
   }
}
