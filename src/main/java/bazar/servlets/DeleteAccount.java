package bazar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.ClientBs;
import bazar.pojos.Client;

public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteAccount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("session") != null) {
			Client client = (Client) httpSession.getAttribute("client");
			System.out.println(client.getId());
			ClientBs.deleteClient(client);
			response.sendRedirect("Logout");
		}
		else {
			response.sendRedirect("Index");
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

}
