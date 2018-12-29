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
 * <b>修改记录：</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018年12月20日
 * </li>
 * </p>
 * 
 * <b>类说明：</b>
 * <p> 
 * 
 * </p>
 */
public class NIOServer extends Thread {
	//所谓的NIO非阻塞io的意思是 客户端发送网络请求的时候数据传输到服务器之间的阻塞~
	//channel 底层操作系统会将数据接收完成后再通知 我们做处理
	//ps 尼玛吖，不是说的流阻塞沃日...
    public void run() {
    	//自动资源释放try(){}catch(){}
        try (
        	//创建一个调度员
        	Selector selector = Selector.open();
        	//创建一个服务端的连接
            ServerSocketChannel serverSocket = ServerSocketChannel.open();) {// 创建 Selector 和 Channel
            //绑定端口
        	serverSocket.bind(new InetSocketAddress("127.0.0.1", 8888));
        	//声明非阻塞
            serverSocket.configureBlocking(false);
            // 注册到 Selector，为一个新的服务端连接（SelectionKey.OP_ACCEPT为状态）
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();// 阻塞等待就绪的 Channel，这是关键点之一
                //获取存入调度者中的客户端连接
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                //循环进行处理
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                   // 生产系统中一般会额外进行就绪状态检查
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
   // 省略了与前面类似的 main
    public static void main(String[] args) throws IOException {
    	NIOServer server = new NIOServer();
        server.start();
        try (Socket client = new Socket("127.0.0.1", 8888)) {
            BufferedReader bufferedReader = new BufferedReader(new  InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(s -> System.out.println(s));
        }
    }
}

