<%@page language="java" 
		contentType="text/html; charset=ISO-8859-1"
    	pageEncoding="ISO-8859-1"
    	import="com.TechLord.model.*"
    	import="java.util.*"
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<title>TL - Modifica Password</title>
	<meta name="description" content="EndGam Gaming Magazine Template">
	<meta name="keywords" content="endGam,gGaming, magazine, html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<!-- Icona -->
	<link href="img/iconatl.ico" rel="shortcut icon"/>

	<!-- Google Font -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,400i,500,500i,700,700i,900,900i" rel="stylesheet">

	<!-- JQUERY 3.4.1 -->
	<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	   	    
	<!-- Stylesheets -->
	<link rel="stylesheet" href="css/style.css"/>
	<link rel="stylesheet" href="css/font-awesome.min.css"/>
	
</head>

<body>
	
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>


	<!-- Header section start -->
     	  <%@ include file="fragment/header3.jsp" %>
	<!-- Header section end -->
	
	<%
	
		if(utenteLoggato==null)
		{
			response.sendRedirect("log-area.jsp");
			return;
		}
	
	%>
	
	
	<!-- MAIN SECTION Modifica Password -->

	<div class="main">
		<div class="shop_top" id="regimargin">
		    <div class="container">
				<div class="row justify-content-center">
					<div class="register-top-grid register-bottom-grid justify-content-center">
						<h3>MODIFICA PASSWORD</h3>
						<div>
							<span>Vecchia Password<label>*</label></span>
							<input name="password" type="password" id="old_psw" autofocus> 
						</div>
						<div style="visibility: hidden">
							<span>USELESS<label>*</label></span>
							<input id="usernameUtente" name="username" type="text" value="<%=utenteLoggato.getUsername()%>" disabled> 
						</div>
						<div>
						<span>Nuova Password<label>*</label></span>
							<input name="password2" type="password" id="new_psw"> 
						</div>
						<div>
							<span>Conferma nuova Password<label>*</label></span>
							<input name="password2" type="password" id="cnfr_new_psw"> 
						</div>
					</div>
				</div>
				<div class="button2">
					<div class="row justify-content-center">
						<input type="submit" class="setButton" value="MODIFICA" onclick="modifyPassword()">
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Footer section -->
		<%@ include file="fragment/footer.jsp" %>
	<!-- Footer section end -->


	<!--====== Javascripts & Jquery ======-->
	<script src="js/jquery.slicknav.min.js"></script>
	<script src="js/main.js"></script>
	
	<script>
	
		function modifyPassword()
		{
			var value1 = $("#old_psw").val();
			var value2 = $("#new_psw").val();
			var value3 = $("#cnfr_new_psw").val();
			var value4 = $("#usernameUtente").val();
			var value5 = ("modifyPassword");
			
			var RGEXpassword_user = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/;
			var password_userRES = RGEXpassword_user.test(value2);
			
			if(password_userRES == false)
			{
				setTimeout(function(){location.href="modify-password.jsp"}, 200);
				return false;
			}
			else {
			    $.ajax({
			        type: "POST",
			        url: "Utente",
			        data: {"action" : value5, "old_psw" : value1, "new_psw" : value2, "cnfr_new_psw" : value3, "username" : value4},
			        success: function(results){
			        	Swal.fire({
			  			  title: 'Password modificata con Successo!',
			  			  timer: 2700,
			  			  type: 'success',
			  			  showCancelButton: false,
			  			  showConfirmButton: false,
			  			  width: '400px',
			  			})
			  			setTimeout(function(){location.href="home.jsp"}, 2850);
			        },
			        error: function (result){
			        	Swal.fire({
				  			 title: 'Password non Modificata!',
				  			 timer: 2000,
				  			 type: 'error',
				  			 showCancelButton: false,
				  			 showConfirmButton: false,
				  			 width: '500px'
				  		})
			        }
			    });
			}
		};
			   	
	</script>
	
</body>
</html>