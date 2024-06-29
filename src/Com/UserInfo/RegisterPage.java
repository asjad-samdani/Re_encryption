package Com.UserInfo;

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

import Dao.user;
import Com.Connection.*;

/**
 * Servlet implementation class RegisterPage
 */
@WebServlet("/RegisterPage")
public class RegisterPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
    public RegisterPage() {
        super();
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
		String U_Name, U_Email_ID, U_Password, U_Gender,U_Role,U_Mobile;
		U_Name=request.getParameter("name");
		System.out.println(U_Name);
		U_Email_ID=request.getParameter("email");
		System.out.println(U_Email_ID);
		U_Password=request.getParameter("password");
		System.out.println(U_Password);
		U_Mobile=request.getParameter("Contact");
		System.out.println(U_Mobile);
		U_Gender=request.getParameter("gender");
		System.out.println(U_Gender);
		U_Role=request.getParameter("role");
		System.out.println(U_Role);
		String bod=request.getParameter("bod");
		try {
			
			ResultSet rs=user.chckemail(U_Email_ID);
			
				if(rs.next())
				{
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Email ID Already Exist');");  
					out.println("</script>");  
					response.sendRedirect("U_register.jsp?no=1");
				}
				else
				{
					String payment="1000";
					
					String queryString = "insert into tbluser(U_Name,U_Email_ID,U_Password,U_Gender,U_Role,U_Mobile,Account_Balances,Birth_Date) values(?,?,?,?,?,?,?,?)";
					connection =ConnectionFactory.getInstance().getConnection();
					ptmt = connection.prepareStatement(queryString);
					ptmt.setString(1,U_Name);
					ptmt.setString(2,U_Email_ID);
					ptmt.setString(3,U_Password);
					ptmt.setString(4,U_Gender);
					ptmt.setString(5,U_Role);
					ptmt.setString(6,U_Mobile);
					ptmt.setString(7,payment);
					ptmt.setString(8,bod);
					
					int i = ptmt.executeUpdate();
					
					
					String queryString1 = "insert into tblUserName(Email_ID,Cost_Data) values(?,?)";
					connection =ConnectionFactory.getInstance().getConnection();
					PreparedStatement	ptmt = connection.prepareStatement(queryString1);
					ptmt.setString(1,U_Email_ID);
					ptmt.setString(2,payment);
					ptmt.executeUpdate();
					String Duration_Time="0",Duration_Data="0",Status="0";
					String str01 = "insert into tblsmart_contracts_new(EmailID,Duration_Data,Duration_Time,Status_U) values(?,?,?,?)";
					
					PreparedStatement ptmt01 = connection.prepareStatement(str01);
						ptmt01.setString(1,U_Email_ID);
						ptmt01.setString(2,Duration_Data);
						ptmt01.setString(3,Duration_Time);
						ptmt01.setString(4,Status);
						ptmt01.executeUpdate();
						
						
						String otpcode="0";
						String queryString001 = "insert into userotpcode(Email,OTP_Code) values(?,?)";
						connection =ConnectionFactory.getInstance().getConnection();
						PreparedStatement	ptmt001 = connection.prepareStatement(queryString001);
						ptmt001.setString(1,U_Email_ID);
						ptmt001.setString(2,otpcode);
						ptmt001.executeUpdate();
						
					if(i>0)
					{
						out.println("<script type=\"text/javascript\">");  
						out.println("alert('Register Successfully go to Login Page');");  
						out.println("</script>");    
						request.getRequestDispatcher("Signin.jsp").include(request, response);
					}else
					{					
						ServletContext context=getServletContext();	
						RequestDispatcher dispatcher=context.getRequestDispatcher("/Signin.jsp");
						dispatcher.forward(request, response);
					}
				}
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
	}
public static String userid() {
        
         int aNumber = 0;
         aNumber = (int)((Math.random() * 90000)+10000);
        return String.valueOf(aNumber);
    }
}
