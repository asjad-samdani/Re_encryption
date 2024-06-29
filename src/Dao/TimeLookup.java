package Dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Com.Connection.ConnectionFactory;

public class TimeLookup {

	public static int timeflag=0,dateflag=0;
	public static int timer5min(String emailid, String O_File_Name,String Duration_Data) {
		try {
System.out.println("EmailID=>"+emailid+"\t Owner File=>"+O_File_Name+"\tDuration=>"+Duration_Data);
			String StartTime = "", dbdate = "";
			Connection connection = ConnectionFactory.getInstance()
					.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblsmart_contracts_new  where EmailID='"
							+ emailid
							+ "'and O_File_Name='"
							+ O_File_Name
							+ "' and Duration_Data='" + Duration_Data + "' order by S_ID desc");
			if (resultset.next()) {
				StartTime = resultset.getString("CurrentTime_U");
				dbdate = resultset.getString("CurrentDate_U");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(StartTime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 5);
				dNow = cal.getTime();

				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				System.out.println("Date working" + newdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					System.out.println("Date working");
					String startTimeStr = currenttime; // Getting start time

					String endTimeStr = StartTime; // Getting end time
					System.out.println("startTimeStr=>" + startTimeStr
							+ "\t endTimeStr=>" + StartTime);
					compareTimeJava8(startTimeStr, endTimeStr, emailid,
							O_File_Name, Duration_Data);

				}// if end // date working end
				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblsmart_contracts_new set Status_U='Expiry' where EmailID='"
							+ emailid
							+ "' and O_File_Name='"
							+ O_File_Name
							+ "'and Duration_Data='" + Duration_Data + "'";
					st001.executeUpdate(queryString001);
					timeflag=1;
				}
			} else {

			}
		} catch (ParseException | SQLException e) {
			//
			e.printStackTrace();
		} // Instantiate a Date object
		return timeflag;
	}
	public static int timer10min(String emailid, String O_File_Name,String Duration_Data) {
		try {
System.out.println("EmailID=>"+emailid+"\t Owner File=>"+O_File_Name+"\tDuration=>"+Duration_Data);
			String StartTime = "", dbdate = "";
			Connection connection = ConnectionFactory.getInstance()
					.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblsmart_contracts_new  where EmailID='"
							+ emailid
							+ "'and O_File_Name='"
							+ O_File_Name
							+ "' and Duration_Data='" + Duration_Data + "' order by S_ID desc");
			if (resultset.next()) {
				StartTime = resultset.getString("CurrentTime_U");
				dbdate = resultset.getString("CurrentDate_U");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(StartTime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 10);
				dNow = cal.getTime();

				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				System.out.println("Date working" + newdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					System.out.println("Date working");
					String startTimeStr = currenttime; // Getting start time

					String endTimeStr = StartTime; // Getting end time
					System.out.println("startTimeStr=>" + startTimeStr
							+ "\t endTimeStr=>" + StartTime);
					compareTimeJava8(startTimeStr, endTimeStr, emailid,
							O_File_Name, Duration_Data);

				}// if end // date working end
				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblsmart_contracts_new set Status_U='Expiry' where EmailID='"
							+ emailid
							+ "' and O_File_Name='"
							+ O_File_Name
							+ "'and Duration_Data='" + Duration_Data + "'";
					st001.executeUpdate(queryString001);
					timeflag=1;
				}
			} else {

			}
		} catch (ParseException | SQLException e) {
			//
			e.printStackTrace();
		} // Instantiate a Date object
		return timeflag;
	}
	
	
	
	
	public static int timer15min(String emailid, String O_File_Name,
			String Duration_Data) {
		try {
System.out.println("EmailID=>"+emailid+"\t Owner File=>"+O_File_Name+"\tDuration=>"+Duration_Data);
			String StartTime = "", dbdate = "";
			Connection connection = ConnectionFactory.getInstance()
					.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblsmart_contracts_new  where EmailID='"
							+ emailid
							+ "'and O_File_Name='"
							+ O_File_Name
							+ "' and Duration_Data='" + Duration_Data + "' order by S_ID desc");
			if (resultset.next()) {
				StartTime = resultset.getString("CurrentTime_U");
				dbdate = resultset.getString("CurrentDate_U");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(StartTime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 15);
				dNow = cal.getTime();

				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				System.out.println("Current Date working=>" + newdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					
					String startTimeStr = currenttime; // Getting start time

					String endTimeStr = StartTime; // Getting end time
					System.out.println("Start Time=>" + startTimeStr
							+ "\t End Time=>" + StartTime);
					compareTimeJava8(startTimeStr, endTimeStr, emailid,
							O_File_Name, Duration_Data);

				}// if end // date working end
				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblsmart_contracts_new set Status_U='Expiry' where EmailID='"
							+ emailid
							+ "' and O_File_Name='"
							+ O_File_Name
							+ "'and Duration_Data='" + Duration_Data + "'";
					st001.executeUpdate(queryString001);
					timeflag=1;
				}
			} else {

			}
		} catch (ParseException | SQLException e) {
			//
			e.printStackTrace();
		} // Instantiate a Date object
		return timeflag;
	}
	public static int timer20min(String emailid, String O_File_Name,
			String Duration_Data) {
		try {
System.out.println("EmailID=>"+emailid+"\t Owner File=>"+O_File_Name+"\tDuration=>"+Duration_Data);
			String StartTime = "", dbdate = "";
			Connection connection = ConnectionFactory.getInstance()
					.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblsmart_contracts_new  where EmailID='"
							+ emailid
							+ "'and O_File_Name='"
							+ O_File_Name
							+ "' and Duration_Data='" + Duration_Data + "' order by S_ID desc");
			if (resultset.next()) {
				StartTime = resultset.getString("CurrentTime_U");
				dbdate = resultset.getString("CurrentDate_U");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(StartTime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 20);
				dNow = cal.getTime();

				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				System.out.println("Date working" + newdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					System.out.println("Date working");
					String startTimeStr = currenttime; // Getting start time

					String endTimeStr = StartTime; // Getting end time
					System.out.println("startTimeStr=>" + startTimeStr
							+ "\t endTimeStr=>" + StartTime);
					compareTimeJava8(startTimeStr, endTimeStr, emailid,
							O_File_Name, Duration_Data);

				}// if end // date working end
				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblsmart_contracts_new set Status_U='Expiry' where EmailID='"
							+ emailid
							+ "' and O_File_Name='"
							+ O_File_Name
							+ "'and Duration_Data='" + Duration_Data + "'";
					st001.executeUpdate(queryString001);
					timeflag=1;
				}
			} else {

			}
		} catch (ParseException | SQLException e) {
			//
			e.printStackTrace();
		} // Instantiate a Date object
		return timeflag;
	}
	public static int timer30min(String emailid, String O_File_Name,
			String Duration_Data) {
		try {
			// int flag=0;
			String StartTime = "", dbdate = "";
			Connection connection = ConnectionFactory.getInstance()
					.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("select * from tblsmart_contracts_new  where EmailID='"
							+ emailid
							+ "'and O_File_Name='"
							+ O_File_Name
							+ "' and Duration_Data='" + Duration_Data + "' order by S_ID desc");
			if (resultset.next()) {
				StartTime = resultset.getString("CurrentTime_U");
				dbdate = resultset.getString("CurrentDate_U");

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
				Date dNow;

				dNow = sdf.parse(StartTime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 30);
				dNow = cal.getTime();
				Date currentdate = new Date();
				String newdate = sdfnew.format(currentdate);
				if (newdate.compareTo(dbdate) == 0) {
					Date d = new Date();
					String currenttime = sdf.format(d);
					System.out.println("Expiry");
					String startTimeStr = currenttime; // Getting start time

					String endTimeStr = StartTime; // Getting end time
					System.out.println("startTimeStr=>" + startTimeStr
							+ "\t endTimeStr=>" + StartTime);
					// String startTimeStr ="09" + ":" + "59" + ":"+ "30";
					// String endTimeStr = "09" + ":" + "31" + ":"+ "30";

					compareTimeJava8(startTimeStr, endTimeStr, emailid,
							O_File_Name, Duration_Data);

				}// if end

				else {
					System.out.println("Date Expiry");
					Statement st001 = connection.createStatement();
					String queryString001 = "update tblsmart_contracts_new set Status_U='Expiry' where EmailID='"
							+ emailid
							+ "' and O_File_Name='"
							+ O_File_Name
							+ "' and Duration_Data='" + Duration_Data + "'";
					st001.executeUpdate(queryString001);
					timeflag=1;

				}
			} else {

			}
		} catch (ParseException | SQLException e) {
			//
			e.printStackTrace();
		}
		return timeflag;
	}

	
	
	public static void compareTimeJava8(String startTimeStr, String endTimeStr,
			String emailid, String O_File_Name, String Duration_Data) {

		LocalDate today = LocalDate.now();

		String startTimeStrT = today + " " + startTimeStr;
		String endTimeStrT = today + " " + endTimeStr;

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");

		try {
			Connection connection = ConnectionFactory.getInstance()
					.getConnection();

			LocalDateTime startTime = LocalDateTime.parse(startTimeStrT,
					formatter);
			// System.out.println(startTime);
			LocalDateTime endTime = LocalDateTime.parse(endTimeStrT, formatter);

			Duration d = Duration.between(startTime, endTime);
			long minutes = TimeUnit.SECONDS.toMinutes(d.getSeconds());
			System.out.println("Second" + d.getSeconds() + "\tMinutes"
					+ minutes);
			if (d.getSeconds() == 0) {
				Statement st001 = connection.createStatement();
				String queryString001 = "update tblsmart_contracts_new set Status_U='Expiry' where EmailID='"
						+ emailid
						+ "' and O_File_Name='"
						+ O_File_Name
						+ "' and Duration_Data='" + Duration_Data + "'";
				st001.executeUpdate(queryString001);
				System.out.println("Both Start time and End Time are equal");
				timeflag=1;
			} else if (d.getSeconds() > 0) {
				System.out.println("Start time is less than end time");
				timeflag=0;
			} else {
				Statement st001 = connection.createStatement();
				String queryString001 = "update tblsmart_contracts_new set Status_U='Expiry' where EmailID='"
						+ emailid
						+ "' and O_File_Name='"
						+ O_File_Name
						+ "' and Duration_Data='" + Duration_Data + "'";
				st001.executeUpdate(queryString001);
				System.out.println("Start time is greater than end time");
				timeflag=1;
			}

		} catch (DateTimeParseException | SQLException e) {
			System.out.println("Invalid Input" + e.getMessage());

		}

	}

	public static void main(String args[]) {

		String start = "2019-06-24";
		String end = "2019-06-24";

		// Parsing the date
		LocalDate dateBefore = LocalDate.parse(start);
		LocalDate dateAfter = LocalDate.parse(end);

		// calculating number of days in between
		long noOfDaysBetween = (ChronoUnit.DAYS.between(dateBefore, dateAfter)) + 1;

		// displaying the number of days
		System.out.println(noOfDaysBetween);
		if (noOfDaysBetween <= 7) {
			if (noOfDaysBetween < 0) {
			} else {
				System.out.println("if" + noOfDaysBetween);
			}
		} else {
			System.out.println(noOfDaysBetween);
		}
		//
		// timer15min("jitusunsofttech@gmail.com", "dataset.txt");

		Date d = new Date();
		System.out.println("time=>" + d);

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String currenttime = "";
		Date dNow = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(dNow);
		cal.add(Calendar.DATE, 15);

		dNow = cal.getTime();
		currenttime = sdf.format(dNow);
		System.out.println(currenttime + "time=>" + dNow);
		;

		// Random r = new Random();
		// String name ="DO";// name.substring(0,2) + r.nextInt(9) + 1;
		// int aNumber = 0;
		// aNumber = (int)((Math.random() * 90000)+10000);
		// System.out.println(aNumber);
		// String startTimeStr = "01:14:00"; // Getting
		// // start
		// // time
		//
		// String endTimeStr = "12:59:00"; // Getting end
		// // time
		//
		// // String startTimeStr ="09" + ":" + "59" + ":"+ "30";
		// // String endTimeStr = "09" + ":" + "31" + ":"+ "30";
		//
		// compareTimeJava8(startTimeStr, endTimeStr);

		// compareTime(startTimeStr, endTimeStr);
	}
}
