package imageTransfer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerCode 
{
	public static void main(String[] args) throws IOException 
    {
       //�������˵�socket
       ServerSocket s = new ServerSocket(Constant.PORT1);
       System.out.println("The outputs are from the Server: ");    
       //��ȡ��ʼʱ��
       Date date = new Date();
       Long starttime = date.getTime();
       try 
       {
          // for(;;)                          
           //{
              //����,ֱ���пͻ�������
              Socket socket = s.accept();
              //ͨ�����캯���������߳�
              new DataReceiver(socket);
              
              s.close();
              //��ȡ����ʱ��
              Date date1 = new Date();
              Long endtime = date1.getTime();
              Long duration = endtime-starttime;
              System.out.println("The receive time of is:"+duration);
         //  }
       }
       finally 
       {
             return;
       }
   }
}