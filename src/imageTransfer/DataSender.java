package imageTransfer;
import java.net.*;
import java.util.Date;
import java.io.*;

class DataSender extends Thread 
{
	//客户端的socket
	private Socket socket;    
	//线程统计数，用来给线程编号
    int cid = Constant.CLIENT_ID++;
	private DataOutputStream cout;
	private FileInputStream cin;
	//构造函数
	public DataSender(InetAddress addr, int port) throws IOException 
	{
		File file_in = new File(Constant.FILE_IN);
		long size = file_in.length();
		//实例化IO对象 
		cin = new FileInputStream(file_in);    
		System.out.println("The length of original file is: "+ cin.available());
		socket = new Socket(addr, port);
		cout = new DataOutputStream(socket.getOutputStream());
			
		//读取开始时间
		Date date = new Date();
		Long starttime = date.getTime();
		
		//开启线程
		start();
		
		//读取结束时间
		Date date1 = new Date();
		Long endtime = date1.getTime();
		Long duration = endtime-starttime;
		System.out.println("The send time of "+ cid +" is:"+duration);
     }
  
	//线程主体方法
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