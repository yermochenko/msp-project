package by.vsu.msp.service;

import by.vsu.msp.dao.NoteDao;
import by.vsu.msp.dao.pgsql.DatabaseConnector;
import by.vsu.msp.dao.pgsql.NoteDaoImpl;

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

	private NoteDao noteDao;
	private NoteDao getNoteDaoInstance() throws ServiceException {
		if(noteDao == null) {
			NoteDaoImpl noteDao = new NoteDaoImpl();
			this.noteDao = noteDao;
			noteDao.setConnection(getConnectionInstance());
		}
		return noteDao;
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
