<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="com.champloo.bean.*"
		import="java.util.*"
		import="javafx.util.Pair"
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
			HashMap<Pair<ProductBean, ProductDetailsBean>, Integer> products_in_cart = (HashMap) request.getSession().getAttribute("productsInCart");
			
			if (products_in_cart == null)
				products_in_cart = (HashMap) request.getSession().getAttribute("products_in_activeCart");
		%>

		<!-- Home -->

		<div class="home">
			<div class="home_container d-flex flex-column align-items-center justify-content-end">
				<div class="home_content text-center">
					<div class="home_title">Carrello</div>
				</div>
			</div>
		</div>

		<!-- Cart -->

		<div class="cart_section">
		<%	if(cart != null)
			{
		%>
			<input type="hidden" id="id_cart" value="<%=cart.getId_cart()%>">
		<% 
			}
		%>
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
									int total_price = 0;
										if(products_in_cart != null)
										{
											Iterator iterator = products_in_cart.entrySet().iterator();
											int num_products = 1;
											while (iterator.hasNext())
											{
												HashMap.Entry entry = (HashMap.Entry) iterator.next();
												Pair<ProductBean, ProductDetailsBean> pairInCart = (Pair)entry.getKey();
												ProductBean product = (ProductBean) pairInCart.getKey();
												ProductDetailsBean productDetails = (ProductDetailsBean) pairInCart.getValue();
												
												int qntInCart = (int) entry.getValue();
									%>
									<!-- Cart Item -->
									<input type="hidden" id="products_to_checkout" value="<%=products_in_cart%>">
									<li class="cart_item item_list d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-lg-end justify-content-start">
										<div class="product d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-start mr-auto">
											<div><div class="product_number">
												<input type="hidden" id="productDetails" value="<%=productDetails.getId_prod_details()%>">
												<input type="hidden" id="product" value="<%=product.getId_prod()%>">
												<img id="removeProd" src="images/delete.png" style="margin:-30px; cursor: pointer;">
											</div></div>
											<div><div class="product_image"><img src=<%=productDetails.getImg_path_folder()%>img1.png" alt=""></div></div>
											<div class="product_name_container">
												<div class="product_name"><a href="Product?operation=showProduct&id_product=<%=product.getId_prod()%>&color=<%=productDetails.getColor()%>"><%=product.getName()%></a></div>
												<div class="product_text"><%=product.getType()+" - BRAND: "+product.getBrand()%></div>
											</div>
										</div>
										<div class="product_color product_text"><span>Colore: </span><%=productDetails.getColor()%></div>
										<div class="product_size product_text"><span>Taglia: </span><%=productDetails.getSize()%></div>
										<div class="product_price product_text"><input name="productPrice" type="hidden" id="productPrice" value=<%=product.getPrice()%>><span>Prezzo: </span><%=product.getPrice()%> &euro;</div>
										<div class="product_quantity_container">
											<div class="product_quantity ml-lg-auto mr-lg-auto text-center">
												<input type="hidden"  value=<%=qntInCart%>>
												<p class="product_text product_num" name="qntSelectorLabel"><%=qntInCart%></p>
												<div class="qty_sub qty_button trans_200 text-center" id="qnt_selector">
													<input type="hidden" id="product" value="<%=product.getId_prod()%>">
													<input type="hidden" id="productDetails" value="<%=productDetails.getId_prod_details()%>">
													<span>-</span>
												</div>
												<div class="qty_add qty_button trans_200 text-center" id="qnt_selector">
													<input type="hidden" id="product" value="<%=product.getId_prod()%>">
													<input type="hidden" id="productDetails" value="<%=productDetails.getId_prod_details()%>">
													<span>+</span>
												</div>
											</div>
										</div>
										<div class="product_total product_text">
											<input type="hidden"><%=product.getPrice()*qntInCart%> &euro;
										</div>
									</li>
									<%			
												total_price += (product.getPrice()*qntInCart);
											}
									%>
								</ul>
							</div>

							<!-- Cart Buttons -->
							<div class="cart_buttons d-flex flex-row align-items-start justify-content-start">
								<div class="cart_buttons_inner ml-sm-auto d-flex flex-row align-items-start justify-content-start flex-wrap">
									<div class="button button_clear trans_200"><a href="Cart?operation=clearCart" style="text-decoration: none !important;">Svuota carrello</a></div>
									<div class="button button_continue trans_200"><a href="Product?operation=retrieveAll" style="color: white; text-decoration: none !important;">Continua lo shopping</a></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row cart_extra_row">
					<div class="col-lg-6">
						<div class="cart_extra cart_extra_1">
							<div class="cart_extra_content cart_extra_coupon">
								<div class="shipping">
									<div class="cart_extra_title">Metodo di spedizione</div>
									<ul>
										<li class="shipping_option d-flex flex-row align-items-center justify-content-start">
											<label class="radio_container">
												<input type="radio" id="radio_1" name="shipping_radio" value="veloce" class="shipping_radio">
												<span class="radio_mark"></span>
												<span class="radio_text">Corriere Espresso - (1/2 giorni lavorativi)</span>
											</label>
											<div class="shipping_price ml-auto">4.99 &euro;</div>
										</li>
										<li class="shipping_option d-flex flex-row align-items-center justify-content-start">
											<label class="radio_container">
												<input type="radio" id="radio_2" name="shipping_radio" value="standard" class="shipping_radio">
												<span class="radio_mark"></span>
												<span class="radio_text">Corriere Standard - (3/5 giorni lavorativi)</span>
											</label>
											<div class="shipping_price ml-auto">1.99 &euro;</div>
										</li>
										<li class="shipping_option d-flex flex-row align-items-center justify-content-start">
											<label class="radio_container">
												<input type="radio" id="radio_3" name="shipping_radio" value="lenta" class="shipping_radio" required checked>
												<span class="radio_mark"></span>
												<span class="radio_text">Consegna Lenta - (7+ giorni lavorativi)</span>
											</label>
											<div class="shipping_price ml-auto">Gratuita</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 cart_extra_col">
						<div class="cart_extra cart_extra_2">
							<div class="cart_extra_content cart_extra_total">
								<div class="cart_extra_title">Totale carrello</div>
								<ul class="cart_extra_total_list">
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Prodotti</div>
										<div class="cart_extra_total_value ml-auto" id="total_price">
											<!--  <p class="cart_extra_total_value ml-auto"><%=total_price%></p> -->
										</div>
										<div class="cart_extra_total_value ml-auto" style="margin-left: 5px !important;"> &euro;</div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Spedizione</div>
										<div class="cart_extra_total_value ml-auto" id="price_shipping">GRATUITA</div>
									</li>
									<li class="d-flex flex-row align-items-center justify-content-start">
										<div class="cart_extra_total_title">Totale</div>
										<div class="cart_extra_total_value ml-auto" id="total_price_order">
											<!--  <p class="cart_extra_total_value ml-auto"><%=total_price%></p>-->
										</div>
										<div class="cart_extra_total_value ml-auto" style="margin-left: 5px !important;"> &euro;</div>
									</li>
								</ul>
								<div class="checkout_button trans_200" onclick="submitCheckout()"><a style="color: white; cursor: pointer;">Procedi all'acquisto</a></div>
							</div>
						</div>
					</div>
					<%				}
					%>
				</div>
			</div>
		</div>

        <!-- Footer -->

		<%@ include file="footer.jsp" %>
	</div>
