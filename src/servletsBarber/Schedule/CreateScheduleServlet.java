package servletsBarber.Schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.LocalData;
import entities.Role;
import entities.Schedule;
import entities.User;
import logic.Admin;
import logic.LocalAdmin;
import logic.Schedules;
import logic.SignUp;
import utils.Days;

/**
 * Servlet implementation class CreateSchedule
 */
@WebServlet("/CreateScheduleServlet")
public class CreateScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("localsList", LocalAdmin.getAll());
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Barber/Schedules/CreateSchedule.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Schedule newSchedule = new Schedule();
		User user = (User)request.getSession().getAttribute("user");
		String localId = request.getParameter("local");
		String dayOfWeek = request.getParameter("day_of_week");
		String startTime = request.getParameter("start_time");
		String endTime = request.getParameter("end_time");
		
		newSchedule.setLocal(LocalAdmin.getById(Integer.parseInt(localId)));
		newSchedule.setDay_of_week(Days.valueOf(dayOfWeek));
		newSchedule.setBarber(user);
		newSchedule.setStart_time(LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:MM")));
		newSchedule.setEnd_time(LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:MM")));
		
		try {
			String destPage = "WEB-INF/Barber/Schedules/CreateSchedule.jsp";
        	if (!Schedules.isTimeAvailable()){
        		String message = "El horario no está disponible para este peluquero.";
                request.setAttribute("errorMessage", message);
        	}else {
            	int idNewUser = Schedules.add(newSchedule);
            	request.setAttribute("localsList", LocalAdmin.getAll());
            	if (idNewUser != -1) {
            		String message = "El horario se agregó correctamente";
            		request.setAttribute("successMessage", message);
            	} else {
            		String message = "Hubo un error en el registro.";
                    request.setAttribute("errorMessage", message);
            	}
        	}
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }	
	}

}
