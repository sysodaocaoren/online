<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
<!--
.style21 {
	font-weight: bold
}
-->
</style>
<link href="../css/css.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
.STYLE22 {
	color: #FF0000
}
-->
</style>
<script language="javascript" type="text/javascript">
	function gotoServlet(obj) {

		obj.submit();
	}
</script>
<title>快乐订餐 欢迎你的到来</title>
</head>

<body>

	<jsp:include page="../includeFile/shippingTop.jsp" />
	<table width="955" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>

		<tr>
			<td height="8"></td>
		</tr>
		<tr>
			<td height="400" valign="top">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="25">
							<div align="center">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="1%">&nbsp;</td>
										<td width="65%"><img src="../images/lc02.gif" width="599"
											height="49">
										</td>
										<td width="34%" valign="bottom">&nbsp;</td>
									</tr>
								</table>
							</div></td>
					</tr>
					<tr>
						<td height="1" bgcolor="#E9E9E9"></td>
					</tr>
					<tr>
						<td height="12" background="../images/xian.gif"></td>
					</tr>
					<tr>
						<td>
							<table width="95%" height="334" border="0" align="center"
								cellpadding="0" cellspacing="0">
					
								<tr valign="top">
									<td height="225" align="center"><span class="style21">您的购物车列表信息
											(共: ${counts } 条) </span><font color="#FF6600">&nbsp;</font>
										<table width="845" height="69" border="0">
										
											<tr>
											
												<td width="34" height="24">编号</td>
												<td width="144">菜名</td>
												<td width="70">数量</td>
												<td width="44">单价</td>
												<td width="120">总价</td>
												<td width="258" align="left">备注</td>
												<td>操作</td>
											</tr>
											<c:forEach items="${list }" var="mm" begin="0" step="1">
											<tr>
												<td>${mm.menuId }</td>
												<td>${mm.name }</td>
												<td>${mm.count }</td>
												<td>${mm.price }</td>
												<td>${mm.total }</td>
												<td align="left">${mm.content }</td>
												<td><a href="order_deleteMenu.action?orderID=${mm.orderID }">删除</a></td>
											</tr>
											</c:forEach>
											<tr>
												<td></td>
												<td></td>
												<td>总数量：${totalCount }</td>
												<td></td>
												<td>总价：${totalprice }</td>
												<td align="left"></td>
												<td></td>
											</tr>
											<tr>
												<td colspan="7">
												<a href="info_getInfo.action"> <img src="../images/Settlement.png" /> </a>
												</td>
											</tr>							
										</table></td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
	</table>
	<div align="center">
		<iframe src="../includeFile/shippingBottom.jsp" width="1050"
			height="80" marginheight="0" marginwidth="0" frameborder="0"
			scrolling="no"></iframe>

	</div>
</body>
</html>
