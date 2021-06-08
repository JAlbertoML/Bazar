package bazar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.AdminBs;
import bazar.bs.ClientBs;
import bazar.bs.UserBs;
import bazar.pojos.Admin;
import bazar.pojos.Client;
import bazar.pojos.Person;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		Person person = null;
		// Obetenemos el username y la contraseña del formulario HTML de inicio de sesión
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// Obtenermos objeto client de la base de datos. Si es nulo, algo está mal o podría ser un administrador, si no es nulo, verifica credenciales
		Client client = ClientBs.getClientByUsername(username);
		if(client == null) {
			Admin admin = AdminBs.getAdminByUsername(username);
			if(admin == null) {
				System.out.println("No hay usuario");
				// Si no hay usuario o no coninciden las credenciales, se manda mensaje al frontend
				httpSession.setAttribute("loginMessage", "El usuario o la contraseña son incorrectos. Intentalo de nuevo.");
			}
			else {
				if(UserBs.areCredentialsRight(admin.getUser(), password))
					person = admin;
				else 
					httpSession.setAttribute("loginMessage", "El usuario o la contraseña son incorrectos. Intentalo de nuevo.");
			}
		} else {
			if(UserBs.areCredentialsRight(client.getUser(), password))
				person = client;
			else 
				httpSession.setAttribute("loginMessage", "El usuario o la contraseña son incorrectos. Intentalo de nuevo.");
		}
		if(person != null) {
			httpSession.setAttribute("session", 1); // Indicador de inicio de sesión
			httpSession.setAttribute("client", person);
			httpSession.removeAttribute("loginMessage");
			httpSession.removeAttribute("registerMessage");
			httpSession.removeAttribute("openmodal");
			httpSession.removeAttribute("person");
			request.removeAttribute("loginMessage");
			request.removeAttribute("openmodal");
			request.removeAttribute("message");
			request.removeAttribute("person");
		}
		// Se redirige al index
		response.sendRedirect("Index");
	}
}
