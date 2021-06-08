package bazar.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.pojos.Client;

public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Profile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("session") != null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
			Client client = (Client) httpSession.getAttribute("client");
			if (client != null) 
				request.setAttribute("client", client);
			if(httpSession.getAttribute("registerMessage") != null) {
				request.setAttribute("message", httpSession.getAttribute("registerMessage"));
				request.setAttribute("openmodal", httpSession.getAttribute("openmodal"));
			}
			if(client.getPhoto().equals("images/logo.png"))
				request.setAttribute("disableRemoveButton", "disabled");
			else
				request.removeAttribute("disableRemoveButton");
			httpSession.removeAttribute("registerMessage");
			httpSession.removeAttribute("openmodal");
			requestDispatcher.forward(request, response);
		} else {
			response.sendRedirect("Index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Index");
	}
}