package by.vsu.msp.controller;

import by.vsu.msp.domain.Note;
import by.vsu.msp.service.NoteService;
import by.vsu.msp.service.ServiceException;
import by.vsu.msp.service.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class NoteListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ServiceFactory serviceFactory = ServiceFactory.newInstance()) {
			NoteService noteService = serviceFactory.getNoteServiceInstance();
			List<Note> notes = noteService.findAll();
			req.setAttribute("notes", notes);
			req.setAttribute("activeNotesCount", notes.stream().filter(note -> !note.isDone()).count());
			req.getRequestDispatcher("/WEB-INF/jsp/note/list.jsp").forward(req, resp);
		} catch(ServiceException e) {
			throw new ServletException(e);
		}
	}
}
