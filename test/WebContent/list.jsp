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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.6.js"></script>
<script type="text/javascript">  
    function del(){  
        if(confirm("ȷ���Ƿ�ɾ��?")){  
            return true;  
        }  
        return false;  
    }  
</script>
</head>

<body>
     <div align="left">
        <table width="478" height="95" border="1" cellspacing="0">
          <tr>
            <td><div align="center">���</div></td>
            <td><div align="center">����</div></td>
            <td><div align="center">�û���</div></td>
            <td><div align="center">����</div></td>
            <td><div align="center">�绰</div></td>
            <td><div align="center">��ַ</div></td>
            <td><div align="center">����</div></td>
          </tr>
          <s:iterator value="#request.list" var="us">
          <tr>
          	<td><s:property value="#us.id"/></td>
          	<td><s:property value="#us.name"/></td>
          	<td><s:property value="#us.username"/></td>
          	<td><s:property value="#us.password"/></td>
          	<td><s:property value="#us.tel"/></td>
          	<td><s:property value="#us.addr"/></td>
          	<td>
          	 <s:a href="userDelete.action?user.id=%{#us.id}" onclick="return del()">ɾ��</s:a>
          	 <s:a href="userGet.action?user.id=%{#us.id}">�޸�</s:a>
          	</td>
          </tr>
          </s:iterator>
        </table>
        <div align="left"><a href="index.jsp" >����</a></div>
      </div>
</body>
</html>
