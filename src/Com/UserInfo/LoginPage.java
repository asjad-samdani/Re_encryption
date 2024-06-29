package Com.UserInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.Connection.ConnectionFactory;


/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
    public LoginPage() {
        super();
        
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		PrintWriter pw=response.getWriter();
		session.invalidate();
		pw.println("<script> alert('Logout Successfully');</script>");
		RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
				rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String userName=request.getParameter("email");
		String userPass=request.getParameter("password");
		
		try {

			String queryString01 = "select * FROM tblusername WHERE Email_ID='"+userName+"'";
			connection =ConnectionFactory.getInstance().getConnection();
			PreparedStatement ptmt01 = connection.prepareStatement(queryString01);
			
			ResultSet resultSet01 = ptmt01.executeQuery();
			if(resultSet01.next())
			{
				HttpSession session=request.getSession(true);
				 session.setAttribute("Account_Balances",resultSet01.getString("Cost_Data"));
			}
			
			
			
			String queryString = "select * FROM tbluser WHERE U_Email_ID='"+userName+"'and U_Password='"+userPass+"'";
			connection =ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			resultSet = ptmt.executeQuery();
			if(resultSet.next())
			{
				HttpSession session=request.getSession(true);
				session.setAttribute("U_Email_ID",userName);
				session.setAttribute("U_Role",resultSet.getString("U_Role"));
				 session.setAttribute("U_Name",resultSet.getString("U_Name"));
				request.getRequestDispatcher("/Service_ProvidersHomePage.jsp").include(request, response);
					
			}else{
					
				response.sendRedirect("Signin.jsp?no=1");
							
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
}
