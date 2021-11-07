package servletsAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;
import logic.Admin;
import logic.SignUp;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("roleList", Admin.getAllRoles());
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/User/CreateUser.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User newUser = new User();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firsName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String dni =  request.getParameter("dni");
		String phone = request.getParameter("phone");		
		String isEnable = request.getParameter("isEnable");
		
		newUser.setFirstName(firsName);
		newUser.setLastName(lastName);
		if (dni != "") newUser.setDni(Integer.parseInt(dni));
		newUser.setPhone(phone);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setIsEnable(isEnable.equals("on") ? true : false);
		
		try {
			
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }				
	}

}
