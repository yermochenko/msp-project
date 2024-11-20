package by.vsu.msp.dao.pgsql;

import by.vsu.msp.dao.DaoException;
import by.vsu.msp.dao.NoteDao;
import by.vsu.msp.domain.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NoteDaoImpl implements NoteDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Integer create(Note entity) throws DaoException {
		return 0;
	}

	@Override
	public Optional<Note> read(Integer id) throws DaoException {
		return Optional.empty();
	}

	@Override
	public List<Note> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"title\", \"content\", \"date\", \"done\" FROM \"note\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			List<Note> notes = new ArrayList<>();
			while(resultSet.next()) {
				Note note = new Note();
				note.setId(resultSet.getInt("id"));
				note.setTitle(resultSet.getString("title"));
				note.setContent(resultSet.getString("content"));
				note.setDate(resultSet.getDate("date"));
				note.setDone(resultSet.getBoolean("done"));
				notes.add(note);
			}
			return notes;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(resultSet).close(); } catch(Exception ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}

	@Override
	public void update(Note entity) throws DaoException {

	}

	@Override
	public void delete(Integer id) throws DaoException {

	}
}
