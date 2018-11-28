/******************************************************************
 * FileCopy.java
 * CreateDate��2018��11��28��
 * Author��Liupeng
 * Version��F1.0.0.0
 ******************************************************************/

package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * <b>�޸ļ�¼��</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018��11��28��
 * </li>
 * </p>
 * 
 * <b>��˵����</b>
 * <p> 
 * 
 * </p>
 */
public class FileCopy {
	public static void main(String[] args) {
		File source= new File("C:\\Users\\liupeng\\Desktop\\io\\1.txt");
		File dest= new File("C:\\Users\\liupeng\\Desktop\\io\\2.txt");
		copyFileByStream(source, dest);
		copyFileByChannel(source, dest);
	}
	//ͨ��  Stream �������ݸ���
	public static void copyFileByStream(File source,File dest){
		try {
			InputStream is = new FileInputStream(source);
			OutputStream os = new FileOutputStream(dest);
			
			byte[] buffer= new byte[1024];
			int length;
			while((length = is.read(buffer))>0){
				os.write(buffer,0,length);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//ͨ��  Channel �������ݸ���
	public static void copyFileByChannel(File source,File dest){
		try {
			FileChannel sourceChannel = new FileInputStream(source).getChannel();
			FileChannel destChannel = new FileOutputStream(dest).getChannel();
			for (long count = sourceChannel.size();count>0;){
				long transferred = sourceChannel.transferTo(
						sourceChannel.position(),count, destChannel);
				sourceChannel.position(sourceChannel.position()+transferred);
				count -=transferred;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
