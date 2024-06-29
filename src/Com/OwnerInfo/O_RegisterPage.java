package Com.OwnerInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.JavaX;
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
 * Servlet implementation class O_RegisterPage
 */
@WebServlet("/O_RegisterPage")
public class O_RegisterPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public O_RegisterPage() {
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String O_Name, O_Email_ID, O_Password, O_Gender,O_Mobile;
		O_Name=request.getParameter("name");
		System.out.println(O_Name);
		O_Email_ID=request.getParameter("email");
		System.out.println(O_Email_ID);
		O_Password=request.getParameter("password");
		System.out.println(O_Password);
		O_Mobile=request.getParameter("Contact");
		System.out.println(O_Mobile);
		O_Gender=request.getParameter("gender");
		System.out.println(O_Gender);
		
		String role=request.getParameter("role");
		String bod=request.getParameter("bod");
		
		try {
			
			ResultSet rs=user.O_chckemail(O_Email_ID);
			
				if(rs.next())
				{
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Email ID Already Exist');");  
					out.println("</script>");  
					response.sendRedirect("O_register.jsp?no=1");//.include(request, response);
				}
				else
				{
					String O_Status="Pending";
					String O_Number="0";
					connection =ConnectionFactory.getInstance().getConnection();
					String queryString = "insert into tblowner(O_Name, O_Email_ID, O_Password, O_Gender,O_Mobile,O_Role,Birth_Date) values(?,?,?,?,?,?,?)";
					
					ptmt = connection.prepareStatement(queryString);
					ptmt.setString(1,O_Name);
					ptmt.setString(2,O_Email_ID);
					ptmt.setString(3,O_Password);
					ptmt.setString(4,O_Gender);
					ptmt.setString(5,O_Mobile);
					ptmt.setString(6,role);
					ptmt.setString(7,bod);
					
//					ptmt.setString(10,O_Email_IDhash);
					int i = ptmt.executeUpdate();
					String amount="0";
					String str = "insert into tblOwnerName(Email_ID,Cost_Data) values(?,?)";
					
				PreparedStatement ptmt1 = connection.prepareStatement(str);
					ptmt1.setString(1,O_Email_ID);
					ptmt1.setString(2,amount);
					ptmt1.executeUpdate();		
					if(i>0)
					{
						out.println("<script type=\"text/javascript\">");  
						out.println("alert('Register Successfully go to Login Page');");  
						out.println("</script>");    
						request.getRequestDispatcher("owner_signin.jsp").include(request, response);
					}else
					{					
						ServletContext context=getServletContext();	
						RequestDispatcher dispatcher=context.getRequestDispatcher("/owner_signin.jsp");
						dispatcher.forward(request, response);
					}
				}
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
	}

}
