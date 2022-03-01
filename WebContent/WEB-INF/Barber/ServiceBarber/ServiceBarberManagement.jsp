<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Service"%>
<%@page import="entities.ServiceBarber"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png">
	<link rel="icon" type="image/png" href="./assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>
	  Administrar mis serevicios
	</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
	<!--     Fonts and icons     -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
	<!-- CSS Files -->
	<link href="./assets/css/bootstrap.min.css" rel="stylesheet" />
	<link href="./assets/css/now-ui-kit.css?v=1.3.0" rel="stylesheet" />
	
	<%
	    	LinkedList<Service> allServices = (LinkedList<Service>)request.getAttribute("serviceList");
			LinkedList<Integer> serviceBarber = (LinkedList<Integer>)request.getAttribute("serviceBarberList");
	    
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
	<div class="container">
       	<h1>Mis servicios</h1>
      		<form action="ServiceBarberListServlet" method="post" class="shadow p-5">          
               <label>Servicios</label>
				<div class="mb-4">
		     		<%
	       			for (Service service : allServices) {		       				
		       		%>
						<div class="form-check form-check-inline text-light">
						  <label class="form-check-label">
						    <input class="form-check-input" type="checkbox" <%= serviceBarber.contains(service.getServiceId())? "checked":"" %> name="services" 
						    	value="<%=service.getServiceId()%>"><%=service.getName()%> 
						    <span class="form-check-sign">
						        <span class="check "></span>
						    </span>
						  </label>
						</div>
	       			<% } %>
	       		</div>
	       		<button class="btn btn-block btn-success"
				type="submit" >Guardar</button>
				<a class="btn btn-block btn-primary"
				href="Index" >Volver</a>
	       		<span class="text-success">
	  				${successMessage}
				</span>
				<span class="text-danger">
	  				${errorMessage}
				</span>
      		</form>
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
	</script>
</body>
</html>