
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;



/**
 *
 * @author Stephen
 */
public class MergeThread extends Thread {
    
    PipedInputStream input1; 
    PipedInputStream input2; 
    PipedOutputStream output = new PipedOutputStream();
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
            left = this.input1.read();
            right = this.input2.read();
            while(left != -1 || right != -1){
                if(left == -1){
                    //finish up right
                    this.output.write(right);
                    right = this.input2.read();
                }else if(right == -1){
                    //finish up left
                    this.output.write(left);
                    left = this.input1.read();
                }else if(left <= right){
                    this.output.write(left);
                    left = this.input1.read();
                }else{
                    this.output.write(right);
                    right = this.input2.read();
                }
            }
            this.output.close();
        }catch (IOException e){
            System.out.print(e);
            System.out.println(this.id);
        }
    }
    
    public PipedOutputStream output(){
        return this.output;
    }
    
}