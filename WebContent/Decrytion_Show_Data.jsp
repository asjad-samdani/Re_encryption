<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="Com.Connection.*"%>
<%@page import="Dao.user"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*,Com.algo.*"%>
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
			
			<div class="navbar-collapse collapse" align="right">
				<ul class="nav navbar-nav">
					<li><a class="navbar-brand" href="Index.jsp">
							<h4>Block Chain</h4>
					</a></li>

				</ul>
				<div class="navbar-collapse collapse">
					<jsp:include page="authormenu.jsp"></jsp:include>
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
							<div class="panel-heading" align="center" style="width: 80%">Decrytion
								Process</div>
							<br />

							<form action=Download_User name="frm"
								class="form-light mt-20" method="post">
								<table class="table table-bordered">

									<tbody>
										<%
											String filename=request.getParameter("txtfile");
										String user=request.getParameter("UpdatedText");
										String ownername=request.getParameter("txtownername");
											if (filename == null) {
										%>

										<script type="text/javascript">
											alert("Please Select FileName");
										</script>
										<%-- <jsp:forward page="FileUpdate.jsp"/> --%>
										<%
											}

											String txtfile_key=request.getParameter("txtfile_key");
											ResultSet rs = null;

											PreparedStatement st = null;
											String quer = null;

											try {

												Connection conn = ConnectionFactory.getInstance()
														.getConnection();

												response.setContentType("text/html;charset=UTF-8");

												quer = "select * from file_info where emailid='" + ownername
														+ "' and Filename='" + filename + "'";
												byte[] key = null;
												byte[] data = null;
												st = conn.prepareStatement(quer);
												rs = st.executeQuery();
												String origional = "";
												while (rs.next()) {
													if (txtfile_key.equals(rs.getString("P_Key"))) 
													{
														data=rs.getBytes(6);
														//String finalkey=rs.getString(4);
														key=rs.getBytes(7);
														byte [] pl=AESCoder.decrypt(key, data);
														String decdata=new String(pl);
														%>


										<tr>

											<td align="left">Owner Name<input type="text"
												name="txtownername" readonly="readonly" value="<%=ownername%>" /></td>
											</tr><tr>
											
											<td align="left">File Name<input type="text"
												name="txtfile" readonly="readonly" value="<%=filename%>" /></td>
										</tr>
										<tr>
											<td><textarea rows="4" cols="50" name="UpdatedText"
													style="width: 600px; height: 100px;"><%=decdata%>

</textarea></td>
										</tr>

										<%
											}
													else
													{
														
														%>
														<script type="text/javascript">
														
														alert("Wrong Key Pleage try again");	
														</script>

														 <%-- <jsp:forward page="FileDownload.jsp"/> --%>
														<%
														//no="1";
														//response.sendRedirect("AccessFile.jsp");
													}
													
												}

											} catch (SQLException e) {

												e.printStackTrace();
											}
										%>


										<tr>
											<td><input type="submit" name="ok" value="Download Data"
												class="btn btn-info"></td>
										</tr>
									</tbody>
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
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets1/js/jquery.cslider.js"></script>
	<script src="assets1/js/custom.js"></script>
</body>
</html>