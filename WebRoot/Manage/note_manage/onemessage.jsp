<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理留言板</title>
<link href="../../css/css.css" rel="stylesheet" type="text/css">
</head>
<body>

		<jsp:include page="../manageTop.jsp"/>
		<table align="center"><tr><td height="10px"></td></tr></table>
		<center><font size="5" >查看留言</font></center><br/>
		
		<div style="font-size: 15px;color: #333333;">
		<table align="center">
		<tr height="35px"><td class="ziti"> 标 题 ：</td><td class="ziti">${oneMg.subject }</td></tr>
		<tr height="35px"><td class="ziti"> 作 者 ：</td><td class="ziti">${oneMg.username }</td></tr>
		<tr height="35px"><td class="ziti"> 时 间 ：</td><td class="ziti">${oneMg.date }</td></tr>
		<tr height="35px"><td class="ziti"> 内 容 ：</td><td class="ziti">${oneMg.content }</td></tr>
		</table>
		</div><br/>
		<center><div style="width:550px;text-align:center;"><hr/></div></center>
		<div style="text-align:right;width:550px;"><font size="4" ><a href="manage.jsp"><img src="../../images/fanhui.gif" border="0"/></a></font></div>
		<jsp:include page="../manageBottom.jsp"/>
</body>
</html>