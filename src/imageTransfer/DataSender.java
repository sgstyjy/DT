package imageTransfer;
import java.net.*;
import java.util.Date;
import java.io.*;

class DataSender extends Thread 
{
	//�ͻ��˵�socket
	private Socket socket;    
	//�߳�ͳ�������������̱߳��
    int cid = Constant.CLIENT_ID++;
	private DataOutputStream cout;
	private FileInputStream cin;
	//���캯��
	public DataSender(InetAddress addr, int port) throws IOException 
	{
		File file_in = new File(Constant.FILE_IN);
		long size = file_in.length();
		//ʵ����IO���� 
		cin = new FileInputStream(file_in);    
		System.out.println("The length of original file is: "+ cin.available());
		socket = new Socket(addr, port);
		cout = new DataOutputStream(socket.getOutputStream());
			
		//��ȡ��ʼʱ��
		Date date = new Date();
		Long starttime = date.getTime();
		
		//�����߳�
		start();
		
		//��ȡ����ʱ��
		Date date1 = new Date();
		Long endtime = date1.getTime();
		Long duration = endtime-starttime;
		System.out.println("The send time of "+ cid +" is:"+duration);
     }
  
	//�߳����巽��
	public void run()
	{
		byte[] bb = new byte[Constant.TRANSFER_BUFFER];
		int sendsum =0;
		int send_length = 0;
		try {
			send_length = cin.read(bb, 0, Constant.TRANSFER_BUFFER);
			while(send_length>0){
				cout.write(bb, 0, send_length);
				cout.flush();
				sendsum += send_length;
				send_length=cin.read(bb, 0, Constant.TRANSFER_BUFFER);
			}
			socket.close();
			System.out.println("The data amount of is:"+sendsum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}