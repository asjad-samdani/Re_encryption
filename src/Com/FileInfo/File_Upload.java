package Com.FileInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import Com.Connection.*;
import Com.algo.AESCoder;

/**
 * Servlet implementation class File_Upload
 */
@WebServlet("/File_Upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class File_Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public File_Upload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String ContentType, data = null, EncryptedFile = null;
	private static Logger logger = Logger.getAnonymousLogger();
	public static long size = 0;
	public static String tokendb = "a";
	public static Connection conns;

	String getFileName(Part filePart) {

		String partHeader = filePart.getHeader("content-disposition");
		logger.info("Part Header = " + partHeader);

		for (String cd : filePart.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1)
						.substring(fileName.lastIndexOf('\\') + 1);
			}
		}
		return null;

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public String keyData() {
		StringBuilder ss = new StringBuilder();
		Random r = new Random();
		char ch;

		for (int i = 0; i < 5; i++) {
			ch = (char) (Math.floor(26 * r.nextDouble() + 65));
			ss.append(ch);
		}

		return ss.toString();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InputStream inputStream = null;

		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("O_Name");
		String emailid = (String) session.getAttribute("O_Email_ID");
		String O_number = request.getParameter("O_number");
		Part filePart = request.getPart("file");
		ContentType = filePart.getContentType();

		String fileName = getFileName(filePart);
		session.setAttribute("FILEName", fileName);
		String Name, Position, Department, Branch, Gender;
		Name = request.getParameter("attribute_1");
		Position = request.getParameter("attribute_2");
		Department = request.getParameter("attribute_3");
		Branch = request.getParameter("attribute_4");
		Gender = request.getParameter("attribute_5");
		String role = request.getParameter("role");

		System.out
				.println("-----------------------------------------------------------------");
		System.out.println("ContentType=>" + ContentType);
		int flag = 1;
		System.out.println("Size:-" + filePart.getSize());
		System.out.print("FileName:-" + fileName);
		System.out.println("\nUserName:-" + username);
		size = filePart.getSize();
		if (filePart != null) {
			inputStream = filePart.getInputStream();
		}
		Connection conn;
		try {

			conn = ConnectionFactory.getInstance().getConnection();

			// check side databases
			Statement stavailable = conn.createStatement();
			ResultSet rsavailable = stavailable
					.executeQuery("select * from file_info where filename='"
							+ fileName + "' and emailid='" + emailid + "'");
			if (rsavailable.next()) {
				PrintWriter out1 = null;
				response.setContentType("text/html;charset=UTF-8");
				out1 = response.getWriter();
				out1.println("<html><script>alert('File Already Exists');</script><body>");
				out1.println("");
				out1.println("</body></html>");
				RequestDispatcher rd = request
						.getRequestDispatcher("/File_Upload.jsp");
				rd.include(request, response);
			} else {
				switch (ContentType) {
				case "text/plain":
					System.out.println("Text");
					BufferedReader br = null;
					StringBuilder sb = new StringBuilder();
					String line;
					try {
						// get file path and read file
						br = new BufferedReader(new InputStreamReader(
								inputStream));
						// file line by line read and check null
						while ((line = br.readLine()) != null) {
							// append method concatenates the string
							// representation of any other type of data
							sb.append(line + "\n");
							data = sb.toString();
						}
						br.close();
						// System.out.println("plain text=>"+data);

					} catch (IOException e) {
						System.out.println(e);
					}
					break;
				case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
					String data1 = "";
					XWPFDocument docx = new XWPFDocument(inputStream);
					XWPFWordExtractor wx = new XWPFWordExtractor(docx);
					data = wx.getText();
					System.out.println("text = " + data);
					break;
				case "application/pdf":
					System.out.println("Pdf");

					try {
						PdfReader pdfReader = new PdfReader(inputStream);
						// Get the number of pages in pdf.
						int pages = pdfReader.getNumberOfPages();
						// Iterate the pdf through pages.
						for (int i = 1; i <= pages; i++) {
							// Extract the page content using PdfTextExtractor.
							data = PdfTextExtractor.getTextFromPage(pdfReader,
									i);
							// Print the page content on console.

						}
						System.out.println(data);

						pdfReader.close();

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				default:
					flag = 2;
					try {
						String finalkey =Name+Position+Department+Branch+Gender;// keyData();
						byte[] ar = finalkey.getBytes();

						byte[] key = AESCoder.getRawKey(ar);
						String sql12 = "insert into file_info(Username,Filename,P_Key,emailid,filedata,keydata,role,ContentType) values(?,?,?,?,?,?,?,?)";
						PreparedStatement pstmt12 = conn
								.prepareStatement(sql12);
						pstmt12.setString(1, username);
						pstmt12.setString(2, fileName);
						// pstmt12.setBytes(3, key);
						pstmt12.setString(3, finalkey);
						pstmt12.setString(4, emailid);
						pstmt12.setBlob(5, inputStream);
						pstmt12.setBytes(6, key);
						pstmt12.setString(7, role);
						pstmt12.setString(8, ContentType);
						pstmt12.executeUpdate();
						

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}// end switch case
				if (flag == 1) {

					String finalkey = keyData();
					byte[] ar = finalkey.getBytes();
String status_o="0";
					byte[] key = AESCoder.getRawKey(ar);
					byte[] plain = data.getBytes();
					byte[] cip = AESCoder.encrypt(key, plain);
					String sql12 = "insert into file_info(Username,Filename,P_Key,emailid,filedata,keydata,role,status_o,ContentType) values(?,?,?,?,?,?, ?,?,?)";
					PreparedStatement pstmt12 = conn.prepareStatement(sql12);
					pstmt12.setString(1, username);
					pstmt12.setString(2, fileName);
					// pstmt12.setBytes(3, key);
					pstmt12.setString(3, finalkey);
					pstmt12.setString(4, emailid);
					pstmt12.setBytes(5, cip);
					pstmt12.setBytes(6, key);
					pstmt12.setString(7, role);
					pstmt12.setString(8,status_o);
					pstmt12.setString(9, ContentType);
					
					pstmt12.executeUpdate();

					pw.println("<html><script>alert('File Upload Successfully...');</script><body>");
					pw.println("");
					pw.println("</body></html>");
					RequestDispatcher rd = request
							.getRequestDispatcher("/File_Upload.jsp");
					rd.include(request, response);
				}
			}// end else

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
