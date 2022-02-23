
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entities.User"%>
<%@page import="entities.Publication"%>
<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76"
		href="./assets/img/apple-icon.png">
	<link rel="icon" type="image/png" href="./assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>SALÓN LEGEND</title>
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
		User user = (User) session.getAttribute("user");
		LinkedList<Publication> lp = (LinkedList<Publication>)request.getAttribute("publicationList");
	%>
	
</head>

<body class="index-page sidebar-collapse">
	<!-- Navbar -->
	<nav
		class="navbar navbar-expand-lg bg-primary fixed-top navbar-transparent "
		color-on-scroll="400">
		<div class="container">
			<div class="navbar-translate">
				<a class="navbar-brand" href="./"> Legends </a>
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
						href="PublicationListServlet"> <i
							class="now-ui-icons users_single-02"></i>
							<p>Publicaciones</p>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ServiceListServlet"> <i
							class="now-ui-icons users_single-02"></i>
							<p>Servicios</p>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ListSchedules"> <i 
							class="now-ui-icons shopping_shop"></i>
							<p>Horarios</p>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ServiceBarberListServlet"> <i 
							class="now-ui-icons shopping_shop"></i>
							<p>Mis servicios</p>
					</a></li>
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
		<div class="row d-flex justify-content-around">
		<%for(Publication p: lp){ %>
			<div class="card my-3 col-5 px-0 rounded">
		  <div class="row no-gutters">
		    <div class="col-4">
		      <img class="img-fluid" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.salonsuccessacademy.com%2Fwp-content%2Fuploads%2F2015%2F01%2FFotolia_64007532_Subscription_Monthly_M.jpg&f=1&nofb=1" alt="...">
		    </div>
		    <div class="col-8">
		      <div class="card-body">
		        <h5 class="card-title">Publicacion <%= p.getPublicationId() %></h5>
		        <p class="card-text"><%= p.getText() %>.</p>
		        <p class="card-text"><small class="text-muted"><%= p.getDate() %></small></p>
		      </div>
		    </div>
		  </div>
		</div>
		
		<%} %>
		</div>
		 
		<!--  End Modal -->
		<footer class="footer" data-background-color="black">
			<div class=" container ">
				<nav>
					<ul>
						<li><a href="https://www.creative-tim.com"> Creative Tim
						</a></li>
						<li><a href="http://presentation.creative-tim.com"> About
								Us </a></li>
						<li><a href="http://blog.creative-tim.com"> Blog </a></li>
					</ul>
				</nav>
				<div class="copyright" id="copyright">
					&copy;
					<script>
						document.getElementById('copyright').appendChild(
								document.createTextNode(new Date()
										.getFullYear()))
					</script>
					, Designed by <a href="https://www.invisionapp.com" target="_blank">Invision</a>.
					Coded by <a href="https://www.creative-tim.com" target="_blank">Creative
						Tim</a>.
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
		$(document).ready(function() {
			// the body of this function is in assets/js/now-ui-kit.js
			nowuiKit.initSliders();
		});

		function scrollToDownload() {

			if ($('.section-download').length != 0) {
				$("html, body").animate({
					scrollTop : $('.section-download').offset().top
				}, 1000);
			}
		}
	</script>
</body>

</html>