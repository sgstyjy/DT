package imageTransfer;
import java.net.*;
import java.util.Date;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

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
	public DataSender(InetAddress addr, int port) throws IOException, BiffException 
	{
		//实例化IO对象 
		//File file_in = new File(Constant.FILE_IN);
		//cin = new FileInputStream(file_in);    
		RandomAccessFile cin = new RandomAccessFile(Constant.FILE_IN,"r");
		long size = cin.length();
		System.out.println("The length of original file is: "+ size);
		
		socket = new Socket(addr, port);
		socket.setSoTimeout(6000);
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
		int i =0;
		int sendsum =0;
		int send_length = 0;
		try {
				//读取相似度表
				File file_in2 = new File(Constant.COMPARE_U12_U14WEB);
				//InputStream hashtable2 = new FileInputStream(file_in2);
				Workbook book = Workbook.getWorkbook(file_in2);
				Sheet sheet = book.getSheet(0);
		
	    		while(i<Constant.TOTALBLOCKS){
	    					String tempstr = sheet.getCell(i/Constant.COLUMNS, i%Constant.COLUMNS).getContents();
	    					if(tempstr!=null){
	    						    long templ = Long.parseLong(tempstr);
	    						    cin
	    					}
	    					send_length = cin.read(bb, 0, Constant.TRANSFER_BUFFER);
	    					while(send_length>0){
	    							//cout.write( intToByteArray(i));
	    							cout.write(bb, 0, send_length);
	    							cout.flush();
	    							sendsum += send_length;
	    							send_length=cin.read(bb, 0, Constant.TRANSFER_BUFFER);
	    							i++;
	    					}
				socket.close();
				System.out.println("The data blocks are:"+i);
				System.out.println("The data amount of is:"+sendsum);
			}
		} catch (IOException | BiffException e) {
			e.printStackTrace();
		}
	}
	// int to byte[]
	private byte[] intToByteArray (final int integer) {
		int byteNum = (40 - Integer.numberOfLeadingZeros (integer < 0 ? ~integer : integer)) / 8;
		byte[] byteArray = new byte[4];

		for (int n = 0; n < byteNum; n++)
		byteArray[3 - n] = (byte) (integer >>> (n * 8));

		return (byteArray);
		}
	// byte[] to int
	public static int byteArrayToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
 }
}