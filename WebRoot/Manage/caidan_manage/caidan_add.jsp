<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="../../css/order_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript" src="../../JS/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
  function onchange(){
	  var file=$("#file").val();
	  debugger;
	  $("#filename").val(file);
	  console.log(file);
  }
</script>
<jsp:include page="../manageTop.jsp"/>
<form  method="post" action="${pageContext.request.contextPath }/test/menu_addMenu.action" enctype="multipart/form-data">
  
  <table width="694" height="183" border="0" align="center">
    <tr>
      <td height="55" colspan="2"><div align="center"><font size="5">添加菜单</font></div></td>
    </tr>
    
    <tr>
      <td width="261"><div align="right">菜名：</div></td>
      <td width="423"><input type="text" name="menu.menu_name" id="name"></td>
    </tr>
    <tr><td height="8px"></td></tr>
    <tr>
      <td><div align="right">金额：</div></td>
      <td><input type="text" name="menu.menu_price" id="price">
      元</td>
    </tr>
    <tr><td height="8px"></td></tr>
    <tr>
      <td><div align="right">菜单图片：</div></td>
      <td>
      	<input type="hidden" name="filename" id="filename"/>
      	<input type="file" id="file" name="file" value="选择图片"/>
      </td>
    </tr>
    <tr><td height="8px"></td></tr>
    <tr>
      <td><div align="right">简介：</div></td>
      <td><textarea name="menu.menu_content" cols="40" rows="4" ></textarea></td>
    </tr>
    <tr><td height="8px"></td></tr>
      <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="button" id="button" value="提交">
      <div align="right"><a href="caidan_index.jsp"><img src="../../images/fanhui.gif" border="0"/></a></div></td>
    </tr>
  </table>
</form>
	<jsp:include page="../manageBottom.jsp"/>
</body>
</html>