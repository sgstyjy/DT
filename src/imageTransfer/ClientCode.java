package imageTransfer;
import java.io.IOException;
import java.net.InetAddress;

public class ClientCode 
{
  public static void main(String[] args) throws IOException, InterruptedException {
	  int threadNo = 0;
      InetAddress addr = InetAddress.getByName("141.5.103.55");
      System.out.println("The outputs are from the Client: ");
      //for(threadNo = 0;threadNo<3;threadNo++)
           new DataSender(addr,Constant.PORT1);
  }

}