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
                      <a href="LoginPage" class="dropdown-toggle" data-toggle="dropdown"><label style="color: white;"><%=session.getAttribute("U_Name") %></label><b class="caret"></b></a> 
                      
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
	
	<!-- /Header -->

<%
String key=request.getParameter("key");
if(key==null)
{
	
}
else if(key.equals("1"))
{
	%>

<script type="text/javascript">

alert("Allready Working");		</script>


<%
//response.sendRedirect("File_Downloads.jsp");

}
else if(key.equals("2"))
{
%>
<script type="text/javascript">
		        			
		        			alert("Please do smart contract first");	
		        			</script>	
		        			<%
}
else if(key.equals("3"))
{
	%>

<script type="text/javascript">

alert("Wrong Key Please try again");		</script>


<%
//response.sendRedirect("File_Downloads.jsp");

}
%>

	<div id="courses">
		
	</div>
	<!-- container -->
	<section class="container">
		<div class="heading">
			<!-- Heading -->
			<h2>Smart Contract</h2>
		</div>
		
				<TABLE class="table table-bordered">
            <TR class="danger">
               <TH>ID</TH>
               <TH>Owner Name</TH>
                <TH>Owner Email-ID</TH>
               <TH>File Name</TH>
               <th>Access Contracts</th>
             
               <th>Request Status</th>
           </TR>
		<% 
        	Connection connection =ConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String emailid=(String) session.getAttribute("U_Email_ID");
            ResultSet resultset =statement.executeQuery("select * from file_info"); 
			while(resultset.next())
             {
				//if (!emailid.equals(resultset.getString(5)))
				{

        %>
           <TR>
           
               <TD> <%= resultset.getString(1) %></TD>
               <TD> <%= resultset.getString(2) %> </TD>
               <TD> <%= resultset.getString(5) %> </TD>
               
               <TD> <%= resultset.getString(3) %> </TD>
              
               <TD> <a href="Show_Data.jsp?name=<%=resultset.getString(5)%>,<%=resultset.getString(3)%>"><button type="button" class="btn btn-info">Access Contracts</button></a></TD>
         <TD> <%= resultset.getString(5) %> </TD>
           </TR>
       <% 
       }
           } 
       %>
         </TABLE>

	</section>
	

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="assets1/js/modernizr-latest.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets1/js/jquery.cslider.js"></script>
	<script src="assets1/js/custom.js"></script>
</body>
</html>
