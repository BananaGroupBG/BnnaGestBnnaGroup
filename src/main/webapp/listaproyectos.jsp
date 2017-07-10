<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="head.jsp"></jsp:include>
<body>
	<jsp:include page="header.jsp"></jsp:include>


	<div class="row">
		<div class="col s12 m6">
			<div class="card blue left-align">
				<!-- ******** Para cada Proyecto **************** -->
				<c:forEach var="proy" items="${listaProyectosAMostrar}"
					varStatus="counter">

					<div class="card-content white-text">

						<span class="card-title">Titulo: ${proy.titulo}</span>
						<p>Descripción: ${proy.descripcion}</p>
						<p>Fecha inicio: ${proy.fechaI}</p>
						<p>Activo: ${proy.activo}</p>
					</div>

					<div class="right hide-on-med-and-down">
						<a class="btn-floating yellow darken-1" href="#modal1"
							class="btn modal-trigger"><i class="material-icons">visibility</i></a>
						<a class="btn-floating green"><i class="material-icons">mode_edit</i></a>
						<a class="btn-floating red"><i class="material-icons">delete</i></a>
					</div>
				</c:forEach>
				<!-- ********  FIN Para cada Proyecto **************** -->
			</div>


			<!-- Modal Structure -->
			<div id="modal1" class="modal">
				<div class="modal-content">
					<h4>Titulo: Proyecto 1</h4>
					<p>Descripción: sdafsdfsdfsdfasdfsdfdsf</p>
					<p>Fecha inicio : 02/01/2017</p>
					<p>Activo: Sí</p>
				</div>
				<div class="row">
					<div class="col s12 m6">
						<div class="card blue""left-align">
							<div class="card-content white-text">
								<span class="card-title">Titulo: Tarea 1</span>
								<p>Descripción: dfsfsdfsdafsadfsadfadsafasdf</p>
								<p>Responsable : juanito</p>
								<p>Proyecto: 11111</p>
							</div>
							<div class="right hide-on-med-and-down">
								<a class="btn-floating yellow darken-1" href="#modal1"
									class="btn modal-trigger"><i class="material-icons">visibility</i></a>
								<a class="btn-floating green"><i class="material-icons">mode_edit</i></a>
								<a class="btn-floating red"><i class="material-icons">delete</i></a>
							</div>



							<div class="card-action">
								<a>""</a> <a>""</a>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="#!"
						class="modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
				</div>
			</div>
			<div class="card-panel hoverable"></div>
		</div>
		</a>
	</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	<!--Import jQuery before materialize.js-->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

	<script>
		$(document).ready(function() {
			// the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
			$('.modal').modal();
		});
	</script>
</body>
</html>