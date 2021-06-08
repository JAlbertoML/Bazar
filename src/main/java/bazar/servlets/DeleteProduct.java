package bazar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.ProductBs;

public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("session") != null) {
			ProductBs.deleteProduct(Integer.parseInt(request.getParameter("idProduct")));
			response.sendRedirect("AdminProducts");
		}
		else {
			response.sendRedirect("Index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

}
