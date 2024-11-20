package by.vsu.msp.dao;

import by.vsu.msp.domain.Entity;

import java.io.Serializable;
import java.util.Optional;

public interface Dao<T extends Entity<PK>, PK extends Serializable> {
	PK create(T entity) throws DaoException;
	Optional<T> read(PK id) throws DaoException;
	void update(T entity) throws DaoException;
	void delete(PK id) throws DaoException;
}
