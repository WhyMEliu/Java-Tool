package basic;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
public class SocketClient {

	/**
	 * <b>����˵����</b>
	 * <ul>
	 * 
	 * </ul>
	 * @param args 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		//Ҫ���ӷ�������ip��ַ
		String host = "127.0.0.1";
		int port = 55533;
		//���������������
		Socket socket = new Socket(host,port);
		//�������Ӻ��������
		OutputStream outputStream = socket.getOutputStream();
		String message="��� socket";
		outputStream.write(message.getBytes("UTF-8"));
		outputStream.close();
		socket.close();
	}

}
