<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理</title>
<link href="../../css/order_style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" language="java">
	function submitOrder()
	{
		var id = document.getElementById("orderid").value;
		var forms = document.orderform;
		forms.action="../../test/order_submitOrder.action?orderID="+id;
		forms.submit();
	}
	function searchMethod()
	{
		var name = document.getElementById("search").value;
		var fm = document.sForm;
		fm.action="../../test/order_searchOrder.action?name="+name;
		fm.submit();
	}
</script>
</head>
<body>
	<jsp:include page="../manageTop.jsp" />
	<table width="995" border="0" align="center">
	<tr>
		<td width="150">
		<c:if test="${fg==1 }"><img src="../../images/10.gif" border="0"/></c:if>
		<c:if test="${fg!=1 }">
		<a href="${pageContext.request.contextPath }/test/order_accounted.action?fg=1">
		<img src="../../images/10.gif" border="0"/>
		</a></c:if>
		</td>
		<td width="150">
		<c:if test="${fg==0 }"><img src="../../images/11.gif" border="0"/></c:if>
		<c:if test="${fg!=0 }">
		<a href="${pageContext.request.contextPath }/test/order_accounted.action?fg=0">
		<img src="../../images/11.gif" border="0"/>
		</a></c:if>
		</td>
		<td width="400"></td>
		<td align="center">
			<form action="" name="sForm" method="post">
			搜索用户：<input type="text" id="search"/>
			<a href="#"><img src="../../images/i_fdj.gif" border="0" width="23px" height="23px" onclick="searchMethod()"/></a>
			</form>
		</td>
	</tr>
	</table>

	<form action="" name="orderform" method="post">
		<table width="995"  border="0" align="center">
			<tr class="tr_sty">
			  <td width="33">序号</td>
			  <td width="50">订餐者ID</td>
			  <td width="85">订餐者</td>
			  <td width="105">菜名</td>
			  <td width="45">份额</td>
			  <td width="50">单价</td>
			  <td width="66">总价</td>
			  <td width="139">电话</td>
			  <td width="160">送餐时间</td>
			  <td width="50">
			  <c:if test="${fg==1 }">确认核查</c:if>
			  <c:if test="${fg==0 }">审核状态</c:if>
			  </td>
			 </tr>
			 <% int i=0; %>
			<c:forEach items="${infolist }" var="ls" begin="0" step="1">
			<tr class="<% if((i++)%2==0) out.print("tr_sty0"); else out.print("tr_sty1");%>">
				<td>${ls.menuId }</td>
				<td>${ls.userID }</td>
				<td>${ls.username }</td>
				<td>${ls.name }</td>
				<td>${ls.count }</td>
				<td>${ls.price }</td>
				<td>${ls.total }</td>
				<td>${ls.userTel }</td>
				<td>${ls.sendTime }</td>			
				<td>
				<input id="orderid" type="hidden" value="${ls.orderID }"/>
				<c:if test="${fg==1 }">				
				<input type="submit" value=" 确 认 " onclick="submitOrder()"/>			
				</c:if>
				<c:if test="${fg==0 }"> 已核查</c:if>
				</td>
			</tr>
			
			</c:forEach> 	
					
		</table>
		</form>
		<!-- -------------------------------以下是实现分页显示------------------------------------ -->
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
			<td align="center" height="25"><font size="3px">共${pageinfo.totalCount }条 &nbsp;&nbsp;&nbsp;第<c:forEach begin="${pageinfo.startPage }" end="${pageinfo.endPage }" var="pg">
				<c:if test="${pg!=page }">
				<a href="${pageContext.request.contextPath }/test/order_accounted.action?fg=${fg }&page=${pg }">${pg } </a>
				</c:if>
				<c:if test="${pg==page }">
				<font color="#0088ff">${pg }</font>
				</c:if>
				</c:forEach>&nbsp;页&nbsp;&nbsp;&nbsp;共${pageinfo.endPage }页</font></td></tr>
		</table>
		
		<% session.removeAttribute("fg"); %>
		<jsp:include page="../manageBottom.jsp" />
</body>
</html>
