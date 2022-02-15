<%@page import="java.util.LinkedList"%>
<%@page import="entities.User"%>
<%@page import="entities.Local" %>
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
	  Peluqueros 
	</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
	<!--     Fonts and icons     -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
	<!-- CSS Files -->
	<link href="./assets/css/bootstrap.min.css" rel="stylesheet" />
	<link href="./assets/css/now-ui-kit.css?v=1.3.0" rel="stylesheet" />
	
	<%
		
	    LinkedList<User> lb = (LinkedList<User>)request.getAttribute("barbersList");
	    Local local = (Local)request.getAttribute("local");
	%>
	
</head>
<body class="bg-dark text-light">
	<div class="container">
		<div class="row">
        	<h1>Peluqueros de <%= local.getName()%></h1>
        	<h2></h>
           	<div class="col-12 col-sm-12 col-lg-12 shadow">
               	<div class="table-responsive">
                   	<table class="table table-dark pt-2">
                   		<thead>
                   			<tr>
                   				<th>Id</th>
                   		    	<th>Nombre</th>
                       			<th>Apellido</th>
                       			<th>Email</th>
                       			<th>Telefono</th>
                       			
                       			
                   			</tr>
                   		</thead>
                   		<tbody>
                   		<%
                   			for (User barber : lb) {
                   		%>
                   			<tr>
                   				<td><%=barber.getUserId()%></td>
                   				<td><%=barber.getFirstName()%></td>
                   				<td><%=barber.getLastName()%></td>
                   				<td><%=barber.getEmail()%></td>
                   				
                   				<td><%=barber.getPhone()%></td>
         
                   				                 						
                   			</tr>
                   		<% } %>
                   		<tr class="text-center"> 
                   			<td colspan=9> <button type="button" class="btn btn-success btn-round" onclick="window.location.href='signin';">Inicio</button></td>
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
	<script type="text/javascript">
		$('.bootstrap-switch').each(function(){
		    $this = $(this);
		    data_on_label = $this.data('on-label') || '';
		    data_off_label = $this.data('off-label') || '';
		
		    $this.bootstrapSwitch({
		        onText: data_on_label,
		        offText: data_off_label
		    });
		});
		
		function changeStatus(id){
			var dataRequestHeader={"Id" : id};
	
		    $.ajax({
	            type:'POST',
	            url:'DeleteUserServlet',  
	            cache:false,
	            headers:dataRequestHeader,
	            success:function(){ location.reload(true)},
	            error:function(xhr,ajaxOptions){
	                alert(xhr.status + " :: " + xhr.statusText);
	                } 
	            });
		}

	</script>
</body>
</html>