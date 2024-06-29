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
				<%
String no=request.getParameter("no");
if(no==null)
{}
else if(no.equals("1"))
{
	%>
	<script type="text/javascript">
			
			alert("Email ID Already Exist");	
			</script>	
		<%
	
}



%>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right mainNav">
					<li><a href="Index.jsp">Home</a></li>
					<li><a href="owner_signin.jsp">Data Owner</a></li>
					<li><a href="Signin.jsp">Service Providers</a></li>
					<li><a href="admin_signin.jsp">User</a></li>
					

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
		<div class="heading">
			<!-- Heading -->
			<h2>Our Authority Register</h2>
		</div>
		<div class="row">
			<div class="col-md-4">
				<img src="./images/index.jpg" alt="" class="img-responsive">
			</div>
			<div class="col-md-8">
				<form  action="AdminRegister" method="post" autocomplete="off"> 
      <div class="modal-content">
        <div class="modal-header"  style="background-color: gray;">
          <button type="button" class="close" data-dismiss="modal">&times;B</button>
          <h4 class="modal-title">Registration Page</h4>
        </div>
        <div class="modal-body">
         <div class="form-group" align="left">
             <input type="text" class="form-control" name="name" placeholder="Your Name" required pattern="[a-zA-Z\s]+" title="Enter Your Name(eg.abc)">
        </div>
         <div class="form-group" align="left">
            <input type="text" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" id="inputEmail" placeholder="Email ID" name="email" required pattern=".*@gmail\.com" title="Enter your email (abc@gmail.com)">
        </div>
        <div class="form-group" align="left">
            <input type="password" class="form-control" id="passwordsignup" placeholder="Password" name="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="Password must contain at least 6 characters, including UPPER/lowercase and numbers.">
        </div>
        <div class="form-group" align="left">
           <select class="form-control" name="gender">
            <option value="a">Select Gender</option>
  <option value="Male">Male</option>
  <option value="Female">Female</option>
   
</select>    </div>
         <div class="form-group" align="left">
            <select class="form-control" name="role">
              <option selected="selected" value="Admin">Admin</option>
  
</select>         </div>
<div class="form-group" align="left">
<input type="date" class="form-control" name="bod"  placeholder="Birth Day">

                  </div>
        <div class="form-group" align="left">
            <input type="text" class="form-control" id="inputPassword" placeholder="Mobile No" name="Contact" required pattern="[7-9]{1}[0-9]{9}"  title="* Mobile number with 7-9 and remaing 9 digit with 0-9">
        </div>
        <div align="left">
        <button type="submit" id="submit" class="btn btn-success">Sign Up</button>
         <button type="reset" class="btn btn-primary">Reset</button><br>
        </div>
         <div align="right">
        Back to User Login Page: <a href="admin_signin.jsp"><button type="button" class="btn btn-link" >Sign In</button></a>        
        </div>
        </div>
      </div>
      </form>

				
			</div>
		</div>
	</section>
	<footer id="footer">
		<div class="container">
			<div class="social text-center">
				<a href="#"><i class="fa fa-twitter"></i></a>
				<a href="#"><i class="fa fa-facebook"></i></a>
				<a href="#"><i class="fa fa-dribbble"></i></a>
				<a href="#"><i class="fa fa-flickr"></i></a>
				<a href="#"><i class="fa fa-github"></i></a>
			</div>

			<div class="clear"></div>
			<!--CLEAR FLOATS-->
		</div>
		<div class="footer2">
			<div class="container">
					<div class="col-md-6 panel">
						<div class="panel-body">
							<p class="text-right">
								Copyright &copy; 2018-19.
							</p>
						</div>
					</div>

				</div>
				<!-- /row of panels -->
			</div>
	</footer>

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="assets1/js/modernizr-latest.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets1/js/jquery.cslider.js"></script>
	<script src="assets1/js/custom.js"></script>
</body>
</html>
