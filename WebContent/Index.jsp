
<%@page import="logic.Roles"%>
<%@page import="entities.Role"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="entities.User"%>
<%@page import="entities.Publication"%>
<%@page import="java.util.LinkedList"%>
<%@page import="dtos.PublicationsIndexDto"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76"
		href="./assets/img/apple-icon.png">
	<link rel="icon" type="image/png" href="./assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>SALÓN LEGENDS</title>
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
	
	<%
		User user = (User)session.getAttribute("user") != null ? (User)session.getAttribute("user") : new User();
		LinkedList<PublicationsIndexDto> lp = (LinkedList<PublicationsIndexDto>)request.getAttribute("publicationList");
	%>
	
</head>

<body class="index-page sidebar-collapse">
	<jsp:include page="NavHeader.jsp" />
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
		<div class="bg-light">
			<div class="d-flex justify-content-center">
				<a class="btn btn-primary btn-round btn-lg mt-4 animate__animated animate__jello animate__infinite	animate__slower" 
					type="button" href="BookTurnServlet">
					<i class="now-ui-icons shopping_basket"></i> Reservá tu turno
				</a>
			</div>
			<div class="d-flex justify-content-center">
				<h1 class="pt-3">Mirá nuestras últimas tendencias</h1>
			</div>
			<section class="row d-flex justify-content-around pb-3">
			<%for(PublicationsIndexDto p: lp){ %>
				<div class="card my-3 col-5 px-0 rounded">
			  		<div class="row no-gutters">
					    <div class="col-6">
					      <img class="img-fluid" src="data:image/jpg;base64,<%=p.getBase64Image()%>" alt="...">
					    </div>
			    		<div class="col-6">
				      		<div class="card-body">
						        <h5 class="card-title"><%=p.getPublication().getTitle() %></h5>
						        <p class="card-text"><%= p.getPublication().getText() %>.</p>
						        <p class="card-text"><small class="text-muted"><%= p.getPublication().getDate() %></small></p>
						        <p class="card-text"><small class="text-muted"><%= p.getCreatedBy().getFullName() %></small></p>
					      	</div>
			    		</div>
			  		</div>
				</div>
			<%} %>
			</section>
		</div>
		

	</div>
	<jsp:include page="/Footer.jsp" />
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