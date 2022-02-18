package servletsBarber.Schedule;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Schedule;
import logic.LocalAdmin;
import logic.PublicationBarber;
import logic.Schedules;

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
