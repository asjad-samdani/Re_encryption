package Com.AdminInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Com.Connection.ConnectionFactory;
import Dao.user;

/**
 * Servlet implementation class AdminRegister
 */
@WebServlet("/AdminRegister")
public class AdminRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegister() {
        super();
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String A_Name, A_Email_ID, A_Password, A_Gender,A_Mobile;
		A_Name=request.getParameter("name");
		System.out.println(A_Name);
		A_Email_ID=request.getParameter("email");
		System.out.println(A_Email_ID);
		A_Password=request.getParameter("password");
		System.out.println(A_Password);
		A_Mobile=request.getParameter("Contact");
		System.out.println(A_Mobile);
		A_Gender=request.getParameter("gender");
		System.out.println(A_Gender);
		String role=request.getParameter("role");
		String bod=request.getParameter("bod");
		try {
			
			ResultSet rs=user.A_chckemail(A_Email_ID);
			
				if(rs.next())
				{
//					out.println("<script type=\"text/javascript\">");  
//					out.println("alert('Email ID Already Exist');");  
//					out.println("</script>");  
					//request.getRequestDispatcher("admin_signin.jsp").include(request, response);
					response.sendRedirect("A_register.jsp?no=1");
				}
				else
				{
					
					String queryString = "insert into tblaa(A_Name, A_Email_ID, A_Password, A_Gender,A_Mobile,A_Role,A_BirthDate) values(?,?,?,?,?,?,?)";
					connection =ConnectionFactory.getInstance().getConnection();
					ptmt = connection.prepareStatement(queryString);
					ptmt.setString(1,A_Name);
					ptmt.setString(2,A_Email_ID);
					ptmt.setString(3,A_Password);
					ptmt.setString(4,A_Gender);
					ptmt.setString(5,A_Mobile);
					ptmt.setString(6,role);
					ptmt.setString(7,bod);
					int i = ptmt.executeUpdate();
					if(i>0)
					{
//						out.println("<script type=\"text/javascript\">");  
//						out.println("alert('Register Successfully go to Login Page');");  
//						out.println("</script>");    
						request.getRequestDispatcher("admin_signin.jsp").include(request, response);
					}else
					{					
						ServletContext context=getServletContext();	
						RequestDispatcher dispatcher=context.getRequestDispatcher("/admin_signin.jsp");
						dispatcher.forward(request, response);
					}
				}
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
	}

}
