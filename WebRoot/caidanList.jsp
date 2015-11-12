<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript">
function searchContent() {
	var objVal = document.getElementById('searchCont').value;
	location.href = "test/menu_menuSearch.action?name=" + objVal;
}
</script>
<title>快乐订餐 欢迎你的到来</title>
</head>

<body>
	<table width="955" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="203" rowspan="2" align="center" valign="bottom"><img
				src="images/logo.gif" width="155" height="120"></td>
			<td width="752" height="35" valign="bottom">
				<div align="center">
					<font color="#FF0000" size="3">${user_name }<c:if test="${user_flag=='0' }"><font color="#FF0000" size="2">(管理员)</font></c:if> </font>
					
					<font color="#FFCC00"> 您好！欢迎来到快乐餐</font>
					 <font color="#0000FF">
					 <c:if test="${empty user_name }">
					 <a href="Book/shipping_Step1.jsp" target="_parent">[请登录]</a></c:if>
					</font> &nbsp; <font color="#0000FF"><a
						href="user/register_page.jsp">[注册]</a>
					</font> &nbsp; <font color="#0000FF"><a href="user/regetpass.jsp">[找回密码]</a>
					</font>
					<br/>当前购物车：商品数(
					${counts }
					份)&nbsp;&nbsp;总共(${totalprice }
					元)					
				</div>					
			 </td>
		</tr>
		<tr>
			<td valign="bottom"><img src="images/t.gif" border="0"
				usemap="#Map"> <map name="Map">
					<area shape="rect" coords="23,8,119,43" href="index.jsp">
					<area shape="rect" coords="162,9,261,43"
						href="test/<% if(session.getAttribute("user_name")!=null) 
							out.print("query_menuList.action");
						else	out.print("user_juegeLogin.action");
						%>">
					<area shape="rect" coords="301,9,422,43" href="${pageContext.request.contextPath }/test/user_loginManage.action">
					<area shape="rect" coords="473,9,567,42"
						href="${pageContext.request.contextPath }/order/queryOrder.jsp">
					<area shape="rect" coords="633,10,724,42"
						href="about/aboutwe.jsp">
				</map></td>
		</tr>
	</table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="8"></td>
		</tr>
		<tr>
			<td height="80" background="images/sb.gif" bgcolor="#F7942C">
				<table width="955" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="234" align="right"><font color="#FFFFFF" face="宋体">
								<input name="Input" id="searchCont" type="text"> </font>
								<a href="#"><img src="images/i_fdj.gif" onClick="searchContent()" title="搜索"></a>
						</td>
						<td width="99"><font color="#FFFFFF" face="宋体">搜索</font></td>
						<td width="96"><font color="#FFFFFF">家常美味</font></td>
						<td width="289"><font color="#FFFFFF">点一点，美味即刻拥有</font></td>
						<td width="237"><font color="#FFFFFF">足不出户体验经典美食</font></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="8"></td>
		</tr>
	</table>
	<table width="955" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top">
				<table width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td style="margin-top: 1000px">
							<div class="content" id="cptj_div2" style="display: block;">
								<br>
								${message }<br/><%session.removeAttribute("message"); %>
								<c:forEach items="${sessionScope.searchList }" var="menu1" begin="0" step="1">
								
									<dl style="margin-left: 15px; float: left; margin-top: 10px">
										<dt style="float: left">
										<a href="test/menu_menuInfo.action?menu_id=${menu1.menu_id }">
											<img src="upload/${menu1.picture_name}" width="130"
												height="120" title="${menu1.menu_name }" /></a>
										<dd style="clear: left; float: left; margin: 0px; text-align: center; width: 130px">
										<a href="test/menu_menuInfo.action?menu_id=${menu1.menu_id }">
										${menu1.menu_name }<br>
											口味：家常菜<br> 价格：${menu1.menu_price }元 </a>
									</dl>
								</c:forEach>
								
							</div>
						</td>
					</tr>
				</table></td>
		</tr>
		<tr>
		<td>
		<!-- -------------------------------以下是实现分页显示------------------------------------ -->
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr><td align="center" height="25"><font size="3">共${pageinfo.totalCount }条 &nbsp;&nbsp;&nbsp;第<c:forEach begin="${pageinfo.startPage }" end="${pageinfo.endPage }" var="pg">
				<c:if test="${pg!=page }">
				<a href="test/menu_menuSearch.action?name=${name }&page=${pg }">${pg } </a>
				</c:if>
				<c:if test="${pg==page }">
				<font color="#0088ff">${pg }</font>
				</c:if>
				</c:forEach>&nbsp;页&nbsp;&nbsp;&nbsp;共${pageinfo.endPage }页</font></td></tr>
		</table>
		</td>
		</tr>
	</table>
	<div align="center">
		<iframe src="includeFile/shippingBottom.jsp" width="1000" height="80"
			marginheight="0" marginwidth="0" frameborder="0" scrolling="no"></iframe>

	</div>
	<map name="Map2">
		<area shape="rect" coords="17,7,108,33"
			href="Manage/caidan_manage/caidanList.jsp">
	</map>
</body>
</html>