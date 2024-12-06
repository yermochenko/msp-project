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
import java.util.Optional;

public class NoteEditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("id");
		if(idParam != null) {
			try {
				Integer id = Integer.parseInt(idParam);
				try(ServiceFactory serviceFactory = ServiceFactory.newInstance()) {
					NoteService noteService = serviceFactory.getNoteServiceInstance();
					Optional<Note> note = noteService.findById(id);
					if(note.isPresent()) {
						req.setAttribute("note", note.get());
					} else {
						throw new IllegalArgumentException();
					}
				} catch(ServiceException e) {
					throw new ServletException(e);
				}
			} catch(IllegalArgumentException e) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		}
		req.getRequestDispatcher("/WEB-INF/jsp/note/edit.jsp").forward(req, resp);
	}
}
