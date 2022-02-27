package servletsUser.Turn;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Role;
import entities.Turn;
import entities.User;
import logic.Admin;
import logic.ServicesBarber;
import logic.SignUp;

/**
 * Servlet implementation class ConfirmTurnServlet
 */
@WebServlet("/ConfirmTurnServlet")
public class ConfirmTurnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmTurnServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int clientId = Integer.parseInt(request.getParameter("clientId"));
		int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));

		String hour = request.getParameter("turn-hour");
		
		String[] servicesId = request.getParameterValues("services");
		LocalTime servicesDuration = ServicesBarber.getTotalDuration(servicesId);
		Turn turn = new Turn();
		
	
	}}


