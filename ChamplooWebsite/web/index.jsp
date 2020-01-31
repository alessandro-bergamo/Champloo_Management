<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="com.champloo.bean.*"
		import="java.util.*"
		import="javafx.util.Pair" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Champloo Store</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="description" content="Little Closet template">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Icona -->
	<link href="images/icona.png" rel="shortcut icon" />

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
				<li><a href="Product?operation=retrieveAll">Catalogo</a>
					<div class="submenu">
						<ul>
							<li><a href="Product?operation=retrieveByCategory&category=T-Shirt">T-Shirt</a></li>
							<li><a href="Product?operation=retrieveByCategory&category=Felpe">Felpe</a></li>
							<li><a href="Product?operation=retrieveByCategory&category=Giacche">Giacche</a></li>
							<li><a href="Product?operation=retrieveByCategory&category=Camicie">Camicie</a></li>
							<li><a href="Product?operation=retrieveByCategory&category=Pantaloni">Pantaloni</a></li>
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

		<%@ include file="header.jsp"%>

		<!-- Stop Header -->

		<%
		ArrayList<Pair<ProductBean, ProductDetailsBean>> prodotti_in_vetrina;
		
			if (((prodotti_in_vetrina = (ArrayList) request.getSession().getAttribute("window")) == null) || ((prodotti_in_vetrina = (ArrayList) request.getSession().getAttribute("window")).isEmpty())) 
			{
		%>

		<div style="display: none;">
			<form action="Product" id="hiddenform" method="POST">
				<input type="hidden" id="loadedvalue" name="loaded" value="0"> 
				<input type="hidden" name="operation" value="createWindow">
			</form>
		</div>

		<%
			} else {
		%>

		<input type="hidden" id="loadedvalue" name="loaded" value="1">

		<%
			}
		%>

		<!-- Home -->

		<div class="home">
			<!-- Home Slider -->
			<div class="home_slider_container">
				<div class="owl-carousel owl-theme home_slider">

					<!-- Slide -->
					<div class="owl-item">
						<div class="background_image" style="background-image: url(images/sfondo.jpg)"></div>
						<div class="container fill_height">
							<div class="row fill_height">
								<div class="col fill_height">
									<div
										class="home_container d-flex flex-column align-items-center justify-content-start">
										<div class="home_content">
											<div class="home_title">Nuovi arrivi</div>
											<div class="home_subtitle">Collezione Invernale 2020</div>
											<div class="home_items">
												<div class="row">
													<div class="col-sm-3 offset-lg-1">
														<div class="home_item_side">
															<a href="Product?operation=showProduct&id_product=8&color=nero"><img src="images/modelli/nike2.jpg" alt=""></a>
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-8 offset-sm-2 offset-md-0">
														<div class="product home_item_large">
															<div class="product_tag d-flex flex-column align-items-center justify-content-center">
																<div>
																	<div>A partire da</div>
																	<div>39<span>.99 &euro;</span></div>
																</div>
															</div>
															<div class="product_image">
																<a href="Product?operation=showProduct&id_product=8&color=nero"><img src="images/modelli/ck1.jpg" alt=""></a>
															</div>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="home_item_side">
															<a href="Product?operation=showProduct&id_product=8&color=nero"><img src="images/modelli/montec.jpg" alt=""></a>
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

					<!-- Slide -->
					<div class="owl-item">
						<div class="background_image"
							style="background-image: url(images/sfondo.jpg)"></div>
						<div class="container fill_height">
							<div class="row fill_height">
								<div class="col fill_height">
									<div
										class="home_container d-flex flex-column align-items-center justify-content-start">
										<div class="home_content">
											<div class="home_title">Nuovi arrivi</div>
											<div class="home_subtitle">Collezione Estate 2020</div>
											<div class="home_items">
												<div class="row">
													<div class="col-sm-3 offset-lg-1">
														<div class="home_item_side">
															<a href="Product?operation=showProduct&id_product=8&color=nero"><img src="images/modelli/adidas.jpg" alt=""></a>
														</div>
													</div>
													<div
														class="col-lg-4 col-md-6 col-sm-8 offset-sm-2 offset-md-0">
														<div class="product home_item_large">
															<div
																class="product_tag d-flex flex-column align-items-center justify-content-center">
																<div>
																	<div>A partire da</div>
																	<div>69<span>.99 &euro;</span></div>
																</div>
															</div>
															<div class="product_image">
																<a href="Product?operation=showProduct&id_product=8&color=nero"><img src="images/modelli/supreme.jpg" alt=""></a>
															</div>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="home_item_side">
															<a href="Product?operation=showProduct&id_product=8&color=nero"><img src="images/modelli/vans.jpg" alt=""></a>
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
					<!-- Slide -->
				</div>

				<div class="home_slider_nav home_slider_nav_prev">
					<i class="fa fa-chevron-left" aria-hidden="true"></i>
				</div>
				<div class="home_slider_nav home_slider_nav_next">
					<i class="fa fa-chevron-right" aria-hidden="true"></i>
				</div>

				<!-- Home Slider Dots -->

			</div>
		</div>

		<!-- Products -->

		<div class="products">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 offset-lg-3">
						<div class="section_title text-center">Vetrina Champloo</div>
						<hr>
					</div>
				</div>
				<div class="row page_nav_row">
					<div class="col">
						<div class="page_nav"></div>
					</div>
				</div>
				<div class="row products_row">

					<!-- Product -->
					<%
						if (prodotti_in_vetrina != null) {
					
								for (int I = 0; I < prodotti_in_vetrina.size(); I++) {
									ProductBean product = prodotti_in_vetrina.get(I).getKey();
									ProductDetailsBean product_details = prodotti_in_vetrina.get(I).getValue();
					%>

					<div class="col-xl-4 col-md-6">
						<div class="product">
							<div class="product_image">
								<img src=<%=product_details.getImg_path_folder()%>img1.png" alt="">
							</div>
							<div class="product_content">
								<div
									class="product_info d-flex flex-row align-items-start justify-content-start">
									<div>
										<div>
											<div class="product_name">
												<a href="Product?operation=showProduct&id_product=<%=product.getId_prod()%>&color=<%=product_details.getColor()%>"><%=product.getName()%></a>
											</div>
											<div class="product_category">
												In <a href="category.html"><%=product.getType()%> <%=product_details.getColor()%></a>
											</div>
										</div>
									</div>
									<div class="ml-auto text-right">
										<%
											if (product.getAverage_rating() == 0) {
										%>
										<div class="rating_r rating_r_0 home_item_rating">
											<%
												} else if (product.getAverage_rating() <= 20
																	&& product.getAverage_rating() != 0) {
											%>
											<div class="rating_r rating_r_1 home_item_rating">
												<%
													} else if (product.getAverage_rating() > 20
																		&& product.getAverage_rating() <= 40) {
												%>
												<div class="rating_r rating_r_2 home_item_rating">
													<%
														} else if (product.getAverage_rating() > 40
																			&& product.getAverage_rating() <= 60) {
													%>
													<div class="rating_r rating_r_3 home_item_rating">
														<%
															} else if (product.getAverage_rating() > 60 && product.getAverage_rating() <= 80) {
														%>
														<div class="rating_r rating_r_4 home_item_rating">
															<%
																} else if (product.getAverage_rating() > 80) {
															%>
															<div class="rating_r rating_r_5 home_item_rating">
																<%
																	}
																%>
																<i></i><i></i><i></i><i></i><i></i>
															</div>
															<div class="product_price text-right"><%=String.valueOf(product.getPrice()).substring(0, String.valueOf(product.getPrice()).indexOf("."))%><span><%=String.valueOf(product.getPrice()).substring(String.valueOf(product.getPrice()).indexOf("."))%>&euro;</span></div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<%
											
												}
											}
										%>
									</div>
								</div>

								<!-- Boxes -->

								<div class="boxes">
									<div class="container">
										<div class="row">
											<div class="col">
												<div
													class="boxes_container d-flex flex-row align-items-start justify-content-between flex-wrap">

													<!-- Box -->
													<div class="box">
														<div class="background_image"
															style="background-image: url(images/box_1.jpg)"></div>
														<div
															class="box_content d-flex flex-row align-items-center justify-content-start">
															<div class="box_left">
																<div class="box_image">
																	<a href="category.html">
																		<div class="background_image"
																			style="background-image: url(images/box_1_img.jpg)"></div>
																	</a>
																</div>
															</div>
															<div class="box_right text-center">
																<div class="box_title">Le novità su Champloo</div>
															</div>
														</div>
													</div>

													<!-- Box -->
													<div class="box">
														<div class="background_image"
															style="background-image: url(images/box_2.jpg)"></div>
														<div
															class="box_content d-flex flex-row align-items-center justify-content-start">
															<div class="box_left">
																<div class="box_image">
																	<a href="category.html">
																		<div class="background_image"
																			style="background-image: url(images/box_2_img.jpg)"></div>
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
														<div class="background_image"
															style="background-image: url(images/box_3.jpg)"></div>
														<div
															class="box_content d-flex flex-row align-items-center justify-content-start">
															<div class="box_left">
																<div class="box_image">
																	<a href="category.html">
																		<div class="background_image"
																			style="background-image: url(images/box_3_img.jpg)"></div>
																	</a>
																</div>
															</div>
															<div class="box_right text-center">
																<div class="box_title">I più amati</div>
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
												<div
													class="feature d-flex flex-row align-items-start justify-content-start">
													<div class="feature_left">
														<div class="feature_icon">
															<img src="images/icon_1.svg" alt="">
														</div>
													</div>
													<div
														class="feature_right d-flex flex-column align-items-start justify-content-center">
														<div class="feature_title">Pagamenti veloci e sicuri</div>
													</div>
												</div>
											</div>

											<!-- Feature -->
											<div class="col-lg-4 feature_col">
												<div class="feature d-flex flex-row align-items-start justify-content-start">
													<div class="feature_left">
														<div class="feature_icon ml-auto mr-auto">
															<img src="images/icon_2.svg" alt="">
														</div>
													</div>
													<div class="feature_right d-flex flex-column align-items-start justify-content-center">
														<div class="feature_title">Reso gratuito</div>
													</div>
												</div>
											</div>

											<!-- Feature -->
											<div class="col-lg-4 feature_col">
												<div
													class="feature d-flex flex-row align-items-start justify-content-start">
													<div class="feature_left">
														<div class="feature_icon">
															<img src="images/icon_3.svg" alt="">
														</div>
													</div>
													<div
														class="feature_right d-flex flex-column align-items-start justify-content-center">
														<div class="feature_title">Consegna gratuita</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<!-- Footer -->
						
								<%@ include file="footer.jsp"%>
								
							</div>
						</div>
					</div>
			
			<script>
				function submittaForm() {
						var val = parseInt($("#loadedvalue").val());
						if (val == 0) {
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
