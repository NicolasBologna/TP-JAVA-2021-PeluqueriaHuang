<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.LinkedList"%>
<%@page import="entities.Local"%>
<!DOCTYPE html>
<html>
<%
	LinkedList<Local> localsList = (LinkedList<Local>)request.getAttribute("localsList");
%>

	<head>
	<meta charset="ISO-8859-1">
	<title>Book Turn</title>
	</head>
	<body>
	
	<main>
		<div class="container">
			<h1 class="text-center pt-2">Solicitar turno</h1>
			<form action="BookTurnServlet" method="post" class="shadow p-5">
				<div class="form-group mb-4">
					<label for="local">Peluquería</label> 
					<select name="local" class="browser-default custom-select">
						  <%
						   for (Local local: localsList) {
						   %>
						<option value="<%=local.getLocalId()%>">
							<%=local.getName()%>
						</option>
						  <% } %>
					</select>	       		
				   </div>
				   
				   
			</form>
		</div>
</main>
	
	
	
	</body>
	</html>