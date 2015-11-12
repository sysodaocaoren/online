<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>找回密码</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript"  src="../JS/regist.js" charset="gb2312"></script>
	<link href="../css/te.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  <br>
	<jsp:include page="../includeFile/shippingTop.jsp"></jsp:include>
  <%
  request.setCharacterEncoding("utf-8");
  %>
  	<center>
  	<br>
  	<form action="../test/user_findPass.action" method="post" name="passform">
  	<table width="550" height="300">
				<tr>
					<td width="70" height="35">&nbsp;用&nbsp;户&nbsp;名&nbsp;</td>
					<td  width="160">
						<input id="name" name="person.user_name" type="text" onBlur="checkName()">
					</td>
					<td width="">
						<div align="left"><span id="username">*</span></div>
					</td>
				</tr>
				
				<tr>
					<td height="37">真实姓名</td>
					<td>
						<input id="realname" name="person.user_realname" type="text" onBlur="checkRealName()">
					</td>
					<td>
						<div align="left"><span id="rname">*</span></div>
					</td>
				</tr>
				<tr>
					<td height="38">邮&nbsp;&nbsp;&nbsp;&nbsp;箱</td>
					<td>
						<input id="email" name="person.user_mail" type="text" onBlur="checkMail()">	
					</td>
					<td>
						<div align="left"><span id="mail">*</span></div>
					</td>
				</tr>
				<tr>
					<td>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
					<td>
						<input name="person.user_sex" type="radio" checked="checked" value="男">男
						<input name="person.user_sex" type="radio" value="女">女				
					</td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<input name="okSub" value="确定" type="submit"  onclick="Pass()">&nbsp;&nbsp;
						<input name="" value="重置" type="reset">
						<br>				
					</td>
				</tr>
			</table>
	
  	</form>
  	</center>
  		<br><br>
		<div align="center">
		<iframe src="../includeFile/shippingBottom.jsp" width="1050"
			height="80" marginheight="0" marginwidth="0" frameborder="0"
			scrolling="no"></iframe>

	</div>
  </body>
</html>
