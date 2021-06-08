package bazar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.ClientBs;
import bazar.bs.ProductBs;
import bazar.pojos.Client;
import bazar.pojos.Product;
import bazar.pojos.ProductInCart;

public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RemoveFromCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("session") != null) {
			Product product = ProductBs.getProductById(Integer.parseInt(request.getParameter("idProduct")));
			String page = request.getParameter("page");
			String username = request.getParameter("user");
			Client client = ClientBs.getClientByUsername(username);
			ArrayList<ProductInCart> cart = client.getCart();
			for (ProductInCart productInCart : cart) {
				if(productInCart.getProduct().getId() == product.getId()) {
					cart.remove(productInCart);
					break;
				}
			}
			if(cart.size() == 0)
				client.setCart(null);
			else
				client.setCart(cart);
			ClientBs.editCart(cart, username);
			httpSession.setAttribute("client", client);
			httpSession.setAttribute("cart", cart);
			if(page.equals("Store") || page.equals("Index")) {
				httpSession.setAttribute("openmodalcart", "$('#modalCart').modal('show');");
				if(cart.size() > 0) {
					httpSession.setAttribute("cart", cart);
				} else {
					httpSession.setAttribute("sizeCart", 0);
				}
			}
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
