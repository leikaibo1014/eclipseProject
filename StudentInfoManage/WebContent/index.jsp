<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理系统登录</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">

	function resetValue(){
		document.getElementById("userName").value="";
		document.getElementById("password").value="";
	}
</script>
</head>
<body>
	<div align="center" style="padding-top: 50px;">

		<form action="login" method="post">
		<table  width="740" height="500" background="images/logg.jpg" >
			<tr height="180">
				<td colspan="4"></td>
			</tr>
			<tr height="80">
				<td width="40%"></td>
				<td width="10%">用户名：</td>
				<td><input type="text" value="${userName }" name="userName" id="userName" class="easyui-validatebox" required="true"/></td>
				<td width="60%"></td>
			</tr>
			<tr height="80">
				<td width="40%"></td>
				<td width="10%">密  码：</td>
				<td><input type="password" value="${password }" name="password" id="password"  class="easyui-validatebox" required="true"/></td>
				<td width="30%"></td>
			</tr>
			<tr height="50">
				<td width="40%"></td>
				<td width="10%">&nbsp&nbsp&nbsp&nbsp<input type="submit" value="登录"/></td>
				<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="button" value="重置" onclick="resetValue()"/></td>
				<td width="30%"></td>
			</tr>
			<tr height="10">
				<td width="40%"></td>
				<td colspan="3">
					<font color="red">${error }</font>
				</td>
			</tr>
			<tr >
				<td></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>