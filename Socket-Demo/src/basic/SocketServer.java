package basic;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <b>�޸ļ�¼��</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018-11-9
 * </li>
 * </p>
 * 
 * <b>��˵����</b>
 * <p> 
 * 
 * </p>
 */
public class SocketServer {

	/**
	 * <b>����˵����</b>
	 * <ul>
	 * 
	 * </ul>
	 * @param args 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//����ָ���Ķ˿�
		int port=55533;
		ServerSocket server = new ServerSocket(port);
		//�ȴ����ӵĵ���
		System.out.println("һֱ�ȴ����ӵĵ���~");
		Socket socket= server.accept();
		
		//���������Ӻ󣬴�socket�л�ȡ�����������������������ж�ȡ
		InputStream inputStream = socket.getInputStream();
		byte[] bytes = new byte[1024];
		int len;
		StringBuilder sb = new StringBuilder();
		while ((len= inputStream.read(bytes))!=-1 ){
			//ע������ʽ�����ͷ��ͽ��շ�Ҫͳһ������ʹ��utf-8;
			sb.append(new String(bytes,0,len,"UTF-8"));
		}
		System.out.println("get message from client:"+sb);
		inputStream.close();
		socket.close();
		server.close();
	}
}
