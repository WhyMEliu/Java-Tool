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
 *<P>类说明：<br/>
 *<br/>
 *可用于单个图片上传，多个图片依次单个上传，页面file的name属性值要与接受的picture保持一致。需要导入ajaxfileupload.js
 *<br/>|20151224|duzc
 */

public class UpPicTest {
//	接受上传图片
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
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "");
        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
        
        // 存储文件的路径及文件名
        String dst = "/users/zsds/....."; 
        
        SFTPChannel channel = test.getSFTPChannel();
        try {
			ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
//			上传图片
			chSftp.put(new FileInputStream(picture), dst, ChannelSftp.OVERWRITE); 
//			关闭通道
			chSftp.quit();
			channel.closeChannel();
//			上传完毕
			response.setStatus(200); 
//			页面接受的信息
			String retxt ="{status:'success'}";
			response.getWriter().print(retxt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setStatus(400); 
			e.printStackTrace();
		}
	}
}
