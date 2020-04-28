<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
	<center>
		<c:forEach items="${listProducts}" var="product">
			<table border='1'>
				<tr>
					<td width='200px'><b>${product.name}</b></td>
					<td width='100px'><form method='post'>
							<input type='hidden' name='idProduct' value='${product.id}'><input
								type='submit' value='More'>
						</form></td>
				</tr>
				<tr>
					<td><img height='${imgHeight}'
						src="/lesson08/static/images/${product.id}.jpg" /></td>
					<td>${product.description}</td>
				</tr>
				<tr>
					<td><i>price:</i> ${product.price}</td>
					<td align='right'><button>Buy!</button></td>
				</tr>

			</table>

		</c:forEach>
	</center>
</body>



</body>
</html>