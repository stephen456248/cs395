
import java.io.PipedOutputStream;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stephen
 */
public class SectionThread implements Runnable{
    PipedOutputStream output = new PipedOutputStream();
    public SectionThread(int[] numbers, int id){
        Arrays.sort(numbers, id*numbers.length/32, numbers.length/32 +id*numbers.length/32);
    }
    
    public PipedOutputStream output() {                //getter
        return this.output;
    }

    public void run(){

    }
    
}
