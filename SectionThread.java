
import java.io.IOException;
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
public class SectionThread extends Thread{
    PipedOutputStream output = new PipedOutputStream();
    int [] numbers;
    int id;
    public SectionThread(int[] numbers, int id){
        this.numbers = numbers;
        this.id = id;
        Arrays.sort(numbers, id*numbers.length/32, numbers.length/32 +id*numbers.length/32);
    }
    
    public PipedOutputStream output() {                //getter
        return this.output;
    }

    public void run(){
        int index = 0;
        try{
            for(int i=0; i<this.numbers.length/32; i++){
                this.output.write(this.numbers[i+this.numbers.length/32*this.id]);
            }
            this.output.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
    
}
