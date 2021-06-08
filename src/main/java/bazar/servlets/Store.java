package bazar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bazar.bs.AdminBs;
import bazar.bs.ProductBs;
import bazar.pojos.Admin;
import bazar.pojos.Client;
import bazar.pojos.Person;
import bazar.pojos.Product;
import bazar.pojos.ProductInCart;

public class Store extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Store() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("store.jsp");
		Boolean isThereASession = false;
		ArrayList<ProductInCart> cart = new ArrayList<ProductInCart>();
		Person client = (Person) httpSession.getAttribute("client");
		Admin admin = AdminBs.getAdminByUsername("admin");
		try {
			if(client.getName().equals(admin.getName()))
				client = (Admin) httpSession.getAttribute("client");
			else
				client = (Client) httpSession.getAttribute("client");
		} catch (Exception e) {
			// TODO: handle exception
		}
		// Si el atributo de sesión existe, se manda la señal al frontend
		if (httpSession.getAttribute("session") != null) {
			isThereASession = true;
		}
		ArrayList<Product> arrayProducts = ProductBs.getAllProducts();
		try {
			cart = ((Client) client).getCart();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(cart != null) {
			for (Product product : arrayProducts) {
				for (ProductInCart productInCart : cart) {
					if(product.getId() == productInCart.getProduct().getId()) {
						product.setInCart(true);
						break;
					}
				}
			}
		}
		request.setAttribute("isThereASession", isThereASession);
		request.setAttribute("client", client);
		request.setAttribute("arrayProducts", arrayProducts);
		request.setAttribute("sizeCart", httpSession.getAttribute("sizeCart"));
		request.setAttribute("cart", httpSession.getAttribute("cart"));
		request.setAttribute("openmodalcart", httpSession.getAttribute("openmodalcart"));
		request.setAttribute("subtotal", httpSession.getAttribute("subtotal"));
		request.setAttribute("openmodalpay", httpSession.getAttribute("openmodalpay"));
		request.setAttribute("productsInOrder", httpSession.getAttribute("productsInOrder"));
		request.setAttribute("alertOk", httpSession.getAttribute("alertOk"));
		request.setAttribute("quantityMessage", httpSession.getAttribute("quantityMessage"));
		httpSession.removeAttribute("openmodalcart");
		httpSession.removeAttribute("openmodalpay");
		httpSession.removeAttribute("alertOk");
		httpSession.removeAttribute("quantityMessage");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

}
