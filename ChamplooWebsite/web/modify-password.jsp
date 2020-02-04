<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
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
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"   integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="   crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/additional-methods.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/additional-methods.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">

    <!-- CDN SWEETALERT2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

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
                <li><a href="Product?operation=retrieveAll">Catalogo</a>
                    <div class="submenu">
                        <ul>
                            <li><a href="Product?operation=retrieveByCategory&category=T-shirt">T-Shirt</a></li>
                            <li><a href="Product?operation=retrieveByCategory&category=Felpa">Felpe</a></li>
                            <li><a href="Product?operation=retrieveByCategory&category=Giacca">Giacche</a></li>
                            <li><a href="Product?operation=retrieveByCategory&category=Camicia">Camicie</a></li>
                            <li><a href="Product?operation=retrieveByCategory&category=Pantalone">Pantaloni</a></li>
                        </ul>
                    </div>
                </li>
                <li><a href="#">Contatti</a></li>
            </ul>
        </div>
        <!-- Contact Info -->
        <div class="menu_contact">
            <div
                    class="menu_phone d-flex flex-row align-items-center justify-content-start">
                <div>
                    <div></div>
                </div>
                <div></div>
            </div>
            <div class="menu_social">
                <ul
                        class="menu_social_list d-flex flex-row align-items-start justify-content-start">
                    <li><a href="#"><i class="fa fa-facebook"
                                       aria-hidden="true"></i></a></li>
                    <li><a href="#"><i class="fa fa-youtube-play"
                                       aria-hidden="true"></i></a></li>
                    <li><a href="#"><i class="fa fa-google-plus"
                                       aria-hidden="true"></i></a></li>
                    <li><a href="#"><i class="fa fa-instagram"
                                       aria-hidden="true"></i></a></li>
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
		<div class="shop_top" id="regimargin2">
		    <div class="container">
		    <form name="modifyPassword" id="modifyPassword">
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
							<input name="nuovapass1" type="password" id="nuovapass1"> 
						</div>
						<div>
							<span>Conferma nuova Password<label>*</label></span>
							<input name="nuovapass2" type="password" id="nuovapass2"> 
						</div>
					</div>
				</div>
				</form>
				<div>
					<div class="row justify-content-center">
						<input type="submit" class="form-button" value="MODIFICA" onclick="modifyPassword()">
					</div>
				</div>
			</div>
		</div>
	</div>





	
	


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
    <script src="js/modifyPassword.js"></script>
    <script src="js/logout.js"></script>

</body>
</html>