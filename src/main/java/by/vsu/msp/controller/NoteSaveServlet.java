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
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NoteSaveServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String idParam = req.getParameter("id");
		String titleParam = req.getParameter("title");
		String contentParam = req.getParameter("content");
		String dateParam = req.getParameter("date");
		String doneParam = req.getParameter("done");
		try {
			Note note = new Note();
			if(idParam != null) {
				note.setId(Integer.parseInt(idParam));
			}
			if(titleParam == null || titleParam.isBlank()){
				throw new IllegalArgumentException();
			}
			note.setTitle(titleParam.trim());
			if(contentParam != null && !contentParam.isBlank()){
				note.setContent(contentParam.trim());
			}
			try {
				note.setDate(sdf.parse(dateParam));
			} catch(ParseException e) {
				throw new IllegalArgumentException();
			}
			note.setDone(doneParam != null);
			try(ServiceFactory serviceFactory = ServiceFactory.newInstance()) {
				NoteService noteService = serviceFactory.getNoteServiceInstance();
				noteService.save(note);
				resp.sendRedirect(req.getContextPath() + "/note/list.html");
			} catch(ServiceException e) {
				throw new ServletException(e);
			}
		} catch(IllegalArgumentException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
