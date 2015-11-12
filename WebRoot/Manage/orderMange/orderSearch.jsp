
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
</style>
<link href="../../css/order_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="java">
	function submitOrder()
	{
		var id = document.getElementById("orderid").value;
		var forms = document.orderform;
		var cg = document.getElementById("change");
		cg.innerHTML="已核查";
		forms.action="../../test/order_submitOrder.action?search=sh&orderID="+id;
		forms.submit();
		
	}
	function searchMethod()
	{
		var name = document.getElementById("search").value;
		if(name==""||name==null){
			alert("查询内容不能为空！！");
			return;
		}else{
			var fm = document.sForm;
			fm.action="../../test/order_searchOrder.action?name="+name;
			fm.submit();
		}
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
			收索用户：<input type="text" id="search"/>
			<a href="#"><img src="../../images/i_fdj.gif" width="23px" border="0" height="23px" onclick="searchMethod()"/></a>
			</form>
		</td>
	</tr>
	</table>
	<form action="" name="orderform" method="post">
	<table width="950" border="0" align="center">
		<tr class="tr_sty">
			<td width="35">序号</td>
			<td width="55">订餐者ID</td>
			<td width="140">订餐者</td>
			<td width="83">菜名</td>
			<td width="67">份额</td>
			<td width="60">单价</td>
			<td width="80">总价</td>
			<td width="118">电话</td>
			<td width="117">送餐时间</td>
			<td>审核状态</td>
		</tr>
		
		 <% int i=0; %>
			<c:forEach items="${searchlist }" var="ls" begin="0" step="1">
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
				<input id="name" type="hidden" value="${name }"/>
				<c:if test="${ls.state=='1' }">	<span id="change">		
				<input type="button" value="确认审核" onclick="submitOrder()"/>	</span>			
				</c:if>
				<c:if test="${ls.state=='0' }"> 已核查</c:if>
				</td>
			</tr>
			
			</c:forEach> 
	</table>
	</form><%session.removeAttribute("searchlist"); %>
	<br/><br/>
	<center><font size="5px">${message }</font></center><br/><br/><br/><br/><%session.removeAttribute("message"); %>
	<jsp:include page="../manageBottom.jsp" />
</body>
</html>
