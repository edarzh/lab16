<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
	<title>List</title>
</head>
<body>
<h2>List</h2>
<ol>
	<c:forEach var="entry" items="${requestScope.list}">
		<li>${entry.key}
			<button type="button" class="simple" onclick="collapse(this)">[+]</button>
			<ul class="collapsible">
				<c:forEach var="inner" items="${entry.value}">
					<li>${inner}</li>
				</c:forEach>
			</ul>
		</li>
	</c:forEach>
</ol>
</body>
</html>