<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="Com.Connection.*"%>
<%@page import="Dao.*"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="java.io.*,java.util.*,java.util.Date"%>

<%@ page import="javax.servlet.*,java.text.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Block Chain</title>
<link rel="favicon" href="assets1/images/favicon.png">
<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet" href="assets1/css/bootstrap.min.css">
<link rel="stylesheet" href="assets1/css/font-awesome.min.css">
<link rel="stylesheet" href="assets1/css/bootstrap-theme.css"
	media="screen">
<link rel="stylesheet" type="text/css" href="assets1/css/da-slider.css" />
<link rel="stylesheet" href="assets1/css/style.css">
<script src="assets1/js/html5shiv.js"></script>
<script src="assets1/js/respond.min.js"></script>
</head>
<body>
	<div class="navbar navbar-inverse" style="background-color: black;">
		<div class="container" align="right">
			<%
				Connection connection01 = ConnectionFactory.getInstance()
						.getConnection();
				String uemail = (String) session.getAttribute("U_Email_ID");
				String queryString01 = "select * FROM tblusername WHERE Email_ID='"
						+ uemail + "'";
				PreparedStatement ptmt01 = connection01
						.prepareStatement(queryString01);
				String costdb = "";
				ResultSet resultSet01 = ptmt01.executeQuery();
				if (resultSet01.next()) {
					costdb = resultSet01.getString("Cost_Data");
				}
			%>
			<div class="navbar-collapse collapse" align="right">
				<ul class="nav navbar-nav">
					<li><a class="navbar-brand" href="Index.jsp">
							<h4>Block Chain</h4>
					</a></li>

				</ul>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav pull-right mainNav">

						<li><a href="UserHomePage.jsp">Home</a></li>
						
                    <li><a href="Smart_Cont_Access.jsp">File Access</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><label style="color: white;"><%=session.getAttribute("U_Name")%></label><b
								class="caret"></b></a>

							<ul class="dropdown-menu">
								<li><a href="LoginPage"><label style="color: white;">Logout</label></a></li>
								<li><a href="LoginPage"><label style="color: white;">Account
											Balance:-<%=costdb%></label></a></li>

								<!-- <li><a href="#">Dropdown Link 1</a></li>
                            <li class="active"><a href="#">Dropdown Link 2</a></li> -->


							</ul></li>
					</ul>
				</div>
			</div>
		</div>

	</div>
	<!-- /.navbar -->

	<!-- Header -->
	<header id="headO1">
		<div class="container">
			<div class="banner-content"></div>
		</div>
	</header>
	<!-- /Header -->


	<div id="courses"></div>
	<!-- container -->
	<section class="container">
		<br>
		<div class="row">
			<div class="col-md-4">
				<img src="./images/login.jpg" alt="" class="img-responsive">
			</div>
			<div class="col-md-8">
				<div>
					<div>
						<div class="panel panel-primary">
							<div class="panel-heading" align="center">Smart Contracts</div>
							<br />
							<div class="panel-body">

								<form class="form-light mt-20" action="updateSmart"
									method="post">

									<br> <br>
									<%
									
										String Cost_Data = "",O_Cost="";
										Connection connection = ConnectionFactory.getInstance()
												.getConnection();
										String day = request.getParameter("day");
										String emailid = (String) session.getAttribute("U_Email_ID");
										String O_Name = request.getParameter("ownername");
										System.out.println(O_Name+"New Show Data Dur=>"+day);
										String O_FileName = request.getParameter("O_filename");
										int flag = 0;
										Statement st1=connection.createStatement();
								        ResultSet rs01=st1.executeQuery("select * from tblsmart_contracts_new where EmailID='"+emailid+"' and O_File_Name='"+O_FileName+"' and O_Email_ID='"+O_Name+"'");
								       if(rs01.next())
								        {
								        	day=rs01.getString("Duration_Data");
								        }
										 if(day.equals("5#Min"))
										{
											TimeLookup.timer5min(emailid, O_FileName,day);
										}
										else if(day.equals("10#Min"))
										{
											TimeLookup.timer10min(emailid, O_FileName,day);
										}
										else if(day.equals("15#Min"))
										{
											TimeLookup.timer15min(emailid,O_FileName,day);
										}
										else if(day.equals("20#Min"))
										{
											
											TimeLookup.timer20min(emailid,O_FileName,day);
										}
										else if(day.equals("30#Min"))
										{
											TimeLookup.timer30min(emailid,O_FileName,day);
										}
										
										String days = request.getParameter("day");
										Statement statement101 = connection.createStatement();
									
										
										ResultSet resultset101 = statement101
												.executeQuery("select * from tblsmart_contracts_new  where EmailID='"
														+ emailid + "'and O_File_Name='"+O_FileName+"' and O_Email_ID='"+O_Name+"' and Duration_Data='"+days+"' and Status_U='Working'");
										if (resultset101.next()) {
											flag = 1;

										} else {
											flag = 2;
										}
										if (flag == 1)

										{
											
											//System.out.println("Working");
											%>
											<script type="text/javascript">
											
											alert("Allready Working");	
											</script>
											<%
											response.sendRedirect("Smart_Cont_Access.jsp?key=1");
										} else {
											System.out.println("Working Expiry");
											Statement statement = connection.createStatement();
											
											String[] nday = days.split("#");
											System.out.println("Minutes=>"+nday[0]);
											String attribute_1 = request.getParameter("attribute_1");
											ResultSet resultset = statement
													.executeQuery("select * from tblSmart_Contracts where Duration_Data='"
															+ days + "' ");
											while(resultset.next()) {
												Cost_Data = resultset.getString("Cost_Data");
												O_Cost=resultset.getString("Owner_Cost");

											}
									%>
									<table border="1" width=100%>
										<tr>
											<td>Day</td>
											<td><input type="text" class="form-control"
												value="<%=days%>" readonly="readonly" name="attribute_1"
												placeholder="Enter File Name" required></td>
										</tr>

										<tr>
											<td>Available Cost</td>
											<td><input type="text" class="form-control"
												value="<%=attribute_1%>" readonly="readonly"
												name="availablecost" placeholder="Enter File Name" required>
											</td>
										</tr>

										<tr>
											<td>Transfer Cost</td>
											<td><input type="text" class="form-control"
												value="<%=Cost_Data%>" readonly="readonly"
												name="costtransfer" placeholder="Enter File Name" required>
											</td>
										</tr>
										<tr>
											<td>Owner Cost</td>
											<td><input type="text" class="form-control"
												value="<%=O_Cost%>" readonly="readonly"
												name="costtransfer" placeholder="Enter File Name" required>
											</td>
										</tr>
										<tr>
											<td>Owner Email</td>
											<td><input type="text" class="form-control"
												value="<%=O_Name%>" readonly="readonly" name="ownername"
												placeholder="Enter File Name" required></td>
										</tr>
										<tr>
											<td>FileName</td>
											<td><input type="text" class="form-control"
												value="<%=O_FileName%>" readonly="readonly"
												name="O_filename" placeholder="Enter File Name" required>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center"><input type="submit"
												class="btn btn-two"></td>
										</tr>

									</table>
									<%
										}
									%>


									<br />
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="assets1/js/modernizr-latest.js"></script>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets1/js/jquery.cslider.js"></script>
	<script src="assets1/js/custom.js"></script>
</body>
</html>