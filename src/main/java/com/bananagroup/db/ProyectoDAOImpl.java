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
						rs.getDate("fechaI"), null, rs.getBoolean("activo"),
						new Tarea(rs.getInt("tid"), rs.getString("descripcion"), new Usuario(rs.getInt("uid"),
								rs.getString("nombre"), rs.getString("email"), rs.getString("password"))));
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
		return false;
	}

	@Override
	public boolean insertProyecto(Proyecto proyecto) {
		return false;
	}

	@Override
	public boolean updateProyecto(Proyecto proyecto) {
		return false;
	}

	@Override
	public List<Proyecto> getUserProyectos(int uid) {
		List<Proyecto> listADevolver = new ArrayList<Proyecto>();

		try {
			logger.info("datasource:"+this.datasource);
			
			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql ="SELECT p.* FROM proyecto p, usuario u WHERE p.responsable=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(
						new Proyecto(rs.getInt("pid"), rs.getString("titulo"), rs.getString("descripcion"), rs.getDate("fechaI"), null, rs.getBoolean("activo"), null)
						/*new Proyecto(rs.getInt("pid"), rs.getString("titulo"), rs.getString("descripcion"),
						rs.getDate("fecha_inicio"), null, rs.getboolean("activo"),
						new Tarea(rs.getInt("tid"), rs.getString("descripcion"), 
								new Usuario(rs.getInt("uid"),rs.getString("nombre"), rs.getString("email"), rs.getString("password")
							)
						)
					)*/
			);
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

	@Override
	public List<Proyecto> getProyectosOptions() {
		// TODO Auto-generated method stub
		return null;
	}
}