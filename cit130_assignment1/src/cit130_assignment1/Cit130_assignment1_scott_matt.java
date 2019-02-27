/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cit130_assignment1;

/**
 *
 * @author Matts
 */
import java.util.*;
import javax.swing.JOptionPane;

public class Cit130_assignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        functions();
    }
    
    public static void functions() {
        String[] choices = {"Inch Conversion", "Feet Conversion", "Yard Conversion",
            "Square Yard Conversion", "Square Mile Conversion", "Cubic Feet Conversion",
            "Cubic Yard Conversion", "Ounce Conversion", "Pound Conversion", "Pint Conversion",
            "Quart Conversion"};
        String choice = (String) JOptionPane.showInputDialog(null, "Chose One", "Choices", 
                JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
        numberIn(choice);
    }
    
    
    public static void numberIn(String choice) {
        double input_number = Double.parseDouble(JOptionPane.showInputDialog("Enter the number. "));
        switch_statement(choice, input_number);
    }
    
    
    public static void switch_statement(String choice, double number) {
        switch (choice) {
            case "Inch Conversion": lengthInches(number);
            break;
            case "Feet Conversion": lengthFeet(number);
            break;
            case "Yard Conversion": lengthYards(number);
            break;
            case "Square Yard Conversion": areaSquareYards(number);
            break;
            case "Square Mile Conversion": areaSquareMiles(number);
            break;
            case "Cubic Feet Conversion": areaCubicFeet(number);
            break;
            case "Cubic Yard Conversion": areaCubicYards(number);
            break;
            case "Ounce Conversion": weightOunces(number);
            break;
            case "Pound Conversion": weightPounds(number);
            break;
            case "Pint Conversion": volumePints(number);
            break;
            case "Quart Conversion": volumeQuarts(number);
            break;
    }}

        
    
    
    public static void lengthInches(double inches) {
        double centimeters, meters, feet, yards, kilometers;
        String s_centimeters, s_meters, s_feet, s_yards, s_kilometers;
        s_centimeters = "Centimeters";
        s_meters = "Meters";
        s_feet = "Feet";
        s_yards = "Yards";
        s_kilometers = "Kilometers";
        centimeters = inches * 2.54;
        meters = centimeters / 100;
        feet = inches * 0.08333333333;
        yards = feet / 3;
        kilometers = centimeters * .000001;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_centimeters, centimeters);
        returnValues.put(s_meters, meters);
        returnValues.put(s_feet, feet);
        returnValues.put(s_yards, yards);
        returnValues.put(s_kilometers, kilometers);
        
        Responses(returnValues);
        
    }
    
    public static void lengthFeet(double feet) {
        double meters, yards, kilometers, miles;
        String s_meters, s_yards, s_kilometers, s_miles;
        s_meters = "Meters";
        s_yards = "Yards";
        s_kilometers = "Kilometers";
        s_miles = "Miles";
        yards = feet / 3;
        meters = feet / 0.3048;
        kilometers = feet / 0.0003048;
        miles = feet / 0.000189394;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_meters, meters);
        returnValues.put(s_yards, yards);
        returnValues.put(s_kilometers, kilometers);
        returnValues.put(s_miles, miles);
        
        Responses(returnValues);
    }
    
    public static void lengthYards(double yards){
        String s_meters = "Meters";
        String s_feet = "Feet";
        String s_kilometers = "Kilometers";
        String s_miles = "Miles";
        double meters = yards * 0.9144;
        double feet = yards * 3;
        double kilometers = meters * .0001;
        double miles = yards * 0.000568182;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_meters, meters);
        returnValues.put(s_feet, feet);
        returnValues.put(s_kilometers, kilometers);
        returnValues.put(s_miles, miles);
        Responses(returnValues);
    }
    
    public static void areaSquareYards(double square_yards) {
        String s_square_inches = "Square Inches";
        String s_square_meters = "Square Meters";
        String s_square_feet = "Square Feet";
        double square_feet = square_yards * 3;
        double square_meters = square_yards * 0.836127;
        double square_inches = square_yards * 1295.99944199424;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_square_inches, square_inches);
        returnValues.put(s_square_meters, square_meters);
        returnValues.put(s_square_feet, square_feet);
        Responses(returnValues);
    }
    
    public static void areaSquareMiles(double square_miles) {
        String s_square_inches = "Square Inches";
        String s_square_yards = "Square Yards";
        String s_square_feet = "Square Feet";
        double square_inches = square_miles * 4014488909.93;
        double square_yards = square_miles * 3097599.55;
        double square_feet = square_miles * 27878395.93;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_square_inches, square_inches);
        returnValues.put(s_square_yards, square_yards);
        returnValues.put(s_square_feet, square_feet);
        Responses(returnValues);       
    }
        

    public static void areaCubicFeet(double cubic_feet){
        String s_cubic_inches = "Cubic Inches";
        String s_cubic_meters = "Cubic Meters";
        String s_cubic_yards = "Cubic Yards";
        double cubic_inches = cubic_feet * 12;
        double cubic_yards = cubic_feet / 3;
        double cubic_meters = cubic_yards * 0.764555;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_cubic_inches, cubic_inches);
        returnValues.put(s_cubic_yards, cubic_yards);
        returnValues.put(s_cubic_meters, cubic_meters);
        Responses(returnValues);

    }
    
    public static void areaCubicYards(double cubic_yards) {
        String s_cubic_inches = "Cubic Inches";
        String s_cubic_meters = "Cubic Meters";
        String s_cubic_feet = "Cubic Feet";
        double cubic_inches = cubic_yards * 36;
        double cubic_feet = cubic_yards / 3;
        double cubic_meters = cubic_yards * 0.764555;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_cubic_inches, cubic_inches);
        returnValues.put(s_cubic_meters, cubic_meters);
        returnValues.put(s_cubic_feet, cubic_feet);
        Responses(returnValues);
    }
    
    public static void weightOunces(double ounces) {
        String s_kilograms = "Kilograms";
        String s_pounds = "Pounds";
        String s_grams = "Grams";
        double pounds = ounces * 0.0625;
        double kilograms = ounces * 0.0283495;
        double grams = ounces * 28.3495;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_kilograms, kilograms);
        returnValues.put(s_grams, grams);
        returnValues.put(s_pounds, pounds);
        Responses(returnValues);
    }
    
    public static void weightPounds(double pounds) {
        String s_ounces = "Ounces";
        String s_kilograms = "Kilograms";
        String s_grams = "Grams";
        double ounces = pounds * 16;
        double kilograms = pounds * 0.453592;
        double grams = pounds * 453.5920000001679;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_kilograms, kilograms);
        returnValues.put(s_grams, grams);
        returnValues.put(s_ounces, ounces);
        Responses(returnValues);
        
    }
    
    public static void volumePints(double pints) {
        //Ounces, Cups, Quarts, and Gallons 
        String s_ounces = "Ounces";
        String s_cups = "Cups";
        String s_quarts = "Quarts";
        String s_gallons = "Gallons";
        double ounces = pints * 16;
        double cups = pints * 2;
        double quarts = pints * .5;
        double gallons = pints * .125;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_ounces, ounces);
        returnValues.put(s_cups, cups);
        returnValues.put(s_quarts, quarts);        
        returnValues.put(s_gallons, gallons);
        Responses(returnValues);
    }
    
    public static void volumeQuarts(double quarts) {
        //Ounces, Pints, Cups, and Gallons 
        String s_ounces = "Ounces";
        String s_pints = "Pints";
        String s_cups = "Cups";
        String s_gallons = "Gallons";
        double ounces = quarts * 32;
        double pints = quarts * 2;
        double cups = quarts * 4;
        double gallon = quarts * .25;
        
        Map<String, Double> returnValues = new HashMap<>();
        returnValues.put(s_ounces, ounces);
        returnValues.put(s_pints, pints);
        returnValues.put(s_cups, cups);        
        returnValues.put(s_gallons, gallon);
        Responses(returnValues);
    }
    
    public static void Responses(Map returned_values){
        Set entries = returned_values.entrySet();
        Iterator iterator = entries.iterator();
        String output = "";
        while(iterator.hasNext()){
            Map.Entry value = (Map.Entry)iterator.next();
            //output += (value.getKey() + ": " + String.valueOf(value.getValue()) + "\n");
            output += String.format("%s: %.5f\n", value.getKey(), value.getValue());
        }
        JOptionPane.showMessageDialog(null, output);
    }
    
}

