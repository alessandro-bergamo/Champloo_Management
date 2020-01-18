<%@page
        language="java"
        contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"
        import="com.champloo.bean.*"
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
    <link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">

    <!-- STILI CSS -->
    <link rel="stylesheet" type="text/css" href="styles/user_area.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">

</head>
<body>



    <div class="super_container">

        <!-- Header -->

        <header class="header">
            <div class="header_overlay"></div>
            <div class="header_content d-flex flex-row align-items-center justify-content-center">
                <div class="logo">
                    <div class="logo_cpl d-flex flex-row align-items-center justify-content-start">
                            <div>
                                <a href="index.jsp">
                                    <img src="images/logo_cpl.png" alt="">
                                </a>
                                </div>
                            <div></div>
                    </div>    
                </div>
            </div>
        </header>

        <%
            UserBean utenteLoggato;
            synchronized(session)
            {
                utenteLoggato = (UserBean) request.getSession().getAttribute("utenteLoggato");
            }

            if(utenteLoggato != null)
                response.sendRedirect("index.jsp");
        %>

        <div class="super_container_inner">
            <div class="super_overlay"></div>

            <!-- MAIN PAGE login -->

            <div class="main">
                <div class="shop_top" id="regimargin">
                        <div class="row">
                        <div class="col-md-6">
                            <div class="login-page">
                                <h4 class="title">Nuovo Utente - Benefici</h4>
                                <ul>
                                    <li>Controlla i tuoi acquisti in qualsiasi momento: Traccia lo status dei tuoi ordini e stampa i tuoi coupon.</li>
                                    <li>Shop più veloce: non c'è bisogno di riempire il modulo con il tuo contratto e i dettagli di spedizione mentre stai comprando.</li>
                                    <li>Accesso anticipato ai contenuti in offerta ed agli ultimi arrivi.</li>
                                </ul>
                                <div class="button1">
                                    <a href="registrazione.jsp"><input type="submit" name="Submit" value="REGISTRATI"></a>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="login-title">
                                <h4 class="title">Utente Registrato</h4>
                                <div id="loginbox" class="loginbox">
                                    <form action="UserControl" method="post" name="login" id="login-form">
                                    	<input name="operation" value="login" type="hidden" >
                                        <fieldset class="input">
                                            <p id="login-form-username">
                                                <label for="email">Email</label>
                                                <input id="email" type="email" name="email" class="inputbox" size="18" autocomplete="off">
                                            </p>
                                            <p id="login-form-password">
                                                <label for="password">Password</label>
                                                <input id="password" type="password" name="password" class="inputbox" size="18" autocomplete="off">
                                            </p>
                                            <div class="remember">
                                                <p id="login-form-remember">
                                                    <label for="modlgn_remember"><a href="#">Hai dimenticato la password? </a></label>
                                                </p>
                                                <input type="submit" name="Submit" class="button" value="LOGIN">
                                                <div class="clear"></div>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        <div class="clear"></div>
                        </div>
                    </div>
                </div>
            </div>

            
        </div>
    </div>

    
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="styles/bootstrap-4.1.2/popper.js"></script>
    <script src="styles/bootstrap-4.1.2/bootstrap.min.js"></script>
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
