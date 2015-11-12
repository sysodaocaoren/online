<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加留言</title>
<script language="javascript" src="../JS/addnote.js" type="text/javascript" charset="gb2312"></script>
<link href="../css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br/>
	<jsp:include page="../includeFile/shippingTop.jsp"></jsp:include>
    <br>
    <center><font size="5" >留言板</font></center>
    <table align="center"><tr><td align="right" width="400px"><a href="../test/mg_showOneself.action">查看我的留言记录</a></td></tr></table>
    	
	<center>
	<form action="" method="post" name="mainForm">
		<table width="600px">
			<tr>
				<td width="60px">主题:</td>
  				<td align="left">
					<input id="subject" name="message.subject" type="text" onBlur="checkSubject()">
					<br>
				</td>
				<td>
					<span id="mgsubject"></span>
				</td>
			</tr>
			<tr>
				<td>作者:</td>
				<td align="left">					
					<input name="message.username" type="text" disabled="disabled" value="${user_name }"/>
				
				</td>
			</tr>
			<tr>
				<td>内容:</td>
				<td align="left">
					<textarea id="content" name="message.content" cols="50" rows="6"  onblur="checkContent()"></textarea>
				
				</td>
				<td>
					<span id="mgcontent"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
		      <input name="" type="submit" value="提交" onClick="getSub()">
				</td>
		  </tr>
		</table>
	</form>
	</center>
	<table width="550px" align="center"><tr><td>
	<c:forEach items="${messageList }" var="mg" begin="0" step="1">
		<fieldset style="border:1px solid  #FF9900;	padding:8px ;" >
			<legend><font color="#FF9900" size="3">${mg.username }</font></legend>主题：${mg.subject}<br/><br/>&nbsp;&nbsp;${mg.content }<br/><br/>
			<div align="right">时间:${mg.date }</div>	
		</fieldset>
	</c:forEach></td></tr>	
	</table>
	<table width="100%" cellspacing="0" cellpadding="0">
			<tr><td align="center" height="25"><font size="3">共${pageinfo.totalCount }条 &nbsp;&nbsp;&nbsp;第<c:forEach begin="${pageinfo.startPage }" end="${pageinfo.endPage }" var="pg">
				<c:if test="${pg!=page }">
				<a href="../test/mg_messageUI.action?page=${pg }">${pg } </a>
				</c:if>
				<c:if test="${pg==page }">
				<font color="#0088ff">${pg }</font>
				</c:if>
				</c:forEach>&nbsp;页&nbsp;&nbsp;&nbsp;共${pageinfo.endPage }页</font></td></tr>
		</table>
	<div align="center">
	<br><br><br><br><br><br><br><br>
		<iframe src="../includeFile/shippingBottom.jsp" width="1050"
					height="80" marginheight="0" marginwidth="0" frameborder="0"
					scrolling="no"></iframe>

</div>
</body>
</html>