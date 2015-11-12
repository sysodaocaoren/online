
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
<!--
.style21 {
	font-weight: bold 
}
-->
</style>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
	function add() {		
		var objForm = document.bookCook;
		objForm.submit();
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
				<td height="40" align="center">
				<font size="4px">${myMenu.menu_name } 详细信息 欢迎选购</font>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#E9E9E9"></td>
			</tr>
			<tr>
				<td height="12" background="../images/xian.gif"></td>
			</tr>
			<tr>
				<td>

				<form name="bookCook" action="order_addMenu.action" method="post">
				<table width="95%" height="334" border="0" align="center"
					cellpadding="0" cellspacing="0">
					<tr valign="top">
						<td width="40%" height="225" align="center"><img width="260"
							src="../upload/${myMenu.picture_name}"/> <br><br>
						<input type="hidden" name="userID" value="${user_id }">
						<input type="hidden" name="username" value="${user_name }">
						<input type="hidden" name="menuID" value="${myMenu.menu_id }">
						<td width="60%">
						<p>单价：${myMenu.menu_price } 元</p>
						<p>菜肴简介：${myMenu.menu_content }</p>
						<p>付款方式：先送货再付款</p>
						<p>选择口味：<input name="orders.order_notice" type="radio" value="清淡">清淡
						<input name="orders.order_notice" type="radio" value="微辣" checked>
						微辣 <input name="orders.order_notice" type="radio" value="中辣">中辣 <input
							name="orders.order_notice" type="radio" value="特辣">特辣 <input
							name="orders.order_notice" type="radio" value="重口味">重口味
						<p>选购数量： <input name="orders.order_num" type="text" value="1"
							size="6"> 份</p>
						<p>其它要求： <textarea name="orders.other_notice" id="order_notic"
							cols="30"></textarea></p>

						<a href="#"><img src="../images/addingShipping.png" onclick="add()" /></a>
						<a href="menu_menuSearch.action"><img src="../images/m.gif" /></a>
						</td>

					</tr>
				</table>
				</form>
				</td>
			</tr>
		</table>
		</td>
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
