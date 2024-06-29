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
 * Servlet implementation class A_File_Reject
 */
@WebServlet("/A_File_Reject")
public class A_File_Reject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public A_File_Reject() {
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
			 String msg="Reject";
			 ResultSet rs;
			 Statement st;
			con=ConnectionFactory.getInstance().getConnection();
			st=(Statement) con.createStatement();
			st.executeUpdate("update filerequest set Status='"+msg+"',RequestAuthorName='"+authorname+"' where Ownername='"+ownername+"' and Filename='"+filename+"' and username='"+user+"'");
			request.getRequestDispatcher("/AuthorityHomePage.jsp").include(request, response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
