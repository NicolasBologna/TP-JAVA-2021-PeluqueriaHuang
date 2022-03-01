<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.util.LinkedList"%>
<%@page import="entities.Service"%>
<%@page import="entities.User"%>
<%@page import="entities.Role"%>
<%@page import="entities.Turn"%>
<%@page import="logic.Roles"%>
<!DOCTYPE html>
<html>
<%
	User user = (User)session.getAttribute("user") != null ? (User)session.getAttribute("user") : new User();
	Turn turn = (Turn)request.getAttribute("new-turn");
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
<body class="index-page sidebar-collapse" onload="window.location='#form-turn';">
	<jsp:include page="/NavHeader.jsp" />
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
		<div class="container shadow my-4 py-4" id="form-turn">
			<h1 class="text-center pt-2">Solicitar turno</h1>
			
			<div class="progress-container progress-success">
			  <span class="progress-badge">Tu turno</span>
			  <div class="progress mb-3">
			    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
			      <span class="progress-value">100%</span>
			    </div>
			  </div>
			</div>
			
			<div>
				<span class="font-weight-bold">El día</span>
				<p><%= turn.getDate() %></p>
			</div>
			<div>
				<span class="font-weight-bold">A las</span>
				<p><%= turn.getHour() %></p>
			</div>
			<div>
				<span class="font-weight-bold">La duración aproximada de tu turno será</span>
				<p><%= turn.getDuration() %></p>
			</div>
			
			<div class="d-flex justify-content-center">
				<a class="btn btn-primary btn-round btn-lg" type="submit" id="submit-button" href="index">
					<i class="now-ui-icons arrows-1_minimal-left"></i> Volver al inicio
				</a>
			</div>
			
		</div>
	
	
			<!--  End Modal -->
	<jsp:include page="/Footer.jsp" />
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
	
	</script>
	</body>
</html>