<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Role"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="./assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="./assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Alta de Usuario</title>
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

	LinkedList<Role> rolesList = (LinkedList<Role>)request.getAttribute("roleList");
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
		<h1 class="text-center pt-2">Crear Usuario</h1>
		<form action="CreateUserServlet" method="post" class="shadow p-5">
			<div class="form-group mb-4">
				<label for="email">Email</label> <input type="email" name="email"
					id="email" class="form-control" placeholder="email@ejemplo.com"
					required>
			</div>
			<div class="form-group mb-4">
				<label for="password">Contraseña</label> <input type="password"
					name="password" id="password" minlength="8" class="form-control"
					placeholder="Ingrese su contraseña" required>
			</div>
			<div class="form-group mb-4">
				<label for="first_name">Nombre</label> <input type="text"
					name="first_name" id="first_name" class="form-control"
					placeholder="Ingrese su nombre" required>
			</div>
			<div class="form-group mb-4">
				<label for="last_name">Apellido</label> <input type="text"
					name="last_name" id="last_name" class="form-control"
					placeholder="Ingrese su apellido" required>
			</div>
			<div class="form-group mb-4">
				<label for="dni">Documento Nacional de Identidad</label> <input
					type="text" name="dni" id="dni" class="form-control"
					placeholder="(Opcional)Ingrese su dni">
			</div>
			<div class="form-group mb-4">
				<label for="phone">Teléfono</label> <input type="text" name="phone"
					id="phone" class="form-control" placeholder="Ingrese su teléfono"
					required>
			</div>
			
			<div class="form-group mb-4">
				<label for="isEnable">Usuario Habilitado</label> <br>
				<input type="checkbox" checked name="isEnable" class="bootstrap-switch"
				    data-on-label="SI"
				    data-off-label="NO"
				/>
			</div>
			
			<label>Roles</label>
			<div class="mb-4">
	     		<%
	       			for (Role role : rolesList) {
	       		%>
	       		
				<div class="form-check form-check-inline ">
				  <label class="form-check-label">
				    <input class="form-check-input" type="checkbox" name="roles" value="<%=role.getId()%>"><%=role.getRole()%>
				    <span class="form-check-sign">
				        <span class="check "></span>
				    </span>
				  </label>
				</div>
	       		<% } %>
       		</div>
       		
       		<span class="text-danger">
			  ${errorMessage}
			</span>
						
			<button name="register" id="register" class="btn btn-block btn-success"
				type="submit">
				<span>Agregar Usuario</span>
			</button>
			
			<a class="btn btn-block btn-primary"
				href="UserListServlet">
				<span>Volver</span>
			</a>
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