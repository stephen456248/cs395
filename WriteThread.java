
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


/**
 *
 * @author Stephen
 */
public class WriteThread extends Thread {
    
    PipedInputStream input;
    ObjectInputStream inputStream;
    int id;
    int[] numbers;
    
    public WriteThread(PipedOutputStream output, int [] numbers){
        this.numbers = numbers;
        this.id = 200;
        try{
            this.input = new PipedInputStream(output);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public void run(){
        int intToWrite;
        int index = 0;
        try{
            this.inputStream = new ObjectInputStream(this.input);
            intToWrite = this.inputStream.readInt();
            while(intToWrite != -1 && index < numbers.length){
                this.numbers[index] = intToWrite;
                index++;
                intToWrite = this.inputStream.readInt();
            }
            this.inputStream.close();
        } catch (IOException e) {
            System.out.print(e);
            System.out.println(this.id);
        }
    }
    
}
