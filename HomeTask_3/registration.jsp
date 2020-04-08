<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>

<%
// login (email) type: text (errors emty or not email)
// name type: text     (errors not empty)
// age type: text  12~100
// address select  (check address)
// gender radiobutton (errors not empty)
// comments textarea (not empty)(check not null)
// i agree to install AmigoBrowser (no DB) checkbox
// password password  min:8char(2-numbers,2-Headletters Latin only)(errors pass not ???)
// repeat password  (no DB) password

// rigthside Error text
%>

<%!boolean existError = true;%>

<%
String login = request.getParameter("login");
String password = request.getParameter("password");
String repassword = request.getParameter("re-password");
String age = request.getParameter("age");
String gender = request.getParameter("gender");
String address = request.getParameter("address");
String comments = request.getParameter("comments");
String amigo = request.getParameter("amigo");

StringBuilder errorText = new StringBuilder("<ul>");
String regexMail = "^(.+)@(.+)$";
String regexPassword = "(?=(.*[a-zA-Z0-9]){2})";
int minLenghtPassword = 8;

if(login != null){
	existError = false;
	
	if(login.length() == 0) {
		existError = true;
		errorText.append("<li>Empty Login</li>");
	}else{
		Pattern pattern = Pattern.compile(regexMail);
		Matcher matcher = pattern.matcher(login);

		if(!matcher.matches()){
			existError = true;
			errorText.append("<li>email must be clever</li>");
		}
	}

	if(password.length() == 0) {
		existError = true;
		errorText.append("<li>Empty Password</li>");
	}else{

		if(password.length() < minLenghtPassword){
			existError = true;
			errorText.append("<li>Password must have min 8 characters</li>");
		}

		Pattern pattern = Pattern.compile(regexPassword);
		Matcher matcher = pattern.matcher(password);

		if(!matcher.find()){
			existError = true;
			errorText.append("<li>Password must have: 2 numbers,2 big and small letters</li>");
		}
	} 

	if(repassword.length() == 0) {
		existError = true;
		errorText.append("<li>Empty RePassword</li>");
	} 

	if(!password.equals(repassword)){
		existError = true;
		errorText.append("<li>Password and RePassword must be equals</li>");
	}

	if(age.length() == 0){
		existError = true;
		errorText.append("<li>Age field is empty</li>");
	}else{
		int ageInt = Integer.parseInt(age);

		if(ageInt < 12 || ageInt > 100){
			existError = true;
			errorText.append("<li>age must be more than 11 and less than 100</li>");
		}
	}

	if(gender == null){
		existError = true;
		errorText.append("<li>chose your destiny</li>");
	}

	if(comments == null){
		existError = true;
		errorText.append("<li>Write comments pls</li>");
	}
	
	errorText.append("</ul>");
	
}

	if(existError){
%>

<form action = ''>
	<table border = '1'>
	<tr>
		<td width = '50' align = 'center'>
		Login
		</td>
		<td width = '50' align = 'center'>
		<input type = 'text', name = 'login'/>
		</td>
	  </tr>
	  
	  <tr>
		<td width = '50' align = 'center'>
		Password
		</td>
		<td width = '50' align = 'center'>
		<input type = 'password', name = 'password'/>
		</td>
	  </tr>
	  
	  <tr>
		<td width = '50' align = 'center'>
		Re-Password
		</td>
		<td width = '50' align = 'center'>
		<input type = 'password', name = 're-password'/>
		</td>
	  </tr>
	  
	  <tr>
		<td width = '50' align = 'center'>
		Age
		</td>
		<td width = '50' align = 'center'>
		<input type="text", name = 'age' oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" />
		</td>
	  </tr>
	  
	  <tr>
		<td width = '50' align = 'center'>
		Gender
		</td>
		<td width = '50' align = 'center'>
		F<input type = 'radio', name = 'gender' value='F'/>
		M<input type = 'radio', name = 'gender' value='M'/>
		</td>
	  </tr>
	  
	  	  <tr>
		<td width = '50' align = 'center'>
		Address
		</td>
		<td width = '50' align = 'center'>
		<select name='address'>
			<option value='1'> DNR </option>
			<option value='2'> LNR </option>
			<option value='3'> Crimea </option>
		</select>
		</td>
	  </tr>
	  
	  	  <tr>
		<td width = '50' align = 'center'>
		Comments
		</td>
		<td width = '50' align = 'center'>
		<textarea name='comments' cols='20' rows='10'></textarea>
		</td>
	  </tr>
	  
	  	  <tr>
		<td width = '50' align = 'center'>
		Amigo
		</td>
		<td width = '50' align = 'center'>
		<input type = 'checkbox', name = 'amigo'/>
		</td>
	  </tr>
	  
	  <tr>
		<td width = '50' align = 'center'>
		</td>
		<td width = '50' align = 'center'>
		<input type = 'submit', value = 'OK'/>
		</td>
	  </tr>
	  
	</table>
</form>	

<%
}

	if(existError)
		out.write(errorText.toString());
	else
		out.write("Registration Success");
%>