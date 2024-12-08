package by.vsu.msp.controller;

import by.vsu.msp.domain.User;
import by.vsu.msp.service.ServiceException;
import by.vsu.msp.service.ServiceFactory;
import by.vsu.msp.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		if(login != null && password != null) {
			try(ServiceFactory serviceFactory = ServiceFactory.newInstance()) {
				UserService userService = serviceFactory.getUserServiceInstance();
				Optional<User> user = userService.findByLoginAndPassword(login, password);
				if(user.isPresent()) {
					HttpSession session = req.getSession();
					session.setAttribute("user", user.get());
					resp.sendRedirect(req.getContextPath());
				} else {
					resp.sendRedirect(req.getContextPath() + "/login.html?err-msg=" + URLEncoder.encode("Имя пользователя или пароль не опознаны", StandardCharsets.UTF_8));
				}
			} catch(ServiceException e) {
				throw new ServletException(e);
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
