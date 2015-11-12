<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../../css/order_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="../manageTop.jsp"/>
<table align="center" width="650px">
	<tr>
		<td height="50px"><a href="addUser.jsp"><img src="../../images/14.gif" border="0"></a></td>
		<td width="250px"></td>
	</tr>
</table>
<% int i=0; %>
<table align="center" width="650px">
	<tr class="tr_sty">
		<td>ID</td><td>用户名</td><td>真实姓名</td><td>性别</td><td>E-mail</td><td>身份</td><td>操作</td>
	</tr>
	<c:forEach items="${userList }" var="ml" begin="0" step="1">
	<tr class="<% if((i++)%2==0) out.print("tr_sty0"); else out.print("tr_sty1");%>" height="25px">
		<td>${ml.user_id }</td><td>${ml.user_name }</td><td>${ml.user_realname }</td><td>${ml.user_sex }</td><td>${ml.user_mail }</td>
		<td>
		<c:if test="${ml.user_flag=='0' }">管理员</c:if>
		<c:if test="${ml.user_flag=='1' }">普通用户</c:if>
		</td>
		<td><a href="${pageContext.request.contextPath }/test/user_deleteUser.action?id=${ml.user_id}">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/test/user_updateUI.action?id=${ml.user_id}">更新</a></td>
	</tr>
	</c:forEach>
</table>

<!-- -------------------------------以下是实现分页显示------------------------------------ -->
<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
			<td align="center" height="25"><font size="3px">共${pageinfo.totalCount }条 &nbsp;&nbsp;&nbsp;第<c:forEach begin="${pageinfo.startPage }" end="${pageinfo.endPage }" var="pg">
				<c:if test="${pg!=page }">
				<a href="${pageContext.request.contextPath }/test/user_userList.action?page=${pg }">${pg } </a>
				</c:if>
				<c:if test="${pg==page }">
				<font color="#0088ff">${pg }</font>
				</c:if>
				</c:forEach>&nbsp;页&nbsp;&nbsp;&nbsp;共${pageinfo.endPage }页</font>
			</td>
			</tr>
</table>
<jsp:include page="../manageBottom.jsp"/>
</body>
</html>