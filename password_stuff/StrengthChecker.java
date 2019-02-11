/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password_stuff;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * Checks to see if a password fits the minimum requirements
 * @author Matts
 */
public class StrengthChecker {

    ArrayList<String> required = new ArrayList<String>();
    private int minimum;
    private int maximum;
    private boolean containsSymbol = false;
    private boolean lengthCorrect = false;

    
    
    /**
     * Sets the list of required special characters
     * in required.
     * @param args 
     */
    public void setRequired(String[] args) {
        required.addAll(Arrays.asList(args));
    }
    
    /**
     * Sets the minimum and maximum length of the password
     * @param min
     * @param max 
     */
    public void setLength(int min, int max) {
        minimum = min;
        maximum = max;
    }
    
    /**
     * Checks to make sure password fulfills minimum requirements
     * @param password
     * @return 
     */
    public boolean checkPassword(String password){
        for(String sym : required) {
            if(password.contains(sym) == true){
                containsSymbol = true;
            }
        }
        if(password.length() < maximum && password.length() > minimum) {
            lengthCorrect = true;
        }
        if(containsSymbol == true && lengthCorrect == true) {
            return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * Returns a string 
     * @return 
     */
    public String getErrors(){
        String error = "";
        if(containsSymbol == false) {
            error += "Special character missing\n";
        }
        if(lengthCorrect == false){
            error += String.format("Length must be between %d and %d characters long", minimum, maximum);
        }
        if(containsSymbol == true && lengthCorrect == true){
            error = "No errors.";
        }
        return error;
    }
    
    
}
