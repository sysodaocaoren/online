<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单查询</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../JS/queryOrder.js" charset="gb2312"></script>
</head>
<body>
<jsp:include page="../includeFile/shippingTop.jsp" />
<br><br>
<table width="600px" align="center" >
<tr>
	<td width="300px" align="center"><a href="#"><img src="../images/15.gif" border="0" onclick="showToday()"/></a></td>
	<td width="300px" align="center"><a href="#"><img src="../images/16.gif" border="0" onclick="showAll()"/></a></td>
</tr>
<tr>
	<td height="200px" id="today" align="center"></td>
	<td height="200px" id="all" align="center"></td>
</tr>
</table>

<div align="center">
<br><br>
<iframe src="../includeFile/shippingBottom.jsp" width="1050"
					height="80" marginheight="0" marginwidth="0" frameborder="0"
					scrolling="no"></iframe>
</div>
</body>
</html>