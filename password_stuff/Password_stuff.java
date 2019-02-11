package password_stuff;

import java.util.Scanner;
/**
 * Just used for it's main method
 * @author Matt Scott
 */
public class Password_stuff extends StrengthChecker {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new StrengthChecker object
        StrengthChecker sc = new StrengthChecker();
        
        //list of special characters
        String[] chars = {"!", "@", "#", "$", "%"};
        
        //setting the characters in StrengthChecker
        sc.setRequired(chars);
        //setting the minimum and maximum length
        sc.setLength(6, 12);
        
        //set password here in the quotes
        String password = "dgajdsk!";
                
        if(sc.checkPassword(password)==true){
            System.out.println("Password is fine.");
        }else{
            System.out.println(sc.getErrors());
        }
        
        
    }
    
}
