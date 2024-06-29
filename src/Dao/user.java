package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Com.Connection.*;

public class user 
{
	static Connection con;
	static PreparedStatement pt;
	 public static int id;
	 static ResultSet rs;
	 static Statement st;
	 static Statement st1;
	 
	 public static ResultSet chckemail(String email) 
	 {
		try 
		{
			con=ConnectionFactory.getInstance().getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from tblUser where U_Email_ID='"+email+"'");
		}
		catch (Exception e) 
		{
			
		} 
		return rs;
	}
	 
	 public static ResultSet O_chckemail(String email) 
	 {
		try 
		{
			con=ConnectionFactory.getInstance().getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from tblowner where O_Email_ID='"+email+"'");
		}
		catch (Exception e) 
		{
			
		} 
		return rs;
	}
	 public static ResultSet A_chckemail(String email) 
	 {
		try 
		{
			con=ConnectionFactory.getInstance().getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from tblaa where A_Email_ID='"+email+"'");
		}
		catch (Exception e) 
		{
			
		} 
		return rs;
	}
	 public static int request(String Filename,String username,String ownername,String Status,String keymsg,String daycon)
		{
			int i=0;
			try
			{			
				
				con=ConnectionFactory.getInstance().getConnection();
				st=(Statement) con.createStatement();
				Statement stavailable = con.createStatement();
				ResultSet rsavailable = stavailable
						.executeQuery("select * from request where username='"+username+"' and Filename='"+Filename+"' and Ownername='"+ownername+"' and RequestName='"+keymsg+"' and Duration_Data='"+daycon+"'");
			if(rsavailable.next()) 
			{
				i=2;
			}
			else
			{
				 i=1;
			 st.executeUpdate("insert into request (username, Filename, Ownername, Status,Status_Msg,RequestName,Duration_Data,SendRequest,ShareSendRequest) values('"+username+"','"+Filename+"','"+ownername+"','"+Status+"','Status_Msg','"+keymsg+"','"+daycon+"','0','0')");	
			}
			
			
			if(i==2)
			{
				String msg="Status_Msg";
				st.executeUpdate("update  request set Status='"+Status+"',Status_Msg='"+msg+"',RequestName='"+keymsg+"',Duration_Data='"+daycon+"' where username='"+username+"'and  Filename='"+Filename+"' and Ownername='"+ownername+"'");	
					
			}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			return i;
		}
	 
	 public static ResultSet getreqcount(String username) throws SQLException
		{
			String q="select count(*) from request where Ownername='"+username+"' and Status='Pending'";
			con=ConnectionFactory.getInstance().getConnection();
			st=con.createStatement();
			rs=st.executeQuery(q);
			return rs;
		}
		
	 public static ResultSet getfiles(String filename,String ownername) throws SQLException
		{
//			String q="select * from attr_share where filename='"+filename+"'";
//			con=ConnectionFactory.getInstance().getConnection();
//			st=con.createStatement();
//			rs=st.executeQuery(q);
		 String q="select * from file_info where filename='"+filename+"' and emailid='"+ownername+"'";
			con=ConnectionFactory.getInstance().getConnection();
			st=con.createStatement();
			rs=st.executeQuery(q);
			return rs;
		}

	 
	 public static ResultSet getfiles01(String filename,String ownername) throws SQLException
		{
			String q="select * from attr_share where filename='"+filename+"' and emailid='"+ownername+"'";
			con=ConnectionFactory.getInstance().getConnection();
			st=con.createStatement();
			rs=st.executeQuery(q);
		
			return rs;
		}

	 public static int response(String Filename,String username,String ownername,String key)
		{
			int i=0;
			try
			{			
				con=ConnectionFactory.getInstance().getConnection();
				st=(Statement) con.createStatement();
				i=st.executeUpdate("insert into response (user, Filename, FileKey, sender) values ('"+username+"','"+Filename+"','"+key+"','"+ownername+"')");	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return i;
		}
	 
	 public static int updatestatus(String username,String filename,String msg,String user,String msg1)
		{
			int i=0;
			try
			{			
				con=ConnectionFactory.getInstance().getConnection();
				st=(Statement) con.createStatement();
				st1=(Statement) con.createStatement();
				String m="Request Accept";
				 st1.executeUpdate("update filerequest set Status='"+m+"' where Ownername='"+username+"' and Filename='"+filename+"' and username='"+user+"'");	
				 i=st.executeUpdate("update request set Status='"+msg+"',Status_Msg='"+msg+"' where Ownername='"+username+"' and Filename='"+filename+"' and username='"+user+"' and RequestName='"+msg1+"'");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return i;
		}
}
