package by.vsu.msp.controller;

import by.vsu.msp.domain.Note;
import by.vsu.msp.model.NoteRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class NoteListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Note> notes = NoteRepository.read();
		req.setAttribute("notes", notes);
		req.getRequestDispatcher("/WEB-INF/note/list.html").forward(req, resp);
	}
}
