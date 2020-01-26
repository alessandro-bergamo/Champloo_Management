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
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
	<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">

	<!-- STILI CSS -->

	<link rel="stylesheet" type="text/css" href="styles/checkout.css">
	<link rel="stylesheet" type="text/css" href="styles/checkout_responsive.css">
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
			Float shipping_price = (Float) (request.getSession().getAttribute("shipping_price"));

			System.out.println("SHIPPING PRICE: "+shipping_price);
		%>

		<!-- Home -->

		<div class="home">
			<div class="home_container d-flex flex-column align-items-center justify-content-end">
				<div class="home_content text-center">
					<div class="home_title">Checkout</div>
				</div>
			</div>
		</div>

		<!-- Checkout -->

		<div class="checkout">
			<div class="container">
				<div class="row">
					
					<!-- Billing -->
					<div class="col-lg-6">
						<div class="billing">
							<div class="checkout_title">Indirizzo fatturazione</div>
							<div class="checkout_form_container">
								<form action="#" id="checkout_form" class="checkout_form">
									<div class="row">
										<div class="col-lg-6">
											<!-- Name -->
											<input type="text" id="checkout_nome" class="checkout_input" placeholder="Nome" required="required">
										</div>
										<div class="col-lg-6">
											<!-- Last Name -->
											<input type="text" id="checkout_cognome" class="checkout_input" placeholder="Cognome" required="required">
										</div>
									</div>

									<div>
										<!-- Country -->
										<select name="checkout_nazione" id="checkout_country" class="dropdown_item_select checkout_input" require="required">
											<option>Nazione</option>
											<option>Francia</option>
											<option>Svizzera</option>
											<option>UK</option>
											<option>Italia</option>
										</select>
									</div>
									<div>
										<!-- Address -->
										<input type="text" id="checkout_address" class="checkout_input" placeholder="Indirizzo" required="required">
										<input type="text" id="checkout_address_2" class="checkout_input checkout_address_2" placeholder="Indirizzo secondario" required="required">
									</div>
									<div>
										<!-- Zipcode -->
										<input type="text" id="checkout_codiceP" class="checkout_input" placeholder="Codice postale" required="required">
									</div>
                                        <div>
                                            <!-- Regione -->
                                            <input type="text" id="checkout_regione" class="checkout_input" placeholder="Regione" required="required">
                                        </div>
									                                    <div>
                                        <!-- Provincia -->
                                        <input type="text" id="checkout_provincia" class="checkout_input" placeholder="Provincia" required="required">
                                    </div>
									<div>
										<!-- Phone no -->
										<input type="phone" id="checkout_telefono" class="checkout_input" placeholder="Telefono" required="required">
									</div>
									<div>
										<!-- Email -->
										<input type="phone" id="checkout_email" class="checkout_input" placeholder="Email" required="required">
									</div>
									<div class="checkout_extra">
										<ul>
											<li class="billing_info d-flex flex-row align-items-center justify-content-start">
												<label class="checkbox_container">
													<input type="checkbox" id="cb_1" name="billing_checkbox" class="billing_checkbox">
													<span class="checkbox_mark"></span>
													<span class="checkbox_text">Ho preso visione e accetto i termini e condizioni</span>
												</label>
											</li>
											<li class="billing_info d-flex flex-row align-items-center justify-content-start">
												<label class="checkbox_container">
													<input type="checkbox" id="cb_2" name="billing_checkbox" class="billing_checkbox">
													<span class="checkbox_mark"></span>
													<span class="checkbox_text">Crea un account (O completa l'ordine come guest)</span>
												</label>
											</li>
											<li class="billing_info d-flex flex-row align-items-center justify-content-start">
												<label class="checkbox_container">
													<input type="checkbox" id="cb_3" name="billing_checkbox" class="billing_checkbox">
													<span class="checkbox_mark"></span>
													<span class="checkbox_text">Iscriviti alla nostra newsletter</span>
												</label>
											</li>
										</ul>
									</div>
								</form>
							</div>
						</div>
					</div>

					<!-- Cart Total -->
					<div class="col-lg-6 cart_col">
						<div class="cart_total">
							<div class="cart_extra_content cart_extra_total">
								<div class="checkout_title">Totale carrello</div>
								<ul class="cart_extra_total_list">
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Oggetti</div>
										<div class="cart_extra_total_value ml-auto">$29.90</div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Spedizione</div>
										<div class="cart_extra_total_value ml-auto">Gratis</div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Totale</div>
										<div class="cart_extra_total_value ml-auto">$29.90</div>
									</li>
								</ul>
								<div class="payment_options">
									<div class="checkout_title">Pagamento</div>
									<ul>
										<li class="shipping_option d-flex flex-row align-items-center justify-content-start">
											<label class="radio_container">
												<input type="radio" id="radio_1" name="payment_radio" class="payment_radio">
												<span class="radio_mark"></span>
												<span class="radio_text">Paypal</span>
											</label>
										</li>
										<li class="shipping_option d-flex flex-row align-items-center justify-content-start">
											<label class="radio_container">
												<input type="radio" id="radio_2" name="payment_radio" class="payment_radio">
												<span class="radio_mark"></span>
												<span class="radio_text">Pagamento alla consegna</span>
											</label>
										</li>
										<li class="shipping_option d-flex flex-row align-items-center justify-content-start">
											<label class="radio_container">
												<input type="radio" id="radio_3" name="payment_radio" class="payment_radio" checked>
												<span class="radio_mark"></span>
												<span class="radio_text">Carta di credito</span>
											</label>
										</li>
									</ul>
								</div>
								
								<div class="checkout_button trans_200"><a href="checkout.html">Conferma ordine</a></div>
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
	<script src="plugins/easing/easing.js"></script>
	<script src="plugins/parallax-js-master/parallax.min.js"></script>
	<script src="js/checkout.js"></script>
</body>
</html>
