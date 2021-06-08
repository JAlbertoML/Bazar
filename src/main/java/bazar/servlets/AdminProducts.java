package bazar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.ProductBs;
import bazar.pojos.Admin;
import bazar.pojos.Product;
import bazar.pojos.User;

public class AdminProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminProducts() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminProducts.jsp");
		HttpSession httpSession = request.getSession();
		Boolean isThereASession = false;
		Admin admin = (Admin) httpSession.getAttribute("client");
		request.removeAttribute("openmodal");
		// Si el atributo de sesión existe, se manda la señal al frontend
		if (httpSession.getAttribute("session") != null && admin.getUser().getType() == User.TYPE_ADMIN) {
			isThereASession = true;
			ArrayList<Product> arrayProducts = ProductBs.getAllProducts();
			request.setAttribute("arrayProducts", arrayProducts);
			request.setAttribute("client", admin);
			request.setAttribute("isThereASession", isThereASession);
			if(httpSession.getAttribute("addProductMessage") != null) {
				request.setAttribute("message", httpSession.getAttribute("addProductMessage"));
			}
			if(httpSession.getAttribute("editProductMessage") != null) {
				request.setAttribute("editMessage", httpSession.getAttribute("editProductMessage"));
			}
			httpSession.removeAttribute("editProductMessage");
			request.setAttribute("editableProduct", httpSession.getAttribute("editableProduct"));
			request.setAttribute("openmodal", httpSession.getAttribute("openmodalForEdit"));
			requestDispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("Index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}
}