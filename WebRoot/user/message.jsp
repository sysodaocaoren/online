<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>提示信息</title>
<link href="../css/te.css" rel="stylesheet" type="text/css" />
</head>
<body>

		<jsp:include page="../includeFile/shippingTop.jsp"></jsp:include>
		<div class="divworm"><center>
		<br/><br/><br/><br/><br/>
			${message }

		</center></div>
		<div align="center">
		<iframe src="../includeFile/shippingBottom.jsp" width="1050"
			height="80" marginheight="0" marginwidth="0" frameborder="0"
			scrolling="no"></iframe>

		</div>

</body>
</html>