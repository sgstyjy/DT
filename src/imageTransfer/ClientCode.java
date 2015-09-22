package imageTransfer;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import jxl.read.biff.BiffException;

public class ClientCode 
{
  public static void main(String[] args) throws IOException, InterruptedException, BiffException {
	      int threadNo = 0;
          InetAddress addr = InetAddress.getByName("localhost");
         System.out.println("The outputs are from the Client: ");
      
	    //read the start time
		 Date date = new Date();
	    Long starttime = date.getTime();
		
          //for(threadNo = 0;threadNo<3;threadNo++)
           new DataSender(addr,Constant.PORT1);
           
   		//read the end time
   		Date date1 = new Date();
   		Long endtime = date1.getTime();
   		Long duration = endtime-starttime;
   		System.out.println("The send time  is:"+duration);
  }

}