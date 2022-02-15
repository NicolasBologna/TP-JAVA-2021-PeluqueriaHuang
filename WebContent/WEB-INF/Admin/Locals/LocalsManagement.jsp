<%@page import="java.util.LinkedList"%>
<%@page import="entities.Local"%>
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
	  Administar Peluquerías
	</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
	<!--     Fonts and icons     -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
	<!-- CSS Files -->
	<link href="./assets/css/bootstrap.min.css" rel="stylesheet" />
	<link href="./assets/css/now-ui-kit.css?v=1.3.0" rel="stylesheet" />
	
	<%
	    	LinkedList<Local> lp = (LinkedList<Local>)request.getAttribute("localsList");
	    
	%>
	
</head>
<body class="bg-dark text-light">
	<div class="container">
		<div class="row">
        	<h1>Peluquerías</h1>
           	<div class="col-12 col-sm-12 col-lg-12 shadow">
               	<div class="table-responsive">
                   	<table class="table table-dark pt-2">
                   		<thead>
                   			<tr>
                   				<th>Id</th>
                   		    	<th>Nombre</th>
                       			<th>Coordenadas</th>
                       			<th>Dirección</th>
                 				<th></th>
                       			<th></th>
                       			<th></th>
                   			</tr>
                   		</thead>
                   		<tbody>
                   		<%
                   			for (Local local : lp) {
                   		%>
                   			<tr>
                   				<td><%=local.getLocalId()%></td>
                   				<td><%=local.getName()%></td>
                   				<td><%=local.getCoordenates()%></td>
                   				<td><%=local.getAddress()%></td>
                   				<td><button type="button" class="btn btn-primary btn-round btn-sm my-0" 
                   						onclick="window.location.href='BarbersByLocalListServlet?id='+<%=local.getLocalId()%>;">Peluqueros</button></td>
                   				
                   				<td><button type="button" class="btn btn-primary btn-round btn-sm my-0" 
                   						onclick="window.location.href='EditLocalServlet?id='+<%=local.getLocalId()%>;">Editar</button>
                   				</td>
                   				
                   					<td><button type="button" class="btn <%=local.getIsEnable()?"btn-danger":"btn-success"%> btn-round btn-sm my-0" 
                   						onclick="window.location.href='DeleteLocalServlet?id=<%=local.getLocalId()%>';"><%=local.getIsEnable()?"Borrar":"Recuperar"%></button>
                   				</td>                                     						
                   			</tr>
                   		<% } %>
                   		
                   		<tr class="text-center">
                   			<td ><button type="button" class="btn btn-primary btn-round" onclick="window.location.href='index'">Inicio</button></td>  
                   			<td colspan=9> <button type="button" class="btn btn-success btn-round" onclick="window.location.href='CreateLocalServlet';">Agregar peluquería</button></td>
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
	</script>
</body>
</html>