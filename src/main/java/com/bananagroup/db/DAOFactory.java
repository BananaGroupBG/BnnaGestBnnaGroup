package com.bananagroup.db;

import java.util.logging.Logger;

import javax.sql.DataSource;

public class DAOFactory {
	private static Logger logger = Logger.getLogger("DAOFactory");

	public DataSource datasource;
	private static DAOFactory instance = null;

	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	public static Object getDAO(String daoType) {
		switch (daoType) {
		case "usuario":
			return (Object) UsuarioDAOImpl.getInstance();
		case "proyecto":
			return (Object) ProyectoDAOImpl.getInstance();
		case "tarea":
			return (Object) TareaDAOImpl.getInstance();
		default:
			return null;
		}
	}
}
