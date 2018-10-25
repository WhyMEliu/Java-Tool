/*******************************************************************
 * UpPicTest.java   2015-12-16
 * Copyright2012  by GNNT Company. All Rights Reserved.
 * @author:PHILIPS
 * 
 ******************************************************************/
package com.test.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.test.sftp.SFTPChannel;
import com.test.sftp.SFTPConstants;
import com.test.sftp.SFTPTest;

/**
 *<P>��˵����<br/>
 *<br/>
 *�����ڵ���ͼƬ�ϴ������ͼƬ���ε����ϴ���ҳ��file��name����ֵҪ����ܵ�picture����һ�¡���Ҫ����ajaxfileupload.js
 *<br/>|20151224|duzc
 */

public class UpPicTest {
//	�����ϴ�ͼƬ
	private File picture;
	
	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public void upPic(){
		System.out.println("enter up picture");
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		System.out.println(request.getParameter("name"));
		SFTPTest test = new SFTPTest();

        Map<String, String> sftpDetails = new HashMap<String, String>();
        // ��������ip���˿ڣ��û���������
        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "");
        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
        
        // �洢�ļ���·�����ļ���
        String dst = "/users/zsds/....."; 
        
        SFTPChannel channel = test.getSFTPChannel();
        try {
			ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
//			�ϴ�ͼƬ
			chSftp.put(new FileInputStream(picture), dst, ChannelSftp.OVERWRITE); 
//			�ر�ͨ��
			chSftp.quit();
			channel.closeChannel();
//			�ϴ����
			response.setStatus(200); 
//			ҳ����ܵ���Ϣ
			String retxt ="{status:'success'}";
			response.getWriter().print(retxt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setStatus(400); 
			e.printStackTrace();
		}
	}
}
