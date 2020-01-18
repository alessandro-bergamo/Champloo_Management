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
	<link rel="stylesheet" type="text/css" href="styles/reso.css">
	<link rel="stylesheet" type="text/css" href="styles/reso_responsive.css">

</head>
<body>

<!-- Menu -->

<h2>HTML Forms</h2>

<form action="PaymentMethod" method="GET">
	ID:<br>
	<input type="text" name="id">
	<br>
	CVC:<br>
	<input type="text" name="cvc">
	<br><br>
	NUMBER:<br>
	<input type="text" name="number" maxlength="16">
	<br><br>
	CARD BANK:<br>
	<input type="text" name="bank">
	<br><br>
	OWNER:<br>
	<input type="text" name="owner">
	<br><br>
	USERNAME:<br>
	<input type="text" name="username">
	<br><br>
	EXPIRY DATE:<br>
	<input type="date" name="expiry">
	<br><br>
	REGISTRATION DATE:<br>
	<input type="date" name="registration_date">
	<br><br>
	<input type="hidden" name="operation" value="insert">
	<input type="submit" onclick="myFunction()" value="Submit" id="cliccato">
</form>

<p>If you click the "Submit" button, the form-data will be sent to a page called "/action_page.php".</p>



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


	<script>
		function myFunction()
		{
			var operationz = document.getElementsByName("operation")[0].value;
			alert("ciao "+operationz);
		}
	</script>


</body>

</html>