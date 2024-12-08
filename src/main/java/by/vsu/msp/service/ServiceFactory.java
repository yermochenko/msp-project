package by.vsu.msp.service;

public interface ServiceFactory extends AutoCloseable {
	NoteService getNoteServiceInstance() throws ServiceException;
	UserService getUserServiceInstance() throws ServiceException;

	void close();

	static ServiceFactory newInstance() {
		return new ServiceFactoryImpl();
	}
}
