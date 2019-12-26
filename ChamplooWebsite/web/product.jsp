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
	<link rel="stylesheet" type="text/css" href="styles/product.css">
	<link rel="stylesheet" type="text/css" href="styles/product_responsive.css">

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
			<div class="home_container d-flex flex-column align-items-center justify-content-end">
				<div class="home_content text-center">
					<div class="home_title">Nome prodotto</div>
				</div>
			</div>
		</div>

		<!-- Product -->

		<div class="product">
			<div class="container">
				<div class="row">
					
					<!-- Product Image -->
					<div class="col-lg-6">
						<div class="product_image_slider_container">
							<div id="slider" class="flexslider">
								<ul class="slides">
									<li>
										<img src="images/product_image_1.jpg" />
									</li>
									<li>
										<img src="images/product_image_1.jpg" />
									</li>
									<li>
										<img src="images/product_image_1.jpg" />
									</li>
									<li>
										<img src="images/product_image_1.jpg" />
									</li>
									<li>
										<img src="images/product_image_1.jpg" />
									</li>
									<li>
										<img src="images/product_image_1.jpg" />
									</li>
									<li>
										<img src="images/product_image_1.jpg" />
									</li>
									<li>
										<img src="images/product_image_1.jpg" />
									</li>
								</ul>
							</div>
							<div class="carousel_container">
								<div id="carousel" class="flexslider">
									<ul class="slides">
										<li>
											<div><img src="images/product_1.jpg" /></div>
										</li>
										<li>
											<div><img src="images/product_2.jpg" /></div>
										</li>
										<li>
											<div><img src="images/product_3.jpg" /></div>
										</li>
										<li>
											<div><img src="images/product_4.jpg" /></div>
										</li>
										<li>
											<div><img src="images/product_5.jpg" /></div>
										</li>
										<li>
											<div><img src="images/product_6.jpg" /></div>
										</li>
										<li>
											<div><img src="images/product_7.jpg" /></div>
										</li>
										<li>
											<div><img src="images/product_8.jpg" /></div>
										</li>
									</ul>
								</div>
								<div class="fs_prev fs_nav disabled"><i class="fa fa-chevron-up" aria-hidden="true"></i></div>
								<div class="fs_next fs_nav"><i class="fa fa-chevron-down" aria-hidden="true"></i></div>
							</div>
						</div>
					</div>

					<!-- Product Info -->
					<div class="col-lg-6 product_col">
						<div class="product_info">
							<div class="product_name">Nome oggetto</div>
							<div class="product_category">In <a href="category.html">Nome categoria</a></div>
							<div class="product_rating_container d-flex flex-row align-items-center justify-content-start">
								<div class="rate">
									<input type="radio" id="star5" name="rate" value="5" />
									<label for="star5" title="5 Stelle">5 stars</label>
									<input type="radio" id="star4" name="rate" value="4" />
									<label for="star4" title="4 Stelle">4 stars</label>
									<input type="radio" id="star3" name="rate" value="3" />
									<label for="star3" title="3 Stelle">3 stars</label>
									<input type="radio" id="star2" name="rate" value="2" />
									<label for="star2" title="2 Stelle">2 stars</label>
									<input type="radio" id="star1" name="rate" value="1" />
									<label for="star1" title="1 Stella">1 star</label>
								</div>
								<div class="product_reviews">4.7 su (3514)</div>
								<div class="product_reviews_link"><a href="#">Recensioni</a></div>
							</div>
							<div class="product_price">$3<span>.99</span></div>
							<div class="product_size">
								<div class="product_size_title">Seleziona taglia</div>
								<ul class="d-flex flex-row align-items-start justify-content-start">
									<li>
										<input type="radio" id="radio_1" disabled name="product_radio" class="regular_radio radio_1">
										<label for="radio_1">XS</label>
									</li>
									<li>
										<input type="radio" id="radio_2" name="product_radio" class="regular_radio radio_2" checked>
										<label for="radio_2">S</label>
									</li>
									<li>
										<input type="radio" id="radio_3" name="product_radio" class="regular_radio radio_3">
										<label for="radio_3">M</label>
									</li>
									<li>
										<input type="radio" id="radio_4" name="product_radio" class="regular_radio radio_4">
										<label for="radio_4">L</label>
									</li>
									<li>
										<input type="radio" id="radio_5" name="product_radio" class="regular_radio radio_5">
										<label for="radio_5">XL</label>
									</li>
									<li>
										<input type="radio" id="radio_6" disabled name="product_radio" class="regular_radio radio_6">
										<label for="radio_6">XXL</label>
									</li>
								</ul>
							</div>
							<div class="product_text">
								<p>Descrizione prodotto</p>
							</div>
							<div class="product_buttons">
								<div class="text-right d-flex flex-row align-items-start justify-content-start">
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
					<div class="col-lg-6">
						<div class="box d-flex flex-row align-items-center justify-content-start">
							<div class="mt-auto"><div class="box_image"><img src="images/boxes_1.png" alt=""></div></div>
							<div class="box_content">
								<div class="box_title">Guida alle taglie</div>
								<div class="box_text">Scegli la taglia adatta al tuo capo d'abbigliamento preferito cosi da non sbagliarti</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 box_col">
						<div class="box d-flex flex-row align-items-center justify-content-start">
							<div class="mt-auto"><div class="box_image"><img src="images/boxes_2.png" alt=""></div></div>
							<div class="box_content">
								<div class="box_title">Spedizione</div>
								<div class="box_text">Varie opzioni di spedizione</div>
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
<script src="plugins/flexslider/jquery.flexslider-min.js"></script>
<script src="js/product.js"></script>
</body>
</html>
