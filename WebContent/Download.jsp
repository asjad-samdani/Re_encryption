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
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
				<a class="navbar-brand" href="Index.jsp">
				<h4>Block Chain</h4>
				</a>	
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right mainNav">
					
					<jsp:include page="O_menu.jsp"></jsp:include>
                                                          
                        </ul>
                    </li>

				</ul>
			</div>
			<!--/.nav-collapse -->
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
	
	<div id="courses">
		
	</div>
	<!-- container -->
	<section class="container">
		<div class="heading">
			<!-- Heading -->
			<h2>Our Features</h2>
		</div>
		<div class="row">
			<div class="col-md-4">
				<img src="./images/login.jpg" alt="" class="img-responsive">
			</div>
			<div class="col-md-8">
				<div >
		<div >
		<div class="panel panel-primary">
		
		<div class="panel-body">
						<div style="margin-left: 20px;margin-right: 20px">
		 <TABLE class="table table-bordered">
            <TR class="danger">
               <TH>ID</TH>
               <TH>File Name</TH>
               <TH>Owner Email-ID</TH>
               <TH>Download</TH>
               <TH>Delete</TH>
           </TR>
		<% 
        	Connection connection =ConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
          String nm=(String) session.getAttribute("O_Email_ID");
         
            ResultSet resultset =statement.executeQuery("select * from file_info where emailid='"+nm+"'") ; 
			while(resultset.next())
             {
        %>
           <TR>
           
               <TD> <%= resultset.getString(1) %></TD>
               <TD> <%= resultset.getString(3) %> </TD>
               <TD> <%= resultset.getString(5) %> </TD>
              <TD> <a href="File_Download_Owner?name=<%=resultset.getString(3)%>"><input type="button" name="Download" value="Download" class="btn btn-info"></a></TD>
               <TD> <a href="File_Delete?name1=<%=resultset.getString(3)%>"><input type="button" name="Delete" value="Delete" class="btn btn-danger"></a></TD>
           </TR>
       <% 
           } 
       %>
         </TABLE>
				</div>
</div>
</div>
</div>
</div>
				
			</div>
		</div>
		<div class="container"></div>
	</section>
	
	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="assets1/js/modernizr-latest.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets1/js/jquery.cslider.js"></script>
	<script src="assets1/js/custom.js"></script>
</body>
</html>