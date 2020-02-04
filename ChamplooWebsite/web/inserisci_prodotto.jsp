<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
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
   <script src="/ChamplooWebsite/js/aggiuntaProdotto.js"></script>
    <!-- STILI CSS -->
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
</head>
<body>

<!-- Menu -->

<div class="menu">

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


        <div class="super_container_inner">
            <div class="super_overlay"></div>
        <br>
        <br>
           
           
            

         <br>
         <br>
         <br>
               <!-- Header section end -->


               <section class="insform-pages">
                   <div class="container">
                       <div class="row justify-content-center">
                           <h1>Inserisci Prodotto</h1>
                       </div>
                   </div>
                   <div class="container border-insformpage">
                       <form action="Product" id="aggiuntaProdotto" name="aggiuntaProdotto" method="GET">
                           <div class="row form-group">
                           <input name="operation" type="hidden" value="addProduct"> 
                               <label for="type_product" class="4 form-label">Tipo</label>
                               <div class="col-xl-8">
                                   <select class="form-input" name="type_product" id="type_product">
                                   <option disabled="disabled" selected="" value="default">Seleziona il tipo di prodotto</option>
                                   <option value=T-shirt>T-shirt</option>
                                   <option value="Felpa">Felpa</option>
                                   <option value="Camicia">Camicia</option>
                                   <option value="Giacca">Giacca</option>
                                   <option value="Giubbino">Giubbino</option>
                                   <option value="Jeans">Jeans</option>
                                   <option value="Canotta">Canotta</option>
                                   <option value="Tuta">Tuta</option>
                                   <option value="Pantaloncino">Pantaloncino</option>
                                   </select>
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="model_product" class="4 form-label">Modello</label>
                               <div class="col-xl-8">
                                   <input type="text"  class="form-input" id="model_product"  name="model_product" placeholder="Modello Prodotto"> 
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="brand_product" class="4 form-label">Brand</label>
                               <div class="col-xl-8">
                                   <select class="form-input" name="brand_product">
                                   <option disabled="disabled" selected="" value="default">Seleziona il brand del prodotto</option>
                                   <option value="Nike">Nike</option>
                                   <option value="Adidas">Adidas</option>
                                   <option value="Supreme">Supreme</option>
                                   <option value="Huf">Huf</option>
                                   <option value="Levis">Levi's</option>
                                   <option value="Vans">Vans</option>
                                   <option value="Reebok">Reebok</option>
                                   <option value="Dickies">Dickies</option>
                                   <option value="Helly-Hansen">Helly-Hansen</option>
                                   </select>
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="name_product" class="4 form-label">Nome</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" id="name_product" name="name_product" placeholder="Nome Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="description_product" class="4 form-label">Descrizione</label>
                               <div class="col-xl-8">
                                   <textarea rows="3" class="form-input" name="description_product" placeholder="Descrizione Prodotto"></textarea>
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="size_product" class="4 form-label">Taglia</label>
                               <div class="col-xl-8">
                                   <select class="form-input" name="size_product">
                                   <option disabled="disabled" selected="" value="default">Seleziona la taglia del prodotto</option>
                                   <option value="XS">XS</option>
                                   <option value="S">S</option>
                                   <option value="M">M</option>
                                   <option value="L">L</option>
                                   <option value="XL">XL</option>
                                   </select>
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="color_product" class="4 form-label">Colore</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="color_product" placeholder="Colore Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="price_product" class="4 form-label">Prezzo</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="price_product" placeholder="Prezzo Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="discount_product" class="4 form-label">% Prodotto in Sconto</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="discount_percent_product" placeholder="% Prodotto in Sconto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="discounted_price_product" class="4 form-label">Prezzo Prodotto in Sconto</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="discounted_price_product" placeholder="Prezzo Prodotto in Sconto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="qnt_product" class="4 form-label">Quantità Prodotto</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="qnt_stock_product" placeholder="Quantità Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="status_product" class="4 form-label">Status</label>
                               <div class="col-xl-8">
                                   <select class="form-input" name="status_product">
                                   <option disabled="disabled" selected="" value="default">Seleziona lo status del prodotto</option>
                                   <option value="1">Prodotto normale</option>
                                   <option value="2">Prodotto nuovo</option>
                                   <option value="3">Prodotto in sconto</option>
                                   <option value="4">Prodotto in vetrina</option>                                   
                   					</select>
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="img_folder_path_product" class="4 form-label">Path Folder Immagini</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="img_path_folder_product" placeholder="Path Folder Prodotto" value="img/">
                               </div>
                           </div>
                           <div class="row justify-content-center">
                               <input type="submit" class="form-button" value="Invia">
                               <input type="reset" class="form-button" value="Reset">
                           </div>
                       </form>
                   </div>
               </section>                 
                    <!-- Footer -->
               			<%@ include file="footer.jsp" %>

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
           
            

</body>

</html>