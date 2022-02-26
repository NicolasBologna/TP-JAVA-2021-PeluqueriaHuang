<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.LinkedList"%>
<%@page import="entities.Service"%>
<%@page import="entities.User"%>
<%@page import="entities.Role"%>
<%@page import="entities.Local"%>
<%@page import="logic.Roles"%>
<!DOCTYPE html>
<html>
<%
	User user = (User)session.getAttribute("user") != null ? (User)session.getAttribute("user") : new User();
	String[] servicesList = (String[])request.getAttribute("servicesId");
	LinkedList<User> barbersList = (LinkedList<User>)request.getAttribute("barbersList");
	int idLocal = (int)request.getAttribute("idLocal");
%>

<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76"
		href="./assets/img/apple-icon.png">
	<link rel="icon" type="image/png" href="./assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>Reservá tu turno</title>
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
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>

</head>
<body class="index-page sidebar-collapse" onload="window.location='#form-turno';">
<!-- Navbar -->
	<nav
		class="navbar navbar-expand-lg bg-primary fixed-top navbar-transparent "
		color-on-scroll="400">
		<div class="container">
			<div class="navbar-translate">
				<a class="navbar-brand" href="./">SALÓN LEGENDS</a>
				<button class="navbar-toggler navbar-toggler" type="button"
					data-toggle="collapse" data-target="#navigation"
					aria-controls="navigation-index" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-bar top-bar"></span> <span
						class="navbar-toggler-bar middle-bar"></span> <span
						class="navbar-toggler-bar bottom-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse justify-content-end"
				id="navigation" data-nav-image="./assets/img/blurred-image-1.jpg">
				<ul class="navbar-nav">
				<%if (user.hasRol(Roles.getRoleByName("Administrador"))) { %>
					<li class="nav-item"><a class="nav-link"
						href="UserListServlet"> <i
							class="now-ui-icons users_single-02"></i>
							<p>Usuarios</p>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="LocalListServlet"> <i 
							class="now-ui-icons shopping_shop"></i>
							<p>Sucursales</p>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ServiceListServlet"> <i
							class="now-ui-icons objects_diamond"></i>
							<p>Servicios</p>
					</a></li>
				<% } %>
				<%if (user.hasRol(Roles.getRoleByName("Peluquero"))) { %>
					<li class="nav-item"><a class="nav-link"
						href="ListSchedules"> <i 
							class="now-ui-icons ui-1_calendar-60"></i>
							<p>Mis Horarios</p>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ServiceBarberListServlet"> <i 
							class="now-ui-icons text_align-left"></i>
							<p>Mis servicios</p>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="PublicationListServlet"> <i
							class="now-ui-icons business_badge"></i>
							<p>Mis Publicaciones</p>
					</a></li>
				<% } %>
				<%if (user.hasRol(Roles.getRoleByName("Cliente")) || user.hasRol(Roles.getRoleByName("Peluquero"))) { %>
					<li class="nav-item d-none"><a class="nav-link"
						href="ListTurnsServlet"> <i 
							class="now-ui-icons design_scissors"></i>
							<p>Mis Turnos</p>
					</a></li>
				<% } %>
				<%if (user.getEmail() == null) { %>
					<li class="nav-item"><a class="nav-link"
						href="signIn"> <i 
							class="now-ui-icons users_circle-08"></i>
							<p>Ingresar</p>
					</a></li>
				<% } %>
					<li class="nav-item"><a class="nav-link" rel="tooltip"
						title="Seguinos en Instagram" data-placement="bottom"
						href="https://www.instagram.com/salonlegend_/"
						target="_blank"> <i class="fab fa-instagram"></i>
							<p class="d-lg-none d-xl-none">Instagram</p>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- End Navbar -->
	<div class="wrapper">
		<div class="page-header clear-filter" filter-color="orange">
			<div class="page-header-image" data-parallax="true"
				style="background-image: url('assets/img/cover_4.jpg');"></div>
			<div class="container">
				<div class="content-center brand">
					<img class="img-fluid" src="./assets/img/now-logo.svg" alt="" style="max-width: 200px;">
					<h1 class="h1-seo">Salón Legends</h1>
					<h3>Siempre el mejor servicio</h3>
				</div>
			</div>
		</div>
		<div class="container shadow my-4 py-4" id="form-turno">
			<h1 class="text-center pt-2">Solicitar turno</h1>
			
			<div class="progress-container progress-info">
			  <span class="progress-badge">Tu turno</span>
			  <div class="progress">
			    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
			      <span class="progress-value">40%</span>
			    </div>
			  </div>
			</div>
			<form action="GetAvailableTurnsServlet" method="post">				
	       		<div class="form-group">
					<label for="barber">Peluquero</label> 
					<select name="idBarber" class="browser-default custom-select">
	  					<%
		       			for (User barber: barbersList) {
	       				%>
					    <option value="<%=barber.getUserId()%>">
					        <%=barber.getFullName()%>
					    </option>
					  	<% } %>
					</select>	
				</div>
				
				<div class="form-group">
					<label for="start">Start date:</label>
	
					<input type="date" id="start" name="turn-date"
					       id="datePicker"
					       value="<%= java.time.LocalDate.now() %>"
					       min="<%= java.time.LocalDate.now() %>"
					       max="<%= java.time.LocalDate.now().plusMonths(1) %>">   	
		        </div>	
		        
		        <input hidden name="idLocal" value="<%=idLocal%>">
		        
	     		<%
       			for (String service : servicesList) {
	       		%>
				<input hidden class="form-check-input service" type="checkbox" name="services" value="<%=service%>" checked>
	       		<% } %>
		        
   				<button class="btn btn-block btn-primary"
					type="submit">
					<span>Seleccionar</span>
				</button>
			</form>
		</div>
	
	
			<!--  End Modal -->
	<footer class="footer" data-background-color="black">
		<div class=" container ">
			Dise�o basado en una plantilla de <a href="https://www.creative-tim.com"> Creative Tim
			<div class="copyright" id="copyright">
				&copy;
				<script>
					document.getElementById('copyright').appendChild(
							document.createTextNode(new Date()
									.getFullYear()))
				</script>
				UTN - FRRO - JAVA
			</div>
		</div>
	</footer>
	</div>
	<!--   Core JS Files   -->
	<script src="./assets/js/core/jquery.min.js" type="text/javascript"></script>
	<script src="./assets/js/core/popper.min.js" type="text/javascript"></script>
	<script src="./assets/js/core/bootstrap.min.js" type="text/javascript"></script>
	<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
	<script src="./assets/js/plugins/bootstrap-switch.js"></script>
	<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
	<script src="./assets/js/plugins/nouislider.min.js"
		type="text/javascript"></script>
	<!--  Plugin for the DatePicker, full documentation here: https://github.com/uxsolutions/bootstrap-datepicker -->
	<script src="./assets/js/plugins/bootstrap-datepicker.js"
		type="text/javascript"></script>
	<!-- Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc -->
	<script src="./assets/js/now-ui-kit.js?v=1.3.0" type="text/javascript"></script>
	<script>
		
	window.onload=function(){
		var picker = document.getElementById('datePicker');
		console.log(picker)
		picker.addEventListener('change', function(e){
		  var day = new Date(this.value).getUTCDay();
		  if([6,0].includes(day)){
		    e.target.setCustomValidity('week-end not allowed')
		  } else {
		    e.target.setCustomValidity('')
		  }
		});
	}
	
	
	</script>
	</body>
</html>