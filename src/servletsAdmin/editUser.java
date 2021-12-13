package servletsAdmin;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Role;
import entities.User;
import logic.Admin;
import logic.SignUp;

/**
 * Servlet implementation class editUser
 */
@WebServlet("/editUser")
public class editUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("idPersona");
		int idUser = Integer.parseInt(id);
			
		request.setAttribute("user", Admin.getUserById(idUser));
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/User/EditUser.jsp");
        dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		User theUser = new User();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String email = request.getParameter("email");
		String firsName = request.getParameter("nombre");
		String lastName = request.getParameter("apellido");
		String dni =  request.getParameter("dni");
		String phone = request.getParameter("telefono");		
		String isEnable = request.getParameter("isEnable");
		String[] roles = request.getParameterValues("roles");
		
		LinkedList<Role> rolesToAppend = Admin.getRolesByIds(roles);
		
		theUser.setUserId(id);
		theUser.setFirstName(firsName);
		theUser.setLastName(lastName);
		theUser.setDni(Integer.parseInt(dni));
		theUser.setPhone(phone);
		theUser.setEmail(email);
		theUser.setIsEnable(isEnable.equals("on") ? true : false);
		theUser.setRoles(rolesToAppend);
		
		
		try {
			String destPage = "WEB-INF/Admin/User/UserManagement.jsp";
			
		
			request.setAttribute("roleList", Admin.getAllRoles());
			Boolean userUpdate = Admin.updateUser(theUser);
			request.setAttribute("lp", Admin.getAllUsers());
        	if (userUpdate== false) {
        		
        		String message = "Hubo un error en la actualizacion.";
                request.setAttribute("errorMessage", message);
        	}
       
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }				
	}
	

}
