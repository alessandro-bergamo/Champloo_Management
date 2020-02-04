<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="com.champloo.bean.*"
		import="java.util.*"
		import="javafx.util.Pair"
%>
<%@ page import="java.text.SimpleDateFormat" %>
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

	<!-- CDN SWEETALERT2 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

	<!-- STILI CSS -->
	<link rel="stylesheet" type="text/css" href="styles/cart.css">
	<link rel="stylesheet" type="text/css" href="styles/cart_responsive.css">

</head>
<body>

	<!-- Menu -->


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

		<%
			if(utenteLoggato == null)
				response.sendRedirect("user_log.jsp");
		%>

		<%
			LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> orders = new LinkedHashMap <OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>>();

			orders = (LinkedHashMap) session.getAttribute("order");

			if(orders != null && !orders.isEmpty())
			{

				Iterator iterator = orders.entrySet().iterator();
				while(iterator.hasNext())
				{
					HashMap.Entry entry = (HashMap.Entry) iterator.next();
					OrderBean order = (OrderBean) entry.getKey();
					ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>> products_in_order = (ArrayList) entry.getValue();

					String data_creazione = new SimpleDateFormat("dd-MM-yyyy").format(order.getCreation_date());
					String data_consegna = new SimpleDateFormat("dd-MM-yyyy").format(order.getDelivery_date());
		%>

		<!-- Home -->

		<div class="home">
			<div class="home_container d-flex flex-column align-items-center justify-content-end">
				<div class="home_content text-center">
					<div class="home_title">Ordine: #372-<%=order.getId_order()%></div>
				</div>
			</div>
		</div>

		<!-- Cart -->

		<div class="cart_section">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="cart_container">
							
							<!-- Cart Bar -->
							<div class="cart_bar">
								<ul class="cart_bar_list item_list d-flex flex-row align-items-center justify-content-end">
									<li class="mr-auto">Prodotto</li>
									<li>Colore</li>
									<li>Taglia</li>
									<li>Prezzo</li>
									<li>Quantità</li>
									<li>Totale</li>
								</ul>
							</div>

							<!-- Cart Items -->
							<div class="cart_items">
								<ul class="cart_items_list">
									<%
										OrderItemBean order_item;
										ProductBean product;
										ProductDetailsBean productDetails;
										Pair<ProductBean, ProductDetailsBean> product_with_details;
										for(int I=0; I<products_in_order.size(); I++)
										{
											order_item = products_in_order.get(I).getKey();
											product_with_details = products_in_order.get(I).getValue();
											product = product_with_details.getKey();
											productDetails = product_with_details.getValue();
									%>
									<!-- Cart Item -->
									<li class="cart_item item_list d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-lg-end justify-content-start">
										<div class="product d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-start mr-auto">
											<div><div class="product_image"><img src=<%=productDetails.getImg_path_folder()%>img1.png" alt=""></div></div>
											<div class="product_name_container">
												<div class="product_name"><a href="Product?operation=showProduct&id_product=<%=product.getId_prod()%>&color=<%=productDetails.getColor()%>"><%=product.getName()%></a></div>
												<div class="product_text"><%=product.getType()+" - BRAND: "+product.getBrand()%></div>
											</div>
										</div>
										<div class="product_color product_text"><span>Colore: </span><%=productDetails.getColor()%></div>
										<div class="product_size product_text"><span>Taglia: </span><%=productDetails.getSize()%></div>
										<div class="product_price product_text"><span>Prezzo: </span><%=order_item.getPrice_in_order()/order_item.getQnt_in_order()%> &euro;</div>
										<div class="product_quantity_container">
											<div class="product_quantity ml-lg-auto mr-lg-auto text-center">
												<p class="product_text product_num" name="qntSelectorLabel"><%=order_item.getQnt_in_order()%></p>
											</div>
										</div>
										<div class="product_total product_text">
											<%=order_item.getPrice_in_order()%> &euro;
										</div>
									</li>
									<%
										}
									%>
								</ul>
							</div>

							<!-- Cart Buttons -->
						</div>
					</div>
				</div>
				<div class="row cart_extra_row">
					<div class="col-lg-6 cart_extra_col">
						<div class="cart_extra cart_extra_2">
							<div class="cart_extra_content cart_extra_total" style="padding-right: 35px !important; padding-left: 35px !important; padding-bottom: 20px !important;">
								<div class="cart_extra_title">Informazioni Ordine</div>
								<ul class="cart_extra_total_list" style="margin-top: 35px !important; padding: 0px 0px !important;">
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Autore:</div>
										<div class="cart_extra_total_value ml-auto" id="total_price_order">
											<p class="cart_extra_total_value ml-auto"><%=order.getOrder_owner()%></p>
										</div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Indirizzo:</div>
										<div class="cart_extra_total_value ml-auto" id="total_price">
											<p class="cart_extra_total_value ml-auto" style="font-size: 13px !important;"><%=order.getAddress()%></p>
										</div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Metodo di Pagamento:</div>
										<%
											String payment_method = order.getPayment_method();
											char ch;
											for(int I=0;I<payment_method.length();I++)
											{
												if(I==3 || I==8 || I==13)
												{
													ch = payment_method.charAt(I);
													if(!Character.isWhitespace(ch))
														payment_method = new StringBuilder(payment_method).insert(I+1, ' ').toString();
												}
											}
										%>
										<div class="cart_extra_total_value ml-auto" id="price_shipping"><%=payment_method%></div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Prezzo Totale:</div>
										<div class="cart_extra_total_value ml-auto" id="total_price">
											<p class="cart_extra_total_value ml-auto" style="font-weight: bold"><%=order.getTotal_price()%> &euro;</p>
										</div>
									</li>
									<li hidden aria-hidden="true">

									</li>
								</ul>
							</div>
						</div>
					</div>
					<%
						if(utenteLoggato.getType() == 1)
						{
					%>
					<div class="col-lg-6 cart_extra_col">
						<div class="cart_extra cart_extra_2">
							<div class="cart_extra_content cart_extra_total" style="padding-right: 35px !important; padding-left: 35px !important; padding-bottom: 20px !important;">
								<div class="cart_extra_title">Informazioni Status Ordine</div>
								<ul class="cart_extra_total_list" style="margin-top: 35px !important; padding: 0px 0px !important;">
									<%
										int status_order = order.getStatus_order();
										String status = null;
										if(status_order == 1)
											status = "In Elaborazione";
										else if(status_order == 2)
											status = "Pronto";
										else if(status_order == 3)
											status = "In Transito - Arriverà entro il: "+data_consegna;
										else if(status_order == 4)
											status = "Consegnato il: "+data_consegna;
										else if(status_order == 5)
											status = "Annullato il giorno: "+data_consegna;
										else if(status_order == 6)
											status = "Annullato da un Admin il giorno: "+data_consegna;
									%>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Status</div>
										<div class="cart_extra_total_value ml-auto">
											<p class="cart_extra_total_value ml-auto"><%=status%></p>
										</div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Data Creazione:</div>
										<div class="cart_extra_total_value ml-auto">
											<p class="cart_extra_total_value ml-auto"><%=data_creazione%></p>
										</div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<%
											if(status_order == 5 || status_order == 6)
											{
										%>
										<div class="cart_extra_total_title">Data Annullo:</div>
										<%
										} else {
										%>
										<div class="cart_extra_total_title">Data Consegna:</div>
										<%
											}
										%>
										<div class="cart_extra_total_value ml-auto">
											<p class="cart_extra_total_value ml-auto" style="font-weight: bold;"><%=data_consegna%></p>
										</div>
									</li>
									<li hidden aria-hidden="true">
										<input type="hidden" id="id_order" value="<%=order.getId_order()%>">
									</li>
								</ul>
								<%
									if(status_order == 1 || status_order == 2)
									{
								%>
									<div class="checkout_button trans_200" onclick="cancelOrder()"><a style="color: white; cursor: pointer;">Annulla Ordine</a></div>
								<%
									}
								%>
							</div>
						</div>
					</div>
					<%
						} else if(utenteLoggato.getType() == 4)
						{
					%>
					<div class="col-lg-6 cart_extra_col">
						<div class="cart_extra cart_extra_2">
							<div class="cart_extra_content cart_extra_total" style="padding-right: 35px !important; padding-left: 35px !important; padding-bottom: 20px !important;">
								<div class="cart_extra_title">Informazioni Status Ordine</div>
								<ul class="cart_extra_total_list" style="margin-top: 35px !important; padding: 0px 0px !important;">
									<%
										int status_order = order.getStatus_order();
										String status = null;
										if(status_order == 1)
											status = "In Elaborazione";
										else if(status_order == 2)
											status = "Pronto";
										else if(status_order == 3)
											status = "In Transito - Arriverà entro il: "+data_consegna;
										else if(status_order == 4)
											status = "Consegnato il: "+data_consegna;
										else if(status_order == 5)
											status = "Annullato il giorno: "+data_consegna;
										else if(status_order == 6)
											status = "Annullato da un Admin il giorno: "+data_consegna;
									%>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Status</div>
										<div class="cart_extra_total_value ml-auto">
											<select class="form-control" id="statusFormControl">
												<option value="0"><%=status%></option>
												<option value="1">In Elaborazione</option>
												<option value="2">Pronto</option>
												<option value="3">In Transito</option>
												<option value="4">Consegnato</option>
												<option value="5">Annullato</option>
												<option value="6">Annullato da un Admin</option>
											</select>
										</div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Data Creazione:</div>
										<div class="cart_extra_total_value ml-auto">
											<p class="cart_extra_total_value ml-auto"><%=data_creazione%></p>
										</div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<%
											if(status_order == 5 || status_order == 6)
											{
										%>
											<div class="cart_extra_total_title">Data Annullo:</div>
										<%
											} else {
										%>
											<div class="cart_extra_total_title">Data Consegna:</div>
										<%
											}
										%>
										<div class="cart_extra_total_value ml-auto">
											<input placeholder="<%=data_consegna%>" type="text" onfocus="(this.type='date')" onblur="(this.type='text')" id="delivery_date">
										</div>
									</li>
									<li hidden aria-hidden="true">
										<input type="hidden" id="id_order2" value="<%=order.getId_order()%>">
									</li>
								</ul>
								<div class="checkout_button trans_200" onclick="modifyOrder()"><a style="color: white; cursor: pointer;">Conferma Modifiche</a></div>
							</div>
						</div>
					</div>
		<%
						}
				}
			}
		%>
				</div>
			</div>
		</div>

        <!-- Footer -->

		<%@ include file="footer.jsp" %>

		</div>
	</div>

	<script>
		function modifyOrder(){
			var value1 = $("#statusFormControl").val();
			var value2 = $("#delivery_date").val();
			var value3 = ("modifyOrder");
			var value4 = $("#id_order2").val();
			Swal.fire({ //PRIMO POPUP
				title: "Sei sicuro di voler modificare l'ordine?",
				text: "Le modifiche potranno portare a valori errati.",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Conferma',
				cancelButtonText: 'Annulla'
			}).then((result) => {
				if (result.value) {
					$.ajax({
						type: "GET",
						url: "Order",
						data: {"operation" : value3, "status" : value1, "delivery_date" : value2, "order_id" : value4},
						success: function(results){
							Swal.fire({ //SECONDO POPUP
								title: 'Ordine Modificato',
								timer: 1200,
								icon: 'success',
								showCancelButton: false,
								showConfirmButton: false,
								width: '400px',
							}); setTimeout(function(){location.reload()} , 1350);
						}
					})
				}
			})
		}
	</script>

	<script>
		function cancelOrder(){
			var value1 = ("cancelOrder");
			var value2 = $("#id_order").val();
			Swal.fire({ //PRIMO POPUP
				title: "Sei sicuro di voler annullare l'ordine?",
				text: "L'ordine sarà annullato permanentemente.",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Conferma',
				cancelButtonText: 'Annulla'
			}).then((result) => {
				if (result.value) {
					$.ajax({
						type: "GET",
						url: "Order",
						data: {"operation" : value1, "order_id" : value2},
						success: function(results){
							Swal.fire({ //SECONDO POPUP
								title: 'Ordine Annullato',
								timer: 1200,
								icon: 'success',
								showCancelButton: false,
								showConfirmButton: false,
								width: '400px',
							}); setTimeout(function(){location.reload()} , 1350);
						}
					})
				}
			})
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
	<script src="js/cart.js"></script>
	<script src="js/logout.js"></script>

</body>
</html>
