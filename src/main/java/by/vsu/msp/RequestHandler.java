package by.vsu.msp;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class RequestHandler extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String[] colors = {"red", "green", "blue", "orange", "magenta", "cyan", "violet"};
		Random random = new Random();
		String color = colors[random.nextInt(colors.length)];
		out.printf("<html><head><meta charset=\"UTF-8\"></head><body><p style=\"font-size: 200%%; background: yellow; border: 2px solid %s; border-radius: 10px; padding:15px;\">Hello, <b>World</b>!!!</p><p>Context deployment working</p></body></html>", color);
	}
}
