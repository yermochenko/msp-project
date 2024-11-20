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
import java.util.List;

public class NoteListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(Connection connection = DatabaseConnector.getConnection()) {
			NoteDaoImpl noteDao = new NoteDaoImpl();
			noteDao.setConnection(connection);
			List<Note> notes = noteDao.readAll();
			req.setAttribute("notes", notes);
			req.setAttribute("activeNotesCount", notes.stream().filter(note -> !note.isDone()).count());
			req.getRequestDispatcher("/WEB-INF/jsp/note/list.jsp").forward(req, resp);
		} catch(SQLException | DaoException e) {
			throw new ServletException(e);
		}
	}
}
