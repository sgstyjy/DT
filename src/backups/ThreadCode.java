package backups;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// 3. ����ServerThread.java
 
class ThreadCode extends Thread {
    Socket socket = null; // �����뱾�߳���ص�Socket����
    int clientnum; // ���汾���̵Ŀͻ�����
 
    public void ThreadCode(Socket socket, int num) { // ���캯��
        this.socket = socket; // ��ʼ��socket����
        clientnum = num + 1; // ��ʼ��clientnum����
    }
 
    public void run() { // �߳�����
        try {
            String line;
            BufferedReader is = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            // ��Socket����õ�����������������Ӧ��BufferedReader����
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            // ��Socket����õ��������������PrintWriter����
            BufferedReader sin = new BufferedReader(new InputStreamReader(
                    System.in));
            // ��ϵͳ��׼�����豸����BufferedReader����
            System.out.println("Client:" + clientnum + is.readLine());
            // �ڱ�׼����ϴ�ӡ�ӿͻ��˶�����ַ���
            line = sin.readLine();
            // �ӱ�׼�������һ�ַ���
            while (!line.equals("bye")) {
                // ������ַ���Ϊ "bye"����ֹͣѭ��
                os.println(line);
                // ��ͻ���������ַ���
                os.flush();
                // ˢ���������ʹClient�����յ����ַ���
                System.out.println("Server:" + line);
                // ��ϵͳ��׼����ϴ�ӡ���ַ���
                System.out.println("Client:" + clientnum + is.readLine());
                // ��Client����һ�ַ���������ӡ����׼�����
                line = sin.readLine();
                // ��ϵͳ��׼�������һ�ַ���
            } // ����ѭ��
            os.close(); // �ر�Socket�����
            is.close(); // �ر�Socket������
            socket.close(); // �ر�Socket
        } catch (Exception e) {
            System.out.println("Error:" + e);
            // ������ӡ������Ϣ
        }
    }
}
