<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<ul class="nav navbar-nav pull-right mainNav">
					<li><a href="AuthorityHomePage.jsp">Home</a></li>
					
					
					<li><a href="Access_File_Request.jsp">File Access Request</a></li>
			
					
					<li class="dropdown">
                      <a href="LoginPage" class="dropdown-toggle" data-toggle="dropdown"><label style="color: white;"><%= session.getAttribute("A_Name") %></label><b class="caret"></b></a> 
                      
                        <ul class="dropdown-menu">
                           <li><a href="LoginPage"><label style="color: white;">Logout</label></a></li>
                                                         
                        </ul>
                    </li>

				</ul>
</body>
</html>