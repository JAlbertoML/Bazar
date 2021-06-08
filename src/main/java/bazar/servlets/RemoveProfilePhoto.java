package bazar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.ClientBs;
import bazar.pojos.Client;

public class RemoveProfilePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RemoveProfilePhoto() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("session") != null) {
			Client client = ClientBs.getClientByUsername(request.getParameter("username"));
			client.setPhoto("images/logo.png");
			ClientBs.editPhoto(client);
			httpSession.setAttribute("client", client);
			response.sendRedirect("Profile");
		}
		else {
			response.sendRedirect("Index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

}
