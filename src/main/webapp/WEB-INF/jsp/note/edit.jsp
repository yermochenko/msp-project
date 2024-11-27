<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%--@elvariable id="note" type="by.vsu.msp.domain.Note"--%>
<c:choose>
	<c:when test="${not empty note}">
		<c:set var="title" value="Редактирование заметки"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Добавление новой заметки"/>
		<jsp:useBean id="note" class="by.vsu.msp.domain.Note"/>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${title}</title>
		<style>
			label {
				display: block;
			}
		</style>
	</head>
	<body>
		<h1>${title}</h1>
		<c:url var="url_note_save" value="/note/save.html"/>
		<form action="${url_note_save}" method="post">
			<c:if test="${not empty note.id}">
				<input type="hidden" name="id" value="${note.id}">
			</c:if>
			<div>
				<label for="id-title">Название:</label>
				<input type="text" name="title" value="${note.title}" id="id-title">
			</div>
			<div>
				<label for="id-content">Описание:</label>
				<textarea name="content" id="id-content">${note.content}</textarea>
			</div>
			<div>
				<label for="id-date">Дата:</label>
				<fmt:formatDate var="noteDate" value="${note.date}" pattern="yyyy-MM-dd"/>
				<input type="date" name="date" value="${noteDate}" id="id-date">
			</div>
			<div>
				<label>
					<c:if test="${note.done}"><c:set var="checked" value="checked"/></c:if>
					<input type="checkbox" name="done" value="done" ${checked}> выполнена
				</label>
			</div>
			<div>
				<button type="submit">Сохранить</button>
				<c:url var="url_note_list" value="/note/list.html"/>
				<a href="${url_note_list}">Отменить</a>
			</div>
		</form>
	</body>
</html>