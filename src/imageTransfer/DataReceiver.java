package imageTransfer;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DataReceiver extends Thread {
    //IO句柄
    private DataInputStream sin;
    private FileOutputStream sout1;    
    //private FileOutputStream sout2;    
    public DataReceiver(Socket clientSocket) throws IOException 
    {          
           //初始化sin和sout的句柄
           sin = new DataInputStream(clientSocket.getInputStream());
           sout1 = new FileOutputStream(Constant.FILE_OUT); 
          // sout2 = new FileOutputStream(Constant.BLOCKNUM_OUT); 
           //开启线程
           start(); 
    }
    //线程执行的主体函数
    public void run() 
    {
    	byte[] bb = new byte[Constant.TRANSFER_BUFFER];
		int receive_length = 0;
		int receive_blocks =0;
		int receivesum = 0;
        try 
        {
           receive_length = sin.read(bb, 0, Constant.TRANSFER_BUFFER);
           while(receive_length>0){
        	   sout1.write(bb, 0, receive_length);
        	   sout1.flush();
        	   //sout2.write(b);
        	   receive_blocks++;
        	   receivesum += receive_length;
        	   receive_length = sin.read(bb, 0, Constant.TRANSFER_BUFFER);
           }
           sin.close();
           sout1.close();
           System.out.println("The blocks received are: "+receive_blocks);
           System.out.println("The bytes received are: "+receivesum);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
