<%@page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
        import="com.champloo.bean.*"
        import="java.util.*"
        import="java.time.format.DateTimeFormatter"
%>
<%@ page import="javafx.util.Pair" %>
<%@ page import="java.text.SimpleDateFormat" %>

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

    <!-- CDN SWEETALERT2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

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
        LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> orders = new LinkedHashMap <OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>>();

        orders = (LinkedHashMap) session.getAttribute("orders");
    %>

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
                        <a href="user_area_orders.jsp"><h4 class="utenteh42">Ordini</h4></a>
                    </div>
                </div>
            </div>

            <%
                if(orders != null && !orders.isEmpty())
            %>

            <!--- INIZIO DIV ORDINI --->
            <div class="container" id="ordiv">
                <div class="container spacerUP borderutdiv">
                    <%
                        Iterator iterator = orders.entrySet().iterator();
                        while(iterator.hasNext())
                        {
                            HashMap.Entry entry = (HashMap.Entry) iterator.next();
                            OrderBean order = (OrderBean) entry.getKey();
                            ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>> products_in_order = (ArrayList) entry.getValue();

                            String data_creazione = new SimpleDateFormat("dd-MM-yyyy").format(order.getCreation_date());

                    %>
                    <div class="container" style="margin-bottom: 15px">
                        <div class="row justify-content-start test">
                            <div class="col-xl-3">
                                <div class="row">
                                    <h4 class="upH43">ORDINE EFFETTUATO IL: </h4>
                                    <p class="ordP"><%=data_creazione%></p>
                                </div>
                            </div>
                            <div class="col-xl-3">
                                <div class="row">
                                    <h4 class="upH43">IMPORTO TOTALE: </h4>
                                    <div class="col-xl-12">
                                        <div class="row">
                                            <p class="ordP"><%=order.getTotal_price()%> &euro;</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="row">
                                    <h4 class="upH43">SPEDITO A: </h4>
                                    <p class="ordP"><%=order.getOrder_owner()%>, <%=order.getAddress().substring(order.getAddress().indexOf(",")+1)%></p>
                                </div>
                            </div>
                        </div>
                        <div class="row justify-content-start test3">
                            <div class="col-xl-12">
                                <%
                                    int status_order = order.getStatus_order();
                                    String status = null;
                                    String data_consegna = new SimpleDateFormat("dd-MM-yyyy").format(order.getDelivery_date());

                                    if(status_order == 1) {
                                        status = "Creato";
                                %>
                                    <div class="row">
                                        <h4 class="upH43">Status: In Elaborazione</h4>
                                    </div>
                                    <div class="row">
                                        <h4 class="uph44">L'ordine è stato preso in carico e sta per essere processato.</h4>
                                    </div>
                                <%
                                    } else if(status_order == 2) {
                                        status = "Pronto";
                                %>
                                    <div class="row">
                                        <h4 class="upH43">Status: Pronto</h4>
                                    </div>
                                    <div class="row">
                                        <h4 class="uph44">L'ordine è stato processato ed è pronto per essere spedito.</h4>
                                    </div>
                                <%
                                    } else if(status_order == 3) {
                                        status = "In transito";
                                %>
                                    <div class="row">
                                        <h4 class="upH43">Status: In Transito - Arriverà entro il: <%=data_consegna%></h4>
                                    </div>
                                    <div class="row">
                                        <h4 class="uph44">L'ordine è in viaggio verso la destinazione di consegna.</h4>
                                    </div>
                                <%
                                    } else if(status_order == 4) {
                                        status = "Ricevuto";
                                %>
                                    <div class="row">
                                        <h4 class="upH43">Consegnato il: <%=data_consegna%></h4>
                                    </div>
                                    <div class="row">
                                        <h4 class="uph44">L'ordine è in viaggio verso la destinazione di consegna.</h4>
                                    </div>
                                <%
                                    } else if(status_order == 5) {
                                        status = "Annullato";
                                %>
                                    <div class="row">
                                        <h4 class="upH43">Annullato il giorno: <%=data_consegna%></h4>
                                    </div>
                                    <div class="row">
                                        <h4 class="uph44">Ordine annullato.</h4>
                                    </div>
                                <%
                                    } else if(status_order == 6) {
                                %>
                                    <div class="row">
                                        <h4 class="upH43">Annullato il giorno: <%=data_consegna%></h4>
                                    </div>
                                    <div class="row">
                                        <h4 class="uph44">L'ordine è stato annullato. Contattare il <a href="contatti.jsp">supporto utenti</a> per saperne di più.</h4>
                                    </div>
                                <%
                                    }
                                %>
                                <%
                                    OrderItemBean order_item;
                                    ProductBean product;
                                    ProductDetailsBean productDetails;
                                    Pair<ProductBean, ProductDetailsBean> product_with_details;
                                    for(int I=0; I<products_in_order.size(); I++) {
                                        order_item = products_in_order.get(I).getKey();
                                        product_with_details = products_in_order.get(I).getValue();
                                        product = product_with_details.getKey();
                                        productDetails = product_with_details.getValue();
                                %>
                                <div class="row">
                                    <div class="col-xl-2">
                                        <img src=<%=productDetails.getImg_path_folder()%>img1.png" alt="" class="imgItem7" width="auto" height="auto">
                                    </div>
                                    <div class="col-xl-7 spacerInfo">
                                        <h4 class="upH45"><%=product.getBrand()+" - "+product.getName()%></h4>
                                        <h4 class="upH45"><%=order_item.getPrice_in_order()%> &euro; - Quantità: <%=order_item.getQnt_in_order()%></h4>
                                        <input type="hidden" id="id_product" value="<%=product.getId_prod()%>">
                                        <input type="hidden" id="id_product_details" value="<%=productDetails.getId_prod_details()%>">
                                        <input type="button" id="buyAgainButton" class="site-btn5" value="COMPRA DI NUOVO">
                                    </div>
                                    <%
                                        if(I==0) {
                                    %>
                                    <div class="col-xl-3 spacerBtnUP">
                                        <div class="row justify-content-center">
                                            <input type="button" class="site-btn6 setupbtnUP" value="Traccia il mio pacco">
                                        </div>
                                        <div class="row justify-content-center">
                                            <input disabled style="cursor: pointer;" class="site-btn6 setupbtnUP" id="detailsBtn" value="Dettagli dell'ordine">
                                        </div>
                                        <div class="row justify-content-center">
                                            <input type="button" class="site-btn6 setupbtnUP" value="Informativa sugli acquisti">
                                        </div>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <div class="row justify-content-start test2">
                            <div class="col-xl-12">
                                <div class="row justify-content-end">
                                    <h4 class="upH43">Ordine</h4>
                                    <p class="ordP">#372-<%=order.getId_order()%></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </section>

        <!-- END SECTION -->


        <!-- Footer -->
        <%@ include file="footer.jsp" %>

        <script>
            $("[id ='buyAgainButton']").on('click', function addtocart(){
                var value1 = $(this).prev().prev().val();
                var value2 = $(this).prev().val();
                var value3 = ("insertProduct");
                $.ajax({
                    type: "GET",
                    url: "Cart",
                    data: {"id_product" : value1, "id_product_details" : value2, "operation" : value3},
                    success: function(results){
                        Swal.fire({
                            title: 'Aggiunto al Carrello',
                            timer: 1700,
                            icon: 'success',
                            showCancelButton: false,
                            showConfirmButton: false,
                            width: '400px',
                        })
                        setTimeout(function(){ location.reload(); }, 1850);
                    },
                    error: function (result){
                        Swal.fire({
                            title: 'Non aggiunto al Carrello',
                            text: 'Prodotto non disponibile',
                            timer: 2000,
                            icon: 'error',
                            showCancelButton: false,
                            showConfirmButton: false,
                            width: '500px'
                        })
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
