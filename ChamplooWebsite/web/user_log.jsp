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
                                    <li>Shop pi� veloce: non c'� bisogno di riempire il modulo con il tuo contratto e i dettagli di spedizione mentre stai comprando.</li>
                                    <li>Accesso anticipato ai contenuti in offerta ed agli ultimi arrivi.</li>
                                </ul>
                                <div class="button1">
                                    <a href="registrazione.jsp"><input type="submit" name="Submit" value="REGISTRATI"></a>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <h4 class="title">Utente Registrato</h4>
                            <div id="loginbox" class="loginbox">
                                <form name="login-form" id="login-form">
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
                                                <label for="login-form-remember"><a href="password_dimenticata.jsp">Hai dimenticato la password? </a></label>
                                            </p>
                                            <input type="button" onclick="login()" name="Submit" class="button" value="LOGIN">
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

    <script>
        function login()
        {
            var value1 = ("login");
            var value2 = $("#email").val();
            var value3 = $("#password").val();
            $.ajax({
                type: "POST",
                url: "UserControl",
                data: {"operation" : value1, "email" : value2, "password" : value3},
                success: function(results){
                    Swal.fire({
                        title: 'Login Effettuato',
                        timer: 1700,
                        icon: 'success',
                        showCancelButton: false,
                        showConfirmButton: false,
                        width: '400px',
                    }); setTimeout(function(){window.location.replace("index.jsp")} , 1350);
                },
                error: function (result){
                    Swal.fire({
                        title: 'Login non effettuato',
                        timer: 2000,
                        icon: 'error',
                        showCancelButton: false,
                        showConfirmButton: false,
                        width: '500px'
                    }); setTimeout(function(){location.reload()} , 1350);
                }
            });
        };
    </script>

    
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
    <script src="/ChamplooWebsite/js/login-form.js"></script>
    

</body>

</html>
