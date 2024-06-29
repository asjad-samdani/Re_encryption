package Com.OwnerInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.JavaX;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.Connection.ConnectionFactory;

/**
 * Servlet implementation class O_LoginPage
 */
@WebServlet("/O_LoginPage")
public class O_LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
    public O_LoginPage() {
        super();
        JavaX.initComponents();
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String userName=request.getParameter("email");
		String userPass=request.getParameter("password");
		System.out.println(userName+"\t"+userPass);
		try {
			
	        String queryString = "select * FROM tblowner WHERE O_Email_ID='"+userName+"'and O_Password='"+userPass+"'";
			connection =ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
			
			resultSet = ptmt.executeQuery();
			if(resultSet.next())
			{
				System.out.println(userName);
				HttpSession session=request.getSession(true);
				session.setAttribute("O_Email_ID",userName);
				 session.setAttribute("O_Name",resultSet.getString("O_Name"));
				 session.setAttribute("O_Mobile",resultSet.getString("O_Mobile"));
				 session.setAttribute("role",resultSet.getString("O_Role"));
				 session.setAttribute("O_Number",resultSet.getString("O_id"));
				request.getRequestDispatcher("/OwnerHomePage.jsp").include(request, response);
				
			}else{
				response.sendRedirect("owner_signin.jsp?no=1");	
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
