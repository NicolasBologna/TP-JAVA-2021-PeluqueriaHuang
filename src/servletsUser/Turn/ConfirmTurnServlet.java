package servletsUser.Turn;

import java.io.IOException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import entities.Role;
import entities.Schedule;
import entities.Turn;
import entities.User;
import entities.Service;
import logic.Admin;
import logic.LocalAdmin;
import logic.ServicesBarber;
import logic.SignUp;
import logic.Turns;
import logic.Schedules;

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
		
		String hour = request.getParameter("turn-hour");
		String turnDate = request.getParameter("turn-date");
		
		int localId = Integer.parseInt(request.getParameter("idLocal"));
		int barberId = Integer.parseInt(request.getParameter("barberId"));
		
		String[] servicesId = request.getParameterValues("services");
		
		User client = (User)request.getSession().getAttribute("user");
		int clientId = client.getUserId();
		
		LocalTime servicesDuration = ServicesBarber.getTotalDuration(servicesId);
		
		LinkedList<Service> services = ServicesBarber.getServicesById(servicesId);
		
		Schedule schedule= Schedules.getByLocalBarber(localId,barberId);
		
		
		
		Turn newTurn = new Turn();
		newTurn.setClient(Admin.getUserById(clientId));
		newTurn.setSchedule(schedule);
		newTurn.setServices(services);
		newTurn.setDuration(Time.valueOf(servicesDuration));
		newTurn.setHour(LocalTime.parse(hour));
		newTurn.setDate(LocalDate.parse(turnDate));
		
		try {
			String destPage = "WEB-INF/User/Turn/SavedTurn.jsp";
			
			int idNewTurn = Turns.add(newTurn);
            	
			if (idNewTurn != -1) {
					request.setAttribute("new-turn", newTurn);
            		String message = "Su turno fue registrado";
            		request.setAttribute("successMessage", message);
            	} else {
            		String message = "Hubo un error en la solicitud del turno.";
                    request.setAttribute("errorMessage", message);
            	}
        	
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }				
	}}


