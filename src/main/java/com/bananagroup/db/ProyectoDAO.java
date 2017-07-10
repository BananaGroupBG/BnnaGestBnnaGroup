package com.bananagroup.db;

import java.util.List;

import com.bananagroup.models.Proyecto;

public abstract class ProyectoDAO extends DAO {
	public abstract Proyecto getProyecto(int pid);

	public abstract boolean delProyecto(int pid);

	public abstract boolean insertProyecto(Proyecto proyecto);

	public abstract boolean updateProyecto(Proyecto proyecto);

	public abstract List<Proyecto> getUserProyectos(int uid);

	public abstract List<Proyecto> getProyectosOptions();
}
