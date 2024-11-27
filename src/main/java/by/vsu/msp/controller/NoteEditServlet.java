package by.vsu.msp.controller;

import by.vsu.msp.dao.DaoException;
import by.vsu.msp.dao.pgsql.DatabaseConnector;
import by.vsu.msp.dao.pgsql.NoteDaoImpl;
import by.vsu.msp.domain.Note;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class NoteEditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("id");
		if(idParam != null) {
			try {
				Integer id = Integer.parseInt(idParam);
				try(Connection connection = DatabaseConnector.getConnection()) {
					NoteDaoImpl noteDao = new NoteDaoImpl();
					noteDao.setConnection(connection);
					Optional<Note> note = noteDao.read(id);
					if(note.isPresent()) {
						req.setAttribute("note", note.get());
					} else {
						throw new IllegalArgumentException();
					}
				} catch(SQLException | DaoException e) {
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
