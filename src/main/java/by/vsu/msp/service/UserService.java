package by.vsu.msp.service;

import by.vsu.msp.domain.User;

import java.util.Optional;

public interface UserService {
	Optional<User> findByLoginAndPassword(String login, String password) throws ServiceException;
}
