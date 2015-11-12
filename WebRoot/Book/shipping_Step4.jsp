<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
										<td width="65%"><img src="../images/lc04.gif" width="599"
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
									<td height="225" align="center"><span class="style21">提交订单成功，您的当前的清单如下
											</span><font color="#FF6600">&nbsp;</font>

										<table width="691" height="121" border="0">
											<tr>
												<td width="55" height="24">编号</td>
												<td width="167">菜名</td>
												<td width="127">数量</td>
												<td width="94">单价</td>
												<td width="107">总价</td>
												<td width="39">&nbsp;</td>
												<td width="72">&nbsp;</td>
											</tr>
											<c:forEach items="${list }" var="mm" begin="0" step="1">
											<tr>
												<td>${mm.menuId }</td>
												<td>${mm.name }</td>
												<td>${mm.count }</td>
												<td>${mm.price }</td>
												<td>${mm.total }</td>
																								
											</tr>
											</c:forEach>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>总共数量：${totalCount }</td>
												<td>&nbsp;</td>
												<td>总结消费：${totalprice }元</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td><a href="../index.jsp?flag=1"><img
														src="../images/go.gif" title="返回首页继续订购" /> </a></td>
												<td>&nbsp;</td>
												<td colspan="2"></td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>
												<br><form action="../ServletSendEmail" method="post">
														
														<input type="submit" value="打印账单到邮箱">
													</form></td>
												<td>&nbsp;</td>
												<td colspan="2"></td>
												<td>&nbsp;</td>
											</tr>
										</table>
									</td>
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
