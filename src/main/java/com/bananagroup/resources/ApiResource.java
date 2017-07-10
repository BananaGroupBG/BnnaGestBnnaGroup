package com.bananagroup.resources;

import java.util.ArrayList;
import java.util.List;

import com.bananagroup.models.*;
import com.sun.xml.internal.ws.api.message.Message;

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
	// ----FIN Mockeado de datos
	// ------------------------------------------------------

	// ----METODOS GENERICOS DE Task:-----------------
	/* GET|POST /task */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tarea> getTaskList() {
		return this.lasTareas;
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message insertTask(Tarea nuevaTarea) {
		this.lasTareas.add(nuevaTarea);
		return new Message("Tarea añadida");
	}
	// ----FIN METODOS GENERICOS DE Task:-----------------

	// ----METODOS Particulares DE Task/tid yo la llamé
	// idTarea:-----------------
	/* GET|PUT|DELETE /tareas/{tid} */
	// 1-.Obtener tarea/tid
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
}
