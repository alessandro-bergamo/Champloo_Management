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
    <meta http-equiv="Content-Type" content="text/html">
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

        <%
            if(utenteLoggato.getType()==1)
                response.sendRedirect("index.jsp");

            ArrayList<UserBean> users;
            if(((users = (ArrayList) request.getSession().getAttribute("allUsers")) == null) || ((users) = (ArrayList) request.getSession().getAttribute("allUsers")).isEmpty())
            {
        %>

        <div style="display: none;">
            <form action="UserControl" id="hiddenform" method="POST">
                <input type="hidden" id="loadedvalue" name="loaded" value="0">
                <input type="hidden" name="operation" value="getAllUsers">
            </form>
        </div>

        <%
        } else {
        %>

        <input type="hidden" id="loadedvalue" name="loaded" value="1">

        <%
            }
        %>

        <div class="super_container_inner">
            <div class="super_overlay"></div>
           
           <!-- MAIN SECTION Admin-->
           
           <section class="admin-section">
               <div class="container">
                   <div class="row justify-content-center">
                       <h1>Area Admin - Benvenuto</h1>
                   </div>
               </div>
               <div class="container border-adminpage">
                   <div class="row">
                       <div class="col-xl-12">
                           <% if(utenteLoggato.getType()==2) {%>
                           <div class="container divutenti" id="utdiv">
                               <% if(users != null){ %>
                               <div class="row justify-content-end">
                                   <h4 class="uth4">Utenti registrati: <%=users.size()%></h4>
                               </div>
                               <div class="row spacerUt">
                                   <div class="col-xl-11">
                                       <div class="row">
                                           <div class="col-xl-4">
                                               <p id="utP">Nome:</p><p id="utP2"><%=users.get(0).getFirstName()%></p>
                                           </div>
                                           <div class="col-xl-4">
                                               <p id="utP">Cognome:</p><p id="utP2">Bergamo</p>
                                           </div>
                                           <div class="col-xl-4">
                                               <p id="utP">Username:</p><p id="utP2">alex_bergamo</p>
                                           </div>
                                       </div>
                                       <br>
                                       <div class="row">
                                           <div class="col-xl-6">
                                               <p id="utP">E-mail:</p><p id="utP2">a.bergamo2@studenti.unisa.it</p>
                                           </div>
                                           <div class="col-xl-4">
                                               <p id="utP">Password:</p><p id="utP2">************</p>
                                           </div>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <img src="images/delete.png">
                                       <i class="glyphicon glyphicon-remove remove"> </i>
                                   </div>
                               </div>
                               <% } %>
                           </div>
                           <% } else if(utenteLoggato.getType()==3) {%>
                           <div class="container divprod" id="prodiv">
                               <div class="row justify-content-center">
                                   <a href="inserisci_prodotto.jsp"><h4 class="prodh4">Inserisci Prodotto</h4></a>
                               </div>
                               <div class="row spacerProd">
                                   <div class="col-xl-1">
                                       <img src="img/prodotti/sv1.jpg" width="auto" height="auto">
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Tipo:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2">Alimentatore</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Marca:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2">nVidia</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Nome:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2">GTX 1070</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-2">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Modello:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2">8GB DDR4 XD98</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Anno:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2">2017</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Prezzo:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2">2017 &euro;</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">P. Scontato:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2">289 &euro;</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Famiglia:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2">1</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Quantit&agrave;:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2">58</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <img src="images/setting.png" height="32" width="32"><i class="glyphicon glyphicon glyphicon-wrench wrench"> </i></a>
                                       </div>
                                       <div class="row justify-content-center">
                                           <img src="images/delete.png" height="42" width="42"><i class="glyphicon glyphicon-remove remove2"> </i></a>
                                       </div>
                                   </div>
                               </div>
                           </div>
                           <% } else if(utenteLoggato.getType()==4) {%>
                           <div class="container divordini" id="ordiv">
                               <div class="row spacerUt">
                                   <div class="col-xl-11">
                                       <div class="row">
                                           <div class="col-xl-3">
                                               <p id="utP">ID Ordine:</p><p id="utP2">#6352</p>
                                           </div>
                                           <div class="col-xl-4">
                                               <p id="utP">Utente Ordine:</p><p id="utP2">alex_bergamo</p>
                                           </div>
                                           <div class="col-xl-5">
                                               <p id="utP">Metodo di Pagamento:</p><p id="utP2">Carta di credito</p>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="col-xl-6">
                                               <p id="utP">Indirizzo:</p><p id="utP2">Via Francescantonio Biondo n° 14</p>
                                           </div>
                                           <div class="col-xl-3">
                                               <p id="utP">Data Ordine:</p><p id="utP2">27/10/2019</p>
                                           </div>
                                           <div class="col-xl-3">
                                               <p id="utP">Prezzo Totale:</p><p id="utP2">383€</p>
                                           </div>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <img src="images/delete.png">
                                       <i class="glyphicon glyphicon-remove remove"> </i>
                                   </div>
                               </div>
                           </div>
                           <% } %>
                       </div>
                   </div>
               </div>
           </section>
           <!-- END SECTION -->

        <!-- Footer -->
                   
		<%@ include file="footer.jsp" %>

                </div>
            </div>
        </div>

        <script>
            function submittaForm()
            {
                var val = parseInt($("#loadedvalue").val());
                if(val == 0)
                {
                    $("#hiddenform").submit();
                }
            }
        </script>


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
