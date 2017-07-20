package com.bananagroup.resources;

import java.util.ArrayList;
import java.util.List;

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
import com.sun.jersey.api.client.ClientResponse.Status;

import com.bananagroup.db.DAOFactory;
import com.bananagroup.db.ProyectoDAO;
import com.bananagroup.db.TareaDAO;
import com.bananagroup.models.*;
import com.bananagroup.resources.JSONService;

@Path("/proyectos")
public class ApiResourceProyecto extends JSONService {

	/* GET /proyectos */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectList(@HeaderParam("token") String token) {

		int userUid = this.getUserUidFromToken(token);
		Response mResponse = null;

		if (userUid == 0) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		} else {
			ProyectoDAO pDao = (ProyectoDAO) DAOFactory.getDAO("proyecto");
			mResponse = Response.status(200).entity(pDao.getUserProyectos(userUid)).build();
		}

		return mResponse;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertProject(Proyecto nuevoProyecto, @HeaderParam("token") String token) {
		int userUid = this.getUserUidFromToken(token);
		Response mResponse = null;

		if (userUid == 0) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		} else {
			ProyectoDAO pDao = (ProyectoDAO) DAOFactory.getDAO("proyecto");
			pDao.insertProyecto(nuevoProyecto);
			StatusMessage statusMessage = new StatusMessage(Status.ACCEPTED.getStatusCode(), "Proyecto añadido!!");
			mResponse = Response.status(Status.ACCEPTED.getStatusCode()).entity(statusMessage).build();
		}

		return mResponse;
	}

	/* GET /Proyectos */
	@Path("/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProject(@PathParam("pid") int pid, @HeaderParam("token") String token) {

		int userUid = this.getUserUidFromToken(token);
		Response mResponse = null;

		if (userUid == 0) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		} else {
			ProyectoDAO pDao = (ProyectoDAO) DAOFactory.getDAO("proyecto");
			mResponse = Response.status(200).entity(pDao.getProyecto(pid)).build();
		}

		return mResponse;
	}

	/* PUT /Proyectos */
	@Path("/{pid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProject(@PathParam("pid") int pid, Proyecto aProyecto, @HeaderParam("token") String token) {
		int userUid = this.getUserUidFromToken(token);
		Response mResponse = null;

		if (userUid == 0) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		} else {
			ProyectoDAO pDao = (ProyectoDAO) DAOFactory.getDAO("proyecto");
			pDao.updateProyecto(aProyecto);
			StatusMessage statusMessage = new StatusMessage(Status.ACCEPTED.getStatusCode(), "Proyecto modificado!!");
			mResponse = Response.status(200).entity(statusMessage).build();
		}

		return mResponse;
	}

	/* DELETE /Proyectos */
	@Path("/{pid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProject(@PathParam("pid") int pid, @HeaderParam("token") String token) {

		int userUid = this.getUserUidFromToken(token);
		Response mResponse = null;

		if (userUid == 0) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		} else {
			ProyectoDAO pDao = (ProyectoDAO) DAOFactory.getDAO("proyecto");
			pDao.delProyecto(pid);
			StatusMessage statusMessage = new StatusMessage(Status.ACCEPTED.getStatusCode(), "Proyecto borrado!!");
			mResponse = Response.status(200).entity(statusMessage).build();
		}

		return mResponse;
	}
}
