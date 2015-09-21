package imageTransfer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerCode 
{
	public static void main(String[] args) throws IOException 
    {
       //服务器端的socket
       ServerSocket s = new ServerSocket(Constant.PORT1);
       System.out.println("The outputs are from the Server: ");    
       //读取开始时间
       Date date = new Date();
       Long starttime = date.getTime();
       try 
       {
           for(;;)                          
           {
              //阻塞,直到有客户端连接
              Socket socket = s.accept();
              //通过构造函数，启动线程
              new DataReceiver(socket);
           }
       }
       finally 
       {
          s.close();
          //读取结束时间
          Date date1 = new Date();
          Long endtime = date1.getTime();
          Long duration = endtime-starttime;
          System.out.println("The receive time of is:"+duration);
       }
   }
}