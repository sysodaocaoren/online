<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询结果</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<link href="../css/order_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="../includeFile/shippingTop.jsp" />
<br><br>
	<center>
	<font size="3px">${message }</font><br/><br/>
	<% int i=0; int j=1;%>
	<table width="650px">
	<tr class="tr_sty">
		<td>编号</td><td>菜名</td><td>数量</td><td>单价</td><td>总价</td><td>订单时间</td><td>状态</td>
	</tr>
	<c:forEach items="${list }" var="list" begin="0" step="1">
	<tr class="<% if((i++)%2==0) out.print("tr_sty0"); else out.print("tr_sty1");%>" height="25px">
		<td><%=j++ %></td>
		<td>${list.name }</td>
		<td>${list.count }</td>
		<td>${list.price }</td>
		<td>${list.total }</td>
		<td>${list.sendTime }</td>
		<td>
		<c:if test="${list.state=='0' }">已付款</c:if>
		<c:if test="${list.state=='1' }">未付款</c:if>
		</td>
	</tr>	
	</c:forEach>
	</table>
	<div align="center"><a href="queryOrder.jsp"><img src="../images/fanhui.gif" border="0"/></a></div>
	</center>


<%session.removeAttribute("list"); %>
<div align="center">
<br><br>
<iframe src="../includeFile/shippingBottom.jsp" width="1050"
					height="80" marginheight="0" marginwidth="0" frameborder="0"
					scrolling="no"></iframe>
</div>
</body>
</html>