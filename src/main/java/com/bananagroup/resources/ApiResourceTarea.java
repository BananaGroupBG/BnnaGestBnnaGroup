package com.bananagroup.resources;

import java.util.List;

import com.bananagroup.db.DAOFactory;
import com.bananagroup.db.ProyectoDAO;
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
	public Response insertTask(Tarea nuevaTarea, @HeaderParam("token") String token) {
		int userUid = this.getUserUidFromToken(token);
		Response mResponse = null;

		if (userUid == 0) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		} else {
			TareaDAO tDao = (TareaDAO) DAOFactory.getDAO("tarea");
			tDao.insertTarea(nuevaTarea);
			StatusMessage statusMessage = new StatusMessage(Status.ACCEPTED.getStatusCode(), "Tarea añadida!!");
			mResponse = Response.status(Status.ACCEPTED.getStatusCode()).entity(statusMessage).build();
		}

		return mResponse;
	}

	// 1-.Obtener tarea/tid GET/ Tarea
	@Path("/{tid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTask(@PathParam("tid") int tid, @HeaderParam("token") String token) {

		int userUid = this.getUserUidFromToken(token);
		Response mResponse = null;

		if (userUid == 0) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		} else {
			TareaDAO tDao = (TareaDAO) DAOFactory.getDAO("tarea");
			mResponse = Response.status(200).entity(tDao.getTarea(tid)).build();
		}

		return mResponse;
	}

	/* Actualizar tarea / PUT /Tarea */
	@Path("/{tid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(@PathParam("tid") int tid, Tarea aTarea, @HeaderParam("token") String token) {
		int userUid = this.getUserUidFromToken(token);
		Response mResponse = null;

		if (userUid == 0) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		} else {
			TareaDAO tDao = (TareaDAO) DAOFactory.getDAO("tarea");
			tDao.updateTarea(aTarea);
			StatusMessage statusMessage = new StatusMessage(Status.ACCEPTED.getStatusCode(), "Tarea modificado!!");
			mResponse = Response.status(200).entity(statusMessage).build();
		}

		return mResponse;
	}

	/* Borrar tarea / DELETE /Tarea */
	@Path("/{tid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTask(@PathParam("tid") int tid, @HeaderParam("token") String token) {
		int userUid = this.getUserUidFromToken(token);
		Response mResponse = null;

		if (userUid == 0) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		} else {
			TareaDAO tDao = (TareaDAO) DAOFactory.getDAO("tarea");
			tDao.delTarea(tid);
			StatusMessage statusMessage = new StatusMessage(Status.ACCEPTED.getStatusCode(), "Tarea borrada!!");
			mResponse = Response.status(200).entity(statusMessage).build();
		}

		return mResponse;
	}
}
