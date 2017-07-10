package com.bananagroup.controller;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bananagroup.db.DAOFactory;
import com.bananagroup.db.UsuarioDAO;
import com.bananagroup.models.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("LoginServlet");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession misession = (HttpSession) request.getSession();

		if (misession.getAttribute("usuario") != null) {
			request.getRequestDispatcher("/listaproyectos").forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String email = request.getParameter("email");
		String contrasena = request.getParameter("password");

		UsuarioDAO userDAO = (UsuarioDAO) DAOFactory.getInstance().getDAO("usuario");

		Usuario elUsuario = userDAO.getUsuario(email, contrasena);

		if (elUsuario != null) {
			HttpSession misession = (HttpSession) request.getSession();
			misession.setAttribute("usuario", elUsuario);

			request.getRequestDispatcher("/listaproyectos").forward(request, response);
		} else {
			request.setAttribute("mierror", "Email y contraseña erroneos");
			doGet(request, response);
		}

	}

}
