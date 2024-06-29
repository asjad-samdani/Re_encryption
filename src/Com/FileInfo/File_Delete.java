package Com.FileInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.Connection.*;

/**
 * Servlet implementation class File_Delete
 */
@WebServlet("/File_Delete")
public class File_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement st=null;
    public File_Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String Filename=request.getParameter("name1");
		System.out.println("File Name="+Filename);
		HttpSession session=request.getSession(true);
		//String nm=(String)session.getAttribute("userName");
		String emailid=(String) session.getAttribute("O_Email_ID");
		try 
		{
			con=ConnectionFactory.getInstance().getConnection();
			st=con.createStatement();
			String qs="delete from file_info where Filename='"+Filename+"' and emailid='"+emailid+"'";
				Statement st1=con.createStatement();
				st1.executeUpdate(qs);
				
				
				
				System.out.println("File deleted from file table");
//				out.println("<script type=\"text/javascript\">");  
//				out.println("alert('File Deletion Success');");  
//				out.println("</script>");    
				request.getRequestDispatcher("/Downloads.jsp").include(request, response);
		} 
		catch (Exception e) 
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
