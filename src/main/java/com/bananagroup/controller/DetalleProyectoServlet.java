package com.bananagroup.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bananagroup.db.DAOFactory;
import com.bananagroup.db.TareaDAO;
import com.bananagroup.models.Tarea;
import com.bananagroup.models.Usuario;

@WebServlet("/detalle-proyecto")
public class DetalleProyectoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("DetalleProyectoServlet");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Entramos en sesion:
		HttpSession misession = (HttpSession) request.getSession();

		if (misession.getAttribute("usuario") != null) {// "idUsuario"
			Usuario elUsuario = (Usuario) misession.getAttribute("usuario");
			TareaDAO tDAO = (TareaDAO) DAOFactory.getInstance().getDAO("tarea");

			List<Tarea> listaTareaPid = tDAO.getUserTareas(elUsuario.getUid());
			request.setAttribute("listaTareasAMostrar", listaTareaPid);

			request.getRequestDispatcher("detalleproyecto.jsp").forward(request, response);

		} else {
			misession.invalidate();
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
