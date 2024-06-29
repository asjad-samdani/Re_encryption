
package Com.Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Dbconn {
	public static int blockchain1msg=0,blockchain2msg=0,blockchain3msg=0,blockchain4msg=0;
	public static String cid=null,c_Name=null,cp_Name=null,eid=null,CVoter=null,BlockData=null;
	public static String PrevHash1=null,PrevHash2=null,PrevHash3=null,PrevHash4=null;
	
    public Dbconn() throws SQLException {
              
    }
      
   
	public static void main(String args[]) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        System.out.println(sdf.format(currentDate));

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

//        // manipulate date
//        c.add(Calendar.YEAR, 1);
//        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.HOUR, 1);
        c.add(Calendar.MINUTE, 1);
        c.add(Calendar.SECOND, 1);

        SimpleDateFormat sdf01 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();

        System.out.println(sdf01.format(currentDatePlusOne));
    }   
}
