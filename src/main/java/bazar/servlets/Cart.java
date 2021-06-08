package bazar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.ClientBs;
import bazar.pojos.Client;

public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Cart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("session") != null) {
			String page = request.getParameter("page");
			String username = request.getParameter("user");
			Client client = ClientBs.getClientByUsername(username);
			if(client.getCart().size() > 0) {
				httpSession.setAttribute("sizeCart", client.getCart().size());
				httpSession.setAttribute("cart", client.getCart());
			} else {
				httpSession.setAttribute("sizeCart", 0);
			}
			httpSession.setAttribute("openmodalcart", "$('#modalCart').modal('show');");
			response.sendRedirect(page);
		}
		else {
			response.sendRedirect("Index");
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

}
