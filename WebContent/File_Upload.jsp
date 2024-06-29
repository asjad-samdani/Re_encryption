<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="java.text.SimpleDateFormat" %>
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
		<%
		Date day=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentdate=sdf.format(day);
		String role=(String)session.getAttribute("role");
		String mobileno=(String)session.getAttribute("O_Mobile");
		String oname=(String)session.getAttribute("O_Name");
		String O_id=(String) session.getAttribute("O_Number");
		%>
		<div class="panel panel-primary">
		<div class="panel-heading" align="center">File Upload Page</div>
		<br/>
		<div class="panel-body">
		
						<form class="form-light mt-20" action="File_Upload" method="post" enctype="multipart/form-data" >				
						<div class="form-group">
						<input type="file" class="form-control" name="file" required>
						</div>
						<div class="form-group">
						<input type="text" class="form-control" name="attribute_1" placeholder="Enter File Name" required>
						<br>
						<input type="text" class="form-control"  readonly="readonly" name="attribute_2" placeholder="Enter Owner Name" value="<%=oname %>" required>
						<br>
						<input type="text" class="form-control" name="attribute_3" placeholder="Enter  File Description" required>
						<br>
						<input type="text" class="form-control" name="attribute_4" readonly="readonly" placeholder="Enter Date" value="<%=currentdate %>" required>
						<br>
						<input type="text" class="form-control" name="attribute_5" readonly="readonly" placeholder="Enter Mobile No" value="<%=mobileno %>" required>
						
						<!-- <select class="form-control" name="attribute_5">
            <option value="a">Select Gender</option>
  <option value="Male">Male</option>
  <option value="Female">Female</option>  
</select>  -->
						</div>
						<div  align="left"> 
						<input type="text" class="form-control" name="role" readonly="readonly" placeholder="Enter Role" value="<%=role %>" required>
						<br>
						<input type="text" class="form-control" name="O_number" readonly="readonly" placeholder="Enter Owner Numbers" value="<%=O_id %>" required>
						 
        <!-- <select class="form-control" name="role">
            <option value="a">Select Role</option>
  <option value="IT_Dept">IT Dept</option>
  <option value="Manager">Manager</option>
  <option value="Marketing">Marketing</option>
  
</select>  --> 
        </div>
			<br>
						
							<input type="submit" class="btn btn-two">
							<input type="reset" class="btn btn-danger"><br/>
							<br/>
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