<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<link href="../../css/order_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
function searchContent() {
	var objVal = document.getElementById('searchCont').value;
	location.href = "../../test/menu_menuSearch.action?manage=mg&name=" + objVal;
}
</script>
</head>
<body>
<jsp:include page="../manageTop.jsp"/>

<table align="center" width="650px">
	<tr>
		<td><a href="caidan_add.jsp"><img src="../../images/13.gif" border="0"></a></td>
		<td width="250px"></td>
		<td align="center">
			<form action="" name="sForm" method="post">
			搜索菜单：<input type="text" id="searchCont"/>
			<a href="#"><img src="../../images/i_fdj.gif" border="0" width="23px" height="23px" onclick="searchContent()"/></a>
			</form>
		</td>
	</tr>
</table>
<% int i=0; %>
<table align="center" width="650px">
	<tr class="tr_sty">
		<td>ID</td><td>菜名</td><td>单价</td><td>简介</td><td>操作</td>
	</tr>
	<c:forEach items="${searchList }" var="ml" begin="0" step="1">
	<tr class="<% if((i++)%2==0) out.print("tr_sty0"); else out.print("tr_sty1");%>" height="25px">
		<td>${ml.menu_id }</td><td>${ml.menu_name }</td><td>${ml.menu_price }元</td><td>${ml.menu_content }</td>
		<td><a href="${pageContext.request.contextPath }/test/menu_deleteMenu.action?menuID=${ml.menu_id}">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/test/menu_updateUI.action?menuID=${ml.menu_id}">更新</a></td>
	</tr>
	</c:forEach>
</table>
<!-- -------------------------------以下是实现分页显示------------------------------------ -->
<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
			<td align="center" height="25"><font size="3px">共${pageinfo.totalCount }条 &nbsp;&nbsp;&nbsp;第<c:forEach begin="${pageinfo.startPage }" end="${pageinfo.endPage }" var="pg">
			<c:if test="${empty name }">	
				<c:if test="${pg!=page }">
				<a href="${pageContext.request.contextPath }/test/menu_menuList.action?page=${pg }">${pg } </a>
				</c:if>
			</c:if>
			<c:if test="${!empty name }">	
				<c:if test="${pg!=page }">
				<a href="${pageContext.request.contextPath }/test/menu_menuSearch.action?manage=mg&name=${name }&page=${pg }">${pg } </a>
				</c:if>
			</c:if>
				<c:if test="${pg==page }">
				<font color="#0088ff">${pg }</font>
				</c:if>
				</c:forEach>&nbsp;页&nbsp;&nbsp;&nbsp;共${pageinfo.endPage }页</font></td></tr>
		</table>
		<%session.removeAttribute("name"); %>
<jsp:include page="../manageBottom.jsp"/>
</body>
</html>