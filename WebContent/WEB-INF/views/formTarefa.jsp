<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>

	<h1>Add New Doubt</h1>
	<form method="POST" action="save">
		<table>
			<tr>
				<td>Question :</td>
				<td><textarea name="question"></textarea></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="hidden" name="username" value="${username}" /></td>
				<td><input type="submit" value="save" /></td>
			</tr>
		</table>
	</form>
</body>
</html>