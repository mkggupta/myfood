<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login jsp</title>
<script type="text/javascript" src="../js/regvalidation.js"></script>
</head>
<body>

<form id="login" name="login" method="post" action="/myfood/rest/api/user/pub/register" onsubmit="return validateLoginForm()">
<table width="300" border="1">
<tbody>
	<tr>

<div id="error" ></div>
</tr>
<tr>
<td colspan="2">&nbsp;username :<input type="text" maxlength="25"  value="" name="usrName" id="username" width="100%"></td></tr>
<tr>
<td colspan="2">&nbsp;password :<input type="password" maxlength="25" value="" name="password" id="password" width="100%"></td></tr>
<tr>
<td>&nbsp;<input type="submit" value="Sign in"  id="button" ></td>
<td>&nbsp; <a href="http://localhost:8080/myfood/rest/api/user/pub/register">Forgot password?</a></td></tr>
</tbody>
</table>
<form>

</body>
</html>