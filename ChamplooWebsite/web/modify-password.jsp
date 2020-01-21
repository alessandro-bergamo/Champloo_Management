<%@page
        language="java"
        contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"
        import="com.champloo.bean.*"
        import="java.util.*"
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Champloo Store</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Little Closet template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Icona -->
    <link href="images/icona.png" rel="shortcut icon"/>

    <!-- IMPORT VARI (BOOTSTRAP, JQUERY, NODE.JS) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">

    <!-- STILI CSS -->
    <link rel="stylesheet" type="text/css" href="styles/style.css">
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
</head>
<body onload="submittaForm()">

    <!-- Menu -->

    <div class="menu">
        <!-- Navigation -->
        <div class="menu_nav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="category.jsp">Catalogo Uomo</a></li>
                <li><a href="#">Contatti</a></li>
            </ul>
        </div>
        <!-- Contact Info -->
        <div class="menu_contact">
            <div class="menu_phone d-flex flex-row align-items-center justify-content-start">
                <div><div></div></div>
                <div></div>
            </div>
            <div class="menu_social">
                <ul class="menu_social_list d-flex flex-row align-items-start justify-content-start">
                    <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                    <li><a href="#"><i class="fa fa-youtube-play" aria-hidden="true"></i></a></li>
                    <li><a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
                    <li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="super_container">

        <!-- Header -->
		
		<%@ include file="header.jsp" %>

        <!-- Stop Header -->
	
	<%
	
		if(utenteLoggato==null)
		{
			response.sendRedirect("user_log.jsp");
			return;
		}
	
	%>
	
	
	<!-- MAIN SECTION Modifica Password -->

	<div class="main">
		<div class="shop_top" id="regimargin">
		    <div class="container">
				<div class="row justify-content-center">
					<div class="register-top-grid register-bottom-grid justify-content-center">
						<br>
						<br>
						<br>
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
						<input type="submit" class="form-button" value="MODIFICA" onclick="modifyPassword()">
					</div>
				</div>
			</div>
		</div>
	</div>





	
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


        </div>
            

    
    <script src="plugins/greensock/TweenMax.min.js"></script>
    <script src="plugins/greensock/TimelineMax.min.js"></script>
    <script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
    <script src="plugins/greensock/animation.gsap.min.js"></script>
    <script src="plugins/greensock/ScrollToPlugin.min.js"></script>
    <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
    <script src="plugins/easing/easing.js"></script>
    <script src="plugins/progressbar/progressbar.min.js"></script>
    <script src="plugins/parallax-js-master/parallax.min.js"></script>
    <script src="js/custom.js"></script>

</body>
</html>