/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjack;

import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Ran Ren
 */
// This class is a singleton that handles all I/O logic
public class IOHandler {
    private static IOHandler single_instance = null;
    
    private IOHandler()
    {
        
    }
    
    // Static method to get instance of IOHandler
    public static synchronized IOHandler getInstance()
    {
        if (single_instance == null) {
            single_instance = new IOHandler();
        }
        
        return single_instance;
    }
    
    // Gets a string from user input
    public String getString(InputStream input) {
        Scanner scanner = new Scanner(input);
        String string = scanner.nextLine();
        return string;
    }
    
    // Gets user to select an option from input
    public String selectOption(InputStream input, String[] options) {
        Scanner scanner = new Scanner(input);
        Boolean inputValid = false;
        while(!inputValid) {
            String selection = scanner.nextLine();
            for(String option : options) {
                if (selection.equals(option)) {
                    return selection;
                }
            }
            System.out.println("Invalid selection! please try again");
        }
        return null;
    }
}
