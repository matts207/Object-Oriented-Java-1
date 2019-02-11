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
        StrengthChecker sc = new StrengthChecker();
        
        String[] chars = {"!", "@", "#", "$", "%"};
        sc.setRequired(chars);
        //setLength(max, min)
        sc.setLength(6, 12);
        System.out.println(sc.checkPassword("Hgssg$!hhhh"));
        System.out.println(sc.getErrors());
    }
    
}
