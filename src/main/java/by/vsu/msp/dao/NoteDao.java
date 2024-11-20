package by.vsu.msp.dao;

import by.vsu.msp.domain.Note;

import java.util.List;

public interface NoteDao extends Dao<Note, Integer> {
	List<Note> readAll() throws DaoException;
}
