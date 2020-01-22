<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="com.champloo.bean.*"
		import="java.util.*"
%>
<%
	ArrayList<AddressBean> datiUtente = (ArrayList) request.getSession().getAttribute("addresses");
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Champloo Store</title>
    <meta charset="UTF-8">
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
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
</head>
<body>

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

        <div class="super_container_inner">
            <div class="super_overlay"></div>

           <!-- MAIN SECTION User-->

           <section class="utente-section">
               <div class="container">
                   <div class="row justify-content-center">
                       <h1>Area Utente - Benvenuto</h1>
                   </div>
               </div>
               <div class="container border-utentepage">
                   <div class="row justify-content-start">
                       <div class="col-xl-6">
                           <h4 id="list1" class="utenteh4">Informazioni Utente</h4>
                       </div>
                       <div class="col-xl-6">
                           <h4 id="list2" class="utenteh42">Lista Ordini</h4>
                       </div>
                   </div>
               </div>
               <div class="container" id="utdiv">
                   <div class="container spacerUP borderutdiv" id="infPut">
                       <div class="row justify-content-start">
                           <div class="col-xl-3">
                               <div class="row">
                                   <h4 class="upH4">Nome: </h4>
                                   <p class="upPCircle"><%=utenteLoggato.getFirstName()%></p>
                               </div>
                           </div>
                           <div class="col-xl-3">
                               <div class="row">
                                   <h4 class="upH4">Cognome: </h4>
                                   <p class="upPCircle"><%=utenteLoggato.getSurname()%></p>
                               </div>
                           </div>
                           <div class="col-xl-5">
                               <div class="row">
                                   <h4 class="upH4">E-mail: </h4>
                                   <p class="upPCircle"><%=utenteLoggato.getEmail()%></p>
                               </div>
                           </div>
                       </div>
                       <div class="row spacerUP2 justify-content-start">
                           <div class="col-xl-3">
                               <div class="row">
                                   <h4 class="upH4">Username: </h4>
                                   <p class="upPCircle"><%=utenteLoggato.getUsername()%></p>
                               </div>
                           </div>
                           <div class="col-xl-3">
                               <div class="row">
                                   <h4 class="upH4">Password: </h4>
                                   <p class="upPCircle">***********</p>
                               </div>
                           </div>
                           <div class="col-xl-3">
                               <div class="row justify-content-end">
                                   <input type="button" class="site-btn6" id="btninfo" value="Modifica Informazioni">
                               </div>
                           </div>
                       </div>
                   </div>
                   <div class="container spacerUP borderutdiv" id="infPut2">
                   <form action="UserControl" method="POST">
                       <div class="row justify-content-start">
                       <input name="operation" type="hidden" value="updateUser">
                           <div class="col-xl-3">
                               <div class="row">
                                   <h4 class="upH4">Nome: </h4>
                                   <input type="text" class="form-input" name="firstname" value="<%=utenteLoggato.getFirstName()%>">
                               </div>
                           </div>
                           <div class="col-xl-3">
                               <div class="row">
                                   <h4 class="upH4">Cognome: </h4>
                                   <input type="text" class="form-input" name="lastname" value="<%=utenteLoggato.getSurname()%>">
                               </div>
                           </div>
                           <div class="col-xl-5">
                               <div class="row">
                                   <h4 class="upH4">E-mail: </h4>
                                   <p class="upPCircle"><%=utenteLoggato.getEmail()%></p>
                               </div>
                           </div>
                       </div>
                       <div class="row spacerUP2 justify-content-start">
                           <div class="col-xl-3">
                               <div class="row">
                                   <h4 class="upH4">Username: </h4>
                                   <p class="upPCircle"><%=utenteLoggato.getUsername()%></p>
                               </div>
                           </div>
                           <div class="col-xl-3">
                               <div class="row">
                                   <h4 class="upH4">Password: </h4>
                                   <p class="upPCircle"><%=utenteLoggato.getPassword()%></p>
                               </div>
                           </div>
                           <div class="col-xl-6">
                               <div class="row justify-content-center">
                                   <input type="submit" class="form-button" style="width: 130px; margin-top: 0px !important; margin-right: 40px;" value="Applica">
                                   <input type="reset" class="form-button" style="width: 130px; margin-top: 0px !important;
                                    margin-left: 30px;" id="btninfo2" value="Annulla">
                               </div>
                           </div>
                       </div>
                   </form>
                   </div>

                   <%   if(!datiUtente.isEmpty())
                        {   %>
                   <div class="container spacerUP3">
                       <div class="row justify-content-start">
                           <div class="col-xl-5">
                               <h4 style="color:#2fce98">Indirizzi di Spedizione</h4>
                           </div>
                       </div>
                       <div class="row borderutdiv">
                           <div class="col-xl-12">
                               <div class="row justify-content-start">
                                   <div class="col-xl-7">
                                       <div class="row">
                                           <h4 class="upH4">Indirizzo: </h4>
                                           <p class="upPCircle"><%=datiUtente.get(0).getAddress()%>, <%=datiUtente.get(0).getCivic_number()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-5">
                                       <div class="row">
                                           <h4 class="upH4">Città: </h4>
                                           <p class="upPCircle"><%=datiUtente.get(0).getCity()%>, <%=datiUtente.get(0).getProvince()%>, <%=datiUtente.get(0).getCAP()%></p>
                                       </div>
                                   </div>
                               </div>
                               <div class="row justify-content-start">
                                   <div class="col-xl-12">
                                       <div class="row">
                                           <h4 style="color:#2fce98; margin-left: 5px; cursor: pointer;" id="newInd">+ Inserisci nuovo indirizzo</h4>
                                       </div>
                                   </div>
                               </div>
                               <div class="row justify-content-start" id="inputNewInd">
                                   <div class="col-xl-12">
                                       <div class="row">
                                           <div class="col-xl-4">
                                               <input type="text" class="form-input" name="indirizzo" placeholder="Inserisci Indirizzo">
                                           </div>
                                           <div class="col-xl-4">
                                               <input type="text" class="form-input" name="città" placeholder="Inserisci città">
                                           </div>
                                           <div class="col-xl-1">
                                               <input type="text" class="form-input3" id="provincia" maxlength="2" name="provincia" placeholder="Provincia">
                                           </div>
                                           <div class="col-xl-3">
                                               <div class="row justify-content-end">
                                                   <input type="button" class="site-btn6" value="Salva Indirizzo" style="margin-top: 5px">
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>
                           </div>
                       </div>
                   </div>
                   <%   } else {  %>
                   <div class="row justify-content-start">
                       <div class="col-xl-12">
                           <div class="row">
                               <h4 style="color:#2fce98; margin-left: 5px; cursor: pointer;" id="newInd">+ Inserisci nuovo indirizzo</h4>
                           </div>
                       </div>
                   </div>
                   <div class="row justify-content-start" id="inputNewInd">
                       <div class="col-xl-12">
                           <div class="row">
                               <div class="col-xl-4">
                                   <input type="text" class="form-input" name="indirizzo" placeholder="Inserisci Indirizzo">
                               </div>
                               <div class="col-xl-4">
                                   <input type="text" class="form-input" name="città" placeholder="Inserisci città">
                               </div>
                               <div class="col-xl-1">
                                   <input type="text" class="form-input3" id="provincia" maxlength="2" name="provincia" placeholder="Provincia">
                               </div>
                               <div class="col-xl-3">
                                   <div class="row justify-content-end">
                                       <input type="button" class="site-btn6" value="Salva Indirizzo" style="margin-top: 5px">
                                   </div>
                               </div>
                           </div>
                       </div>
                   </div>
                   <%   }   %>
                   <div class="container spacerUP3">
                       <div class="row justify-content-start">
                           <div class="col-xl-5">
                               <h4 style="color:#2fce98">Metodi di Pagamento</h4>
                           </div>
                       </div>
                       <div class="row borderutdiv">
                           <div class="col-xl-12">
                               <div class="row spacerUP4 justify-content-start">
                                   <div class="col-xl-4">
                                       <div class="row">
                                           <h4 class="upH4">Tipo Carta: </h4>
                                           <p class="upPCircle">Carta di Credito - Paypal</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-5">
                                       <div class="row">
                                           <h4 class="upH4">Intestatario Carta: </h4>
                                           <p class="upPCircle">Alessandro Bergamo</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-3">
                                       <div class="row">
                                           <h4 class="upH4">Scadenza Carta: </h4>
                                           <p class="upPCircle">09/20</p>
                                       </div>
                                   </div>
                               </div>
                               <div class="row spacerUP2 justify-content-start">
                                   <div class="col-xl-5">
                                       <div class="row">
                                           <h4 class="upH4">Codice Carta: </h4>
                                           <p class="upPCircle">4023 8098 7361 2617</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-3">
                                       <div class="row">
                                           <h4 class="upH4">Codice CVC: </h4>
                                           <p class="upPCircle">•••</p>
                                       </div>
                                   </div>
                               </div>
                           </div>
                           <div class="row justify-content-start">
                               <div class="col-xl-12">
                                   <h4 style="color:#2fce98; margin-left: 5px; margin-bottom: 15px; cursor: pointer;" id="newCard">+ Inserisci nuovo Metodo di Pagamento</h4>
                               </div>
                           </div>
                           <div class="row justify-content-start" id="inputNewCard">
                               <div class="row">
                                   <div class="col-xl-4">
                                       <select class="arrows" style="margin-left: 20px">
                                           <option value="selTipoCarta" disabled selected hidden>Tipo Carta</option>
                                           <option class="singleOption" value="hurr">Paypal</option>
                                           <option class="singleOption" value="hurr">Carta di Credito</option>
                                       </select>
                                   </div>
                                   <div class="col-xl-4">
                                       <input type="text" class="form-input2" id="codcarta" name="codcarta" maxlength="19" placeholder="Codice Carta">
                                   </div>
                                   <div class="col-xl-2">
                                       <input type="password" class="form-input4" name="codcvc" maxlength="3" placeholder="Codice CVC">
                                   </div>
                                   <div class="col-xl-2">
                                       <input type="date" name="dataexpcarta" id="dataexpcarta" style="margin-left: 40px;" placeholder="Data scadenza">
                                   </div>
                               </div>
                               <div class="row">
                                   <div class="col-xl-12">
                                       <div class="row justify-content-center">
                                           <input type="button" class="site-btn7" value="Salva Carta" style="margin-top: 25px;">
                                       </div>
                                   </div>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>
               <div class="container" id="ordiv">
                   <div class="container spacerUP borderutdiv">
                       <div class="container" style="margin-bottom: 15px">
                           <div class="row justify-content-start test">
                               <div class="col-xl-3">
                                   <div class="row">
                                       <h4 class="upH43">ORDINE EFFETTUATO IL: </h4>
                                       <p class="ordP">14 Giugno 2019</p>
                                   </div>
                               </div>
                               <div class="col-xl-3">
                                   <div class="row">
                                       <h4 class="upH43">IMPORTO TOTALE: </h4>
                                       <div class="col-xl-12">
                                           <div class="row">
                                               <p class="ordP">291 €</p>
                                           </div>
                                       </div>
                                   </div>
                               </div>
                               <div class="col-xl-6">
                                   <div class="row">
                                       <h4 class="upH43">SPEDITO A: </h4>
                                       <p class="ordP">Alessandro Bergamo, Mercato San Severino (SA), 84085</p>
                                   </div>
                               </div>
                           </div>
                           <div class="row justify-content-start test3">
                               <div class="col-xl-12">
                                   <div class="row">
                                       <h4 class="upH43">Consegnato: 2 Luglio 2019</h4>
                                   </div>
                                   <div class="row">
                                       <h4 class="uph44">Il pacco è stato consegnato presso il domicilio selezionato</h4>
                                   </div>
                                   <div class="row">
                                       <div class="col-xl-2">
                                           <img src="img/prodotti/sv1.jpg" class="imgItem7" width="auto" height="auto">
                                       </div>
                                       <div class="col-xl-3 spacerInfo">
                                           <h4 class="upH45">nVidia 1070 8GB DDR5</h4>
                                           <h4 class="upH45">Spedizione Gratuita</h4>
                                           <h4 class="upH45">293,80 €</h4>
                                           <input type="button" class="site-btn5" value="COMPRA DI NUOVO">
                                       </div>
                                       <div class="col-xl-4 spacerDetails" id="details">
                                           <h4 class="uph44" style="margin-bottom: 0px;">Metodo di pagamento:</h4>
                                           <h4 class="upH45">Paypal</h4>
                                           <h4 class="uph44" style="margin-bottom: 0px;">Spedito a:</h4>
                                           <h4 class="upH45">Via Francescantonio Biondo n°12 Mercato San Severino (SA)</h4>
                                       </div>
                                       <div class="col-xl-3 spacerBtnUP">
                                           <div class="row justify-content-center">
                                               <input type="button" class="site-btn6 setupbtnUP" value="Traccia il mio pacco">
                                           </div>
                                           <div class="row justify-content-center">
                                               <input type="button" class="site-btn6 setupbtnUP" id="detailsBtn" value="Dettagli dell'ordine">
                                           </div>
                                           <div class="row justify-content-center">
                                               <input type="button" class="site-btn6 setupbtnUP" value="Fattura">
                                           </div>
                                           <div class="row justify-content-center">
                                               <input type="button" class="site-btn6 setupbtnUP" value="Informativa sugli acquisti">
                                           </div>
                                       </div>
                                   </div>
                               </div>
                           </div>
                           <div class="row justify-content-start test2">
                               <div class="col-xl-12">
                                   <div class="row justify-content-end">
                                       <h4 class="upH43">Ordine</h4>
                                       <p class="ordP"># 239-103-9</p>
                                   </div>
                               </div>
                           </div>
                       </div>
                   </div>

                       </div>
           </section>
        </div>
        <!-- END SECTION -->


        <!-- Footer -->
        <%@ include file="footer.jsp" %>

            
    <script>

          document.getElementById('codcarta').addEventListener('input', function (e) {
          var target = e.target, position = target.selectionEnd, length = target.value.length;

              target.value = target.value.replace(/[^\dA-Z]/g, '').replace(/(.{4})/g, '$1 ').trim();
              target.selectionEnd = position += ((target.value.charAt(position - 1) === ' ' && target.value.charAt(length - 1) === ' ' && length !== target.value.length) ? 1 : 0);
          });

          $(function() {
              $("#dataexpcarta").datepicker({dateFormat: 'mm/yy'});
          });

          $("#list1").on('click', function() {
              $("#ordiv").hide();
              $("#utdiv").show();
          });

          $("#list2").on('click', function() {
              $("#utdiv").hide();
              $("#ordiv").show();
          });

          $("#btninfo").on('click', function() {
              $("#infPut").hide();
              $("#infPut2").show();
          });

          $("#btninfo2").on('click', function() {
              $("#infPut2").hide();
              $("#infPut").show();
          });

          $("#newInd").on('click', function() {
              $("#inputNewInd").toggle();
          });

          $("#newCard").on('click', function() {
              $("#inputNewCard").toggle();
          });

          $('#detailsBtn').click(function(){
          if ( $('#details').css('visibility') == 'hidden' )
              $('#details').css('visibility','visible');
          else
              $('#details').css('visibility','hidden');
          });

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
