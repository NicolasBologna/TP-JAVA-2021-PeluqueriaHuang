<%@page import="entities.User"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Schedule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png">
	<link rel="icon" type="image/png" href="./assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>
	  Administar Horarios
	</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
	<!--     Fonts and icons     -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
	<!-- CSS Files -->
	<link href="./assets/css/bootstrap.min.css" rel="stylesheet" />
	<link href="./assets/css/now-ui-kit.css?v=1.3.0" rel="stylesheet" />
	
	<%
		User user = (User)session.getAttribute("usuario");
    	LinkedList<Schedule> schedulesList = (LinkedList<Schedule>)request.getAttribute("schedulesList");    
	%>
</head>
<body class="bg-dark text-light">
	<div class="container">
		<div class="row">
        	<h1>Mis Horarios</h1>
           	<div class="col-12 col-sm-12 col-lg-12 shadow">
               	<div class="table-responsive">
                   	<table class="table table-dark pt-2">
                   		<thead>
                   			<tr>
                   				<th>Dia de la semana</th>
                   		    	<th>Horario de inicio</th>
                       			<th>Horario de fin</th>
                       			<th>Barbería</th>
                       			<th></th>
                   			</tr>
                   		</thead>
                   		<tbody>
                   		<%
                   			for (Schedule schedule : schedulesList) {
                   		%>
                   			<tr>
                   				<td><%=schedule.getDay_of_week_As_string()%></td>
                   				<td><%=schedule.getStart_time()%> hs</td>
                   				<td><%=schedule.getEnd_time()%> hs</td>
                   				<td><%=schedule.getLocal().getName()%></td>
         
                   				<td><a type="button" class="btn btn-primary btn-round btn-sm my-0" 
                   						href="EditScheduleServlet?idSchedule=<%=schedule.getId()%>">Editar</a>
                   				</td>               						
                   			</tr>
                   		<% } %>
                   		<tr class="text-center">
							<td ><a type="button" class="btn btn-primary btn-round" href='index'>Inicio</a></td>
                   			<td colspan=9> <a type="button" class="btn btn-success btn-round" href="CreateScheduleServlet">Agregar Horario</a></td>
                   		</tr>
                   		</tbody>
                  		</table>
                  		<span class="text-success">
			  				${successMessage}
						</span>
              		</div>
              	</div>	
       	</div>
	</div>
	
	<script src="./assets/js/core/jquery.min.js" type="text/javascript"></script>
	<script src="./assets/js/core/bootstrap.min.js" type="text/javascript"></script>
	<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
	<script src="./assets/js/plugins/bootstrap-switch.js"></script>
	<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
	<script src="./assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
</body>
</html>