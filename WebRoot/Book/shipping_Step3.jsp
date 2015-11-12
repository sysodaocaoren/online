<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
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

<style type="text/css">
<!--
.red {
	color: #FF0000
}

.green {
	color: #00CC00;
}
-->
</style>

<title>快乐订餐 欢迎你的到来</title>
<script type="text/javascript" src="../JS/shipping_step3.js" charset="GB2312"></script>
</head>

<body onLoad="checkEmpty()">

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
										<td width="65%"><img src="../images/lc03.gif" width="599"
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
									<td height="225" align="center">
										<table width="845" height="69" border="0" align="center">
											<tr>
												<td colspan="7">
													<form name="send_Order" action="info_saveInfo.action"
														method="post">
														<table width="836" border="0">
															<tr>
																<td colspan="2">
																	<div align="center">
																		<h3>
																			请你填写你的送餐信息<span class="style21">(共:${totalCount }
																				件商品) </span><font color="#FF6600">&nbsp;</font>
																		</h3>
																	</div></td>
															</tr>
															<tr valign="middle">
																<td width="217">
																	<div align="right">送餐地址：</div></td>
																<td width="609" rowspan="2">
																	<div align="left">
																		<textarea name="userInfo.address" id="order_address" cols="50" rows="3" onChange="checkEmpty()">${userinfo.address }</textarea>
																		<span id="address" class="red">*</span>
																	</div></td>
															</tr>
															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td>
																	<div align="right">送货时间：</div></td>
																<td>
																	<div align="left">
																		<input type="hidden" name="userInfo.sendDate" id="order_date">
																		<select name="selectDay" id="selectDay">
																			<%
																				Calendar can = Calendar.getInstance();
																					can.setTime(new Date());
																					SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
																					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE");
																					for (int i = 0; i < 7; i++) {
																			%>
																			<option value="<%=sdfOne.format(can.getTime())%>" onChange="checkDate()">
																				<%
																					out.print(sdf.format(can.getTime()));
																							if (i == 0) {
																								out.print(" 今天");
																							}
																				%>
																			</option>
																			<% 
																				can.add(can.DAY_OF_MONTH, 1);
																					}
																			%>
																		</select> <select name="selectHour" id="selectHour"
																			onChange="checkDate()">
																			<option value="-1">请选择时间</option>
																			<option value="10">早上9点</option>
																			<option value="10">早上10点</option>
																			<option value="11">早上11点</option>
																			<option value="12">中午12点</option>
																			<option value="13">下午1点</option>
																			<option value="14">下午2点</option>
																			<option value="15">下午3点</option>
																			<option value="16">下午4点</option>
																			<option value="17">下午5点</option>
																			<option value="18">下午6点</option>
																			<option value="19">晚上7点</option>
																			<option value="20">晚上8点</option>
																			<option value="21">晚上9点</option>

																		</select> <select name="selectMinu" id="selectMinu"
																			onchange="checkDate()">
																			<option value="-1">请选择时间</option>
																			<option value="00">0分</option>
																			<option value="10">10分</option>
																			<option value="20">20分</option>
																			<option value="30">30分</option>
																			<option value="40">40分</option>
																			<option value="50">50分</option>

																		</select><span id="time" class="red">*</span>
																	</div></td>
															</tr>
															<tr>
																<td>
																	<div align="right">移动电话：</div></td>
																<td>
																	<div align="left">
																		<input type="text" name="userInfo.movPho"
																			value="${userinfo.movPho }" id="order_movPho"> <span
																			class="green">二选一 (必须等于11位)</span>
																	</div></td>
															</tr>
															<tr>
																<td>
																	<div align="right">固定电话：</div></td>
																<td>
																	<div align="left">
																		<input type="text" name="userInfo.tel" value="${userinfo.tel }"
																			id="order_tel" onBlur="checkTel()"> <span
																			class="green">二选一 (不小于7位，但也不能大于12位)</span>
																	</div></td>
															</tr>
														
															<tr>
																<td>
																	<div align="right">支付方式：</div></td>
																<td>
																	<div align="left">送货上门，当面结算</div></td>
															</tr>
															<tr>
																<td width="217">
																	<div align="right">备注信息：</div></td>
																<td width="609" rowspan="2">
																	<div align="left">
																		<textarea name="userInfo.notice" cols="50" rows="3"
																			id="order_notice"></textarea>
																	</div></td>
															</tr>
															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td colspan="2">
																	<div align="center">
																		<a href="#"><img src="../images/ok.png" onClick="sendOrder()" /></a>
																	</div></td>
															</tr>
														</table>

													</form></td>
											</tr>
											<tr>
												<td width="77" height="24">编号</td>
												<td width="176">菜名</td>
												<td width="98">数量</td>
												<td width="92">单价</td>
												<td width="162">总价</td>
												<td width="214" align="left">备注</td>
											</tr>
											<c:forEach items="${list }" var="mm" begin="0" step="1">
											<tr>
												<td>${mm.menuId }</td>
												<td>${mm.name }</td>
												<td>${mm.count }</td>
												<td>${mm.price }</td>
												<td>${mm.total }</td>
												<td align="left">${mm.content }</td>												
											</tr>
											</c:forEach>
											<tr>
												<td colspan="2">&nbsp;</td>
												<td class="red"><font size="3" class="red">共：${totalCount }份</font></td>
												<td>&nbsp;</td>
												<td>
													<font size="3" class="red">
														总计：${totalprice }元
													</font></td>
												<td align="left">&nbsp;</td>
											</tr>
										</table></td>
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
