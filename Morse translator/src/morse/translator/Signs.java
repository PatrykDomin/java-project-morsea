/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morse.translator;

/**
 *
 * @author Student
 */

public class Signs {
    private String english;
    private String morse;
    
    public void setEnglish (String english) {
        this.english = english;
    }
    
    public void setMorseSequence (String morseS) {
        this.morse = morseS;
    }
    
    public String getEnglish() {
        return this.english;
    }
    
    public String getMorseSequence() {
        return this.morse;
    }
}
