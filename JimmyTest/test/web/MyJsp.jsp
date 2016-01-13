<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <script type="text/javascript">
  var text="body+++"
  document.write(text);
  function func(){
  alert("this is ok button!")
  }
  function modi(){
  document.getElementById("ch").innerHTML="用户名";
  document.getElementById("username").value="jimmy";
  }
  function add(){
  
  }
  </script>
  <div id="1">
  <div id="2">
  <font id="ch">:</font>
  <input type="text" name="username" id="username"/>
  </div>
  </div>
  <input type="button" name="ok" value="ok" onclick="modi()"/>
  </body>
</html>
