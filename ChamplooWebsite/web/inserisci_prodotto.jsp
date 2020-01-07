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
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
</head>
<body>

<!-- Menu -->

<div class="menu">

        <!-- Search -->
        <div class="menu_search">
            <form action="#" id="menu_search_form" class="menu_search_form">
                <input type="text" class="search_input" placeholder="Search Item" required="required">
                <button class="menu_search_button"><img src="images/search.png" alt=""></button>
            </form>
        </div>
        <!-- Navigation -->
        <div class="menu_nav">
            <ul>
                <li><a href="index.html">Home</a></li>
                <li><a href="category.html">Donna</a></li>
                <li><a href="category.html">Uomo</a></li>
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
        <br>
        <br>
           
           
           
           
           
               <script>
               if($){inserimento=='true'}{
                   alert("Prodotto inserito correttamente");
               }
               if($){errore_inserimento=='true'}{
                   alert("Errore durante l'inserimento del prodotto");
               }
               </script>

           </head>
           <body>

               <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
               <script src="js/bootstrap.js"></script>
               <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
               <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

               <script>
                   jQuery.extend(jQuery.validator.messages, {
                       required: "Questo campo è richiesto",
                       remote: "C'è un errore in questo campo, controllare.",
                       name: "Inserire un valore adatto per il nome.",
                       email: "Inserire un indirizzo email valido.",
                       url: "Inserire un indirizzo URL valido.",
                       date: "Inserire una data valida.",
                       dateISO: "Digitare una data valida (ISO).",
                       number: "Digitare un numero valido.",
                       digits: "Digitare solo caratteri numerici.",
                       creditcard: "Digitare un numero valido di carta di credito.",
                       equalTo: "I campi password non corrispondono, controllare.",
                       accept: "Digitare un valore con una valida estensione.",
                       maxlength: $.validator.format("Digitare al massimo {0} caratteri."),
                       minlength: $.validator.format("Digitare almeno {0} caratteri."),
                       rangelength: $.validator.format("Inserire un valore compreso tra gli {0} ed i {1} caratteri."),
                       range: $.validator.format("Digitare un valore compreso tra {0} e {1}."),
                       max: $.validator.format("Inserire un valore minore o uguale di {0}."),
                       min: $.validator.format("Inserire un valore maggiore o uguale di {0}.")
                   });
                       
                           
                   $(document).ready(function(){
                       $('#insertproduct').validate({
                       rules: {
                           type_prod: {
                               minlength:     1,
                               maxlength:  10,
                               required:     true,
                           },
                           model_prod: {
                               required:    true,
                               minlenght:   1,
                               maxlenght:    10,
                               range:         [1, 99999],
                           },
                           brand_prod: {
                               required:     true,
                               minlenght:    1,
                               maxlenght:     20,
                           },
                           name_prod: {
                               minlenght:  1,
                               maxlenght:    30,
                               required:     true,
                           },
                           year_prod: {
                               minlenght:    4,
                               maxlenght:    4,
                               number:        true,
                               range:        [1970-2300],
                               required:     true,
                           },
                           description_prod: {
                               minlenght:    1,
                               maxlenght:    500,
                               required:     true,
                           },
                           price_prod: {
                               minlenght:    1,
                               maxlenght:    8,
                               required:    true,
                               range:         [0-80000],
                               number:        true,
                           },
                           discount_prod: {
                               minlenght:     1,
                               maxlenght:     99,
                               required:    true,
                               number:     true,
                           },
                           discounted_price_prod: {
                               minlenght:    1,
                               maxlenght:     8,
                               required:     true,
                               range:         [0-80000],
                               number:     true,
                           },
                           family_prod: {
                               minlenght:     1,
                               maxlength:     1,
                               required:     true,
                               range:         [0-4],
                               number:     true,
                           },
                           qnt_prod: {
                               minlengt:     1,
                               maxlength:     3,
                               required:     true,
                               range:         [0-999],
                               number:     true,
                           },
                           img_folder_path: {
                               required: true,
                               url: true,
                           }
                       },
                           highlight: function(element) {
                               $(element).closest('.control-group').removeClass('success').addClass('error');
                           },
                           success: function(element) {
                               element.text('OK!').addClass('valid').closest('.control-group').removeClass('error').addClass('success');
                           }
                       });
                   }); // end document.ready
               
               </script>
               
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
                       <form action="InsertProduct" id="insertproduct" method="GET">
                           <div class="row form-group">
                               <label for="type_prod" class="4 form-label">Tipo</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="type_prod" placeholder="Tipo Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="model_prod" class="4 form-label">Modello</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="model_prod" placeholder="Modello Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="brand_prod" class="4 form-label">Brand</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="brand_prod" placeholder="Brand Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="name_prod" class="4 form-label">Nome</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="name_prod" placeholder="Nome Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="year_prod" class="4 form-label">Anno</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="year_prod" placeholder="Anno Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="description_prod" class="4 form-label">Descrizione</label>
                               <div class="col-xl-8">
                                   <textarea rows="5" class="form-input" name="description_prod" placeholder="Descrizione Prodotto"></textarea>
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="price_prod" class="4 form-label">Prezzo</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="price_prod" placeholder="Prezzo Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="discount_prod" class="4 form-label">% Prodotto in Sconto</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="discount_prod" placeholder="% Prodotto in Sconto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="discounted_price_prod" class="4 form-label">Prezzo Prodotto in Sconto</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="discounted_price_prod" placeholder="Prezzo Prodotto in Sconto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="family_prod" class="4 form-label">Informazioni Prodotto</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="family_prod" placeholder="Informazioni Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="qnt_prod" class="4 form-label">Quantità Prodotto</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="qnt_prod" placeholder="Quantità Prodotto">
                               </div>
                           </div>
                           <div class="row form-group">
                               <label for="img_folder_path" class="4 form-label">Path Folder Immagini</label>
                               <div class="col-xl-8">
                                   <input type="text" class="form-input" name="img_folder_path" placeholder="Path Folder Prodotto">
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
