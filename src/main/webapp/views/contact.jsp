<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Info</title>
</head>
<body>
	<form:form action="saveContact?contactId=${contact.contactId}" modelAttribute="contact" method="POST" align="center">
		<hr>
		<h1 align="center"><font color="lightBlue">Save Contact</font></h1>
		<hr>
		<p>
			<font color="green">${saved}</font>
		</p>
		<p>
			<font color="Red">${error}</font>
		</p>
		<hr>
		
		<div align="center">
		<table>
		
			<tr>
				<td>Contact Name :</td>
				<td><form:input path="contactName" /></td>
			</tr>
			
			<tr>
				<td>Contact Number :</td>
				<td><form:input path="contactNumber" /></td>
			</tr>

			<tr>
				<td>Contact Email Id:</td>
				<td><form:input path="contactEmail" /></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="save"></td>
			</tr>
			
			<tr>
				<td><a href="viewContacts">View All Contacts</a></td>
			</tr>

		</table>
		<hr>
	</div>
	</form:form>
</body>
</html>