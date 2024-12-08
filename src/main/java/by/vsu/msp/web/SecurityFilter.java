package by.vsu.msp.web;

import by.vsu.msp.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SecurityFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		if(session != null) {
			User user = (User) session.getAttribute("user");
			if(user != null) {
				chain.doFilter(req, res);
				return;
			}
		}
		res.sendRedirect(req.getContextPath() + "/login.html?err-msg=" + URLEncoder.encode("Для доступа к странице нужна авторизация", StandardCharsets.UTF_8));
	}
}
