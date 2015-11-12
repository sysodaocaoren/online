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

<style type="text/css">
<!--
.STYLE22 {
	color: #FF0000
}
-->
</style>
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
										<td width="65%"><img src="../images/lc01.gif" width="599"
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
	<form action="../test/user_login.action" method="post" name="mainForm">
		<div class="divworm">
			<br>
			<br>
			<input name="flag" type="hidden" value="">
			 <span>用户名</span>
			<input  name="person.user_name" type="text" > <span id="username">*</span><br> <br> 
			<span>密&nbsp;&nbsp;码</span>
			<input  type="password" name="person.user_pass" > <span id="password">*</span>
			<br><br>
			<input type="radio" name="person.user_flag" value="1" checked="checked">用户
			<input type="radio" name="person.user_flag" value="0">管理员
			<br> <br>
			<input type="submit" value="确定" onclick="checkNull()"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" value="重置">
		</div>
	</form></td>
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
