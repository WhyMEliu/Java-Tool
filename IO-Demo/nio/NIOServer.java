package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * <b>�޸ļ�¼��</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018��12��20��
 * </li>
 * </p>
 * 
 * <b>��˵����</b>
 * <p> 
 * 
 * </p>
 */
public class NIOServer extends Thread {
	//��ν��NIO������io����˼�� �ͻ��˷������������ʱ�����ݴ��䵽������֮�������~
	//channel �ײ����ϵͳ�Ὣ���ݽ�����ɺ���֪ͨ ����������
	//ps ����߹������˵������������...
    public void run() {
    	//�Զ���Դ�ͷ�try(){}catch(){}
        try (
        	//����һ������Ա
        	Selector selector = Selector.open();
        	//����һ������˵�����
            ServerSocketChannel serverSocket = ServerSocketChannel.open();) {// ���� Selector �� Channel
            //�󶨶˿�
        	serverSocket.bind(new InetSocketAddress("127.0.0.1", 8888));
        	//����������
            serverSocket.configureBlocking(false);
            // ע�ᵽ Selector��Ϊһ���µķ�������ӣ�SelectionKey.OP_ACCEPTΪ״̬��
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();// �����ȴ������� Channel�����ǹؼ���֮һ
                //��ȡ����������еĿͻ�������
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                //ѭ�����д���
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                   // ����ϵͳ��һ��������о���״̬���
                    sayHelloWorld((ServerSocketChannel) key.channel());
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sayHelloWorld(ServerSocketChannel server) throws IOException {
        try (SocketChannel client = server.accept();) { 
        	client.write(Charset.defaultCharset().encode("Hello world!"));
        }
    }
   // ʡ������ǰ�����Ƶ� main
    public static void main(String[] args) throws IOException {
    	NIOServer server = new NIOServer();
        server.start();
        try (Socket client = new Socket("127.0.0.1", 8888)) {
            BufferedReader bufferedReader = new BufferedReader(new  InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(s -> System.out.println(s));
        }
    }
}

