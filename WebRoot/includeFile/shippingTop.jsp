
<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="javascript" type="text/javascript">
	function getTime() {
		document.getElementById("getTime").innerHTML = new Date()
				.toLocaleString();
		setTimeout("getTime()", 1000);
	}
</script>
	<table width="955" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr valign="top">
			<td width="203" align="center" valign="bottom"> 
	<font color="#0000FF">
	<c:if test="${!empty user_name }">用户：${user_name }</c:if>
	<c:if test="${empty user_name }">用户：null</c:if>
 	<c:if test="${user_flag=='0' }">(管理员)</c:if>
 
 </font><br>
			<br> 
 <span id="getTime"></span>
 <script language="javascript" type="text/javascript">
	getTime();
</script>
			</td>
			<td width="752" valign="bottom"><img src="../images/t.gif"
				border="0" usemap="#Map"><br />
			<br />
			</td>
		</tr>
		<tr>
			<td height="3" colspan="2" bgcolor="#F7942C"></td>
		</tr>
	</table>

	<map name="Map">
		<area shape="rect" coords="23,8,119,43" href="../index.jsp">
		<area shape="rect" coords="162,9,261,43"
			href="${pageContext.request.contextPath }/test/<% if(session.getAttribute("user_name")!=null) 
							out.print("query_menuList.action");
						else	out.print("user_juegeLogin.action");
						%>">
					<area shape="rect" coords="301,9,422,43" href="${pageContext.request.contextPath }/test/user_loginManage.action">
		<area shape="rect" coords="473,9,567,42"
			href="${pageContext.request.contextPath }/order/queryOrder.jsp">
		<area shape="rect" coords="633,10,724,42" href="../about/aboutwe.jsp">
	</map>
