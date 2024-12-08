package by.vsu.msp.service;

import by.vsu.msp.dao.DaoException;
import by.vsu.msp.dao.UserDao;
import by.vsu.msp.domain.User;

import java.util.Optional;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Optional<User> findByLoginAndPassword(String login, String password) throws ServiceException {
		try {
			return userDao.readByLoginAndPassword(login, password);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
