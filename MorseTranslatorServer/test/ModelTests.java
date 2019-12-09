/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import server.MorseTranslatorModel;

/**
 * @author Patryk Domin
 * @version 2.0
 * Tests
 */
public class ModelTests {
     
     MorseTranslatorModel instance = new MorseTranslatorModel();
     String sign;
     int index;
     
    /**
     * Test of signIndexEnglish method, of class Signs.
     * @author Patryk Domin
     * @version 2.0 
     */
    @Test
    public void testSignIndexEnglish() {
        /**
         * sign: incorrect
         * expected: -1
         */
        try {
            sign = "";
            int result = instance.signIndexEnglish(sign);
            int expected = -1;
            assertThat(result, is(expected));
        } catch (Exception e) {
            
        }
        /**
         * sign: correct
         * expected: 111
         */
        try {
            sign = " ";
            int result = instance.signIndexEnglish(sign);
            int expected = 111;
            assertThat(result, is(expected));
        } catch (Exception e) {
            
        }
        /**
         * sign: correct
         * expected: 0
         */
        try {
           sign = "a";
           int result = instance.signIndexEnglish(sign);
           int expected = 0;
           assertThat(result, is(expected));
        } catch (Exception e) {
            
        }
        /**
         * sign: incorrect
         * expected: -1
         */
        try {
           sign = "WrongOne";
           int result = instance.signIndexEnglish(sign);
           int expected = -1;
           assertThat(result, is(expected));
        } catch (Exception e) {
            
        }
    }

    /**
     * Test of signIndexMorse method, of class Signs.
     * @author Patryk Domin
     * @version 2.0
     */
    @Test
    public void testSignIndexMorse() {
        /**
         * sign: incorrect
         * expected: -1
         */
         try {
            sign = "";
            int result = instance.signIndexMorse(sign);
            int expected = -1;
            assertThat(result, is(expected));
        } catch (Exception e) {
            
        }
        /**
         * sign: correct
         * expected: 222
         */
        try {
            sign = "/";
            int result = instance.signIndexMorse(sign);
            int expected = 222;
            assertThat(result, is(expected));
        } catch (Exception e) {
            
        }
        /**
         * sign: correct
         * expected: 1
         */
        try {
            sign = "-...";
            int result = instance.signIndexMorse(sign);
            int expected = 1;
            assertThat(result, is(expected));
        } catch (Exception e) {
            
        }
        /**
         * sign: correct
         * expected: 111
         */
        try {
            sign = "//";
            int result = instance.signIndexMorse(sign);
            int expected = 111;
            assertThat(result, is(expected));
        } catch (Exception e) {
            
        }
        /**
         * sign: incorrect
         * expected: -1
         */
        try {
            sign = "abc";
            int result = instance.signIndexMorse(sign);
            int expected = -1;
            assertThat(result, is(expected));
        } catch (Exception e) {
            
        }
        /**
         * sign: incorrect
         * expected: -1
         */
        try {
            sign = "-.-.-.-.";
            int result = instance.signIndexMorse(sign);
            int expected = -1;
            assertThat(result, is(expected));
        } catch (Exception e) {
            
        }
    }
    
    /**
     * Test of changeSignFromEnglish method, of class Signs.
     * @author Patryk Domin
     * @version 2.0 
     */    
    @Test
    public void testChangeSignFromEnglish() {
        /**
         * index: correct
         * expected: " "
         */
        try {
        index = 111;
        String result = instance.changeSignFromEnglish(index);
        String expected = " ";
        assertThat(result, is(expected));
        } catch (Exception e) {
        
        } 
        /**
         * index: incorrect
         * expected: null
         */
        try {
        index = -1;
        String result = instance.changeSignFromEnglish(index);
        String expected = null;
        assertThat(result, is(expected));
        } catch (Exception e) {
        
        } 
        /**
         * index: correct
         * expected: "-.-."
         */
        try {
        index = 2;
        String result = instance.changeSignFromEnglish(index);
        String expected = "-.-.";
        assertThat(result, is(expected));
        } catch (Exception e) {
        
        } 
        /**
         * index: incorrect
         * expected: null
         */
        try {
        index = 99;
        String result = instance.changeSignFromEnglish(index);
        String expected = null;
        assertThat(result, is(expected));
        } catch (Exception e) {
        
        } 
    }

    /**
     * Test of changeSignFromMorse method, of class Signs.
     * @author Patryk Domin
     * @version 2.0 
     */
    @Test
    public void testChangeSignFromMorse() {
        /**
         * index: correct
         * expected: " "
         */
        try {
        index = 111;
        String result = instance.changeSignFromMorse(index);
        String expected = " ";
        assertThat(result, is(expected));
        } catch (Exception e) {
        
        } 
        /**
         * index: incorrect
         * expected: null
         */
        try {
        index = -1;
        String result = instance.changeSignFromMorse(index);
        String expected = null;
        assertThat(result, is(expected));
        } catch (Exception e) {
        
        } 
        /**
         * index: correct
         * expected: "c"
         */
        try {
        index = 2;
        String result = instance.changeSignFromMorse(index);
        String expected = "c";
        assertThat(result, is(expected));
        } catch (Exception e) {
        
        } 
        /**
         * index: incorrect
         * expected: null
         */
        try {
        index = 99;
        String result = instance.changeSignFromMorse(index);
        String expected = null;
        assertThat(result, is(expected));
        } catch (Exception e) {
        
        } 
        /**
         * index: correct
         * expected: ""
         */
        try {
        index = 222;
        String result = instance.changeSignFromMorse(index);
        String expected = "";
        assertThat(result, is(expected));
        } catch (Exception e) {
        
        }
    }
    
}
