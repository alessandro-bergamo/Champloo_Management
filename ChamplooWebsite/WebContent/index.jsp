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

            <!-- Home -->

            <div class="home">
                <!-- Home Slider -->
                <div class="home_slider_container">
                    <div class="owl-carousel owl-theme home_slider">
                        
                        <!-- Slide -->
                        <div class="owl-item">
                            <div class="background_image" style="background-image:url(images/home.jpg)"></div>
                            <div class="container fill_height">
                                <div class="row fill_height">
                                    <div class="col fill_height">
                                        <div class="home_container d-flex flex-column align-items-center justify-content-start">
                                            <div class="home_content">
                                                <div class="home_title">Nuovi arrivi</div>
                                                <div class="home_subtitle">Collezione estiva</div>
                                                <div class="home_items">
                                                    <div class="row">
                                                        <div class="col-sm-3 offset-lg-1">
                                                            <div class="home_item_side"><a href="product.html"><img src="images/home_1.jpg" alt=""></a></div>
                                                        </div>
                                                        <div class="col-lg-4 col-md-6 col-sm-8 offset-sm-2 offset-md-0">
                                                            <div class="product home_item_large">
                                                                <div class="product_tag d-flex flex-column align-items-center justify-content-center">
                                                                    <div>
                                                                        <div>A partire da</div>
                                                                        <div>$3<span>.99</span></div>
                                                                    </div>
                                                                </div>
                                                                <div class="product_image"><img src="images/home_2.jpg" alt=""></div>
                                                                <div class="product_content">
                                                                    <div class="product_info d-flex flex-row align-items-start justify-content-start">
                                                                        <div>
                                                                            <div>
                                                                                <div class="product_name"><a href="product.html">Nome prodotto</a></div>
                                                                                <div class="product_category">In <a href="category.html">Nome categoria</a></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="ml-auto text-right">
                                                                            <div class="rating_r rating_r_4 home_item_rating"><i></i><i></i><i></i><i></i><i></i></div>
                                                                            <div class="product_price text-right">$3<span>.99</span></div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="product_buttons">
                                                                        <div class="text-right d-flex flex-row align-items-start justify-content-start">
                                                                            <div class="product_button product_fav text-center d-flex flex-column align-items-center justify-content-center">
                                                                                <div><div><img src="images/heart.svg" alt=""><div>+</div></div></div>
                                                                            </div>
                                                                            <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">
                                                                                <div><div><img src="images/cart_2.svg" alt=""><div>+</div></div></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="home_item_side"><a href="product.html"><img src="images/home_3.jpg" alt=""></a></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Slide -->
                        <div class="owl-item">
                            <div class="background_image" style="background-image:url(images/home.jpg)"></div>
                            <div class="container fill_height">
                                <div class="row fill_height">
                                    <div class="col fill_height">
                                        <div class="home_container d-flex flex-column align-items-center justify-content-start">
                                            <div class="home_content">
                                                <div class="home_title">Popolari</div>
                                                <div class="home_subtitle">Collezione estiva</div>
                                                <div class="home_items">
                                                    <div class="row">
                                                        <div class="col-sm-3 offset-lg-1">
                                                            <div class="home_item_side"><a href="product.html"><img src="images/home_1.jpg" alt=""></a></div>
                                                        </div>
                                                        <div class="col-lg-4 col-md-6 col-sm-8 offset-sm-2 offset-md-0">
                                                            <div class="product home_item_large">
                                                                <div class="product_tag d-flex flex-column align-items-center justify-content-center">
                                                                    <div>
                                                                        <div>A partire da</div>
                                                                        <div>$3<span>.99</span></div>
                                                                    </div>
                                                                </div>
                                                                <div class="product_image"><img src="images/product_1.jpg" alt=""></div>
                                                                <div class="product_content">
                                                                    <div class="product_info d-flex flex-row align-items-start justify-content-start">
                                                                        <div>
                                                                            <div>
                                                                                <div class="product_name"><a href="product.html">Nome oggetto</a></div>
                                                                                <div class="product_category">In <a href="category.html">Nome categoria</a></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="ml-auto text-right">
                                                                            <div class="rating_r rating_r_4 home_item_rating"><i></i><i></i><i></i><i></i><i></i></div>
                                                                            <div class="product_price text-right">$3<span>.99</span></div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="product_buttons">
                                                                        <div class="text-right d-flex flex-row align-items-start justify-content-start">
                                                                            <div class="product_button product_fav text-center d-flex flex-column align-items-center justify-content-center">
                                                                                <div><div><img src="images/heart.svg" alt=""><div>+</div></div></div>
                                                                            </div>
                                                                            <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">
                                                                                <div><div><img src="images/cart_2.svg" alt=""><div>+</div></div></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="home_item_side"><a href="product.html"><img src="images/home_3.jpg" alt=""></a></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Slide -->
                        <div class="owl-item">
                            <div class="background_image" style="background-image:url(images/home.jpg)"></div>
                            <div class="container fill_height">
                                <div class="row fill_height">
                                    <div class="col fill_height">
                                        <div class="home_container d-flex flex-column align-items-center justify-content-start">
                                            <div class="home_content">
                                                <div class="home_title">Trend</div>
                                                <div class="home_subtitle">Estivi</div>
                                                <div class="home_items">
                                                    <div class="row">
                                                        <div class="col-sm-3 offset-lg-1">
                                                            <div class="home_item_side"><a href="product.html"><img src="images/home_1.jpg" alt=""></a></div>
                                                        </div>
                                                        <div class="col-lg-4 col-md-6 col-sm-8 offset-sm-2 offset-md-0">
                                                            <div class="product home_item_large">
                                                                <div class="product_tag d-flex flex-column align-items-center justify-content-center">
                                                                    <div>
                                                                        <div>A partire da</div>
                                                                        <div>$3<span>.99</span></div>
                                                                    </div>
                                                                </div>
                                                                <div class="product_image"><img src="images/product_2.jpg" alt=""></div>
                                                                <div class="product_content">
                                                                    <div class="product_info d-flex flex-row align-items-start justify-content-start">
                                                                        <div>
                                                                            <div>
                                                                                <div class="product_name"><a href="product.html">Nome oggetto</a></div>
                                                                                <div class="product_category">In <a href="category.html">Nome categoria</a></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="ml-auto text-right">
                                                                            <div class="rating_r rating_r_4 home_item_rating"><i></i><i></i><i></i><i></i><i></i></div>
                                                                            <div class="product_price text-right">$3<span>.99</span></div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="product_buttons">
                                                                        <div class="text-right d-flex flex-row align-items-start justify-content-start">
                                                                            <div class="product_button product_fav text-center d-flex flex-column align-items-center justify-content-center">
                                                                                <div><div><img src="images/heart.svg" alt=""><div>+</div></div></div>
                                                                            </div>
                                                                            <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">
                                                                                <div><div><img src="images/cart_2.svg" alt=""><div>+</div></div></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="home_item_side"><a href="product.html"><img src="images/home_3.jpg" alt=""></a></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Slide -->
                        <div class="owl-item">
                            <div class="background_image" style="background-image:url(images/home.jpg)"></div>
                            <div class="container fill_height">
                                <div class="row fill_height">
                                    <div class="col fill_height">
                                        <div class="home_container d-flex flex-column align-items-center justify-content-start">
                                            <div class="home_content">
                                                <div class="home_title">Abbigliamento premium</div>
                                                <div class="home_subtitle">Collezione estiva</div>
                                                <div class="home_items">
                                                    <div class="row">
                                                        <div class="col-sm-3 offset-lg-1">
                                                            <div class="home_item_side"><a href="product.html"><img src="images/home_1.jpg" alt=""></a></div>
                                                        </div>
                                                        <div class="col-lg-4 col-md-6 col-sm-8 offset-sm-2 offset-md-0">
                                                            <div class="product home_item_large">
                                                                <div class="product_tag d-flex flex-column align-items-center justify-content-center">
                                                                    <div>
                                                                        <div>A partire da</div>
                                                                        <div>$3<span>.99</span></div>
                                                                    </div>
                                                                </div>
                                                                <div class="product_image"><img src="images/product_3.jpg" alt=""></div>
                                                                <div class="product_content">
                                                                    <div class="product_info d-flex flex-row align-items-start justify-content-start">
                                                                        <div>
                                                                            <div>
                                                                                <div class="product_name"><a href="product.html">Nome oggetto</a></div>
                                                                                <div class="product_category">In <a href="category.html">Nome categoria</a></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="ml-auto text-right">
                                                                            <div class="rating_r rating_r_4 home_item_rating"><i></i><i></i><i></i><i></i><i></i></div>
                                                                            <div class="product_price text-right">$3<span>.99</span></div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="product_buttons">
                                                                        <div class="text-right d-flex flex-row align-items-start justify-content-start">
                                                                            <div class="product_button product_fav text-center d-flex flex-column align-items-center justify-content-center">
                                                                                <div><div><img src="images/heart.svg" alt=""><div>+</div></div></div>
                                                                            </div>
                                                                            <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">
                                                                                <div><div><img src="images/cart_2.svg" alt=""><div>+</div></div></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="home_item_side"><a href="product.html"><img src="images/home_3.jpg" alt=""></a></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="home_slider_nav home_slider_nav_prev"><i class="fa fa-chevron-left" aria-hidden="true"></i></div>
                    <div class="home_slider_nav home_slider_nav_next"><i class="fa fa-chevron-right" aria-hidden="true"></i></div>

                    <!-- Home Slider Dots -->
                    
                    <div class="home_slider_dots_container">
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <div class="home_slider_dots">
                                        <ul id="home_slider_custom_dots" class="home_slider_custom_dots d-flex flex-row align-items-center justify-content-center">
                                            <li class="home_slider_custom_dot active">01</li>
                                            <li class="home_slider_custom_dot">02</li>
                                            <li class="home_slider_custom_dot">03</li>
                                            <li class="home_slider_custom_dot">04</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <!-- Products -->

            <div class="products">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6 offset-lg-3">
                            <div class="section_title text-center">Popolari su Champloo</div>
                        </div>
                    </div>
                    <div class="row page_nav_row">
                        <div class="col">
                            <div class="page_nav">
                            
                            </div>
                        </div>
                    </div>
                    <div class="row products_row">
                        
                        <!-- Product -->
                        <div class="col-xl-4 col-md-6">
                            <div class="product">
                                <div class="product_image"><img src="images/product_1.jpg" alt=""></div>
                                <div class="product_content">
                                    <div class="product_info d-flex flex-row align-items-start justify-content-start">
                                        <div>
                                            <div>
                                                <div class="product_name"><a href="product.html">Nome oggetto</a></div>
                                                <div class="product_category">In <a href="category.html">Nome categoria</a></div>
                                            </div>
                                        </div>
                                        <div class="ml-auto text-right">
                                            <div class="rating_r rating_r_4 home_item_rating"><i></i><i></i><i></i><i></i><i></i></div>
                                            <div class="product_price text-right">$3<span>.99</span></div>
                                        </div>
                                    </div>
                                    <div class="product_buttons">
                                        <div class="text-center d-flex flex-row align-items-start justify-content-center">
 
                                            <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/cart.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Product -->
                        <div class="col-xl-4 col-md-6">
                            <div class="product">
                                <div class="product_image"><img src="images/product_2.jpg" alt=""></div>
                                <div class="product_content">
                                    <div class="product_info d-flex flex-row align-items-start justify-content-start">
                                        <div>
                                            <div>
                                                <div class="product_name"><a href="product.html">Nome oggetto</a></div>
                                                <div class="product_category">In <a href="category.html">Nome categoria</a></div>
                                            </div>
                                        </div>
                                        <div class="ml-auto text-right">
                                            <div class="rating_r rating_r_4 home_item_rating"><i></i><i></i><i></i><i></i><i></i></div>
                                            <div class="product_price text-right">$3<span>.99</span></div>
                                        </div>
                                    </div>
                                    <div class="product_buttons">
                                        <div class="text-right d-flex flex-row align-items-start justify-content-start">
                                            <div class="product_button product_fav text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/heart_2.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                            <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/cart.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Product -->
                        <div class="col-xl-4 col-md-6">
                            <div class="product">
                                <div class="product_image"><img src="images/product_3.jpg" alt=""></div>
                                <div class="product_content">
                                    <div class="product_info d-flex flex-row align-items-start justify-content-start">
                                        <div>
                                            <div>
                                                <div class="product_name"><a href="product.html">Nome oggetto</a></div>
                                                <div class="product_category">In <a href="category.html">Nome categoria</a></div>
                                            </div>
                                        </div>
                                        <div class="ml-auto text-right">
                                            <div class="rating_r rating_r_4 home_item_rating"><i></i><i></i><i></i><i></i><i></i></div>
                                            <div class="product_price text-right">$3<span>.99</span></div>
                                        </div>
                                    </div>
                                    <div class="product_buttons">
                                        <div class="text-right d-flex flex-row align-items-start justify-content-start">
                                            <div class="product_button product_fav text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/heart_2.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                            <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/cart.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Product -->
                        <div class="col-xl-4 col-md-6">
                            <div class="product">
                                <div class="product_image"><img src="images/product_4.jpg" alt=""></div>
                                <div class="product_content">
                                    <div class="product_info d-flex flex-row align-items-start justify-content-start">
                                        <div>
                                            <div>
                                                <div class="product_name"><a href="product.html">Nome oggetto</a></div>
                                                <div class="product_category">In <a href="category.html">Nome categoria</a></div>
                                            </div>
                                        </div>
                                        <div class="ml-auto text-right">
                                            <div class="rating_r rating_r_4 home_item_rating"><i></i><i></i><i></i><i></i><i></i></div>
                                            <div class="product_price text-right">$3<span>.99</span></div>
                                        </div>
                                    </div>
                                    <div class="product_buttons">
                                        <div class="text-right d-flex flex-row align-items-start justify-content-start">
                                            <div class="product_button product_fav text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/heart_2.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                            <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/cart.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Product -->
                        <div class="col-xl-4 col-md-6">
                            <div class="product">
                                <div class="product_image"><img src="images/product_5.jpg" alt=""></div>
                                <div class="product_content">
                                    <div class="product_info d-flex flex-row align-items-start justify-content-start">
                                        <div>
                                            <div>
                                                <div class="product_name"><a href="product.html">Nome oggetto</a></div>
                                                <div class="product_category">In <a href="category.html">Nome categoria</a></div>
                                            </div>
                                        </div>
                                        <div class="ml-auto text-right">
                                            <div class="rating_r rating_r_4 home_item_rating"><i></i><i></i><i></i><i></i><i></i></div>
                                            <div class="product_price text-right">$3<span>.99</span></div>
                                        </div>
                                    </div>
                                    <div class="product_buttons">
                                        <div class="text-right d-flex flex-row align-items-start justify-content-start">
                                            <div class="product_button product_fav text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/heart_2.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                            <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/cart.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Product -->
                        <div class="col-xl-4 col-md-6">
                            <div class="product">
                                <div class="product_image"><img src="images/product_6.jpg" alt=""></div>
                                <div class="product_content">
                                    <div class="product_info d-flex flex-row align-items-start justify-content-start">
                                        <div>
                                            <div>
                                                <div class="product_name"><a href="product.html">Nome oggetto</a></div>
                                                <div class="product_category">In <a href="category.html">Nome categoria</a></div>
                                            </div>
                                        </div>
                                        <div class="ml-auto text-right">
                                            <div class="rating_r rating_r_4 home_item_rating"><i></i><i></i><i></i><i></i><i></i></div>
                                            <div class="product_price text-right">$3<span>.99</span></div>
                                        </div>
                                    </div>
                                    <div class="product_buttons">
                                        <div class="text-right d-flex flex-row align-items-start justify-content-start">
                                            <div class="product_button product_fav text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/heart_2.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                            <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">
                                                <div><div><img src="images/cart.svg" class="svg" alt=""><div>+</div></div></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    
            </div>

            <!-- Boxes -->

            <div class="boxes">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="boxes_container d-flex flex-row align-items-start justify-content-between flex-wrap">

                                <!-- Box -->
                                <div class="box">
                                    <div class="background_image" style="background-image:url(images/box_1.jpg)"></div>
                                    <div class="box_content d-flex flex-row align-items-center justify-content-start">
                                        <div class="box_left">
                                            <div class="box_image">
                                                <a href="category.html">
                                                    <div class="background_image" style="background-image:url(images/box_1_img.jpg)"></div>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="box_right text-center">
                                            <div class="box_title">Il meglio di Champloo</div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Box -->
                                <div class="box">
                                    <div class="background_image" style="background-image:url(images/box_2.jpg)"></div>
                                    <div class="box_content d-flex flex-row align-items-center justify-content-start">
                                        <div class="box_left">
                                            <div class="box_image">
                                                <a href="category.html">
                                                    <div class="background_image" style="background-image:url(images/box_2_img.jpg)"></div>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="box_right text-center">
                                            <div class="box_title">Scelte popolari</div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Box -->
                                <div class="box">
                                    <div class="background_image" style="background-image:url(images/box_3.jpg)"></div>
                                    <div class="box_content d-flex flex-row align-items-center justify-content-start">
                                        <div class="box_left">
                                            <div class="box_image">
                                                <a href="category.html">
                                                    <div class="background_image" style="background-image:url(images/box_3_img.jpg)"></div>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="box_right text-center">
                                            <div class="box_title">I pi� amati</div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Features -->

            <div class="features">
                <div class="container">
                    <div class="row">
                        
                        <!-- Feature -->
                        <div class="col-lg-4 feature_col">
                            <div class="feature d-flex flex-row align-items-start justify-content-start">
                                <div class="feature_left">
                                    <div class="feature_icon"><img src="images/icon_1.svg" alt=""></div>
                                </div>
                                <div class="feature_right d-flex flex-column align-items-start justify-content-center">
                                    <div class="feature_title">Pagamenti veloci e sicuri</div>
                                </div>
                            </div>
                        </div>

                        <!-- Feature -->
                        <div class="col-lg-4 feature_col">
                            <div class="feature d-flex flex-row align-items-start justify-content-start">
                                <div class="feature_left">
                                    <div class="feature_icon ml-auto mr-auto"><img src="images/icon_2.svg" alt=""></div>
                                </div>
                                <div class="feature_right d-flex flex-column align-items-start justify-content-center">
                                    <div class="feature_title">Prodotti premium</div>
                                </div>
                            </div>
                        </div>

                        <!-- Feature -->
                        <div class="col-lg-4 feature_col">
                            <div class="feature d-flex flex-row align-items-start justify-content-start">
                                <div class="feature_left">
                                    <div class="feature_icon"><img src="images/icon_3.svg" alt=""></div>
                                </div>
                                <div class="feature_right d-flex flex-column align-items-start justify-content-center">
                                    <div class="feature_title">Consegna gratuita</div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <!-- Footer -->
		
			<%@ include file="footer.jsp" %>
			
		
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
 