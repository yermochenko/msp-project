<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Список заметок</title>
		<style>
			.done {
				text-decoration: line-through;
			}
		</style>
	</head>
	<body>
		<h1>Список заметок (JSTL)</h1>
		<ol>
			<%--@elvariable id="notes" type="java.util.List"--%>
			<c:forEach var="note" items="${notes}">
				<%--@elvariable id="note" type="by.vsu.msp.domain.Note"--%>
				<c:choose>
					<c:when test="${note.done}">
						<c:set var="cssClass" value="done"/>
					</c:when>
					<c:otherwise>
						<c:remove var="cssClass"/>
					</c:otherwise>
				</c:choose>
				<li class="${cssClass}">
					<fmt:formatDate value="${note.date}" pattern="dd.MM.yyyy"/>, ${note.title}
				</li>
			</c:forEach>
		</ol>
		<%--@elvariable id="activeNotesCount" type="java.lang.Integer"--%>
		<p>Всего активных: ${activeNotesCount}</p>
	</body>
</html>