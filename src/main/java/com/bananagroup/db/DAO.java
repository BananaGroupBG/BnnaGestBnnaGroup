package com.bananagroup.db;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	private static Logger logger = Logger.getLogger("DAO");

	public DataSource datasource;

	private static DAO instance = null;

	public static DAO getInstance() {
		if (instance == null) {
			instance = new DAO();
		}
		return instance;
	}

	protected DAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			this.datasource = (DataSource) envContext.lookup("jdbc/bananadb");
		} catch (Exception e) {
			logger.info("Error al instanciar el Datasource!!!!");
			e.printStackTrace();
		}
	}
}
