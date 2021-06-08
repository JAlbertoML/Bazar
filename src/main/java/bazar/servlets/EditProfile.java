package bazar.servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.ClientBs;
import bazar.bs.EditProfileBs;
import bazar.pojos.Client;

public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		Client client = (Client) httpSession.getAttribute("client");
		Map<String, String[]> parameterMap = request.getParameterMap();
		String message = EditProfileBs.getEditMessage(parameterMap, client);
		if (message == null) {
			if (ClientBs.editClient(parameterMap, client)) {
				httpSession.setAttribute("client", ClientBs.getClientByUsername(client.getUser().getUser()));
				response.sendRedirect("Profile");
			}
		} else {
			httpSession.setAttribute("registerMessage", message);
			httpSession.setAttribute("openmodal", "$('#EditProfile').modal('show');");
			response.sendRedirect("Profile");
		}
	}

}
