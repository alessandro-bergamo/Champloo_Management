<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="com.champloo.bean.*"
		import="java.util.*"
%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
	ArrayList<AddressBean> datiUtente = (ArrayList) request.getSession().getAttribute("addresses");
	ArrayList<PaymentMethodBean> paymentMethods = (ArrayList) request.getSession().getAttribute("methods");
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
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
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
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
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
                            <a href="user_area.jsp"><h4 class="utenteh4">Area Utente</h4></a>
                        </div>
                        <div class="col-xl-6">
                            <a href="Order?operation=showOrdersPerUser"><h4 class="utenteh42">Ordini</h4></a>
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
                   <form action="UserControl" name="userInfo" id="userInfo" method="POST">
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
                                   <h4 class="upH4">Password: &#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;</h4>
                                   <p class="upPCircle"><a href="modify-password.jsp">Cambia password</a></p>
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

                   <%
                       int stampati = 0;

                       if(!datiUtente.isEmpty() && datiUtente != null)
                       {

                   %>
                   <div class="container spacerUP3">
                       <div class="row justify-content-start">
                           <div class="col-xl-5">
                               <h4 style="color:#2fce98">Indirizzi di Spedizione</h4>
                           </div>
                       </div>
                       <div class="row borderutdiv">
                           <div class="col-xl-12">
                               <%
                                   for(int I=0;I<datiUtente.size(); I++)
                                   {
                                       stampati++;
                               %>
                               <div class="row justify-content-start">
                                   <div class="col-xl-6">
                                       <div class="row">
                                           <h4 class="upH4">Indirizzo: </h4>
                                           <p class="upPCircle"><%=datiUtente.get(I).getAddress()%>, <%=datiUtente.get(I).getCivic_number()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-5">
                                       <div class="row">
                                           <h4 class="upH4">Città: </h4>
                                           <p class="upPCircle"><%=datiUtente.get(I).getCity()%>, <%=datiUtente.get(I).getProvince()%>, <%=datiUtente.get(I).getCAP()%></p>
                                       </div>
                                   </div> 
                                   <div class="col-xl-1">
                                   		<div class="row">
                                            <input type="hidden" id="id_address" value="<%=datiUtente.get(I).getId_address()%>">
                                            <%
                                                if(I==0) {
                                            %>
                                                <img src="images/delete.png" id="deleteAddressID" height="30" width="30" style="cursor: pointer !important;">
                                            <%
                                                } else if(I==1) {
                                            %>
                                                <img src="images/delete.png" id="deleteAddressID" height="30" width="30" style="margin-top: -3px !important; cursor: pointer !important;">
                                            <%
                                                } else if(I==2) {
                                            %>
                                                <img src="images/delete.png" id="deleteAddressID" height="30" width="30" style="margin-top: -5px !important; cursor: pointer !important;">
                                            <%
                                                }
                                            %>
                                   		</div>
                                   </div>
                               </div>
                   <%               }
                       } if(stampati < 3) {   %>
                               <div class="row justify-content-start">
                                   <div class="col-xl-12">
                                       <div class="row">
                                           <h4 style="color:#2fce98; margin-left: 5px; cursor: pointer;" id="newInd">+ Inserisci nuovo indirizzo</h4>
                                       </div>
                                   </div>
                               </div>
                               <div class="row justify-content-start" id="inputNewInd">
                                   <form id="addressModify">
                                       <div class="col-xl-12">
                                           <div class="row">
                                               <div class="col-xl-4">
                                                   <input type="text" class="form-input" name="address" placeholder="Inserisci Indirizzo">
                                               </div>
                                               <div class="col-xl-4">
                                                   <input type="text" class="form-input" name="city" placeholder="Inserisci città">
                                               </div>
                                               <div class="col-xl-1">
                                                   <input type="text" class="form-input3" id="province" maxlength="2" name="province" placeholder="Provincia">
                                               </div>
                                           </div>
                                           <div class="row spacerUP2 justify-content-start">
                                               <div class="col-xl-4">
                                                   <input type="text" class="form-input" name="cap" placeholder="Inserisci CAP">
                                               </div>
                                               <div class="col-xl-3">
                                                   <input type="text" class="form-input3" style="margin-left: 0px !important; width: 200px !important;" name="civic_number" maxlength="5" placeholder="Inserisci numero civico">
                                               </div>
                                               <div class="col-xl-5">
                                                   <div class="row justify-content-center">
                                                       <input type="button" class="form-button" onclick="insertAddress()" value="Salva Indirizzo" style="margin-top: 5px; padding: 0px !important;">
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </form>
                               </div>
                       <%   } %>
                           </div>
                       </div>
                   </div>
                   <%
                       int methods_stampati = 0;

                       if(!paymentMethods.isEmpty() && paymentMethods != null)
                       {
                   %>
                   <div class="container spacerUP3">
                       <div class="row justify-content-start">
                           <div class="col-xl-5">
                               <h4 style="color:#2fce98">Metodi di Pagamento</h4>
                           </div>
                       </div>
                       <div class="row borderutdiv">
                           <div class="col-xl-12 spacerMethods">
                               <%
                                   for(int I=0;I<paymentMethods.size(); I++)
                                   {
                                       methods_stampati++;
                               %>
                               <div class="row spacerUP4 justify-content-start">
                                   <div class="col-xl-4">
                                       <div class="row">
                                           <h4 class="upH4">Tipo Carta: </h4>
                                           <p class="upPCircle"><%=paymentMethods.get(I).getCard_bank()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-5">
                                       <div class="row">
                                           <h4 class="upH4">Intestatario Carta: </h4>
                                           <p class="upPCircle"><%=paymentMethods.get(I).getCard_owner()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-3">
                                       <div class="row">
                                           <h4 class="upH4">Scadenza Carta: </h4>
                                           <p class="upPCircle"><%=paymentMethods.get(I).getExpiry_date()%></p>
                                       </div>
                                   </div>
                               </div>
                               <div class="row spacerUP2 justify-content-start">
                                   <div class="col-xl-6">
                                       <div class="row">
                                           <h4 class="upH4">Codice Carta: </h4>
                                           <p class="upPCircle"><%=paymentMethods.get(I).getCard_number()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-5">
                                       <div class="row">
                                           <h4 class="upH4">Codice CVC: </h4>
                                           <p class="upPCircle">&#8226;&#8226;&#8226;
                                           
                                           </p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                  		<div class="row">
                                            <input type="hidden" value="<%=paymentMethods.get(I).getId_method()%>">
                                  			<img src="images/delete.png" style="margin-top: -10px; cursor:pointer;" id="deleteMethod" height="42" width="42">
                                  	 	</div>
                                   </div>
                               </div>
                               <%
                                   }
                               %>
                           <%
                                } if(methods_stampati < 3 && methods_stampati!=0) {   %>
                                <div class="row justify-content-start">
                                    <div class="col-xl-12" style="margin-top: 10px;">
                                        <h4 style="color:#2fce98; margin-left: 5px; margin-bottom: 15px; cursor: pointer;" id="newCard">+ Inserisci nuovo Metodo di Pagamento</h4>
                                    </div>
                                </div>
                                <div class="row justify-content-start" id="inputNewCard">
                                <form id="insertPaymentMethod">
                                    <div class="row">
                                        <div class="col-xl-3">
                                            <select class="form-control" id="paymentMethodType" style="margin-left: 20px !important;">
                                                <option value="N26">N26</option>
                                                <option value="Hype">Hype</option>
                                                <option value="Paypal">Paypal</option>
                                                <option value="Postepay">Postepay</option>
                                                <option value="ViaBuy">ViaBuy</option>
                                            </select>
                                        </div>
                                        <div class="col-xl-4">
                                            <input type="text" class="form-input2" id="number" name="number" maxlength="16" autocomplete="off" placeholder="Codice Carta">
                                        </div>
                                        <div class="col-xl-2">
                                            <input type="password" class="form-input4" name="cvc" id="cvc" maxlength="3" autocomplete="off" placeholder="Codice CVC">
                                        </div>
                                        <div class="col-xl-2">
                                             <input type="text" class="form-input4" name="expiry" id="txtDate" autocomplete="off" style="margin-left: 40px" placeholder="Data scadenza">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xl-7">
                                            <div class="row justify-content-center">
                                                <input type="text" class="form-input4" style="width: 220px; margin-top: 20px !important;" id="owner" autocomplete="off" placeholder="Proprietario Carta">
                                            </div>
                                        </div>
                                        <div class="col-xl-5">
                                            <div class="row justify-content-center">
                                                <input type="button" class="site-btn7" onclick="insertMethod()" value="Salva Carta" style="margin-top: 25px; cursor: pointer !important;">
                                            </div>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                           <%   } else if(methods_stampati == 0) {  %>
                               <div class="container spacerUP3">
                                   <div class="row justify-content-start">
                                       <div class="col-xl-5">
                                           <h4 style="color:#2fce98">Metodi di Pagamento</h4>
                                       </div>
                                   </div>
                                   <div class="row justify-content-start">
                                       <div class="col-xl-12" style="margin-top: 10px;">
                                           <h4 style="color:#2fce98; margin-left: 5px; margin-bottom: 15px; cursor: pointer;" id="newCard">+ Inserisci nuovo Metodo di Pagamento</h4>
                                       </div>
                                   </div>
                                   <div class="row justify-content-start" id="inputNewCard">
                                   <form id="insertPaymentMethod">
                                       <div class="row">
                                           <div class="col-xl-3">
                                               <select class="form-control" id="paymentMethodType" style="margin-left: 20px !important;">
                                                   <option value="N26">N26</option>
                                                   <option value="Hype">Hype</option>
                                                   <option value="Paypal">Paypal</option>
                                                   <option value="Postepay">Postepay</option>
                                                   <option value="ViaBuy">ViaBuy</option>
                                               </select>
                                           </div>
                                           <div class="col-xl-4">
                                               <input type="text" class="form-input2" id="number" name="number" maxlength="16" autocomplete="off" placeholder="Codice Carta">
                                           </div>
                                           <div class="col-xl-2">
                                               <input type="password" class="form-input4" name="cvc" id="cvc" maxlength="3" autocomplete="off" placeholder="Codice CVC">
                                           </div>
                                           <div class="col-xl-2">
                                               <input type="text" class="form-input4" name="expiry" id="txtDate" autocomplete="off" style="margin-left: 40px" placeholder="Data scadenza">
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="col-xl-7">
                                               <div class="row justify-content-center">
                                                   <input type="text" class="form-input4" style="width: 220px; margin-top: 20px !important;" id="owner" autocomplete="off" placeholder="Proprietario Carta">
                                               </div>
                                           </div>
                                           <div class="col-xl-5">
                                               <div class="row justify-content-center">
                                                   <input type="button" class="site-btn7" onclick="insertMethod()" value="Salva Carta" style="margin-top: 25px; cursor: pointer !important;">
                                               </div>
                                           </div>
                                       </div>
                                       </form>
                                   </div>
                               </div>
                           <%   }   %>
                       </div>
                       </div>
                   </div>
                </div>
            </section>
        </div>
    </div>
    <script>
        $(document).ready(function() {
            $('#txtDate').datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'MM yy',

                onClose: function() {
                    var iMonth = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                    var iYear = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                    $(this).datepicker('setDate', new Date(iYear, iMonth, 1));
                },

                beforeShow: function() {
                    if ((selDate = $(this).val()).length > 0)
                    {
                        iYear = selDate.substring(selDate.length - 4, selDate.length);
                        iMonth = jQuery.inArray(selDate.substring(0, selDate.length - 5), $(this).datepicker('option', 'monthNames'));
                        $(this).datepicker('option', 'defaultDate', new Date(iYear, iMonth, 1));
                        $(this).datepicker('setDate', new Date(iYear, iMonth, 1));
                    }
                }
            });
        });
    </script>

    <script>

          $(function() {
              $("#txtDate").datepicker({dateFormat: 'mm/yy'});
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

    </script>

    <script>
        function insertMethod()
        {
            var value1 = $("#paymentMethodType").val();
            var value2 = $("#number").val();
            var value3 = $("#cvc").val();
            var value4 = $("#txtDate").val();
            var value5 = $("#owner").val();
            $.ajax({
                type: "POST",
                url: "PaymentMethod",
                data: {"bank": value1, "number": value2, "cvc": value3, "owner" : value5, "expiry" : value4, "operation" : "insert"},
                success: function (results) {
                    Swal.fire({
                        title: 'Metodo di Pagamento Aggiunto',
                        timer: 1700,
                        icon: 'success',
                        showCancelButton: false,
                        showConfirmButton: false,
                        width: '600px',
                    }); setTimeout(function(){location.reload()} , 1350);
                },
            });
        }
    </script>

    <script>
        $("[id = 'deleteMethod']").on('click', function deleteMethod(){
            var value1 = $(this).prev().val();
            var value2 = ("delete");
            Swal.fire({ //PRIMO POPUP
                title: "Sei sicuro di voler eliminare il metodo di pagamento?",
                text: "Verrà eliminato permanentemente.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Elimina',
                cancelButtonText: 'Annulla',
                width: '800px'
            }).then((result) => {
                if (result.value) {
                    $.ajax({
                        type: "GET",
                        url: "PaymentMethod",
                        data: {"operation": value2, "id_pmethod": value1},
                        success: function (results) {
                            Swal.fire({ //SECONDO POPUP
                                title: 'Metodo Eliminato',
                                timer: 1200,
                                icon: 'success',
                                showCancelButton: false,
                                showConfirmButton: false,
                                width: '500px',
                            });
                            setTimeout(function () {location.reload()}, 1350);
                        }
                    });
                }
            });
        });
    </script>

    <script>
        $("[id = 'deleteAddressID']").on('click', function deleteAddress() {
            var value1 = $(this).prev().val();
            var value2 = ("deleteAddress");
            Swal.fire({ //PRIMO POPUP
                title: "Sei sicuro di voler eliminare l'indirizzo?",
                text: "L'indirizzo sarà eliminato permanentemente.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Elimina',
                cancelButtonText: 'Annulla',
                width: '600px'
            }).then((result) => {
                if (result.value) {
                    $.ajax({
                        type: "GET",
                        url: "Address",
                        data: {"operation": value2, "id_address": value1},
                        success: function (results) {
                            Swal.fire({ //SECONDO POPUP
                                title: 'Indirizzo Eliminato',
                                timer: 1200,
                                icon: 'success',
                                showCancelButton: false,
                                showConfirmButton: false,
                                width: '500px',
                            });
                            setTimeout(function () {
                                location.reload()
                            }, 1350);
                        }
                    });
                }
            });
        });
    </script>

    <script>
        function insertAddress()
        {
            var value1 = $("input[name=address]").val();
            var value2 = $("input[name=city]").val();
            var value3 = $("input[name=province]").val();
            var value4 = $("input[name=civic_number]").val();
            var value5 = $("input[name=cap]").val();
            $.ajax({
                type: "POST",
                url: "Address",
                data: {"address": value1, "city": value2, "province": value3, "civic_number" : value4, "cap" : value5, "operation" : "insert"},
                success: function (results) {
                    Swal.fire({
                        title: 'Indirizzo Aggiunto',
                        timer: 1700,
                        icon: 'success',
                        showCancelButton: false,
                        showConfirmButton: false,
                        width: '400px',
                    }); setTimeout(function(){location.reload()} , 1350);
                },
            });
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
    <script src="/ChamplooWebsite/js/userAreaForm.js"></script>
    <script src="/ChamplooWebsite/js/addressForm.js"></script>
    <script src="/ChamplooWebsite/js/paymentMethodForm.js"></script>
    <script src="js/logout.js"></script>

</body>

</html>
