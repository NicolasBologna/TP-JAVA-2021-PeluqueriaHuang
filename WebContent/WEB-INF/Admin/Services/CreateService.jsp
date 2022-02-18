<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Service"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="./assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="./assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Carga de Servicio</title>
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
		<h1 class="text-center pt-2">Crear Servicio</h1>
		<form action="CreateServiceServlet" method="post" class="shadow p-5">
			<div class="form-group mb-4">
				<label for="name">Nombre</label> <input type="text"
					name="name" id="name" class="form-control"
					placeholder="Ingrese el nombre del servicio" required>
			</div>
			<div class="form-group mb-4">
				<label for="description">Descripcion</label> <input type="text"
					name="description" id="description" class="form-control"
					placeholder="Ingrese descripcion del servicio" required>
			</div>
			<div class="form-group mb-4">
				<label for="price">Precio</label> <input
					type="text" name="price" id="price" class="form-control"
					placeholder="Ingrese el precio">
			</div>
       		<div class="form-group mb-4">
       		
					
				<label for="duration">Duracion</label> <input
					type="time" name="duration" id="duration" 
					class ="form-control" step = "2">
			</div>
       		<span class="text-danger">
			  ${errorMessage}
			</span>
			
			<span class="text-success">
			  ${successMessage}
			</span>
			
			<button name="register" id="register" class="btn btn-block btn-primary"
				type="submit">
				<span>Agregar</span>
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
</body>
</html>