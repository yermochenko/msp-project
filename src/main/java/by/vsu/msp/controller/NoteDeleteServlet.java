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

public class NoteDeleteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			try(ServiceFactory serviceFactory = ServiceFactory.newInstance()) {
				NoteService noteService = serviceFactory.getNoteServiceInstance();
				Optional<Note> note = noteService.delete(id);
				if(note.isPresent()) {
					resp.sendRedirect(req.getContextPath() + "/note/list.html");
				} else {
					throw new IllegalArgumentException();
				}
			} catch(ServiceException e) {
				throw new ServletException(e);
			}
		} catch(IllegalArgumentException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
