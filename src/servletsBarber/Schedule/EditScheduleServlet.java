package servletsBarber.Schedule;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ScheduleData;
import entities.Schedule;
import entities.User;
import logic.LocalAdmin;
import logic.PublicationBarber;
import logic.Schedules;
import utils.Days;

/**
 * Servlet implementation class EditScheduleServlet
 */
@WebServlet("/EditScheduleServlet")
public class EditScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idSchedule");
		int idSchedule = Integer.parseInt(id);
		
		request.setAttribute("schedule", Schedules.getById(idSchedule));
		request.setAttribute("localsList", LocalAdmin.getAll());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Barber/Schedules/EditSchedule.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scheduleId = Integer.parseInt(request.getParameter("schedule_id"));
		Schedule editedSchedule = Schedules.getById(scheduleId);

		String localId = request.getParameter("local");
		String dayOfWeek = request.getParameter("day_of_week");
		String startTime = request.getParameter("start_time");
		String endTime = request.getParameter("end_time");
		
		editedSchedule.setLocal(LocalAdmin.getById(Integer.parseInt(localId)));
		editedSchedule.setDay_of_week(Days.valueOf(dayOfWeek));
		editedSchedule.setStart_time(LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm")));
		editedSchedule.setEnd_time(LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm")));
		
		try {
			String destPage = "WEB-INF/Barber/Schedules/SchedulesList.jsp";
        	if (!Schedules.isTimeAvailable()){
        		String message = "El horario no está disponible para este peluquero.";
                request.setAttribute("errorMessage", message);
        	}else {
            	boolean isUpdateSuccesful = Schedules.update(editedSchedule);
            	request.setAttribute("localsList", LocalAdmin.getAll());
            	if (isUpdateSuccesful) {
            		String message = "El horario se modificó correctamente";
            		request.setAttribute("successMessage", message);
            	} else {
            		String message = "Hubo un error en la actualización del horario.";
                    request.setAttribute("errorMessage", message);
            	}
        		User user = (User)request.getSession().getAttribute("user");
    			LinkedList<Schedule> barberSchedules = Schedules.getAllByBarber(user.getUserId());
    			Collections.sort(barberSchedules);
    			request.setAttribute("schedulesList",barberSchedules);
        	}
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }	
	}

}
