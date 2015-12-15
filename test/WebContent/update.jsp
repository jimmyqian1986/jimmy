<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.6.js"></script>
</head>
<body>
	<h1><font color="red">Save User</font></h1>            
    <s:form action="userUpdate">
        <s:textfield name="user.name" label="姓名"></s:textfield>  
        <s:textfield name="user.username" label="用户名"></s:textfield>  
        <s:textfield name="user.password" label="密码"></s:textfield>  
        <s:textfield name="user.tel" label="电话"></s:textfield>  
        <s:textfield name="user.addr" label="地址"></s:textfield>  
        <s:submit value="修改"></s:submit>
        <a href="index.jsp" >返回</a>
    </s:form>
</body>
</html>
