<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.sql.*" %>
<%@ page import="Com.Connection.*" %>
<%@page import="Dao.user"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Block Chain</title>
	<link rel="favicon" href="assets1/images/favicon.png">
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets1/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets1/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets1/css/bootstrap-theme.css" media="screen">
	<link rel="stylesheet" type="text/css" href="assets1/css/da-slider.css" />
	<link rel="stylesheet" href="assets1/css/style.css">
	<script src="assets1/js/html5shiv.js"></script>
	<script src="assets1/js/respond.min.js"></script>
</head>
<body>
	<div class="navbar navbar-inverse" style="background-color: black;">
		<div class="container" align="right">
			<%
				Connection connection01=ConnectionFactory.getInstance().getConnection();
				String uemail=(String)session.getAttribute("U_Email_ID");
				String queryString01 = "select * FROM tblusername WHERE Email_ID='"+uemail+"'";
				PreparedStatement ptmt01 = connection01.prepareStatement(queryString01);
				String costdb="";
				ResultSet resultSet01 = ptmt01.executeQuery();
				if(resultSet01.next())
				{
					costdb=resultSet01.getString("Cost_Data");
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
					
					<li ><a href="UserHomePage.jsp">Home</a></li>
					
                    <li class="active"><a href="Smart_Cont_Access.jsp">File Access</a></li>
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown"><label style="color: white;">User Name:-<%=session.getAttribute("U_Name") %></label><b class="caret"></b></a> 
                      
                        <ul class="dropdown-menu">
                           <li><a href="LoginPage"><label style="color: white;">Logout</label></a></li>
                         
                                                          
                        </ul>
                    </li>
				</ul>
			</div>
			</div>
			</div>
		
			</div>
	<!-- /.navbar -->

	<!-- Header -->
	<header id="headO1">
		<div class="container">
			<div class="banner-content">
				
			</div>
		</div>
	</header>
	<!-- /Header -->


	<div id="courses">
		
	</div>
	<!-- container -->
	<section class="container">
		<br>
		<div class="row">
			<div class="col-md-4">
				<img src="./images/login.jpg" alt="" class="img-responsive">
			</div>
			<div class="col-md-8">
				<div >
		<div  >
		<div class="panel panel-primary">
		<div class="panel-heading" align="center" style="width: 80%">Smart Contracts</div>
		<br/>
		
						<form class="form-light mt-20" action="New_Show_Data.jsp" method="post" >				
						
						 
						<table border="1" width=80%>
        <tr>
        <td>
        Day</td>
        <td>
        <select class="form-control" name="day" required="required">
            <option value="">Select Day</option>
  <option value="5#Min">5 Minutes </option>
  <option value="10#Min">10 Minutes</option>
  <option value="15#Min">15 Minutes</option>
  <option value="20#Min">20 Minutes</option>
  <option value="30#Min">30 Minutes</option>
  
  
</select>  
</td>
</tr>
<tr>
<td>Available Cost</td>
                <% 
                String d=request.getParameter("name");
                String[] dd=d.split(",");
                String ownername=dd[0].toString();
                String filename=dd[1].toString();
                session.setAttribute("ownername",ownername);
                
                System.out.println("FileName="+filename);
                

                String emailid=(String) session.getAttribute("U_Email_ID");
        String Cost_Data="";
        	Connection connection =ConnectionFactory.getInstance().getConnection();
        	Statement statement = connection.createStatement();
            ResultSet resultset =statement.executeQuery("select * from tblUserName where Email_ID='"+emailid+"' ") ; 
			while(resultset.next())
             {
				Cost_Data=resultset.getString("Cost_Data");
				
				}
        %>
        <td>
						<input type="text" class="form-control" value="<%=Cost_Data %>" readonly="readonly" name="attribute_1" placeholder="Enter File Name" required>
			</td>
			</tr>
				<tr>
				<td>Owner Email</td>
			 <td>
						<input type="text" class="form-control" value="<%=ownername %>" readonly="readonly" name="ownername" placeholder="Enter File Name" required>
			</td>
			</tr>
				<tr>
				<td>FileName</td>
			 <td>
						<input type="text" class="form-control" value="<%=filename %>" readonly="readonly" name="O_filename" placeholder="Enter File Name" required>
			</td>		
				</tr>
				<tr>
				
				<td colspan="2" align="center">
							<input type="submit" class="btn btn-two" value="Next">
					</td>
					</tr>
					</table>
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
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets1/js/jquery.cslider.js"></script>
	<script src="assets1/js/custom.js"></script>
</body>
</html>