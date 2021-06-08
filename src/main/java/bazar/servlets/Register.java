package bazar.servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bazar.bs.ClientBs;
import bazar.bs.RegisterBs;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		Map<String, String[]> parameterMap = request.getParameterMap();
		String message = RegisterBs.getRegisterMessage(parameterMap);
		if (message == null) {
			if (ClientBs.addClient(parameterMap)) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				message = "Te has registrado con exito. Inicia sesión";
				request.setAttribute("loginMessage", message);
				request.setAttribute("openmodal", "$('#Login').modal('show');");
				requestDispatcher.forward(request, response);
			}
		} else {
			httpSession.setAttribute("registerMessage", message);
			httpSession.setAttribute("openmodal", "$('#Register').modal('show');");
			httpSession.setAttribute("person", RegisterBs.getTmpPerson(parameterMap));
			response.sendRedirect("Index");
		}
	}
}
