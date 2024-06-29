package Com.AdminInfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Com.algo.AESCoder;

/**
 * Servlet implementation class Download_User
 */
@WebServlet("/Download_User")
public class Download_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download_User() {
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
		// TODO Auto-generated method stub
		ServletOutputStream op = response.getOutputStream();
		String txtfile=request.getParameter("txtfile");
		String dataset=request.getParameter("UpdatedText");
		System.out.println("File download successfully");	
		response.setHeader("Content-Type",
				"application/octet-stream");
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + txtfile + "\"");
		
		op.println(dataset);
	}

}
