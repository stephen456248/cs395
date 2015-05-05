
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    ObjectOutputStream outputStream;
    int [] numbers;
    int id;
    public SectionThread(int[] numbers, int id){
        this.numbers = numbers;
        this.id = id;
        Arrays.sort(numbers, id*numbers.length/32, numbers.length/32 +id*numbers.length/32);
    }
    
    public PipedOutputStream output() {
        return this.output;
    }

    public void run(){
        int index = 0;
        try{
            this.outputStream = new ObjectOutputStream(this.output);
            for(int i=0; i<this.numbers.length/32; i++){
                this.outputStream.writeInt(this.numbers[i+this.numbers.length/32*this.id]);
            }
            this.outputStream.writeInt(-1);
            this.outputStream.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
    
}
