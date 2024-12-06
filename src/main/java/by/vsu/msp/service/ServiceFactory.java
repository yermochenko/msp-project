package by.vsu.msp.service;

public interface ServiceFactory extends AutoCloseable {
	NoteService getNoteServiceInstance() throws ServiceException;

	void close();

	static ServiceFactory newInstance() {
		return new ServiceFactoryImpl();
	}
}
