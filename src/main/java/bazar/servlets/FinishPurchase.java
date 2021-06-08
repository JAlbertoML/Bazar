package bazar.servlets;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import bazar.bs.ClientBs;
import bazar.bs.OrderBs;
import bazar.bs.ProductBs;
import bazar.bs.SendOrder;
import bazar.pojos.Client;
import bazar.pojos.Order;
import bazar.pojos.ProductInCart;

@MultipartConfig
public class FinishPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FinishPurchase() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		Random random = new Random();
		Part archivo = request.getPart("payPhoto");
		String context = request.getServletContext().getRealPath("images/payments");
		String foto = Paths.get(archivo.getSubmittedFileName()).getFileName().toString(); 
		String name = File.separator + random.nextInt() + foto;
		archivo.write(context + name);
		Client client = (Client) httpSession.getAttribute("client");
		BigDecimal ammount = (BigDecimal)httpSession.getAttribute("subtotal");
		@SuppressWarnings("unchecked")
		ArrayList<ProductInCart> products = (ArrayList<ProductInCart>) httpSession.getAttribute("productsInOrder");
		LocalDate date = LocalDate.now();
		Order order = new Order(date, client.getCart(), ammount.add(new BigDecimal(80)), "images/payments" + name, client.getId());
		if(SendOrder.sendOrderMessage(order, client, products)) {
			httpSession.setAttribute("openmodalcart", "$('#modalCart').modal('show');");
			httpSession.setAttribute("alertOk", "Tu órden se ha enviado con éxito. Se ha limpiado tu carrito.");
			OrderBs.addOrder(order);
			try {
				client.getCart().clear();
			} catch (Exception e) {
				// TODO: handle exception
			}
			ClientBs.editCart(client.getCart(), client.getUser().getUser());
			for (ProductInCart productInCart : products) {
				productInCart.getProduct().setQuantitySold(productInCart.getProduct().getQuantitySold() + productInCart.getQuantity());
				productInCart.getProduct().setQuantity(productInCart.getProduct().getQuantity() - productInCart.getQuantity());
				ProductBs.editQuantities(productInCart.getProduct());
			}
			httpSession.removeAttribute("cart");
			httpSession.setAttribute("sizeCart", 0);
			
		} else {
			httpSession.setAttribute("openmodalcart", "$('#modalCart').modal('show');");
			httpSession.setAttribute("alertOk", "Tu órden no se ha podido completar. Por favor vuelve a intentarlo más tarde. Si el problema persiste, <a href=\"Index#contact\">contáctanos</a>");
		}
		response.sendRedirect("Store");
	}

}
