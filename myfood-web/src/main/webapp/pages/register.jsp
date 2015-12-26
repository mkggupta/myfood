<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>register jsp</title>
<script type="text/javascript" src="../js/regvalidation.js"></script>
</head>
<body>

<form id="register" name="register" method="post" action="/myfood/rest/api/user/pub/register" onsubmit="return validateForm()">
<table width="300" border="1">
<tbody>
	<tr>

<div id="error" ></div>
</tr>
<tr>
<td colspan="2">&nbsp;FirstName :<input type="text" maxlength="25"  value="" name="fName" id="fName" width="100%"></td></tr>
<tr>
<tr>
<td colspan="2">&nbsp;LastName :<input type="text" maxlength="25"  value="" name="lName" id="lName" width="100%"></td></tr>
<tr>
<tr>
<td colspan="2">&nbsp;Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :<input type="text" maxlength="100"  value="" name="usrName" id="username" width="100%"></td></tr>
<tr>
<td colspan="2">&nbsp;Password&nbsp;&nbsp;:<input type="password" maxlength="25" value="" name="password" id="password" width="100%"></td></tr>
<tr>
 <input type="hidden" name="registrationMode" value="4"> 
<td>&nbsp;<input type="submit" value="Register Me"  id="button" ></td>
<td>&nbsp; <a href="http://localhost:8080/myfood/rest/api/user/pub/register">Forgot password?</a></td></tr>
</tbody>
</table>
<form>

</body>
</html>