
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;



/**
 *
 * @author Stephen
 */
public class MergeThread extends Thread {
    
    PipedInputStream input1;
    ObjectInputStream inputStream1;
    PipedInputStream input2;
    ObjectInputStream inputStream2;
    PipedOutputStream output = new PipedOutputStream();
    ObjectOutputStream outputStream;
    int id;
    
    public MergeThread (
        int id,
        PipedOutputStream output1,
        PipedOutputStream output2)
    {
        this.id = id;
        try{
            this.input1 = new PipedInputStream(output1);
            this.input2 = new PipedInputStream(output2);
        } catch (IOException e){
            System.out.println(e);
        }
    }
    
    public void run(){
        int left;
        int right;
        try{
            this.outputStream = new ObjectOutputStream(this.output);
            this.inputStream1 = new ObjectInputStream(this.input1);
            this.inputStream2 = new ObjectInputStream(this.input2);
            left = this.inputStream1.readInt();
            right = this.inputStream2.readInt();
            
            while(left != -1 || right != -1){
                if(left == -1){
                    //finish up right
                    this.outputStream.writeInt(right);
                    right = this.inputStream2.readInt();
                }else if(right == -1){
                    //finish up left
                    this.outputStream.writeInt(left);
                    left = this.inputStream1.readInt();
                }else if(left <= right){
                    this.outputStream.writeInt(left);
                    left = this.inputStream1.readInt();
                }else{
                    this.outputStream.writeInt(right);
                    right = this.inputStream2.readInt();
                }
            }
            this.outputStream.writeInt(-1);
            this.outputStream.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
    
    public PipedOutputStream output(){
        return this.output;
    }
    
}