package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;
import logic.*;

/**
 * Servlet implementation class Signup
 */
@WebServlet({ "/Signup", "/signup", "/SignUp" })
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User)request.getSession().getAttribute("user");
		RequestDispatcher dispatcher = (user != null) ? request.getRequestDispatcher("index") : request.getRequestDispatcher("WEB-INF/User/Signup.jsp");
	
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
	
		User newUser = new User();
		SignUp ctrl = new SignUp();
		
		String firsName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String dni =  request.getParameter("dni");
		String phone = request.getParameter("phone");		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		newUser.setFirstName(firsName);
		newUser.setLastName(lastName);
		if (dni != "") newUser.setDni(Integer.parseInt(dni));
		newUser.setPhone(phone);
		newUser.setEmail(email);
		newUser.setPassword(password);
		
		try {
			
			String destPage = "WEB-INF/User/Signup.jsp";
        	if (ctrl.checkEmailAvailability(newUser.getEmail())) {
        		String message = "El Email ya está en uso";
                request.setAttribute("message", message);
        	}else {
            	int idNewUser = ctrl.addNewUser(newUser);
            	if (idNewUser != -1) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", newUser);
                    destPage = "/index";
            	} else {
            		String message = "Hubo un error en el registro.";
                    request.setAttribute("message", message);
            	}
        	}
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }				
	}

}
