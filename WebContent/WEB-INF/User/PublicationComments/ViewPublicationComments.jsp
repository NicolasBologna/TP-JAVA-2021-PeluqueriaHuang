<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Service"%>
<%@page import="entities.User"%>
<%@page import="entities.Role"%>
<%@page import="entities.Local"%>
<%@page import="entities.Comment"%>
<%@page import="logic.Roles"%>
<%@page import="dtos.PublicationDetailsDto"%>
<!DOCTYPE html>
<html>
<%
User user = (User) session.getAttribute("user") != null ? (User) session.getAttribute("user") : new User();
PublicationDetailsDto dto = (PublicationDetailsDto) request.getAttribute("dto");
%>

<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="./assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="./assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Publicación</title>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

</head>
<body class="index-page sidebar-collapse"
	onload="window.location='#comments';">
	<jsp:include page="/NavHeader.jsp" />
	<div class="wrapper">
		<div class="page-header clear-filter" filter-color="orange">
			<div class="page-header-image" data-parallax="true"
				style="background-image: url('assets/img/cover_4.jpg');"></div>
			<div class="container">
				<div class="content-center brand">
					<img class="img-fluid" src="./assets/img/now-logo.svg" alt=""
						style="max-width: 200px;">
					<h1 class="h1-seo">Salón Legends</h1>
					<h3>Siempre el mejor servicio</h3>
				</div>
			</div>
		</div>
		<div class="container shadow my-3 py-3">
			<div class="card">
				<img src="data:image/jpg;base64,<%=dto.getBase64Image()%>"
					class="card-img-top" alt="Corte" />
				<div class="card-body">
					<h5 class="card-title"><%=dto.getPublication().getTitle()%></h5>
					<p class="card-text"><%=dto.getPublication().getText()%></p>
					<p>
						<small class="card-text text-muted"><b>Fecha de
								publicación:</b> <%=dto.getPublication().getDate()%></small>
					</p>
					<p>
						<small class="card-text text-muted"><b>Publicado por:</b> <%=dto.getCreatedBy().getFullName()%></small>
					</p>
				</div>
			</div>


			<div class="container d-flex justify-content-center">
					<div class="card shadow-0 border"
						style="background-color: #f0f2f5;">
						<div class="card-body p-4">
							<form action="PublicationCommentsServlet" method="post"
								id="commentForm">
								<div class="form-outline mb-4">
									<input type="text" required id="addAComment"
										class="form-control" placeholder="Escribí tu comentario..."
										name="comment" />
									<input hidden type="number" value="<%=dto.getPublication().getPublicationId()%>" name="publicationId"/>
									<button class="form-label border-0" type="submit"
										style="cursor: pointer;">+ Agregar comentario</button>
								</div>
							</form>
							<%
							for (Comment comment : dto.getComments()) {
							%>
							<div class="card mb-4">
								<div class="card-body">
									<p><%=comment.getText()%></p>

									<div class="d-flex justify-content-between">
										<div class="d-flex flex-row align-items-center">
											<img
												src="https://randomuser.me/api/portraits/thumb/men/<%=comment.getCreator().getUserId()%>.jpg"
												alt="avatar" width="25" height="25" />
											<p class="small mb-0 ms-2 ml-2"><%=comment.getCreator().getFullName()%></p>
										</div>
										<div class="d-flex flex-row align-items-center">
											<p class="small text-muted mb-0"><%=comment.getDate()%></p>
										</div>
									</div>
								</div>
							</div>
							<%
							}
							%>

						</div>
					</div>
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