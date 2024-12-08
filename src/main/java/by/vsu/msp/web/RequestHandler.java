package by.vsu.msp.web;

import by.vsu.msp.domain.User;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RequestHandler extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		if(session != null) {
			User user = (User) session.getAttribute("user");
			if(user != null) {
				resp.sendRedirect(req.getContextPath() + "/note/list.html");
				return;
			}
		}
		resp.sendRedirect(req.getContextPath() + "/about.html");
	}
}
