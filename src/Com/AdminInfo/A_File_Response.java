package Com.AdminInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.Connection.ConnectionFactory;

/**
 * Servlet implementation class A_File_Response
 */
@WebServlet("/A_File_Response")
public class A_File_Response extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public A_File_Response() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	HttpSession session=request.getSession(true);
		    String authorname=(String)session.getAttribute("A_Email_ID");
			String filename=request.getParameter("filename");
			System.out.println("Filename="+filename);
			String user=request.getParameter("user");
			System.out.println("user="+user);
			String ownername=request.getParameter("ownername");
			 Connection con;
			 PreparedStatement pt;
			 int id;
			 String msg="Accept";
			 ResultSet rs;
			 Statement st;
			 Statement st1;
			 Statement st10;
			 Statement st101 = null;
			con=ConnectionFactory.getInstance().getConnection();
			st=(Statement) con.createStatement();
			st1=(Statement) con.createStatement();
			st10=(Statement) con.createStatement();
			st101=(Statement) con.createStatement();
			//st10=(Statement) con.createStatement();
			st.executeUpdate("update filerequest set Status='"+msg+"',RequestAuthorName='"+authorname+"' where Ownername='"+ownername+"' and Filename='"+filename+"' and username='"+user+"'");
			//st1.executeUpdate("update tblaccessnew set Status_U='"+msg+"' where Owner_Email_ID='"+ownername+"' and File_Name='"+filename+"' and User_Email_ID='"+user+"'");
			st10.executeUpdate("update file_info set status_o='"+msg+"' where emailid='"+ownername+"' and Filename='"+filename+"'");
			st101.executeUpdate("update request set Status='"+msg+"' where Ownername='"+ownername+"' and Filename='"+filename+"' and username='"+user+"'");
			
			request.getRequestDispatcher("/Access_File_Request.jsp").include(request, response);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
