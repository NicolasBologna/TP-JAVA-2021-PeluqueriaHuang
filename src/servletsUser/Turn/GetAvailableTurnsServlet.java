package servletsUser.Turn;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ServicesBarber;
import logic.Turns;

@WebServlet("/getSchedulesServlet")
public class GetAvailableTurnsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetAvailableTurnsServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	/*	
		String[] servicesId = request.getParameterValues("serviceId");
		int barber_id = Integer.parseInt(request.getParameter("idBarber"));
		int idLocal = Integer.parseInt(request.getParameter("idLocal"));
		LocalTime servicesDuration = ServicesBarber.getTotalDuration(servicesId);
		
		Date[] daysNot = Turns.getDaysNot(servicesDuration, barber_id, idLocal);
		request.setAttribute("schedules");
		request.setAttribute("barbersList", ServicesBarber.getBarbersByServices(servicesId,idLocal));
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/User/Turn/SelectBarber.jsp");
        dispatcher.forward(request, response);

	*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
