package bazar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.ProductBs;
import bazar.pojos.Client;
import bazar.pojos.Person;
import bazar.pojos.Product;
import bazar.pojos.ProductInCart;

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Index() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		HttpSession httpSession = request.getSession();
		ArrayList<ProductInCart> cart = new ArrayList<ProductInCart>();
		Random random = new Random();
		Person client = (Person) httpSession.getAttribute("client");
		Boolean isThereASession = false;	// Si el atributo de sesión existe, se manda la señal al frontend
		ArrayList<Integer> randomNums = new ArrayList<Integer>();
		Integer rnd = null;
		if (httpSession.getAttribute("session") != null) {
			isThereASession = true;
		}
		if (httpSession.getAttribute("loginMessage") != null) {
			request.setAttribute("loginMessage", httpSession.getAttribute("loginMessage"));
			request.setAttribute("openmodal", "$('#Login').modal('show');");
		}
		if (httpSession.getAttribute("registerMessage") != null) {
			request.setAttribute("message", httpSession.getAttribute("registerMessage"));
			request.setAttribute("openmodal", httpSession.getAttribute("openmodal"));
			request.setAttribute("person", httpSession.getAttribute("person"));
		}
		if(httpSession.getAttribute("editMessage") != null) {
			request.setAttribute("message", httpSession.getAttribute("editMessage"));
			request.setAttribute("openmodal", httpSession.getAttribute("openmodal"));
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
		ArrayList<Product> arrayIndexProducts = new ArrayList<Product>();
		Integer num;
		if(arrayProducts.size() >= 3)
			num = 3;
		else
			num = arrayProducts.size();
		for(int i = 0; i < num; i++) {
			do {
				rnd = random.nextInt(arrayProducts.size());
			} while(randomNums.contains(rnd));
			randomNums.add(rnd);
			if(arrayProducts.get(rnd).getQuantity() > 0) {
				arrayIndexProducts.add(arrayProducts.get(rnd));
			}
		}
		randomNums.clear();
		httpSession.removeAttribute("editMessage");
		httpSession.removeAttribute("openmodal");
		request.setAttribute("arrayIndexProducts", arrayIndexProducts);
		request.setAttribute("isThereASession", isThereASession);
		request.setAttribute("cartSize", httpSession.getAttribute("cartSize")); 
		request.setAttribute("client", client); // Enviar datos del usuario al frontend
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Index");
	}
}