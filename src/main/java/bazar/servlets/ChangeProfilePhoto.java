package bazar.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import bazar.bs.ClientBs;
import bazar.pojos.Client;

@MultipartConfig
public class ChangeProfilePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangeProfilePhoto() {
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
		String context = request.getServletContext().getRealPath("images/profile");
		String foto = Paths.get(archivo.getSubmittedFileName()).getFileName().toString(); 
		String name = File.separator + random.nextInt() + foto;
		archivo.write(context + name);
		Client client = (Client) httpSession.getAttribute("client");
		client.setPhoto("images/profile" + name);
		ClientBs.editPhoto(client);
		httpSession.setAttribute("client", ClientBs.getClientByUsername(client.getUser().getUser()));
		response.sendRedirect("Profile");
	}
}
