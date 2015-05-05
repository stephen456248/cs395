
/**
 *
 * @author Stephen
 */
import java.util.*;
import java.util.Random;
import java.util.Scanner;
import java.lang.Object;
import java.io.InputStream;

public class SMell04 {

    private static void displayMenu(){
        
        System.out.println(
            "+----------------------------------------------------------------------------|\n" +
            "|          Merge Sort with Multi Threads and Piped Input and Output          |\n" +
            "+----------------------------------------------------------------------------+\n" +
            "| G/G: Ask a integer N >= 1,000,000, and generate a integer array of N       |\n" +
            "|      elements, and fill the array with N integers (int).                   |\n" +
            "+----------------------------------------------------------------------------+\n" +
            "| C/c: Display the contents of array, 10 integers per line, and 20 lines     |\n" +
            "|      screen. Allow quit during the display. If it quits in the middle,     |\n" +
            "|      the last 3 lines must be display.                                     |\n" +
            "+----------------------------------------------------------------------------+\n" +
            "| S/s: Sort the array using multithreaded piped I/O algorithm described above|\n" +
            "+----------------------------------------------------------------------------+\n" +
            "| F/f: Shuffle the data.                                                     |\n" +
            "+----------------------------------------------------------------------------+\n" +
            "| H//h/?: Show this menu.                                                    |\n" +
            "+----------------------------------------------------------------------------+\n" +
            "| E/Q: Exit or quit the program.                                             |\n" + 
            "+----------------------------------------------------------------------------+\n" 
        );
    }        
                 
    private static boolean switchInput(char input){
           boolean keepLooping = true;
           switch(input){
               case 'g':
                   Thread_Pipe.generateNumbers();
                   break;
               case 'f':
                   Thread_Pipe.shuffleData();
                   break;
               case 'c':
                   Thread_Pipe.displayArrayContents();
                   break;
               case 's':
                   Thread_Pipe.sortTheArray();
                   break;
               case 'h':
                   displayMenu();
                   break;
               case 'q':
               case 'e':
                   keepLooping = false;
                   break;
               default:
                   System.out.print("Please enter a valid option.");
                   displayMenu();
           }
           return keepLooping;
       }
    
    
    
    
    public static void main(String[] args) {
        displayMenu();
        char input = getInput();
        switchInput(input);
        boolean keepLooping = true;
        while(keepLooping){
            input = getInput();
            keepLooping = switchInput(input);
        }   
    }
    
    public static char getInput(){
        System.out.print("Your next selection (H/h/? for full menu) : ");
        Scanner scanner = new Scanner (System.in);
        char input = scanner.next().charAt(0);
        return input;
    }
    
}
