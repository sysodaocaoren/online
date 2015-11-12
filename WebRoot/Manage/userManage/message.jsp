<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示信息</title>
<link href="../../css/te.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../manageTop.jsp"/>
	<center>
	<br/><br/><br/><br/>
	<font size="4px">${message }</font>
	</center>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/>
<jsp:include page="../manageBottom.jsp"/>
</body>
</html>