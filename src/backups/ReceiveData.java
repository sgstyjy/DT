package backups;
import java.io.*;
import java.net.*;

class ReceiveData extends Thread 
{
       //�ͻ��˵�socket
       private Socket clientSocket;
       //IO���
       private BufferedReader sin;
       private PrintWriter sout;    
       public ReceiveData(Socket s) throws IOException 
       {
              clientSocket = s;            
              //��ʼ��sin��sout�ľ��
              sin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
              sout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);             
              //�����߳�
              start(); 
       }
       //�߳�ִ�е����庯��
       public void run() 
       {
              try 
              {
                    //��ѭ��������ͨѶ����
                     for(;;) 
                     {
                    	 String str = sin.readLine();
                    	 //������յ�����byebye���˳�����ͨѶ
                         if (str.equals("byebye"))
                        	 break;
                         System.out.println("In Server reveived the info: " + str);
                         sout.println(str);
                     }
                     System.out.println("closing the server socket!");
              } 
              catch (IOException e) 
              {
                     e.printStackTrace();
              }
              finally 
              {
                     System.out.println("close the Server socket and the io.");
                     try 
                     {
                            clientSocket.close();
                     } 
                     catch (IOException e) 
                     {
                            e.printStackTrace();
                     }
              }
       }
}