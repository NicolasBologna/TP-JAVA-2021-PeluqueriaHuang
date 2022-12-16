package servletsUser.Turn;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ServicesBarber;

/**
 * Servlet implementation class GetBarbersByServicesServlet
 */
@WebServlet("/GetBarbersByServicesServlet")
public class GetBarbersByServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBarbersByServicesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] servicesId = request.getParameterValues("services");
		int idLocal = Integer.parseInt(request.getParameter("idLocal"));
		
		request.setAttribute("servicesId", servicesId);
		request.setAttribute("idLocal", idLocal);
		request.setAttribute("barbersList", ServicesBarber.getBarbersByServices(servicesId,idLocal));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/User/Turn/SelectBarber.jsp");
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
