package backups;
import java.net.*;

import java.io.*;

class DataTransfer extends Thread 
{
  //�ͻ��˵�socket
  private Socket socket;    
  //�߳�ͳ�������������̱߳��
  private static int cnt = 0;
  private int clientId = cnt++;
  private BufferedReader in;
  private PrintWriter out;
  //���캯��
  public DataTransfer(InetAddress addr) 
  {
    try 
    {
      socket = new Socket(addr, 3333);
    }
    catch(IOException e) 
    {
          e.printStackTrace();
    }
    //ʵ����IO����
    try 
    {    
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));    
      out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
      //�����߳�
      start();
     } 
     catch(IOException e) 
     {
        //�����쳣���ر�socket 
          try 
          {
            socket.close();
          } 
          catch(IOException e2) 
          {
              e2.printStackTrace();       
          }
     }
}  
//�߳����巽��
public void run() 
{
    try 
    {
      out.println("Hello Server,My id is " + clientId );
      String str = in.readLine();
      System.out.println(str);
      out.println("byebye");
    } 
    catch(IOException e) 
    {
       e.printStackTrace();  
    }
    finally 
    {
      try 
      {
        socket.close();
      } 
      catch(IOException e) 
      {
        e.printStackTrace();
      }    
   }
 }
}