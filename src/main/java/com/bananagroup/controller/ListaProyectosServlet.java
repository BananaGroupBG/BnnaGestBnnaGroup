package com.bananagroup.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bananagroup.db.ProyectoDAO;
import com.bananagroup.models.Proyecto;
import com.bananagroup.models.Usuario;
import com.bananagroup.db.DAOFactory;
import com.bananagroup.db.ProyectoDAO;

@WebServlet("/listaproyectos")
public class ListaProyectosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("ListaProyectosServlet");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Entramos en sesion:
		HttpSession misession = (HttpSession) request.getSession();

		if (misession.getAttribute("usuario") != null) {
			Usuario elUsuario = (Usuario) misession.getAttribute("usuario");
			ProyectoDAO pDAO = (ProyectoDAO) DAOFactory.getInstance().getDAO("proyecto");

			List<Proyecto> listaProyectosUid = pDAO.getUserProyectos(elUsuario.getUid());
			logger.info("listaProyectosUid:"+listaProyectosUid.size());
			
			request.setAttribute("listaProyectosAMostrar", listaProyectosUid);

			request.getRequestDispatcher("listaproyectos.jsp").forward(request, response);

		} else {
			misession.invalidate();
			response.sendRedirect("login");

		} // END "idUsuario"

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
