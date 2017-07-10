package com.bananagroup.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.bananagroup.models.Usuario;
import com.bananagroup.db.UsuarioDAOImpl;

public final class UsuarioDAOImpl extends UsuarioDAO {
	private static Logger logger = Logger.getLogger("UsuarioDAOImpl");

	private static UsuarioDAOImpl instance = null;

	public static UsuarioDAOImpl getInstance() {
		if (instance == null) {
			instance = new UsuarioDAOImpl();
		}
		return instance;
	}

	@Override
	public Usuario getUsuario(String email, String pass) {
		Usuario usuarioADevolver = null;

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT u.* FROM usuario u WHERE u.email=? AND u.password=? LIMIT 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			pstm.setString(2, pass);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				usuarioADevolver = new Usuario(rs.getInt("uid"), rs.getString("nombre"), rs.getString("email"),
						rs.getString("password"));
			}

			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			usuarioADevolver = null;
		}

		return usuarioADevolver;
	}

	@Override
	public Usuario getUsuario(int uid) {
		Usuario usuarioADevolver = null;

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT u.* FROM usuario u WHERE u.uid=?LIMIT 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				usuarioADevolver = new Usuario(rs.getInt("uid"), rs.getString("nombre"), rs.getString("email"),
						rs.getString("password"));
			}

			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			usuarioADevolver = null;
		}

		return usuarioADevolver;
	}

	@Override
	public boolean delUsuario(int uid) {
		return false;
	}

	@Override
	public boolean insertUsuario(Usuario user) {
		return false;
	}

	@Override
	public boolean updateUsuario(Usuario user) {
		return false;
	}

}

