package com.bananagroup.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.bananagroup.models.Proyecto;
import com.bananagroup.models.Usuario;

public final class ProyectoDAOImpl extends ProyectoDAO {
	private static Logger logger = Logger.getLogger("ProyectoDAOImpl");

	private static ProyectoDAOImpl instance = null;

	public static ProyectoDAOImpl getInstance() {
		if (instance == null) {
			instance = new ProyectoDAOImpl();
		}
		return instance;
	}

	@Override
	public Proyecto getProyecto(int pid) {
		Proyecto proyectoADevolver = null;

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT p.* FROM proyecto p WHERE p.pid=? LIMIT 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pid);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				proyectoADevolver = new Proyecto(rs.getInt("pid"), rs.getString("titulo"), rs.getString("descripcion"),
						rs.getDate("fechaI"),
						new Usuario(rs.getInt("uid"), rs.getString("nombre"), rs.getString("email"), null),
						rs.getString("activo"), null);

			}

			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			proyectoADevolver = null;
		}
		return proyectoADevolver;
	}

	@Override
	public boolean delProyecto(int pid) {

		try {
			Connection conn = this.datasource.getConnection();

			String sql = "DELETE t.* FROM tarea t, proyecto p WHERE proyecto_padre=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pid);
			ResultSet rs = pstm.executeQuery();
			// ----------------------
			pstm.close();

			sql = "DELETE p.* FROM proyecto p WHERE pid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pid);
			rs = pstm.executeQuery();
			// ----------------------
			pstm.close();
			// ----------------------
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			return false;
		}
		logger.info("Proyecto y tareas borrado/as");
		return true;
	}

	@Override
	public boolean insertProyecto(Proyecto proyecto) {

		try {
			Connection conn = this.datasource.getConnection();

			String sql = "INSERT INTO proyecto VALUES (?,?,?,?,?);";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "titulo");
			pstm.setString(2, "descripcion");
			pstm.setString(3, "fechaI");
			pstm.setString(4, "responsable");
			pstm.setString(5, "activo");
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
	public boolean updateProyecto(Proyecto proyecto) {

		try {
			Connection conn = this.datasource.getConnection();

			String sql = "UPDATE proyecto p SET p.titulo=?,p.descripcion=?,p.fechaI=?,p.responsable=?,p.activo=? WHERE p.pid=?";// 1
																																// Solo
																																// cambio

			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setString(1, "titulo");
			pstm.setString(2, "descripcion");
			pstm.setString(3, "fechaI");
			pstm.setString(4, "responsable");
			pstm.setString(5, "activo");
			pstm.setString(6, "pid");

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
	public List<Proyecto> getUserProyectos(int uid) {
		List<Proyecto> listADevolver = new ArrayList<Proyecto>();

		try {

			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql = "SELECT p.*,u.* FROM proyecto p, usuario u WHERE u.uid=? AND  p.responsable=u.uid";
			
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, uid);
			logger.info("**** Query:"+pstm.toString()+":"+uid);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(new Proyecto(rs.getInt("pid"), rs.getString("titulo"), rs.getString("descripcion"),
						rs.getDate("fechaI"),
						new Usuario(rs.getInt("uid"), rs.getString("nombre"), rs.getString("email"), null),
						rs.getString("activo"), null));
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