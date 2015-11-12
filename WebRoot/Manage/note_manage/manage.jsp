<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理留言板</title>
<link href="../../css/order_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function selectAll()  
	{ 
		for (var i=0;i<document.myform.messages.length;i++) { 
			var temp=document.myform.messages[i]; 
			temp.checked=!temp.checked; 
		} 
		if (document.myform.selectButton.value=="全部选择") 
		{ 
  			document.myform.selectButton.value="取消全选"; 
		} 
		else 
		{ 
 			 document.myform.selectButton.value="全部选择"; 
		} 
	}
</script>
</head>
<body>

		<jsp:include page="../manageTop.jsp"/>
		<table align="center"><tr><td height="10px"></td></tr></table>
		<center><font size="5" >管理留言板</font></center>
		<%int i=1; int j=0;%>		
		<form action="${pageContext.request.contextPath }/test/mg_deleteMessage.action" name="myform" method="post">
		<table width="550px" align="center">
		<tr class="tr_sty"><th width="80px"></th><th>编号</th><th width="150px">主题</th><th>作者</th><th>时间</th></tr>
		<c:forEach items="${messageList }" var="mg" begin="0" step="1">
		<tr class="<% if((j++)%2==0) out.print("tr_sty0"); else out.print("tr_sty1");%>" height="25px">
		<td align="center"><input type="checkbox" name="messages" value="${mg.messageID }" ></td>
		<td align="center"><%=i++ %></td><td align="center"><a href="../../test/mg_showOneMessage.action?messageID=${mg.messageID }">${mg.subject }</a> </td>
		<td align="center">${mg.username }</td> <td align="center">${mg.date }</td>
		</tr>	
		</c:forEach>
		</table>
		<center><div style="width:550px;"><hr/></div>
		<input name="selectButton" type="button" value="全部选择" onclick="selectAll()">&nbsp;&nbsp;
		<input type="submit" value="删除已选项"></center>
		</form>
		
		<!-- -------------------------------以下是实现分页显示------------------------------------ -->
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr><td align="center" height="25" ><font size="3" class="ziti">共${pageinfo.totalCount }条 &nbsp;&nbsp;&nbsp;第<c:forEach begin="${pageinfo.startPage }" end="${pageinfo.endPage }" var="pg">
				<c:if test="${pg!=page }">
				<a href="${pageContext.request.contextPath }/test/mg_messageUI.action?page=${pg }">${pg } </a>
				</c:if>
				<c:if test="${pg==page }">
				<font color="#0088ff">${pg }</font>
				</c:if>
				</c:forEach>&nbsp;页&nbsp;&nbsp;&nbsp;共${pageinfo.endPage }页</font></td></tr>
		</table>
		
	<jsp:include page="../manageBottom.jsp"/>
</body>
</html>