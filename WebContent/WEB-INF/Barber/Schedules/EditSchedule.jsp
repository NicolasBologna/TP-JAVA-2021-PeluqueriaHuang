<%@page import="java.util.LinkedList"%>
<%@page import="entities.Schedule"%>
<%@page import="entities.Local"%>
<%@page import="utils.Days"%>
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
<title>Editar Horario</title>
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
	Schedule s = (Schedule)request.getAttribute("schedule");
	LinkedList<Local> localsList = (LinkedList<Local>)request.getAttribute("localsList");
	
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
		<h1 class="text-center pt-2">Editar Horario</h1>
		<form action="EditScheduleServlet" method="post" class="shadow p-5">
			<input value="<%= s.getId() %>" name="schedule_id" class="d-none">	
			<div class="form-group mb-4">				
				<label for="local">Peluquería</label> 
				<select name="local" class="browser-default custom-select">
  					<%
	       			for (Local local: localsList) {
       				%>
				    <option value="<%=local.getLocalId()%>" 
				    	<% if(local.getLocalId() == s.getLocal().getLocalId()){%> Selected <% }%>
				    >
				        	<%=local.getName()%>
				    </option>
				  	<% } %>
				</select>	       		
       		</div>
			<div class="form-group mb-4">
				<label for="day_of_week">Día de la semana</label> 
				<select name="day_of_week" class="browser-default custom-select">
  					<%
	       			for (Days day: Days.values()) {
       				%>
				    <option value="<%=day.name()%>"
				    	<% if(day == s.getDay_of_week()){%> Selected <% }%>
				    >
				        <%=day%>
				    </option>
				  	<% } %>
				</select>	       		
       		</div>
			<div class="form-group mb-4">
				<label for="start_time">Horario de inicio</label> <input type="time"
					name="start_time" id="start_time" class="form-control" min="07:00" max="23:45" required value="<%=s.getStart_time()%>" step="900">
			</div>
			<div class="form-group mb-4">
				<label for="end_time">Horario de fin</label> <input type="time"
					name="end_time" id="end_time" class="form-control"  min="07:00" max="24:00" required value="<%=s.getEnd_time()%>" step="900">
			</div>
			
			<button name="register" id="register" class="btn btn-block btn-primary"
				type="submit">
				<span>Editar</span>
			</button>
			
			<a class="btn btn-block btn-primary"
				href="ListSchedules">
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