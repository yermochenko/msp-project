package by.vsu.msp.web;

import by.vsu.msp.dao.pgsql.DatabaseConnector;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ApplicationStartHandler implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext context = event.getServletContext();
			String jdbcDriver   = context.getInitParameter("jdbc-driver");
			String jdbcUrl      = context.getInitParameter("jdbc-url");
			String jdbcUser     = context.getInitParameter("jdbc-user");
			String jdbcPassword = context.getInitParameter("jdbc-password");
			DatabaseConnector.init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
		} catch(ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
