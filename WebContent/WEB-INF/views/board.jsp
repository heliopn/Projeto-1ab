<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="mvc.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Doubt's List</h1>
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<td>ID</td>
			<td>Question</td>
			<td>Answer</td>
			<td>Autor</td>
			<td>Edit</td>
			<td>Delete</td>
			<td>
				<form action="newPost">
					<input type="hidden" name="username" value="${username}">
					<input type="submit" name="button" value="New doubt" />
				</form>
			</td>
		</tr>
		<c:forEach var="form" items="${list}">
		<% Form form = (Form) request.getSession().getAttribute("form"); %>
			<tr>
				<td>${form.id}</td>
				<td>${form.question}</td>
				<td>${form.answer}</td>
				<td>${form.autor}</td>
			<td>
				<form action="editpost">
					<input type="hidden" name="editid" value="${form.id}">
					<input type="hidden" name="editquestion" value="${form.question}">
					<input type="submit" name="button" value="edit" />
				</form>
			</td>
			<td>
				<form action="deletpost">
					<input type="hidden" name="deletid" value="${form.id}">
					<input type="submit" name="button" value="delet" />
				</form>
			</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<form action="/Duvidas">
		<input type="submit" name="button" value="Logout" />
	</form>
</body>
</html>