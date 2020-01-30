<%@ page import="java.util.ArrayList" %>
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
			Float shipping_price = (Float) request.getSession().getAttribute("shipping_price");
			Float order_price = (Float) request.getSession().getAttribute("total_price");
			Float total_price_order = (Float) request.getSession().getAttribute("total_price_order");

			ArrayList<AddressBean> addresses = (ArrayList) request.getSession().getAttribute("addresses");
			ArrayList<PaymentMethodBean> methods = (ArrayList) request.getSession().getAttribute("methods");
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
				<form action="Order" method="GET" id="create_order">
					<div class="row">
						<!-- Billing -->
						<div class="col-lg-6">
							<div class="billing">
								<div class="payment_options" style="margin-top: 0px !important;">
									<div class="checkout_title">Indirizzi</div>
									<ul>
										<%
											if(addresses != null && !addresses.isEmpty()) {
											for(int I=0;I<addresses.size();I++) {
										%>
										<li class="shipping_option d-flex flex-row align-items-center justify-content-start">
											<label class="radio_container">
												<input type="radio" id="radio_<%=I+1%>" name="address" value="<%=addresses.get(I).getAddress()+", "+addresses.get(I).getCity()+", "+addresses.get(I).getCivic_number()+", "+addresses.get(I).getProvince()%>" class="payment_radio" required>
												<span class="radio_mark"></span>
												<span class="radio_text"><%=addresses.get(I).getAddress()+", "+addresses.get(I).getCity()+", "+addresses.get(I).getCivic_number()+", "+addresses.get(I).getProvince()%></span>
											</label>
										</li>
										<%	} } else {	%>
										<div>
											CIAO
										</div>
										<% 	}	 %>
									</ul>
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
											<div class="cart_extra_total_value ml-auto"><%=order_price%> &euro;</div>
										</li>
										<li class="d-flex flex-row align-items-center justify-content-start">
											<div class="cart_extra_total_title">Spedizione</div>
											<%	if(shipping_price == 0.0) {%>
											<div class="cart_extra_total_value ml-auto">Gratuita</div>
											<% 	} else {%>
											<div class="cart_extra_total_value ml-auto"><%=shipping_price%> &euro;</div>
											<%	}%>
										</li>
										<li class="d-flex flex-row align-items-center justify-content-start">
											<div class="cart_extra_total_title">Totale</div>
											<div class="cart_extra_total_value ml-auto"><%=total_price_order%> &euro;</div>
										</li>
									</ul>
									<div class="payment_options">
										<div class="checkout_title">Pagamento</div>
										<ul>
											<%
												if(methods != null && !methods.isEmpty()) {
												for(int I=0;I<methods.size();I++) {	%>
											<li class="shipping_option d-flex flex-row align-items-center justify-content-start">
												<label class="radio_container">
													<input type="radio" id="radio_<%=I+1%>" name="payment_method" value="<%=methods.get(I).getCard_number()%>" class="payment_radio" required>
													<span class="radio_mark"></span>
													<span class="radio_text"><%=methods.get(I).getCard_bank()+", "+methods.get(I).getCard_number()%></span>
												</label>
											</li>
											<%	} } else {	%>
											<div>
												CIAO2
											</div>
											<% 	}	%>
										</ul>
									</div>
									<input type="hidden" value="createOrder" name="operation">
									<div class="checkout_button trans_200"><a style="color: white; cursor: pointer;" onclick="createOrder()">Conferma ordine</a></div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>

		<!-- Footer -->

		<%@ include file="footer.jsp" %>

	</div>

	<script>
		function createOrder() {
			$("#create_order").submit();
			$.ajax({
				success: function (results) {
					Swal.fire({
						title: 'Ordine Creato',
						timer: 1700,
						icon: 'success',
						showCancelButton: false,
						showConfirmButton: false,
						width: '400px',
					})
				},
				error: function (result) {
					Swal.fire({
						title: 'Ordine non creato',
						timer: 2000,
						icon: 'error',
						showCancelButton: false,
						showConfirmButton: false,
						width: '500px'
					})
				}
			});
		}
	</script>

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
