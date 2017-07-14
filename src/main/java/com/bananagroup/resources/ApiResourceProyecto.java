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
import com.bananagroup.models.*;
import com.bananagroup.resources.JSONService;

@Path("/proyectos")
public class ApiResourceProyecto extends JSONService {
	private static List<Proyecto> listaProyectos;

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

	/* POST /proyectos */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message insertProject(Proyecto nuevoProyecto) {
		listaProyectos.add(nuevoProyecto);
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
}
