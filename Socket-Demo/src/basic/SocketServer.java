package basic;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
public class SocketServer {

	/**
	 * <b>方法说明：</b>
	 * <ul>
	 * 
	 * </ul>
	 * @param args 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//监听指定的端口
		int port=55533;
		ServerSocket server = new ServerSocket(port);
		//等待连接的到来
		System.out.println("一直等待连接的到来~");
		Socket socket= server.accept();
		
		//建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
		InputStream inputStream = socket.getInputStream();
		byte[] bytes = new byte[1024];
		int len;
		StringBuilder sb = new StringBuilder();
		while ((len= inputStream.read(bytes))!=-1 ){
			//注意编码格式，发送方和接收方要统一，建议使用utf-8;
			sb.append(new String(bytes,0,len,"UTF-8"));
		}
		System.out.println("get message from client:"+sb);
		inputStream.close();
		socket.close();
		server.close();
	}
}
