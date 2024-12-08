package by.vsu.msp.dao.pgsql;

import by.vsu.msp.dao.DaoException;
import by.vsu.msp.dao.UserDao;
import by.vsu.msp.domain.User;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Integer create(User user) throws DaoException {
		String sql = "INSERT INTO \"user\" (\"login\", \"password\") VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
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
	public Optional<User> read(Integer id) throws DaoException {
		String sql = "SELECT \"id\", \"login\", \"password\" FROM \"user\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			User user = null;
			if(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
			}
			return Optional.ofNullable(user);
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(resultSet).close(); } catch(Exception ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}

	@Override
	public Optional<User> readByLoginAndPassword(String login, String password) throws DaoException {
		String sql = "SELECT \"id\", \"login\", \"password\" FROM \"user\" WHERE \"login\" = ? AND \"password\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			User user = null;
			if(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
			}
			return Optional.ofNullable(user);
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(resultSet).close(); } catch(Exception ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}

	@Override
	public void update(User user) throws DaoException {
		String sql = "UPDATE \"user\" SET \"login\" = ?, \"password\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}

	@Override
	public void delete(Integer id) throws DaoException {
		String sql = "DELETE FROM \"user\" WHERE \"id\" = ?";
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
