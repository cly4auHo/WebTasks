<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<meta charset="UTF-8" />
	<link href="style.css" rel="stylesheet" />
</head>
<body>

<%@ page import="jsplogic.LogPasChecker" %> 

<%
String login = request.getParameter("login");
String pasword = request.getParameter("password");

	if(login !=null && pasword !=null){
		LogPasChecker checker = new LogPasChecker();

		if(checker.printer(login, pasword)){
			out.write("well play");
		}else{
			out.write("bad move");
		}	
	}
%>

<a href="registration.jsp">Registration</a>

<form id="loginForm" action="" method="post">

	<div class="field">
		<label>Enter your login:</label>
		<div class="input"><input type="text" name="login" value="" id="login" /></div>
	</div>

	<div class="field">

		<a href="#" id="forgot">Forgot your password?</a>

		<label>Enter your password:</label>
		<div class="input"><input type="password", name="password", value="", id="pass" /></div>
	</div>

	<div class="submit">
		<button type="submit">Enter</button>
		<label id="remember"><input name="", type="checkbox", value="" /> Remember me</label>
	</div>
	
</form>
</body>
</html>