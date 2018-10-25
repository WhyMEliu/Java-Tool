package com.test.sftp;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import com.jcraft.jsch.ChannelSftp;

public class SFTPTest {

    public SFTPChannel getSFTPChannel() {
        return new SFTPChannel();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SFTPTest test = new SFTPTest();

        Map<String, String> sftpDetails = new HashMap<String, String>();
        // ��������ip���˿ڣ��û���������
        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "");
        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
        
        String src = "C:\\Users\\Public\\Pictures\\Sample Pictures\\11.png"; // �����ļ���
        String dst = "/users/sdbj/test/5.jpg"; // Ŀ���ļ���
        
        SFTPChannel channel = test.getSFTPChannel();
        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
        
        /**
         * �����1
        OutputStream out = chSftp.put(dst, ChannelSftp.OVERWRITE); // ʹ��OVERWRITEģʽ
        byte[] buff = new byte[1024 * 256]; // �趨ÿ�δ�������ݿ��СΪ256KB
        int read;
        if (out != null) {
            System.out.println("Start to read input stream");
            InputStream is = new FileInputStream(src);
            do {
                read = is.read(buff, 0, buff.length);
                if (read > 0) {
                    out.write(buff, 0, read);
                }
                out.flush();
            } while (read >= 0);
            System.out.println("input stream read done.");
        }
        **/
        
//        chSftp.put(src, dst, ChannelSftp.OVERWRITE); // �����2
        
        chSftp.put(new FileInputStream(src), dst, ChannelSftp.OVERWRITE); // �����3
        
        chSftp.quit();
        channel.closeChannel();
    }
}