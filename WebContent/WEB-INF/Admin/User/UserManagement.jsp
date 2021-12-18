<%@page import="java.util.LinkedList"%>
<%@page import="entities.User"%>
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
	  Administar Usuarios
	</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
	<!--     Fonts and icons     -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
	<!-- CSS Files -->
	<link href="./assets/css/bootstrap.min.css" rel="stylesheet" />
	<link href="./assets/css/now-ui-kit.css?v=1.3.0" rel="stylesheet" />
	
	<%
		User p = (User)session.getAttribute("usuario");
	    	LinkedList<User> lp = (LinkedList<User>)request.getAttribute("lp");
	    
	%>
	
</head>
<body class="bg-dark text-light">
	<div class="container">
		<div class="row">
        	<h1>Usuarios</h1>
           	<div class="col-12 col-sm-12 col-lg-12 shadow">
               	<div class="table-responsive">
                   	<table class="table table-dark pt-2">
                   		<thead>
                   			<tr>
                   				<th>id</th>
                   		    	<th>nombre</th>
                       			<th>apellido</th>
                       			<th>email</th>
                       			<th>Dni</th>
                       			<th>tel</th>
                       			<th>habilitado</th>
                       			<th></th>
                       			<th></th>
                   			</tr>
                   		</thead>
                   		<tbody>
                   		<%
                   			for (User per : lp) {
                   		%>
                   			<tr>
                   				<td><%=per.getUserId()%></td>
                   				<td><%=per.getFirstName()%></td>
                   				<td><%=per.getLastName()%></td>
                   				<td><%=per.getEmail()%></td>
                   				<td><%=per.getDni()%></td>
                   				<td><%=per.getPhone()%></td>
         
                   				<td>
	                   				<input type="checkbox" <%=per.getIsEnable()?"checked":""%> name="checkbox" class="bootstrap-switch" onclick="changeEnable(<%=per.getUserId()%>)"
									    data-on-label="ON"
									    data-off-label="OFF"
									/>
                   				</td>
                   				<td><button type="button" class="btn btn-primary btn-round btn-sm my-0" 
                   						onclick="window.location.href='EditUserServlet?idPersona='+<%=per.getUserId()%>;">Editar</button>
                   				</td>
                   				
                   				<td><button type="button" class="btn <%=per.getIsEnable()?"btn-danger":"btn-success"%> btn-round btn-sm my-0" 
                   						onclick="window.location.href='DeleteUserServlet?id=<%=per.getUserId()%>';"><%=per.getIsEnable()?"Borrar":"Recuperar"%></button>
                   				</td>                   						
                   			</tr>
                   		<% } %>
                   		<tr class="text-center"> 
                   			<td colspan=9> <button type="button" class="btn btn-success btn-round" onclick="window.location.href='CreateUserServlet';">Agregar usuario</button></td>
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