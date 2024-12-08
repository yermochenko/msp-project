package by.vsu.msp.service;

import by.vsu.msp.dao.NoteDao;
import by.vsu.msp.dao.UserDao;
import by.vsu.msp.dao.pgsql.DatabaseConnector;
import by.vsu.msp.dao.pgsql.NoteDaoImpl;
import by.vsu.msp.dao.pgsql.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceFactoryImpl implements ServiceFactory {
	private NoteService noteService;
	@Override
	public NoteService getNoteServiceInstance() throws ServiceException {
		if(noteService == null) {
			NoteServiceImpl noteService = new NoteServiceImpl();
			this.noteService = noteService;
			noteService.setNoteDao(getNoteDaoInstance());
		}
		return noteService;
	}

	private UserService userService;
	@Override
	public UserService getUserServiceInstance() throws ServiceException {
		if(userService == null) {
			UserServiceImpl userService = new UserServiceImpl();
			this.userService = userService;
			userService.setUserDao(getUserDaoInstance());
		}
		return userService;
	}

	private NoteDao noteDao;
	private NoteDao getNoteDaoInstance() throws ServiceException {
		if(noteDao == null) {
			NoteDaoImpl noteDao = new NoteDaoImpl();
			this.noteDao = noteDao;
			noteDao.setConnection(getConnectionInstance());
		}
		return noteDao;
	}

	private UserDao userDao;
	private UserDao getUserDaoInstance() throws ServiceException {
		if(userDao == null) {
			UserDaoImpl userDao = new UserDaoImpl();
			this.userDao = userDao;
			userDao.setConnection(getConnectionInstance());
		}
		return userDao;
	}

	private Connection connection;
	private Connection getConnectionInstance() throws ServiceException {
		if(connection == null) {
			try {
				connection = DatabaseConnector.getConnection();
			} catch(SQLException e) {
				throw new ServiceException(e);
			}
		}
		return connection;
	}

	@Override
	public void close() {
		if(connection != null) {
			try {
				connection.close();
			} catch(SQLException ignored) {}
		}
	}
}
