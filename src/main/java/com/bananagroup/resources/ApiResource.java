package com.bananagroup.resources;

import java.util.ArrayList;
import java.util.List;

import com.bananagroup.models.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class ApiResource {

	private static List<Proyecto> listaProyectos;

	static {
		listaProyectos = new ArrayList<Proyecto>();

		listaProyectos.add(new Proyecto(0, "tit", "des", null, null, false, null));

	}

	/* GET /proyectos */
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Proyecto> getProjectList() {
		return this.listaProyectos;
	}

	/* POST /proyectos */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message insertProject(Proyecto nuevoProyecto) {
		this.listaProyectos.add(nuevoProyecto);
		return new Message("Proyecto añadido");
	}

	/* GET /Proyectos */
	@Path("/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Proyecto getProject(@PathParam("pid") int pid) {

		Proyecto unProyecto = new Proyecto();
		for (Proyecto proyecto : listaProyectos) {
			if (proyecto.getPid() == pid) {
				unProyecto = proyecto;
				break;
			}
		}

		return unProyecto;
	}

	/* PUT /Proyectos */
	@Path("/{pid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateProject(@PathParam("pid") int pid, Proyecto aProyecto) {
		for (Proyecto proyecto : listaProyectos) {
			if (proyecto.getPid() == pid) {
				listaProyectos.remove(proyecto);
				listaProyectos.add(aProyecto);
				break;
			}
		}

		return new Message("Proyecto modificado");
	}

	/* DELETE /Proyectos */
	@Path("/{pid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteProject(@PathParam("pid") int pid) {
		for (Proyecto proyecto : listaProyectos) {
			if (proyecto.getPid() == pid) {
				listaProyectos.remove(proyecto);
				break;
			}
		}

		return new Message("Proyecto borrado");
	}

	private static List<Tarea> lasTareas;

	// Definicion de la Lista:
	static {
		// Defino quees un Array:
		lasTareas = new ArrayList<Tarea>();

		// Doy datos ejemplo de los Array que forman la lista.Estaran en la
		// BBDD:
		// Los voy addicionando=add a la lista lasTAreas. Definicion de los
		// attributos: id,desc,resp:
		lasTareas.add(new Tarea(1, "Tarea de compras", null));
		lasTareas.add(new Tarea(2, "Tarea de ventas", null));
		lasTareas.add(new Tarea(1, "Tarea de comprobacion de stoks", null));
	}

	// Obtener lista de tareas/ GET / Tarea
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tarea> getTaskList() {
		return this.lasTareas;
	}

	// Insertar tarea/ POST / Tarea
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message insertTask(Tarea nuevaTarea) {
		this.lasTareas.add(nuevaTarea);
		return new Message("Tarea añadida");
	}

	// 1-.Obtener tarea/tid GET/ Tarea
	@Path("/{tid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Tarea getTask(@PathParam("tid") int tid) {

		Tarea unaTarea = new Tarea(0, null, null);
		for (Tarea taska : lasTareas) {
			if (taska.getTid() == tid) {
				unaTarea = taska;
				break;
			}
		}
		return unaTarea;
	}

	/* Actualizar tarea / PUT /Tarea */
	@Path("/{tid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateTask(@PathParam("tid") int tid, Tarea aTarea) {
		for (Tarea tarea : lasTareas) {
			if (tarea.getTid() == tid) {
				lasTareas.remove(tarea);
				lasTareas.add(aTarea);
				break;
			}
		}

		return new Message("Tarea modificada");
	}

	/* Borrar tarea / DELETE /Tarea */
	@Path("/{tid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteTask(@PathParam("tid") int tid) {
		for (Tarea tarea : lasTareas) {
			if (tarea.getTid() == tid) {
				lasTareas.remove(tarea);
				break;
			}
		}

		return new Message("Tarea borrada");
	}
}