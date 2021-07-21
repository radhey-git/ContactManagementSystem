<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<script>
	function confirmDelete() {
		return confirm("Are you sure, you want to delete?");
	}
</script>
<meta charset="ISO-8859-1">
<title>Display All Contacts</title>

</head>
<body>
	<h2>View Contacts</h2>
	<a href="loadForm">+ Add New Contact</a>
	<br>
	<p>Total Pages : :${totalPages}</p>
	<table border="1">
		<thead>
			<tr>
				<th>Sr.No</th>
				<th>Name</th>
				<th>Email</th>
				<th>Number</th>
				<th>Action</th>
			</tr>
		</thead>
		<c:forEach items="${contacts}" var="c" varStatus="count">
			<tr>
				<td>${count.index+1}</td>
				<td>${c.contactName}</td>
				<td>${c.contactEmail}</td>
				<td>${c.contactNumber}</td>
				<td><a href="editContact?cid=${c.contactId}">Edit</a> &nbsp; <a
					href="deleteContact?cid=${c.contactId}"
					onclick="return confirmDelete()">Delete</a></td>
			</tr>
		</c:forEach>
		
	</table>
	<br>
	<!-- Previous hiper link -->
	<c:if test="${currPno>1}">
		<a href="viewContacts?pno=${currPno-1}">Previous</a>
	</c:if>
	<!-- Page No hiper link -->
	<c:forEach begin="1" end="${totalPages}" var="pageNo">
		<a href="viewContacts?pno=${pageNo}">${pageNo}</a>
	</c:forEach>
	<!-- Next hiper link -->
	<c:if test="${currPno< totalPages}">
		<a href="viewContacts?pno=${currPno + 1}">Next</a>
	</c:if>
</body>
</html>