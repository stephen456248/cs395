


/**
 *
 * @author Stephen
 */

import java.util.*;
import java.util.Scanner;
import java.lang.Object;
import java.io.InputStream;
import java.lang.Object;
import java.io.InputStream;
import java.io.IOException;



public class Thread_Pipe {
    public static int [] numbers;
    
    public static void generateNumbers(){
        Random random = new Random();
        System.out.print("How many integers do you want?  " );
        int num = 0;
        num = getNumberToGenerate();
        while(num < 1000000){
            System.out.println("The number needs to be larger than 1,000,000 ");
            System.out.print("How many integers do you want?  " );
            num = getNumberToGenerate();
        }
        numbers = new int [num];
        for(int i=0; i<num; i++){
            numbers[i] = random.nextInt(1000000)+1;
        }
    }
    
    public static int getNumberToGenerate(){        
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        return num;
    }
    
    public static void shuffleData(){
        Random random = new Random();
        for(int i=0; i<numbers.length; i++){
            swap(i, random.nextInt(numbers.length));
        }
    }
    
    private static void swap(int index, int randomIndex){
        int tempInt = numbers[index];
        numbers[index] = numbers[randomIndex];
        numbers[randomIndex] = tempInt;
    }

    public static void displayArrayContents(){
        int index = 0;
        boolean keepPrintingPages = true;
        boolean keepPrintingNumbers = true;
        
        while(keepPrintingPages && index<numbers.length){
            for(int line=0; line<20; line++){
                for(int cell=0; cell<10; cell++){
                    if(index >= numbers.length){
                        keepPrintingNumbers = false;
                    }
                    if(keepPrintingNumbers){
                        System.out.print(numbers[index]);
                        index++;
                        if(cell < 9){
                            System.out.print(" ");
                        }
                    }else{
                        keepPrintingPages = false;
                    }
                }
                if(keepPrintingNumbers){
                    System.out.println();
                }
            }
            System.out.println();
            keepPrintingPages = checkIfContinue();
        }
        //print last 3
        if(keepPrintingNumbers){
            if(numbers.length - index >= 30){
                int tempIndex = 0;
                for(int line = 0; line<3; line++){
                    for(int cell = 0; cell<10; cell++){
                        System.out.print(numbers[numbers.length - 30 + tempIndex]);
                        System.out.print(" ");
                        tempIndex++;
                    }
                    System.out.println();
                }
            }else{
                
                for(int line = 0; line<3; line++){
                    for(int cell = 0; cell<10; cell++){
                        if(keepPrintingNumbers){
                            System.out.print(numbers[index]);
                            System.out.print(" ");
                            index++;
                        }
                        if(index >= numbers.length){
                            keepPrintingNumbers = false;
                        }
                    }
                    System.out.println();
                }
            }
        }
    } 
    
    private static boolean checkIfContinue(){
        
        System.out.print("Press E/e or Q/q to quit or enter for the next 20: ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if(line.isEmpty()){
            return true;
        }else if(Character.toLowerCase(line.charAt(0)) == 'q' ||
                 Character.toLowerCase(line.charAt(0)) == 'e'){
            return false;
        }else{
            return true;
        }
    }

    public static void sortTheArray(){
        //Arrays.sort(numbers);
        
        Thread[] sectionThreads = new Thread[32];
        for(int i=0; i<sectionThreads.length; i++){
            sectionThreads[i] = new Thread(new SectionThread(numbers, i));
        }
        
        
        
        
        //PipedInputStream;
        //PipedOutputStream;
    }
}











