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
		<%--@elvariable id="user" type="by.vsu.msp.domain.User"--%>
		<c:if test="${not empty user}">
			<c:url var="url__logout" value="/logout.html"/>
			<p>Добро пожаловать, ${user.login}. <a href="${url__logout}">Выйти</a></p>
		</c:if>
		<h1>Список заметок</h1>
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
					<c:choose>
						<c:when test="${not empty user}">
							<c:url var="url_note_edit" value="/note/edit.html">
								<c:param name="id" value="${note.id}"/>
							</c:url>
							<a href="${url_note_edit}"><fmt:formatDate value="${note.date}" pattern="dd.MM.yyyy"/>, ${note.title}</a>
						</c:when>
						<c:otherwise>
							<fmt:formatDate value="${note.date}" pattern="dd.MM.yyyy"/>, ${note.title}
						</c:otherwise>
					</c:choose>
				</li>
			</c:forEach>
		</ol>
		<%--@elvariable id="activeNotesCount" type="java.lang.Integer"--%>
		<p>Всего активных: ${activeNotesCount}</p>
		<c:if test="${not empty user}">
			<c:url var="url_note_edit" value="/note/edit.html"/>
			<p><a href="${url_note_edit}">Добавить новую заметку</a></p>
		</c:if>
	</body>
</html>