package com.bananagroup.resources;

import java.util.List;

import com.bananagroup.db.DAOFactory;
import com.bananagroup.db.TareaDAO;
import com.bananagroup.models.*;
import com.sun.jersey.api.client.ClientResponse.Status;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tareas")
public class ApiResourceTarea extends JSONService {
	private static List<Tarea> lasTareas;

	// Obtener lista de tareas/ GET / Tarea
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTaskList(@HeaderParam("token") String token) {

		int userUid = this.getUserUidFromToken(token);
		Response mResponse = null;

		if (userUid == 0) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		} else {
			TareaDAO tDao = (TareaDAO) DAOFactory.getDAO("tarea");
			mResponse = Response.status(200).entity(tDao.getUserTareas(userUid)).build();

		}

		return mResponse;
	}

	// Insertar tarea/ POST / Tarea
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message insertTask(Tarea nuevaTarea) {
		lasTareas.add(nuevaTarea);
		return new Message("Tarea añadida");
	}

	// 1-.Obtener tarea/tid GET/ Tarea
	@Path("/{tid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Tarea getTask(@PathParam("tid") int tid) {

		Tarea unaTarea = new Tarea(0, null, null, null);
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
