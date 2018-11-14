package basic;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <b>修改记录：</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018-11-9
 * </li>
 * </p>
 * 
 * <b>类说明：</b>
 * <p> 
 * 
 * </p>
 */
public class SocketClient {

	/**
	 * <b>方法说明：</b>
	 * <ul>
	 * 
	 * </ul>
	 * @param args 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		//要连接服务器的ip地址
		String host = "127.0.0.1";
		int port = 55533;
		//与服务器建立连接
		Socket socket = new Socket(host,port);
		//建立连接后获的输出流
		OutputStream outputStream = socket.getOutputStream();
		String message="你好 socket";
		outputStream.write(message.getBytes("UTF-8"));
		outputStream.close();
		socket.close();
	}

}
