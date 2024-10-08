package by.vsu.msp.view;

import by.vsu.msp.domain.Note;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

public class NoteListViewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		@SuppressWarnings("unchecked")
		List<Note> notes = (List<Note>) req.getAttribute("notes");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("""
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
				<h1>Список заметок</h1>
				<ol>
		""");
		for(Note note : notes) {
			out.print("<li");
			if(note.isDone()) {
				out.print(" class=\"done\"");
			}
			out.print(">");
			out.print(sdf.format(note.getDate()) + ", " + note.getTitle());
			out.print("</li>");
		}
		out.printf("""
				</ol>
				<p>Всего активных: %d</p>
			</body>
		</html>
		""", notes.stream().filter(note -> !note.isDone()).count());
	}
}
