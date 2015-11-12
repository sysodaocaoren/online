<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
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
<% 	String flag = request.getParameter("flag");
	session.setAttribute("flag",flag);
%>
<body>
	<table width="955" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="203" rowspan="2" align="center" valign="bottom"><img
				src="images/logo.gif" width="155" height="120">
			</td>
			<td width="752" height="35" valign="bottom">

				<div align="center">
					<font color="#FF0000" size="3">${user_name } </font>
					<c:if test="${user_flag=='0' }"><font color="#FF0000" size="2">(管理员)</font></c:if>
					<font color="#FFCC00"> 您好！欢迎来到快乐餐</font> 
					<font color="#0000FF">
					<c:if test="${empty user_name }">
					<a href="Book/shipping_Step1.jsp" target="_parent">[请登录]</a></c:if>
					</font> &nbsp; <font color="#0000FF"><a
						href="user/register_page.jsp">[注册]</a>
						<a href="Book/shipping_Step1.jsp" target="_parent">[切换用户]</a>
					</font> &nbsp; <font color="#0000FF"><a href="user/regetpass.jsp">[找回密码]</a>
					</font>
					<br/>当前购物车：商品数(
					${counts }
					种)&nbsp;&nbsp;总共(${totalprice }
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
					<area shape="rect" coords="633,10,724,42" href="about/aboutwe.jsp">
				</map>
			</td>
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
						<td width="99"><font color="#FFFFFF" face="宋体">搜索</font>
						</td>
						<td width="96"><a href="test/menu_menuSearch.action">家常美味</a>
						</td>
						<td width="289"><a href="test/menu_menuSearch.action">点一点，美味即刻拥有</a>
						</td>
						<td width="237"><a href="test/menu_menuSearch.action">足不出户体验经典美食</a>
						</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="8"></td>
		</tr>
	</table>
	<table width="955" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="203" valign="top">
				<table width="188" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="27" background="images/kb.gif"
							style="background-repeat: no-repeat">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="15%">&nbsp;</td>
									<td width="85%" class="w12">网站公告</td>
								</tr>
							</table></td>
					</tr>
				</table>
				<table width="180" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
							<p>&nbsp;&nbsp;&nbsp;&nbsp;顾客，你好，在订餐之前，请留意我们的订餐时间，我们订餐时间是早10点到夜晚9点。</p>
							<p>如果需要订餐，请提前2小时预定，否则我们视其为无效订餐，我们会根据情况取消你的订单，请谅解。</p>
							<p>&nbsp;&nbsp;---快乐订餐欢迎您的到来</p></td>
					</tr>
				</table>
				<table width="188" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="8"></td>
					</tr>
				</table>
				<table width="188" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="27" background="images/kb.gif"
							style="background-repeat: no-repeat">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="15%">&nbsp;</td>
									<td width="85%" class="w12">商品分类</td>
								</tr>
							</table></td>
					</tr>
				</table>
				<table width="180" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>早餐(09:00-11:00)</td>
					</tr>
					<tr>
						<td>午餐(11:00-15:00)</td>
					</tr>
					<tr>
						<td>晚餐(15:00-21:00)</td>
					</tr>
				</table>
				<table width="188" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="8"></td>
					</tr>
				</table>
				<table width="188" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="27" background="images/kb.gif"
							style="background-repeat: no-repeat">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="15%">&nbsp;</td>
									<td width="85%" class="w12">商品销售TOP10</td>
								</tr>

							</table></td>
					</tr>
				</table>
				<table width="180" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><a href="test/menu_menuInfo.action?menu_id=102"> NO.1 大盘鸡</a>
						</td>
					</tr>
					<tr>
						<td><a href="test/menu_menuInfo.action?menu_id=105"> NO.2 鱼香肉丝</a>
						</td>
					</tr>
					<tr>
						<td><a href="test/menu_menuInfo.action?menu_id=104"> NO.3 青椒肉丝</a>
						</td>
					</tr>
					<tr>
						<td><a href="test/menu_menuInfo.action?menu_id=115"> NO.4 宫保鸡丁</a>
						</td>
					</tr>
				</table>
				<table width="188" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><a href="test/mg_messageUI.action"><img
								src="images/message.gif" width="187" height="61" border="0">
						</a>
						</td>
					</tr>
				</table></td>
			<td width="752" valign="top">
				<table width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td width="81%" style="margin-top: 1000px" >
							<div class="content" id="cptj_div2" style="display: block;">
								<c:forEach items="${sessionScope.list1 }" var="menu1" begin="0" step="1">
								
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
							</div></td>

						<td width="19%" valign="top" bordercolor="#FF00CC">
							<table id="__4" width="230" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td colspan="3"><img src="images/yh_01.gif" width="230"
										height="41" alt="">
									</td>
								</tr>
								<tr>
									<td width="15" background="images/yh_02.gif">&nbsp;</td>
									<td width="208"><marquee height="150"
											onMouseOut=this.start(); onMouseOver=this.stop()
											direction="up" scrollamount="2">
											<p style="color: #FF3300">消费满100元，立减10元</p>
											<p style="color: #FF3300">消费满200元，立减25元</p>
											<p style="color: #FF3300">订购大盘鸡一份，送10个馒头</p>
											<p style="color: #FF3300">订购四道家常菜，可获赠水果沙拉一份</p>
											<p style="color: #FF3300">情人节，情侣订餐可获赠甜蜜蜜一份</p>
											<p style="color: #FF3300">团购享受8折超值优惠</p>

										</marquee>
									</td>
									<td width="7" background="images/yh_04.gif">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3"><img src="images/yh_05.gif" width="230"
										height="13" alt="">
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="36"><img src="images/tjsp.gif" width="748"
							height="36" border="0" usemap="#Map2"></td>
					</tr>
					<tr>
						<td align="center">
							<div class="content" id="cptj_div2" style="display: block;">
								<c:forEach items="${sessionScope.list1 }" var="menu2" begin="0" step="1">
								
									<dl style="margin-left: 25px; margin-top: 10px; float: left">
										<dt style="float: left">
										<a href="test/menu_menuInfo.action?menu_id=${menu2.menu_id }">
											<img src="upload/${menu2.picture_name}" width="130"
												height="120" title="${menu2.menu_name }" /></a>
										</dt>
										<dd style="clear: left; float: left; margin: 0px; text-align: center; width: 130px">
										<a href="test/menu_menuInfo.action?menu_id=${menu2.menu_id }">
										${menu2.menu_name }<br>
											口味：家常菜<br> 价格：${menu2.menu_price }元</a>
										</dd>
									</dl> 
								</c:forEach>
							</div></td>
					</tr>

				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="36" background="images/zxsj.gif"
							style="background-repeat: no-repeat"><img
							src="images/zxsj.gif" width="748" height="36" border="0"
							usemap="#Map3">
						</td>
					</tr>
					<tr>
						<td align="center">
							<div class="content" id="cptj_div2" style="display: block;">
								<c:forEach items="${sessionScope.list1 }" var="menu3" begin="0" step="1">
								
									<dl style="margin-left: 25px; margin-top: 10px; float: left">
										<dt style="float: left">
										<a href="test/menu_menuInfo.action?menu_id=${menu3.menu_id }">
											<img src="upload/${menu3.picture_name}" width="130"
												height="120" title="${menu3.menu_name }" /></a>
										</dt>
										<dd style="clear: left; float: left; margin: 0px; text-align: center; width: 130px">
										<a href="test/menu_menuInfo.action?menu_id=${menu3.menu_id }">
										${menu3.menu_name }<br>
											口味：家常菜<br> 价格：${menu3.menu_price }元</a>
										</dd>
									</dl> 
								</c:forEach>
							</div></td>
					</tr>

				</table></td>
		</tr>
	</table>
	<div align="center">
		<iframe src="includeFile/shippingBottom.jsp" width="1000" height="80"
			marginheight="0" marginwidth="0" frameborder="0" scrolling="no"></iframe>

	</div>
	<map name="Map2">
		<area shape="rect" coords="17,7,108,33" href="test/menu_menuSearch.action">
	</map>
	<map name="Map3">
		<area shape="rect" coords="18,8,112,34" href="test/menu_menuSearch.action">
	</map>

</body>
</html>