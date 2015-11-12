<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="gb2312"%>
    <% request.setCharacterEncoding("utf-8");     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>

<link href="../css/te.css" rel="stylesheet" type="text/css">

<script type="text/javascript"  src="../JS/regist.js" charset="gb2312"></script>
</head>
<body>
	<br>
		<jsp:include page="../includeFile/shippingTop.jsp"></jsp:include>
			<div class="divworm"><center>
			<form action="" method="post" name="mainForm">
			<br><br><br>
			<table width="550" height="300">
				<tr>
					<td width="70" height="35">&nbsp;用&nbsp;户&nbsp;名&nbsp;</td>
					<td  width="160">
						<input id="name" name="person.user_name" type="text" onBlur="checkName()">
					</td>
					<td width="">
						<div align="left"><span id="username">*请输入以非数字开头的2-8位</span></div>
					</td>
				</tr>
				<tr>
					<td height="35">密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
					<td>
						<input id="pass" name="person.user_pass" type="password" onBlur="checkPass()">
					</td>
					<td>
						<div align="left"><span id="password">*请输入3-8位密码</span></div>
					</td>
				</tr>
				<tr>
					<td height="35">确认密码</td>
					<td>
						<input id="checkpass" name="chpass" type="password" onBlur="checkRepass()">					
					</td>
					<td>
						<div align="left"><span id="repass">*</span></div>
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
						<input name="okSub" value="确定" type="submit"  onclick="goRegiste()">&nbsp;&nbsp;
						<input name="" value="重置" type="reset">
						<br>
						<input type="hidden" name="person.user_flag" value="1">					
					</td>
				</tr>
			</table>
		</form></center>
		</div>
		<div align="center">
		
		<iframe src="../includeFile/shippingBottom.jsp" width="1050"
			height="80" marginheight="0" marginwidth="0" frameborder="0"
			scrolling="no"></iframe>

		</div>
	<br><br>

</body>
</html>