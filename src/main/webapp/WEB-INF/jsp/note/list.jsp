<%@page contentType="text/html;charset=UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="by.vsu.msp.domain.Note"%>
<%@page import="java.text.SimpleDateFormat"%>

<%!
	public static long filterActive(List<Note> notes) {
		return notes.stream().filter(note -> !note.isDone()).count();
	}
%>

<%
	@SuppressWarnings("unchecked")
	List<Note> notes = (List<Note>) request.getAttribute("notes");
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
%>

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
		<h1>Список заметок (JSP)</h1>
		<ol>
			<%
				for(Note note : notes) {
			%>
			<li
				<%
					if(note.isDone()) {
				%>
					class="done"
				<%
					}
				%>
			><%= sdf.format(note.getDate()) %>, <%= note.getTitle() %></li>
			<%
				}
			%>
		</ol>
		<p>Всего активных: <%= filterActive(notes) %></p>
	</body>
</html>