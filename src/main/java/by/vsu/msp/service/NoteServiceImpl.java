package by.vsu.msp.service;

import by.vsu.msp.dao.DaoException;
import by.vsu.msp.dao.NoteDao;
import by.vsu.msp.domain.Note;

import java.util.List;
import java.util.Optional;

public class NoteServiceImpl implements NoteService {
	private NoteDao noteDao;

	public void setNoteDao(NoteDao noteDao) {
		this.noteDao = noteDao;
	}

	@Override
	public List<Note> findAll() throws ServiceException {
		try {
			return noteDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<Note> findById(Integer id) throws ServiceException {
		try {
			return noteDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Note note) throws ServiceException {
		try {
			if(note.getId() == null) {
				noteDao.create(note);
			} else {
				noteDao.update(note);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<Note> delete(Integer id) throws ServiceException {
		try {
			Optional<Note> note = noteDao.read(id);
			if(note.isPresent()) {
				noteDao.delete(id);
			}
			return note;
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
