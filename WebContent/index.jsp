<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>demo</title>
</head>

<body>
	<div class="loginDiv">
		<form action="login" method="POST">
			<div>
				</label> <select name="portal" id="portal">
					<option value="0">�������1</option>
					<option value="1">�������2</option>
					<option value="2">�������3</option>
				</select> <label>�ʺ�: <input type="text" name="account"> <label>����:</label> <input
					type="password" name="password"><label>��֤��:</label>
					<input type="text" name="checkCode" id="checkCode"> <img
					src="getCheckCodeImage?portal=0"
					onclick="this.src='getCheckCodeImage?portal='+document.getElementById('portal').value">
					<input type="submit" value="��¼" class="btn btn-success">
			</div>
		</form>

	</div>
</body>
</html>