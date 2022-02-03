<%@page import="java.util.LinkedList"%>
<%@page import="entities.Local"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="./assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="./assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Editar Local</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
	name='viewport' />
<!--     Fonts and icons     -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!-- CSS Files -->
<link href="./assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="./assets/css/now-ui-kit.css?v=1.3.0" rel="stylesheet" />

<%
	Local l = (Local)request.getAttribute("local");
%>

<style type="text/css">
.form-check .form-check-sign::after{
	color:white;
}

.form-control {
	color:white;
}

</style>

</head>
<body class="bg-dark text-light">
	<main>
	<div class="container">
		<h1 class="text-center pt-2">Editar Local</h1>
		<form action="EditLocalServlet" method="post" class="shadow p-5">
		<div class="form-group mb-4 d-none">
				<label for="id">ID</label> <input type="text" readonly = "" name="id"
					id="id" class="form-control"  value="<%= l.getLocalId()%>">
			</div>
			<div class="form-group mb-4">
				<label for="nombre">Nombre</label> <input type="text"
					name="nombre" id="nombre" class="form-control"
					placeholder="Ingrese su nombre"  value="<%= l.getName()%>">
			</div>
			<div class="form-group mb-4">
				<label for="coordenates">Coordenadas</label> <input type="text"
					name="coordenates" id="coordenates" class="form-control" value="<%= l.getCoordenates()%>">
			</div>
			<div class="form-group mb-4">
				<label for="address">Direccion</label> <input
					type="text" name="address" id="address" class="form-control" value="<%= l.getAddress()%>">
			</div>
			<div class="form-group mb-4">
				<label for="isEnable">Local Habilitado</label> <br>
				<input type="checkbox" checked name="isEnable" class="bootstrap-switch"
				    data-on-label="SI"
				    data-off-label="NO" value = "<%= l.getIsEnable()%>">
			</div>
       		
       		<span class="text-danger">
			  ${errorMessage}
			</span>
			
			<span class="text-success">
			  ${successMessage}
			</span>
			<button name="register" id="register" class="btn btn-block btn-primary"
				type="submit">
				<span>Finalizar</span>
			</button>
		</form>
	</div>
	</main>
	<!--   Core JS Files   -->
	<script src="./assets/js/core/jquery.min.js" type="text/javascript"></script>
	<script src="./assets/js/core/popper.min.js" type="text/javascript"></script>
	<script src="./assets/js/core/bootstrap.min.js" type="text/javascript"></script>
	<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
	<script src="./assets/js/plugins/bootstrap-switch.js"></script>
	<!-- Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc -->
	<script src="./assets/js/now-ui-kit.js?v=1.3.0" type="text/javascript"></script>
	
	<script type="text/javascript">
		$(document).ready(function () {
		    $('#checkBtn').click(function() {
		      checked = $("input[type=checkbox]:checked").length;
		
		      if(!checked) {
		        alert("Debe seleccionar por lo menos un rol.");
		        return false;
		      }
		
		    });
		});
	</script>
</body>
</html>