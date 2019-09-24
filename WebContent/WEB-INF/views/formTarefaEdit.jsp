<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>

	<h1>Edit Doubt</h1>
	<form method="POST" action="editsave">
		<table>
			<tr>
				<td>Id :</td>
				<td>${form.id}</td>
			</tr>
			<tr>
				<td>Question :</td>
				<td>${form.question}</td>
			</tr>
			<tr>
				<td>Answer:</td>
				<td><textarea name="answer"></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="hidden" name="id" value="${form.id}" />
					<input type="hidden" name="question" value="${form.question}" />
					<input type="submit" value="save" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>