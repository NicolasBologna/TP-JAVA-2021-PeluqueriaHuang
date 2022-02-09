package servletsBarber.Schedule;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import entities.Role;
import logic.Schedules;
import logic.Roles;

/**
 * Servlet implementation class ListSchedules
 */
@WebServlet("/ListSchedules")
public class ListSchedules extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListSchedules() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/User/Signin.jsp");
		Role adminRole = Roles.getRoleByName("Admin");
		if (user != null && user.hasRol(adminRole)) {
			request.setAttribute("schedulesList", Schedules.getAllByBarber(user.getUserId()));

			dispatcher = (Schedules.areSchedulesLoaded(user.getUserId())) ? 
					request.getRequestDispatcher("WEB-INF/Barber/Schedules/SchedulesList.jsp") : request.getRequestDispatcher("/CreateScheduleServlet");
		}	
	
		dispatcher.forward(request, response);
	}

}
