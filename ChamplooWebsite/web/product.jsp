<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="com.champloo.bean.*"
		import="java.util.*"
		import="java.text.DecimalFormat"
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
	
	<!-- CDN SWEETALERT2 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

	<!-- STILI CSS -->
	<link rel="stylesheet" type="text/css" href="styles/product.css">
	<link rel="stylesheet" type="text/css" href="styles/product_responsive.css">

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

		<% 	final int SIZES_NUMBER = 4;
			
			HashMap<ProductBean, ArrayList<ProductDetailsBean>> productMap = (HashMap) request.getSession().getAttribute("product");
			ArrayList<ProductDetailsBean> selectedProductsByColor = (ArrayList)request.getSession().getAttribute("selectedProductsByColor");
			
			ArrayList<String> printedColors = new ArrayList<>();
			ProductBean product = null;
			ArrayList<ProductDetailsBean> product_details = null;
			
			HashMap.Entry<ProductBean, ArrayList<ProductDetailsBean>> entry;
			if(productMap != null)
			{
				entry = productMap.entrySet().iterator().next();
				product = entry.getKey();
				product_details = entry.getValue();
			}
			
			if(product != null && product_details != null)
			{
		%>

		<!-- Home -->

		<div class="home">
			<div class="home_container d-flex flex-column align-items-center justify-content-end">
				<div class="home_content text-center">
				</div>
			</div>
		</div>

		<!-- Product -->

		<div class="product">
			<div class="container">
				<div class="row">
					<input type="hidden" id="id_product" value="<%=product.getId_prod()%>">
					<input type="hidden" id="product_status" value="<%=product.getStatus()%>">
					<!-- Product Image -->
					<div class="col-lg-6">
						<div class="product_image_slider_container">
							<div id="slider" class="flexslider">
								<ul class="slides">
									<li>
										<img src=<%=selectedProductsByColor.get(0).getImg_path_folder()%>img1.png" />
									</li>
									<li>
										<img src=<%=selectedProductsByColor.get(0).getImg_path_folder()%>img2.png" />
									</li>
									<li>
										<img src=<%=selectedProductsByColor.get(0).getImg_path_folder()%>img3.png" />
									</li>
									<li>
										<img src=<%=selectedProductsByColor.get(0).getImg_path_folder()%>img4.png" />
									</li>
								</ul>
							</div>
							<div class="carousel_container">
								<div id="carousel" class="flexslider">
									<ul class="slides">
										<li>
											<div>
												<img src=<%=selectedProductsByColor.get(0).getImg_path_folder()%>img1.png">
											</div>
										</li>
										<li>
											<div>
												<img src=<%=selectedProductsByColor.get(0).getImg_path_folder()%>img2.png">
											</div>
										</li>
										<li>
											<div>
												<img src=<%=selectedProductsByColor.get(0).getImg_path_folder()%>img3.png">
											</div>
										</li>
										<li>
											<div>
												<img src=<%=selectedProductsByColor.get(0).getImg_path_folder()%>img4.png">
											</div>
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
							<div class="product_name"><%=product.getName()%></div>
							<div class="product_category">In <a href="category.html"><%=product.getType()%></a></div>
							<div class="product_rating_container d-flex flex-row align-items-center justify-content-start">
								<div class="rate">
									
									<label for="star5" title="5 Stelle" id="ratingScore">5 stars
										<input type="hidden" id="ratingScore" value="5">
									</label>
									
									<label for="star4" title="4 Stelle" id="ratingScore">4 stars
										<input type="hidden" value="4">
									</label>
									
									<label for="star3" title="3 Stelle" id="ratingScore">3 stars
										<input type="hidden" value="3">
									</label>
									
									<label for="star2" title="2 Stelle" id="ratingScore">2 stars
										<input type="hidden" value="2">
									</label>
									
									<label for="star1" title="1 Stella" id="ratingScore">1 star
										<input type="hidden" value="1">
									</label>
									
								</div>
								<% 	float average_rating = product.getAverage_rating();
									int reviewers = product.getNumber_feedback_users();
									float effective_rating = average_rating/reviewers;
									String format = new DecimalFormat("##.##").format(effective_rating); %>
								<div class="product_reviews"><%=format%> su (<%=reviewers%>)</div>
								<div class="product_reviews_link">Recensioni</div>
							</div>
							<div class="product_price"><%=String.valueOf(product.getPrice()).substring(0, String.valueOf(product.getPrice()).indexOf("."))%><span><%=String.valueOf(product.getPrice()).substring(String.valueOf(product.getPrice()).indexOf("."))%> &euro;</span></div>
							<div class="product_colors">
							<%for (int i = 0; i < product_details.size(); i++)
								{
									if(!printedColors.contains(product_details.get(i).getColor()))
									{
								%>
									<div>
										<a href="Product?operation=showProduct&id_product=<%=product.getId_prod()%>&color=<%=product_details.get(i).getColor()%>&id_product_details=<%=product_details.get(i).getId_prod_details()%>"><img src=<%=product_details.get(i).getImg_path_folder()%>img1.png"></a>
									</div>
							<%
									printedColors.add(product_details.get(i).getColor());
									}
								}%>
							</div>
							<div class="product_size">
								<div class="product_size_title">Seleziona taglia</div>
								<ul class="d-flex flex-row align-items-start justify-content-start">
									<%	boolean tagliaS=false, tagliaM=false, tagliaL=false, tagliaXL=false, tagliaXXL=false;
									int id_product_details_by_sizeS=0, id_product_details_by_sizeM=0, id_product_details_by_sizeL=0, id_product_details_by_sizeXL=0;
									
										for(int i = 0; i < selectedProductsByColor.size(); i++)
										{
											if(selectedProductsByColor.get(i).getSize().equals("S"))
											{
												tagliaS = true;
												id_product_details_by_sizeS = selectedProductsByColor.get(i).getId_prod_details();
											}
											else if(selectedProductsByColor.get(i).getSize().equals("M"))
											{
												tagliaM = true;
												id_product_details_by_sizeM = selectedProductsByColor.get(i).getId_prod_details();
											}
											else if(selectedProductsByColor.get(i).getSize().equals("L"))
											{
												tagliaL = true;
												id_product_details_by_sizeL = selectedProductsByColor.get(i).getId_prod_details();
											}
											else if(selectedProductsByColor.get(i).getSize().equals("XL"))
											{
												tagliaXL = true;	
												id_product_details_by_sizeXL = selectedProductsByColor.get(i).getId_prod_details();
											}
										}
						
										if(tagliaS && !( product.getStatus() == ProductBean.UNAVAILABLE_PRODUCT) ) {
											%>
											<input type="radio" id="radio_1" name="product_radio" value="<%=id_product_details_by_sizeS%>" class="regular_radio radio_1" required>
											<label for="radio_1">S</label>
											<%
										}
										if (!tagliaS || product.getStatus() == ProductBean.UNAVAILABLE_PRODUCT)
										{	%>
											<input type="radio" id="radio_1" disabled name="product_radio" class="regular_radio radio_1">
											<label for="radio_1">S</label>
										<%}
										if(tagliaM && !( product.getStatus() == ProductBean.UNAVAILABLE_PRODUCT) ) {
											%>
											<input type="radio" id="radio_2" name="product_radio" value="<%=id_product_details_by_sizeM%>" class="regular_radio radio_2" required>
											<label for="radio_2">M</label>
											<%
										}
										if (!tagliaM || product.getStatus() == ProductBean.UNAVAILABLE_PRODUCT)
										{	%>
											<input type="radio" id="radio_2" disabled name="product_radio" class="regular_radio radio_2">
											<label for="radio_2">M</label>
										<%}
										if(tagliaL && !( product.getStatus() == ProductBean.UNAVAILABLE_PRODUCT) ) {
											%>
											<input type="radio" id="radio_3" name="product_radio" value="<%=id_product_details_by_sizeL%>" class="regular_radio radio_3" required>
											<label for="radio_3">L</label>
											<%
										}
										if (!tagliaL || product.getStatus() == ProductBean.UNAVAILABLE_PRODUCT)
										{	%>
											<input type="radio" id="radio_3" disabled name="product_radio" class="regular_radio radio_3">
											<label for="radio_3">L</label>
										<%}
										if(tagliaXL && !( product.getStatus() == ProductBean.UNAVAILABLE_PRODUCT)) {
											%>
											<input type="radio" id="radio_4" name="product_radio" value="<%=id_product_details_by_sizeXL%>" class="regular_radio radio_4" required>
											<label for="radio_4">XL</label>
											<%
										}
										if (!tagliaXL || product.getStatus() == ProductBean.UNAVAILABLE_PRODUCT)
										{	%>
											<input type="radio" id="radio_4" disabled name="product_radio" class="regular_radio radio_4">
											<label for="radio_4">XL</label>
										<%}
										
									}%>
								</ul>
							</div>
							<div class="product_text">
								<p><%=product.getDescription()%></p>
							</div>
							<div class="product_buttons" onclick="addToCart()">
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

	<script>
		function addToCart()
		{
			var value1 = $("[name='product_radio']");
			var checkedValue = value1.filter(":checked").val();
			var value2 = ("insertProduct");
			var value3 = $("#id_product").val();
			var value4 = $("#product_status").val();
			
			$.ajax({
				type: "GET",
				url: "Cart",
				data: {"id_product" : value3, "status_product" : value4 ,"id_product_details" : checkedValue, "operation" : value2},
				success: function(results){
					Swal.fire({
						title: 'Aggiunto al Carrello',
						timer: 1700,
						icon: 'success',
						showCancelButton: false,
						showConfirmButton: false,
						width: '400px',
					})
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
		};
		
		$("[id='ratingScore']").on("click", function setRating()
		{
			var id_product = $("#id_product").val();
			var rating = $(this).find("input").val();
			var operation = "updateRating";
			
			$.ajax({
				type: "GET",
				url: "Product",
				data: {"id_product" : id_product, "rating" : rating, "operation" : operation},
				success: function(results)
				{
					Swal.fire({
					title: 'Recnsione Effettuata',
					timer: 1700,
					icon: 'success',
					showCancelButton: false,
					showConfirmButton: false,
					width: '400px',
					})
				},
				error: function (result){
					Swal.fire({
						title: 'Recensione non Effettuata',
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
<script src="js/logout.js"></script>

</body>
</html>