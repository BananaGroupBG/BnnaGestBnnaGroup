package com.bananagroup.db;

import java.util.List;

import com.bananagroup.models.Tarea;

public abstract class TareaDAO extends DAO {
	public abstract Tarea getTarea(int tid);

	public abstract boolean delTarea(int tid);

	public abstract boolean insertTarea(Tarea tarea);

	public abstract boolean updateTarea(Tarea tarea);

	public List<Tarea> getUserTareas(int tid) {
		return null;
	}
}
