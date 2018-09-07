<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<form action="/TestProject/user/details" method="post">
		<table style="with: 50%">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="fname" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lname" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" name="mobile" /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><input type="email" name="email" /></td>
			</tr>
				</table>
		<input type="submit" value="Submit" />
	</form>
</body>
</html>