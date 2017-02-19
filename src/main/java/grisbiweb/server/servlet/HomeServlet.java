package grisbiweb.server.servlet;

import grisbiweb.server.utils.FileUtils;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		File file = FileUtils.findGrisbiAccountFile();

		// url like http://host:port/
		String baseUrl = request.getScheme();
		baseUrl += "://";
		baseUrl += request.getServerName();
		baseUrl += ":";
		baseUrl += request.getServerPort();

		// url like http://host:port/grisbi-server-java
		String applicationUrl = baseUrl + request.getContextPath();

		String restUrl = applicationUrl + "/rest/";
		String docUrl = applicationUrl + "/rest/swagger.json";

		request.setAttribute("file", file.getAbsolutePath());
		request.setAttribute("restUrl", restUrl);
		request.setAttribute("docUrl", docUrl);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
	}
}
