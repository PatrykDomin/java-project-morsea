/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Patryk Domin
 * @version 3.0
 * MyException
 */
public class TypeOfInputException extends Exception {
    /**
     * @param msg the detail message.
     */
    public TypeOfInputException(String msg) {
        super(msg);
    }
}
