package Com.AdminInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.JavaX;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.Connection.ConnectionFactory;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        JavaX.initComponents();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String userName=request.getParameter("email");
		String userPass=request.getParameter("password");
		
		try {
			String queryString = "select * FROM tblaa WHERE A_Email_ID='"+userName+"'and A_Password='"+userPass+"'";
			connection =ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			resultSet = ptmt.executeQuery();
			if(resultSet.next())
			{
				HttpSession session=request.getSession(true);
				session.setAttribute("A_Email_ID",userName);
				 session.setAttribute("A_Name",resultSet.getString("A_Name"));
//				out.println("<script type=\"text/javascript\">");  
//				out.println("alert('Login Success');");  
//				out.println("</script>");    
				 System.out.println(resultSet.getString("A_Name"));
				request.getRequestDispatcher("/AuthorityHomePage.jsp").include(request, response);
				
			}else{
					
				//out.print("<h4 align='center'>Wrong Email ID and Password</h4>");
				RequestDispatcher dispatcher=request.getRequestDispatcher("/admin_signin.jsp");
				dispatcher.include(request, response);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
