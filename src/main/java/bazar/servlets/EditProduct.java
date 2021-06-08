package bazar.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import bazar.bs.ProductBs;
import bazar.pojos.Product;

@MultipartConfig
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("session") != null) {
			Product product = ProductBs.getProductById(Integer.parseInt(request.getParameter("idProduct")));
			httpSession.setAttribute("editableProduct", product);
			httpSession.setAttribute("openmodalForEdit", "$('#EditProduct').modal('show');");
			response.sendRedirect("AdminProducts");
		}
		else {
			response.sendRedirect("Index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("openmodalForEdit");
		httpSession.removeAttribute("editableProduct");
		Random random = new Random();
		String photo = null;
		if(!request.getPart("photoInput").getSubmittedFileName().equals("")) {
			Part archivo = request.getPart("photoInput");
			String context = request.getServletContext().getRealPath("images/products");
			String foto = Paths.get(archivo.getSubmittedFileName()).getFileName().toString(); 
			String name = File.separator + random.nextInt() + foto;
			archivo.write(context + name);
			photo = "images/products" + name;
		}
		Map<String, String[]> parameterMap = request.getParameterMap();
		String message = ProductBs.getAddProductMessage(parameterMap);
		if (message == null) {
			if (ProductBs.editProduct(parameterMap, photo, Integer.parseInt(request.getParameter("idProduct")))) {
				httpSession.removeAttribute("addProductMessage");
				response.sendRedirect("AdminProducts");
			}
		} else {
			httpSession.setAttribute("editProductMessage", message);
			response.sendRedirect("AdminProducts");
		}
	}

}
