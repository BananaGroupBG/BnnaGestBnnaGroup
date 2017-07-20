package com.bananagroup.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

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

				tareaADevolver = new Tarea(rs.getInt("tid"), rs.getString("descripcion"), null, null);
				pstm.close();
				conn.close();

				logger.info("Conexión exitosa");
			}
		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			tareaADevolver = null;
		}

		return tareaADevolver;
	}

	@Override
	public boolean delTarea(int tid) {

		try {
			Connection conn = this.datasource.getConnection();

			String sql = "DELETE t.* FROM tarea t WHERE tid=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, tid);
			ResultSet rs = pstm.executeQuery();
			// ----------------------
			pstm.close();
			// ----------------------
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			return false;
		}
		logger.info("Tarea borrada");
		return true;
	}

	@Override
	public boolean insertTarea(Tarea tarea) {

		try {
			Connection conn = this.datasource.getConnection();

			String sql = "INSERT INTO tarea VALUES (?,?,?);";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "descripcion");
			pstm.setString(2, "responsable");
			pstm.setString(3, "proyecto_padre");
			ResultSet rs = pstm.executeQuery();
			// ----------------------
			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateTarea(Tarea tarea) {

		try {
			Connection conn = this.datasource.getConnection();

			String sql = "UPDATE tarea t SET t.descripcion=?,t.responsable=? WHERE t.tid=?";// 1
																							// Solo
																							// cambio

			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setString(1, "descripcion");
			pstm.setString(2, "responsable");
			pstm.setString(3, "tid");

			ResultSet rs = pstm.executeQuery();
			// ----------------------
			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			return false;
		}
		return true;
	}

	@Override
	public List<Tarea> getUserTareas(int uid) {
		List<Tarea> listADevolver = new ArrayList<Tarea>();

		try {
			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql = "SELECT t.*,u.* FROM tarea t, usuario u WHERE u.uid=? AND t.responsable=u.uid";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(new Tarea(rs.getInt("tid"), rs.getString("descripcion"),
						new Usuario(rs.getInt("uid"), rs.getString("nombre"), rs.getString("email"), null), null));

			}

			pstm.close();

			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			e.printStackTrace();
			logger.severe("Error en la conexión de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}

}