</div>

	<script>
/*
	function submittaForm() {
		var val = parseInt($("#loadedvalue").val());
		//if (val == 0) {
				$("#hiddenform").submit( function(e){
							e.stopPropagation();
						}
					);
		}
*/
	var shipping_price = 0;
	var total_price_order;
	var total_price;
	

	function submitCheckout() {
		var value1 = ("submitCheckout");
		$.ajax({
			type: "GET",
			url: "Cart",
			data: {"total_price_order": total_price_order, "shipping_price": shipping_price, "total_price": total_price, "operation": value1},
			success: function()
			{
				window.location = 'checkout.jsp';
			},
			error: function()
			{
				window.location = 'user_log.jsp';
			}
		}); 
	}

	$('input[type=radio][name=shipping_radio]').change(function() {
		if (this.value == 'veloce') {
			shipping_price = 4.99;
			total_price_order = parseFloat(total_price, 10) + parseFloat(shipping_price, 10);
			$("#price_shipping").text("4.99 €");
			$("#total_price_order").find("p").text(total_price_order);
		} else if (this.value == 'standard') {
			shipping_price = 1.99;
			total_price_order = parseFloat(total_price, 10) + parseFloat(shipping_price, 10);
			$("#price_shipping").text("1.99 €");
			$("#total_price_order").find("p").text(total_price_order);
		} else if (this.value == 'lenta') {
			shipping_price = 0;
			total_price_order = parseFloat(total_price, 10) + parseFloat(shipping_price, 10);
			$("#price_shipping").text("GRATUITA");
			$("#total_price_order").find("p").text(total_price_order);
		}
	});

	$ ( function selectQnt()
	{
		$("[id ='qnt_selector']").on('click', function (){
			var operation = "modifyQuantity";
			var id_cart = $("#id_cart").val();
		    var id_product = $(this).find("input#product").val();
		    var id_product_details = $(this).find("input#productDetails").val();	    
		    var operator = $(this).find("span").html();
			
		    var qntInCart = parseInt($(this).parent().find("p").html());
		    var productPrice = parseFloat($(this).parent().parent().prev().find("input").val());
		    
		    if(operator == "+")
		    	qntInCart += 1;
		    else if (operator == "-")
		    	qntInCart -= 1;
		   
		    var total = qntInCart*productPrice; 
		    var totalProductPrice = $(this).parent().parent().next().html(total+" €");
		   
		    //calling the ajax function
		    changeQnt(id_cart,id_product,id_product_details,operator,operation);
		});
	});
	
	function changeQnt(id_cart,id_product,id_product_details,operator,operation)
	{
		 $.ajax({
		    	type: "POST",
		    	url: "Cart",
		    	data: {"id_cart" : id_cart,"id_product" : id_product, "id_product_details" : id_product_details, "operator" : operator, "operation" : operation},
		    	success: function(results){
		    		calcolaTot()
		    	},
	        	error: function(results){
	        		}
		    });
	};
	
	function updateProductsInCart()
	{
			$.ajax({
				type: "GET",
				url : "Cart",
				data : {"operation" : "retrieveProducts"}
					});
	};
	
	function calcolaTot()
	{
		var quantities = document.getElementsByName("qntSelectorLabel");
		var prices = document.getElementsByName("productPrice");
		
		var qntXprice;
		var tot = 0.00;
		
		i=0; 	// quantities[i] � relativa a prices[i]
		while(i<quantities.length && i<prices.length)
		{
			qntXprice = parseInt(quantities[i].innerHTML) * prices[i].value; 
			tot += qntXprice;
			i++;
		}
		
		total_price = tot;
		total_price_order = tot + parseFloat(shipping_price);
		
		var price = numberWithCommas(tot);
		document.getElementById("total_price_order").innerHTML = "<input type=\"hidden\" name=\"totalCartPrice\" value="+total_price_order+"><p class=\"cart_extra_total_value ml-auto\">"+total_price_order+"</p>";
		document.getElementById("total_price").innerHTML = "<input type=\"hidden\" name=\"totalProductPrice\" value="+tot+"><p class=\"cart_extra_total_value ml-auto\">"+total_price+"</p>";
	}																														
	
	function numberWithCommas(tot) {
		var x = parseFloat(tot).toPrecision(4);
	    return x.toString().replace(".", ",");
	}
	
	$("[id ='removeProd']").on('click', function deletefromcart(){
		var id_cart = $("#id_cart").val();
	    var id_product_details = $(this).prev().prev().val();
	    var id_product = $(this).prev().val();
	    
	    $.ajax({
			type: "GET",
			url : "Cart",
			data : {"operation" : "deleteProduct", "id_product" : id_product ,"id_product_details" : id_product_details, "id_cart" : id_cart},
			success:function() {   //handle the successful return by reloading the current page
	            location.reload();
	        }
				});    
	});
	
	// questo � l'equivalente di $(document).ready
	document.addEventListener("DOMContentLoaded", function(event) { 
		calcolaTot();
		total_price_order = $("#total_price_order").find("p").text();
		total_price = $("#total_price").find("p").text();
	});
		
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
