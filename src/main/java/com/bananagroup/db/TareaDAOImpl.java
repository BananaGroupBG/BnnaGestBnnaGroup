package com.bananagroup.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.bananagroup.models.Proyecto;
import com.bananagroup.models.Tarea;
import com.bananagroup.models.Usuario;

public final class TareaDAOImpl extends TareaDAO {
	private static Logger logger = Logger.getLogger("TareaDAOImpl");

	public DataSource datasource;
	private static TareaDAOImpl instance = null;

	public static TareaDAOImpl getInstance() {
		if (instance == null) {
			instance = new TareaDAOImpl();
		}
		return instance;
	}

	@Override
	public Tarea getTarea(int tid) {
		Tarea tareaADevolver = null;

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT t.* FROM tarea t WHERE t.tid=? LIMIT 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, tid);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				tareaADevolver = new Tarea(rs.getInt("tid"), rs.getString("descripcion"), new Usuario(rs.getInt("uid"),
						rs.getString("nombre"), rs.getString("email"), rs.getString("password")));
			}
			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			tareaADevolver = null;
		}

		return tareaADevolver;
	}

	@Override
	public boolean delTarea(int tid) {
		return false;
	}

	@Override
	public boolean insertTarea(Tarea tarea) {
		return false;
	}

	@Override
	public boolean updateTarea(Tarea tarea) {
		return false;
	}

	@Override
	public List<Tarea> getUserTareas(int pid) {
		List<Tarea> listADevolver = new ArrayList<Tarea>();

		try {
			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql = "SELECT t.*,u.* FROM tarea t, usuario u WHERE t.proyecto_padre=? AND u.uid=t.responsable_tarea";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pid);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(new Tarea(rs.getInt("tid"), rs.getString("descripcion"), new Usuario(rs.getInt("uid"),
						rs.getString("nombre"), rs.getString("email"), rs.getString("password"))));

			}

			pstm.close();

			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}

}
