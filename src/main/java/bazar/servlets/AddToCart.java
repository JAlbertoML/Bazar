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

public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddToCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("session") != null) {
			Product product = ProductBs.getProductById(Integer.parseInt(request.getParameter("idProduct")));
			String page = request.getParameter("page");
			String username = request.getParameter("user");
			Client client = ClientBs.getClientByUsername(username);
			if(client != null) {
				ArrayList<ProductInCart> cart = client.getCart();
				if(cart == null) {
					cart = new ArrayList<ProductInCart>();
				}
				product.setInCart(true);
				ProductInCart productInCart = new ProductInCart(product, 1);
				cart.add(productInCart);
				ClientBs.editCart(cart, username);
				client.setCart(cart);
				httpSession.setAttribute("client", client);
				httpSession.setAttribute("cart", cart);
				response.sendRedirect(page);
			} else {
				response.sendRedirect(page);
			}
		}
		else {
			response.sendRedirect("Index");
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

}
