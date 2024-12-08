package by.vsu.msp.dao;

import by.vsu.msp.domain.User;

import java.util.Optional;

public interface UserDao extends Dao<User, Integer> {
	Optional<User> readByLoginAndPassword(String login, String password) throws DaoException;
}
