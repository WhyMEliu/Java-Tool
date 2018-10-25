<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/ajaxfileupload.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <script type="text/javascript">
  function uploadPic(id){
	 
		if(confirm("确认上传图片？")){
			$('#btt').attr("disabled","disabled");
			 var path = document.getElementById(id).value;
			  var name = path.split('\\');
			  name = name[name.length-1];
			$.ajaxFileUpload({  
				 url:'<%=basePath %>/mytest/upPic',
				 data:{prm_test:'test',name:name},
				 type:'post',
				 secureuri:false,  
				 fileElementId:id,                         //文件选择框的id属性  
				 dataType: 'json',                         //服务器返回的格式，可以是json  
				 success: function (data, status){     
					alert(data.status);    
					$('#btt').attr("disabled",false);
				 },  
				 error: function () {  
					alert("上传失败!");    
					$('#btt').attr("disabled",false);
				 }  
			 }); 
		} 
	}
  </script>
  <body>
  <form action="" enctype="multipart/form-data">
    <input type="file" id="picture" name="file"/>
    <input type="button" id="btt" name="btt" value="up picture" onclick="uploadPic('picture')"/>
  </form>
  </body>
</html>
