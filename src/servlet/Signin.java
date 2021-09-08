package servlet;

import java.io.IOException;

import java.util.LinkedList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Persona;
import logic.Login;

import utils.Validators.UserValidators;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/signin", "/SIGNIN", "/Signin", "/SignIn", "/signIn" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Signin.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Persona per = new Persona();
		Login ctrl = new Login();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (!UserValidators.validateEmail(email)) {
			String message = "Formato de Email inválido.";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Signin.jsp");
            dispatcher.forward(request, response);
		}
		
		
		
		per.setEmail(email);
		per.setPassword(password);
		
		try {
			per = ctrl.validate(per);
            String destPage = "WEB-INF/Signin.jsp";
            
            if (per != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", per);
                destPage = "/index";
            } else {
                String message = "Invalid email/password";
                request.setAttribute("message", message);
            }
             
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
             
        } catch (Exception ex) {
            throw new ServletException(ex);
        }				
	}
}
