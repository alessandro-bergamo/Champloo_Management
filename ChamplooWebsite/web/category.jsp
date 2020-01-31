<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="com.champloo.bean.*"
		import="java.util.*"
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
	<link rel="stylesheet" type="text/css" href="styles/category.css">
	<link rel="stylesheet" type="text/css" href="styles/category_responsive.css">

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
			HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap) session.getAttribute("productsByCategory");

			System.out.println("PRODOTTI: "+products);
		%>

		<!-- Home -->

		<div class="home">
			<div class="home_container d-flex flex-column align-items-center justify-content-end">
				<div class="home_content text-center">
					<div class="home_title"><%=session.getAttribute("category")%></div>
					<div class="breadcrumbs d-flex flex-column align-items-center justify-content-center">
					</div>
				</div>
			</div>
		</div>

		<!-- Products -->

		<%
			if(products != null && !products.isEmpty())
			{
		%>

		<div class="products">
			<div class="container">
				<div class="row products_row products_container grid">
					<!-- Product -->
					<%
						Iterator iterator = products.entrySet().iterator();
						while(iterator.hasNext())
						{
							HashMap.Entry entry = (HashMap.Entry) iterator.next();
							ProductBean product = (ProductBean) entry.getKey();
							ArrayList<ProductDetailsBean> productDetails = (ArrayList) entry.getValue();
					%>
					<div class="col-xl-4 col-md-6 grid-item new">
						<div class="product">
							<div class="product_image">
								<img src=<%=productDetails.get(0).getImg_path_folder()%>img1.png" alt="">
							</div>
							<div class="product_content">
								<div class="product_info d-flex flex-row align-items-start justify-content-start">
									<div>
										<div>
											<div class="product_name">
												<a href="Product?operation=showProduct&id_product=<%=product.getId_prod()%>&color=<%=productDetails.get(0).getColor()%>"><%=product.getName()%></a>
											</div>
											<div class="product_category">In <a href="Product?operation=retrieveByCategory&category=<%=session.getAttribute("category")%>"><%=product.getType()%></a></div>
										</div>
									</div>
									<div class="ml-auto text-right">
										<%
											if (product.getAverage_rating() == 0) {
										%>
											<div class="rating_r rating_r_0 home_item_rating">
										<%
											} else if (product.getAverage_rating() <= 20 && product.getAverage_rating() != 0) {
										%>
											<div class="rating_r rating_r_1 home_item_rating">
										<%
											} else if (product.getAverage_rating() > 20 && product.getAverage_rating() <= 40) {
										%>
											<div class="rating_r rating_r_2 home_item_rating">
										<%
											} else if (product.getAverage_rating() > 40 && product.getAverage_rating() <= 60) {
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
					<%	}	%>
				</div>
				<div class="row page_nav_row">
					<div class="col">
						<div class="page_nav">
							<ul class="d-flex flex-row align-items-start justify-content-center">
								<li class="active"><a href="#">01</a></li>
								<li><a href="#">02</a></li>
								<li><a href="#">03</a></li>
								<li><a href="#">04</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%
			}
		%>


		<!-- Footer -->

		<%@ include file="footer.jsp" %>

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
		<script src="plugins/Isotope/isotope.pkgd.min.js"></script>
		<script src="plugins/Isotope/fitcolumns.js"></script>
		<script src="js/category.js"></script>

</body>
</html>
