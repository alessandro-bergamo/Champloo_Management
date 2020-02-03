<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="com.champloo.bean.*"
		import="java.util.*"
%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.text.SimpleDateFormat" %>


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

        <%
            if(utenteLoggato.getType()==1)
                response.sendRedirect("index.jsp");

            ArrayList<UserBean> users = new ArrayList<UserBean>();
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
            HashMap<OrderBean, ArrayList<OrderItemBean>> orders = new HashMap<OrderBean, ArrayList<OrderItemBean>>();

            if(utenteLoggato.getType()==2)
                users = (ArrayList) session.getAttribute("users");
            else if(utenteLoggato.getType()==3)
                products = (HashMap) session.getAttribute("products");
            else if(utenteLoggato.getType()==4)
                orders = (HashMap) session.getAttribute("orders");

        %>

        <div class="super_container_inner">
            <div class="super_overlay"></div>
           
           <!-- MAIN SECTION Admin-->
           
           <section class="admin-section">
               <div class="container">
                   <div class="row justify-content-center">
                       <h1>Area Admin - Benvenuto Admin <%=utenteLoggato.getFirstName()%></h1>
                   </div>
               </div>
               <div class="container border-adminpage">
                   <div class="row">
                       <div class="col-xl-12">
                           <%
                                if(utenteLoggato.getType()==2)
                                {
                           %>
                           <div class="container divutenti" id="utdiv">
                               <%
                                    if(users != null)
                                    {
                               %>
                               <div class="row justify-content-end">
                                   <h4 class="uth4">Utenti registrati: <%=users.size()%></h4>
                               </div>
                               <%       for(int I=0;I<users.size();I++)
                                        {
                               %>
                               <div class="row spacerUt">
                                   <div class="col-xl-11">
                                       <div class="row">
                                           <div class="col-xl-4">
                                               <p id="utP">Nome:</p><p id="utP2"><%=users.get(I).getFirstName()%></p>
                                           </div>
                                           <div class="col-xl-4">
                                               <p id="utP">Cognome:</p><p id="utP2"><%=users.get(I).getSurname()%></p>
                                           </div>
                                           <div class="col-xl-4">
                                               <p id="utP">Username:</p><p id="utP2"><%=users.get(I).getUsername()%></p>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="col-xl-6">
                                               <p id="utP">E-mail:</p><p id="utP2"><%=users.get(I).getEmail()%></p>
                                           </div>
                                           <div class="col-xl-4">
                                               <%
                                                   String status = null;
                                                   if(users.get(I).getType()==1)
                                                       status="Utente";
                                                   else if(users.get(I).getType()==2)
                                                       status="Manager Utenti";
                                                   else if(users.get(I).getType()==3)
                                                       status="Manager Prodotti";
                                                   else if(users.get(I).getType()==4)
                                                       status="Manager Ordini";
                                                   else if(users.get(I).getType()==5)
                                                       status="Utente Bloccato";
                                               %>
                                               <p id="utP">Status:</p><p id="utP2"><%=status%></p>
                                           </div>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <input type="hidden" value="<%=users.get(I).getID()%>">
                                       <img src="images/block.png" id="blockUser" style="cursor: pointer;">
                                       <i class="glyphicon glyphicon-remove remove"> </i>
                                   </div>
                               </div>
                               <%       }
                                    } %>
                           </div>
                           <%   } else if(utenteLoggato.getType()==3)
                                {
                           %>
                           <div class="container divprod" id="prodiv">
                               <%
                                    if(products != null)
                                    {
                               %>
                               <div class="row justify-content-center">
                                   <a href="inserisci_prodotto.jsp"><h4>Inserisci Prodotto</h4></a>
                               </div>
                               <%
                                        Iterator iterator = products.entrySet().iterator();
                                        while(iterator.hasNext())
                                        {
                                            HashMap.Entry entry = (HashMap.Entry) iterator.next();
                                            ProductBean product = (ProductBean) entry.getKey();
                                            ArrayList<ProductDetailsBean> productDetails = (ArrayList) entry.getValue();

                                            System.out.println("SIZE: "+productDetails.size());

                                            for(int I=0; I<productDetails.size(); I++)
                                            {
                               %>
                               <div class="row spacerProd">
                                   <div class="col-xl-1">
                                       <img src=<%=productDetails.get(I).getImg_path_folder()%>img1.png" alt="" style="width: auto; height: auto;">
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Tipo:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2"><%=product.getType()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Marca:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2"><%=product.getBrand()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Nome:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2"><%=product.getName()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-2">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Modello:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2"><%=product.getModel()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Taglia:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2"><%=productDetails.get(I).getSize()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Prezzo:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2"><%=product.getPrice()%> &euro;</p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Status:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <%
                                                int status_prod = product.getStatus();
                                                String status = null;

                                                if(status_prod == 1)
                                                    status = "Normale";
                                                else if(status_prod == 2)
                                                    status = "Scontato";
                                                else if(status_prod == 3)
                                                    status = "Slider";
                                                else if(status_prod == 4)
                                                    status = "Vetrina";
                                                else if(status_prod == 5)
                                                    status = "Nuovo";
                                           %>
                                           <p id="utProd2"><%=status%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Colore:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2"><%=productDetails.get(I).getColor()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <p id="utProd">Quantit&agrave;:</p>
                                       </div>
                                       <div class="row justify-content-center">
                                           <p id="utProd2"><%=productDetails.get(I).getQnt_stock()%></p>
                                       </div>
                                   </div>
                                   <div class="col-xl-1">
                                       <div class="row justify-content-center">
                                           <a href="Product?operation=modifyProduct&prod_id=<%=product.getId_prod()%>"><img src="images/setting.png" height="32" width="32"><i class="glyphicon glyphicon glyphicon-wrench wrench"> </i></a>
                                       </div>
                                       <div class="row justify-content-center">
                                           <input type="hidden" id="product_id" value="<%=product.getId_prod()%>">
                                           <img src="images/delete.png" id="deleteProdID" style="cursor: pointer;" height="42" width="42"><i class="glyphicon glyphicon-remove remove2"> </i>
                                       </div>
                                   </div>
                               </div>
                               <%
                                            }
                                       }
                                   }
                               %>
                           </div>
                           <%
                                } else if(utenteLoggato.getType()==4)
                                {
                           %>
                           <div class="container divordini" id="ordiv">
                               <%
                                    if(orders != null)
                                    {
                                        Iterator iterator = orders.entrySet().iterator();
                                        while(iterator.hasNext())
                                        {
                                            HashMap.Entry entry = (HashMap.Entry) iterator.next();
                                            OrderBean order = (OrderBean) entry.getKey();
                                            ArrayList<OrderItemBean> order_items = (ArrayList) entry.getValue();
                               %>
                               <div class="row spacerUt">
                                   <div class="col-xl-11">
                                       <%
                                           int status_order = order.getStatus_order();
                                           String status = null;
                                           if(status_order == 1)
                                               status = "Creato";
                                           else if(status_order == 2)
                                               status = "Pronto";
                                           else if(status_order == 3)
                                               status = "In transito";
                                           else if(status_order == 4)
                                               status = "Ricevuto";
                                           else if(status_order == 5)
                                               status = "Annullato";

                                       %>
                                       <div class="row">
                                           <div class="col-xl-4">
                                               <p id="utP">ID Ordine:</p><p id="utP2">#372-<%=order.getId_order()%></p>
                                           </div>
                                           <div class="col-xl-8">
                                               <p id="utP">Status:</p><p id="utP2"><%=status%></p>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="col-xl-6">
                                               <p id="utP">Utente Ordine:</p><p id="utP2"><%=order.getOrder_owner()%></p>
                                           </div>
                                           <div class="col-xl-6">
                                               <p id="utP">Metodo di Pagamento:</p><p id="utP2"><%=order.getPayment_method()%></p>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="col-xl-8">
                                               <p id="utP">Indirizzo:</p><p id="utP2"><%=order.getAddress()%></p>
                                           </div>
                                           <div class="col-xl-4">
                                               <p id="utP">Prezzo Totale:</p><p id="utP2"><%=order.getTotal_price()%> &euro;</p>
                                           </div>
                                       </div>
                                       <%
                                           String data_creazione = new SimpleDateFormat("dd-MM-yyyy").format(order.getCreation_date());
                                           String data_arrivo = new SimpleDateFormat("dd-MM-yyyy").format(order.getDelivery_date());
                                       %>
                                       <div class="row">
                                           <div class="col-xl-6">
                                               <p id="utP">Data Creazione Ordine:</p><p id="utP2"><%=data_creazione%></p>
                                           </div>
                                           <div class="col-xl-6">
                                               <p id="utP">Data Arrivo Ordine:</p><p id="utP2"><%=data_arrivo%></p>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="col-xl-12">
                                               <p id="utP" style="font-size: 15px; color: red;">Prodotti</p>
                                           </div>
                                       </div>
                                       <%

                                           for(int I=0; I<order_items.size(); I++)
                                           {
                                       %>
                                       <div class="row">
                                           <div class="col-xl-3">
                                               <p id="utP">Codice:</p><p id="utP2">#<%=order_items.get(I).getId_order_item()%></p>
                                           </div>
                                           <div class="col-xl-3">
                                               <p id="utP">Prezzo:</p><p id="utP2"><%=order_items.get(I).getPrice_in_order()%> &euro;</p>
                                           </div>
                                           <div class="col-xl-2">
                                               <p id="utP">Quantità:</p><p id="utP2"><%=order_items.get(I).getQnt_in_order()%></p>
                                           </div>
                                       </div>
                                       <%
                                           }
                                       %>
                                   </div>
                                   <div class="col-xl-1">
                                       <a href="Order?operation=showOrder&order_id=<%=order.getId_order()%>"><img src="images/setting.png" height="32" width="32" style="margin-top: 20px;"><i class="glyphicon glyphicon glyphicon-wrench wrench"> </i></a>
                                   </div>
                               </div>
                               <%
                                        }
                               %>
                           </div>
                           <%       }
                                }%>
                       </div>
                   </div>
               </div>
           </section>
           <!-- END SECTION -->

        <!-- Footer -->
                   
		<%@ include file="footer.jsp" %>

        </div>
    </div>

    <script>
        $("[id = 'deleteProdID']").on('click', function deleteProductt() {
            var value1 = $(this).prev().val();
            var value2 = ("deleteProduct");
            Swal.fire({ //PRIMO POPUP
                title: "Sei sicuro di voler bloccare l'utente?",
                text: "Assicurati di aver dei motivi ben validi per farlo.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Blocca',
                cancelButtonText: 'Annulla',
                width: '700px'
            }).then((result) => {
                if (result.value) {
                    $.ajax({
                        type: "POST",
                        url: "Product",
                        data: {"operation": value2, "id_product": value1},
                        success: function (results) {
                            Swal.fire({ //SECONDO POPUP
                                title: 'Utente Bloccato con successo',
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
        $("[id = 'blockUser']").on('click', function blockUser() {
            var value1 = $(this).prev().val();
            var value2 = ("blockUser");
            Swal.fire({ //PRIMO POPUP
                title: "Sei sicuro di voler bloccare l'utente?",
                text: "Assicurati di aver dei motivi ben validi per farlo.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Blocca',
                cancelButtonText: 'Annulla',
                width: '700px'
            }).then((result) => {
                if (result.value) {
                    $.ajax({
                        type: "POST",
                        url: "UserControl",
                        data: {"operation": value2, "user_id": value1},
                        success: function (results) {
                            Swal.fire({ //SECONDO POPUP
                                title: 'Utente Bloccato con successo',
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
