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
	public Integer create(Note note) throws DaoException {
		String sql = "INSERT INTO \"note\" (\"title\", \"content\", \"date\", \"done\") VALUES (?, ?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, note.getTitle());
			if(note.getContent() != null) {
				statement.setString(2, note.getContent());
			} else {
				statement.setNull(2, Types.VARCHAR);
			}
			statement.setDate(3, new java.sql.Date(note.getDate().getTime()));
			statement.setBoolean(4, note.isDone());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getInt(1);
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(resultSet).close(); } catch(Exception ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}

	@Override
	public Optional<Note> read(Integer id) throws DaoException {
		String sql = "SELECT \"id\", \"title\", \"content\", \"date\", \"done\" FROM \"note\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			Note note = null;
			if(resultSet.next()) {
				note = new Note();
				note.setId(resultSet.getInt("id"));
				note.setTitle(resultSet.getString("title"));
				note.setContent(resultSet.getString("content"));
				note.setDate(new java.util.Date(resultSet.getDate("date").getTime()));
				note.setDone(resultSet.getBoolean("done"));
			}
			return Optional.ofNullable(note);
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(resultSet).close(); } catch(Exception ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
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
				note.setDate(new java.util.Date(resultSet.getDate("date").getTime()));
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
	public void update(Note note) throws DaoException {
		String sql = "UPDATE \"note\" SET \"title\" = ?, \"content\" = ?, \"date\" = ?, \"done\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, note.getTitle());
			if(note.getContent() != null) {
				statement.setString(2, note.getContent());
			} else {
				statement.setNull(2, Types.VARCHAR);
			}
			statement.setDate(3, new java.sql.Date(note.getDate().getTime()));
			statement.setBoolean(4, note.isDone());
			statement.setInt(5, note.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}

	@Override
	public void delete(Integer id) throws DaoException {
		String sql = "DELETE FROM \"note\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}
}
