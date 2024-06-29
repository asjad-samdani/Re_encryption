package Com.FileInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Blockchain.ptop;
import Com.Connection.ConnectionFactory;
import Com.algo.EncryptDecrypt;

/**
 * Servlet implementation class Re_Encrytion_Process
 */
@WebServlet("/Re_Encrytion_Process")
public class Re_Encrytion_Process extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Re_Encrytion_Process() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public String keyData() 
	{
	
		StringBuilder ss = new StringBuilder();
		Random r = new Random();
		char ch;

		for (int i = 0; i < 5; i++) 
		{
			ch = (char) (Math.floor(26 * r.nextDouble() + 65));
			ss.append(ch);
		}

		return ss.toString();

	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter pw = response.getWriter();
		String updatedText = request.getParameter("UpdatedText");
		String owneremail = request.getParameter("txtownername");
		String filename = request.getParameter("txtfile");
		System.out.println(filename);
		String service_providers_email =(String)session.getAttribute("U_Email_ID");
		try {

			String ContentType="";
			String[] exe=filename.split("\\.");
			System.out.println(exe.length);
			if(exe[1].equals("txt"))
			{
				ContentType="text/plain";
			}

			String key=keyData();
				Connection conn = ConnectionFactory.getInstance()
						.getConnection();
				Statement st = conn.createStatement();
				String EncryptedData = EncryptDecrypt.encrypt(key, updatedText);
				String sql12 = "insert into new_file_info(service_providers,Filename,P_Key,emailid,filedata,ContentType) values(?,?,?,?,?,?)";
				PreparedStatement pstmt12 = conn
						.prepareStatement(sql12);
				pstmt12.setString(1, service_providers_email);
				pstmt12.setString(2, filename);
				pstmt12.setString(3,key);
				pstmt12.setString(4,owneremail );
				pstmt12.setString(5, EncryptedData);
				pstmt12.setString(6, ContentType);
				pstmt12.executeUpdate();
				String data=filename+"#"+owneremail+"#"+service_providers_email;
				ptop.ptopverify(4,data);
				pw.println("<html><script>alert('File Update Successfully...');</script><body>");
				pw.println("");
				pw.println("</body></html>");

			
		} catch (Exception e) {
			System.out.println(e);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/File_Access.jsp");
		rd.include(request, response);
	}

}
