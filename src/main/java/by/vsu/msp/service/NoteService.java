package by.vsu.msp.service;

import by.vsu.msp.domain.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
	List<Note> findAll() throws ServiceException;
	Optional<Note> findById(Integer id) throws ServiceException;
	void save(Note note) throws ServiceException;
	Optional<Note> delete(Integer id) throws ServiceException;
}
