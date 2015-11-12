<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的留言记录</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
</head>
<body>

		<jsp:include page="../includeFile/shippingTop.jsp"></jsp:include>
		<table align="center"><tr><td height="10px"></td></tr></table>
		<center><font size="5" >${user_name }的留言</font></center>

		<table align="center"><tr><td align="right" width="400px"><a href="../test/mg_messageUI.action">返回</a></td></tr></table>
		<table width="550px" align="center"><tr><td>
	<c:forEach items="${oneselfList }" var="mg" begin="0" step="1">
		<fieldset style="border:1px solid  #FF9900;	padding:8px ;" >
			主题：${mg.subject}<br/><br/>&nbsp;&nbsp;${mg.content }<br/><br/>
			<div align="right">时间:${mg.dates }</div>	
		</fieldset><br/>
	</c:forEach></td></tr>	
	</table>
	
		<div align="center">
		<iframe src="../includeFile/shippingBottom.jsp" width="1050"
			height="80" marginheight="0" marginwidth="0" frameborder="0"
			scrolling="no"></iframe>

		</div>

</body>
</html>