package jenkins.training;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

s

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
		description = "Login Servlet",
		urlPatterns = { "/LoginServlet" },
		initParams = {
				@WebInitParam(name = "user", value = "test"),
				@WebInitParam(name = "password", value = "test")
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init() throws ServletException {
		//we can create DB connection resource here and set it to Servlet context
		if(getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/test") &&
				getServletContext().getInitParameter("dbUser").equals("root") &&
				getServletContext().getInitParameter("dbUserPwd").equals("root"))
		getServletContext().setAttribute("DB_Success", "True");
		else throw new ServletException("DB Connection error");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		//get servlet config init params
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		//logging example
		log("User="+user+"::password="+pwd);

		if(userID.equals(user) && password.equals(pwd)){
			response.sendRedirect("LoginSuccess.jsp");
		}else{
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			//PrintWriter out= response.getWriter();
			//out.println("<h1 align=\"center\"><font color=yellow size=\"24\">Either user name or password is wrong.</font></h1>");
			//rd.include(request, response);
			response.sendRedirect("LoginFailure.jsp");
		}
	}
}
