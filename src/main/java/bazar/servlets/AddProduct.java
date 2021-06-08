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

@MultipartConfig
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		Random random = new Random();
		Part archivo = request.getPart("photoInput");
		String context = request.getServletContext().getRealPath("images/products");
		String foto = Paths.get(archivo.getSubmittedFileName()).getFileName().toString(); 
		String name = File.separator + random.nextInt() + foto;
		archivo.write(context + name);
		System.out.println(context + name);
		Map<String, String[]> parameterMap = request.getParameterMap();
		String message = ProductBs.getAddProductMessage(parameterMap);
		if (message == null) {
			if (ProductBs.addProduct(parameterMap, "images/products" + name)) {
				httpSession.removeAttribute("addProductMessage");
				httpSession.removeAttribute("openmodal");
				response.sendRedirect("AdminProducts");
			}
		} else {
			httpSession.setAttribute("addProductMessage", message);
			httpSession.setAttribute("openmodal", "$('#AddProduct').modal('show');");
			response.sendRedirect("AdminProducts");
		}
	}
}