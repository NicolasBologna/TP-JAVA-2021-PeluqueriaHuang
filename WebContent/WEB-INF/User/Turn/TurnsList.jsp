<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.LinkedList"%>
 <%@page import="entities.Turn"%>
 <%@page import="entities.User"%>
 <%@page import="entities.Schedule"%>
 <%@page import="entities.Local"%>
  <%@page import="entities.Service"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png">
	<link rel="icon" type="image/png" href="./assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>
Turnos	</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
	<!--     Fonts and icons     -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
	<!-- CSS Files -->
	<link href="./assets/css/bootstrap.min.css" rel="stylesheet" />
	<link href="./assets/css/now-ui-kit.css?v=1.3.0" rel="stylesheet" />
	
	<%
		
	    LinkedList<Turn> lt = (LinkedList<Turn>)request.getAttribute("turnsList");
	    
	%>
	
</head>
<body class="bg-dark text-light">
	<div class="container">
		<div class="row">
        	<h1>Mis turnos</h1>
           	<div class="col-12 col-sm-12 col-lg-12 shadow">
               	<div class="table-responsive">
                   	<table class="table table-dark pt-2">
                   		<thead>
                   			<tr>
                   				<th hidden>Id</th>
                   				<th>Fecha</th>
                   				<th>Hora</th>
                   		    	<th>Local</th>
                       			<th>Peluquero</th>
                       			
                   			</tr>
                   		</thead>
                   		<tbody>
                   		<%
                   			for (Turn t : lt) {
                   				
             
                   				Schedule s = t.getSchedule();
                   				User b = s.getBarber();
                   				Local l = s.getLocal();
                   				LinkedList<Service> ls = t.getServices();
                   		%>
                   				
                   			<tr >
                   				<td hidden><%=t.getTurnId()%></td>
                   				<td><%= t.getDate() %></td>
                   				<td><%= t.getHour() %></td>
                   				<td><%=l.getAddress()%></td>
                   				<td><%=b.getFullName()%></td>
                   				
                   					
                   				
                   								
                   			</tr>
                   		<% } %>
                   		
                   		</tbody>
                  		</table>
                  		<button type="button" class="btn btn-primary btn-round" onclick="window.location.href='index'">Inicio</button>
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
	<script type="text/javascript">
	function changeStatus(id){
		var dataRequestHeader={"Id" : id};

	    $.ajax({
            type:'POST',
            url:'CompleteTurnServlet',  
            cache:false,
            headers:dataRequestHeader,
            success:function(){ location.reload(true)},
            error:function(xhr,ajaxOptions){
                alert(xhr.status + " :: " + xhr.statusText);
                } 
            });
	}</script>
</body>
</html>