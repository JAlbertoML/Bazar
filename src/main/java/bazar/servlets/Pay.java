package bazar.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.ClientBs;
import bazar.bs.ProductBs;
import bazar.pojos.Client;
import bazar.pojos.ProductInCart;

public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Pay() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		Enumeration<String> parameterNames = request.getParameterNames();
		ArrayList<ProductInCart> productsInOrder = new ArrayList<ProductInCart>();
		BigDecimal subtotal = new BigDecimal(0);
		String[] parts;
		String quantityMessage = "";
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			parts = name.split("-");
			productsInOrder.add(new ProductInCart(ProductBs.getProductById(Integer.parseInt(parts[1])),
					Integer.parseInt(request.getParameter(name))));
			if (new ProductInCart(ProductBs.getProductById(Integer.parseInt(parts[1])),
					Integer.parseInt(request.getParameter(name))).getProduct()
							.getQuantity() < Integer.parseInt(request.getParameter(name))) {
				quantityMessage = "No puedes comprar más de "
						+ new ProductInCart(ProductBs.getProductById(Integer.parseInt(parts[1])),
								Integer.parseInt(request.getParameter(name))).getProduct().getQuantity()
						+ " unidades del producto \""
						+ new ProductInCart(ProductBs.getProductById(Integer.parseInt(parts[1])),
								Integer.parseInt(request.getParameter(name))).getProduct().getName()
						+ "\" debido a que no tenemos suficientes. Intentalo de nuevo";
			}
		}
		if (quantityMessage.equals("")) {
			Client client = (Client) httpSession.getAttribute("client");
			ClientBs.editCart(productsInOrder, client.getUser().getUser());
			for (ProductInCart productInCart : productsInOrder) {
				if (productInCart.getProduct().getDiscount().compareTo(new BigDecimal(0.00)) == 0) {
					subtotal = subtotal
							.add(productInCart.getProduct().getPrice().multiply(new BigDecimal(productInCart.getQuantity())));
				} else {
					subtotal = subtotal
							.add(productInCart.getProduct().getDiscount().multiply(new BigDecimal(productInCart.getQuantity())));
				}
			}
			httpSession.setAttribute("openmodalpay", "$('#modalPay').modal('show');");
			httpSession.setAttribute("subtotal", subtotal);
			httpSession.setAttribute("productsInOrder", productsInOrder);
		} else {
			httpSession.setAttribute("openmodalcart", "$('#modalCart').modal('show');");
			httpSession.setAttribute("quantityMessage", quantityMessage);
		}
		response.sendRedirect("Store");
	}
}
