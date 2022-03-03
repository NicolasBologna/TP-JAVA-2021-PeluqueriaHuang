<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="entities.User"%>
	<%@page import="entities.Role"%>
	<%@page import="logic.Roles"%>

	<%
		User user = (User)session.getAttribute("user") != null ? (User)session.getAttribute("user") : new User();
	%>
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
					<li class="nav-item"><a class="nav-link"
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
				<% }else{ %>
					<li class="nav-item"><a class="nav-link"
						href="logout"> <i 
							class="now-ui-icons users_circle-08"></i>
							<p>Salir</p>
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