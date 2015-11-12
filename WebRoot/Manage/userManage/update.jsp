<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../../css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript"  src="../../JS/regist.js" charset="gb2312"></script>
</head>
<body>
<jsp:include page="../manageTop.jsp"/>
	<div class="divworm"><center>
			<form action="${pageContext.request.contextPath }/test/user_updateUser.action" method="post" name="mainForm">
			<br><br><br>
			<table width="550" height="300">
				<tr>
					<td width="70" height="35">&nbsp;用&nbsp;户&nbsp;名&nbsp;</td>
					<td  width="160">
						<input type="hidden" name="person.user_id" value="${person.user_id }"/>
						<input id="name" name="person.user_name" type="text" onBlur="checkName()" value="${person.user_name }">
					</td>
					<td width="">
						<div align="left"><span id="username">*请输入以非数字开头的2-8位</span></div>
					</td>
				</tr>
				<tr>
					<td height="35">密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
					<td>
						<input id="pass" name="person.user_pass" value="${person.user_pass }" type="password" onBlur="checkPass()">
					</td>
					<td>
						<div align="left"><span id="password">*请输入3-8位密码</span></div>
					</td>
				</tr>
				
				<tr>
					<td height="37">真实姓名</td>
					<td>
						<input id="realname" name="person.user_realname" value="${person.user_realname }" type="text" onBlur="checkRealName()">
					</td>
					<td>
						<div align="left"><span id="rname">*</span></div>
					</td>
				</tr>
				<tr>
					<td height="38">邮&nbsp;&nbsp;&nbsp;&nbsp;箱</td>
					<td>
						<input id="email" name="person.user_mail" value="${person.user_mail }" type="text" onBlur="checkMail()">	
					</td>
					<td>
						<div align="left"><span id="mail">*</span></div>
					</td>
				</tr>
				<tr>
				<td>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
				<c:if test="${person.user_sex=='女' }">					
					<td>					
						<input name="person.user_sex" type="radio"  value="男">男
						<input name="person.user_sex" type="radio" checked="checked" value="女">女				
					</td>
				</c:if>
				<c:if test="${person.user_sex=='男' }">
					<td>					
						<input name="person.user_sex" type="radio"  checked="checked" value="男">男
						<input name="person.user_sex" type="radio"  value="女">女				
					</td>
					</c:if>
				</tr>
				<tr>
					<td>身&nbsp;&nbsp;&nbsp;&nbsp;份</td>
					<c:if test="${person.user_flag=='0' }">
					<td>
						<input name="person.user_flag" type="radio" checked="checked" value="0">管理员&nbsp;
						<input name="person.user_flag" type="radio" value="1">普通用户				
					</td>
					</c:if>
					<c:if test="${person.user_flag=='1' }">
					<td>
						<input name="person.user_flag" type="radio"  value="0">管理员&nbsp;
						<input name="person.user_flag" type="radio" checked="checked" value="1">普通用户				
					</td>
					</c:if>
				</tr>
				<tr align="center">
					<td colspan="2">
						<input name="okSub" value="确定" type="submit" >&nbsp;&nbsp;
						<input name="" value="重置" type="reset">				
					</td>
				</tr>
			</table>
		</form></center>
		</div>
		<jsp:include page="../manageBottom.jsp"/>
</body>
</html>