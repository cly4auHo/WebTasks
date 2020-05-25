<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<script src="<c:url value='/resources/js/jquery-3.5.0.js' />"></script>
<head>
<title>CoffeeShop</title>
<link href="<c:url value='/resources/styles/bootstrap.css' />"
	rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<!-- Custom Theme files -->
<link href="<c:url value='/resources/styles/style.css' />"
	rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="BOX SHOP Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 



</script>
<!--webfont-->
<link
	href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<!-- start menu -->
<link href="<c:url value='/resources/styles/megamenu.css' />"
	rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript"
	src="<c:url value='/resources/js/megamenu.js' />"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>

<link rel="stylesheet"
	href="<c:url value='/resources/styles/flexslider.css' />"
	type="text/css" media="screen" />
<script type="text/javascript">
	$(window).load(function() {
		$("#flexiselDemo").flexisel({
			visibleItems : 5,
			animationSpeed : 1000,
			autoPlay : false,
			autoPlaySpeed : 3000,
			pauseOnHover : true,
			enableResponsiveBreakpoints : true,
			responsiveBreakpoints : {
				portrait : {
					changePoint : 480,
					visibleItems : 1
				},
				landscape : {
					changePoint : 640,
					visibleItems : 2
				},
				tablet : {
					changePoint : 768,
					visibleItems : 3
				}
			}
		});
	});
</script>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.flexisel.js' />"></script>
</head>
<body>
	<!-- header-section-starts -->
	<div class="header">
		<div class="top-header">
			<div class="wrap">
				<div class="header-left">
					<ul>
						<li><a href="#">24x7 Customer Care </a></li> |
						<li><a href="#"> Track Order</a></li>
					</ul>
				</div>
				<div class="header-right">
					<ul>
						<li><i class="user"></i> <a href="/eStoreSpring/login"
							id="userName">${sessionScope.currentUserName}</a></li>
						<li><i class="cart"></i> <a href="/eStoreSpring/cart">Shopping
								Cart</a></li>
						<span id='cartQnt' class="last">${sessionScope.CART_VALUE}</span>

						<c:if test="${sessionScope.keyUser != null}">
							<div style='float: left;'>
								<input type="button" class='botton' onclick='logOut(this)'
									id="loginOut" value="LOG OUT">|
								</li>
							</div>

						</c:if>
						<c:if test="${sessionScope.keyUser == null}">
							<form style='float: left;' action='login' method='post'>
								<input type='submit' class='botton' value='Sign In'>|
								</li>
							</form>
						</c:if>
						<form style='float: left;' action='/eStoreSpring/registration'
							method='post'>

							<li class='login'><input type='submit' class='botton'
								value='Sign Up'></li>
						</form>
					</ul>

				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="wrap">
			<div class="header-bottom">
				<div class="logo">
					<a href="/eStoreSpring"><img width='50px' height='50px'
						src="<c:url value='/resources/images/logo.jpg' />"
						class="img-responsive" alt="" /></a>
				</div>
			</div>
		</div>
	</div>
	<!-- header-section-ends -->
	<div class="wrap">
		<div class="navigation-strip">
			<h4>
				Most Popular<i class="arrow"></i>
			</h4>
			<div class="top-menu">
				<!-- start header menu -->
				<ul class="megamenu skyblue">

					<li><a class="color4" href="/eStoreSpring/products">Products</a>

						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>shop</h4>
										<ul>
											<li><a href="/eStoreSpring/products">new arrivals</a></li>
											<li><a href="/eStoreSpring/products">accessories</a></li>
											<li><a href="/eStoreSpring/products">brands</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>help</h4>
										<ul>
											<li><a href="/eStoreSpring/products">trends</a></li>
											<li><a href="/eStoreSpring/products">contacts</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>account</h4>
										<ul>
											<li><a href="/eStoreSpring/products">login</a></li>
											<li><a href="/eStoreSpring/registration">create an
													account</a></li>
											<li><a href="/eStoreSpring/login">my shopping bag</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col2"></div>
								<div class="col1"></div>
								<div class="col1"></div>
								<div class="col1"></div>
								<div class="col1"></div>
							</div>
						</div></li>

					<li><a class="color1"
						href="/eStoreSpring/products?category=U.S.A.">U.S.A.</a>
						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>shop</h4>
										<ul>
											<li><a href="/eStoreSpring/products">new arrivals</a></li>
											<li><a href="/eStoreSpring/products">accessories</a></li>
											<li><a href="/eStoreSpring/products">brands</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>help</h4>
										<ul>
											<li><a href="/eStoreSpring/products">trends</a></li>
											<li><a href="/eStoreSpring/products">contacts</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>account</h4>
										<ul>
											<li><a href="/eStoreSpring/products">login</a></li>
											<li><a href="/eStoreSpring/registration">create an
													account</a></li>
											<li><a href="/eStoreSpring/login">my shopping bag</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col2"></div>
								<div class="col1"></div>
								<div class="col1"></div>
								<div class="col1"></div>
								<div class="col1"></div>
							</div>
						</div></li>
					<li class="grid"><a class="color2"
						href="/eStoreSpring/products?category=Euro">Euro</a>
						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>shop</h4>
										<ul>
											<li><a href="/eStoreSpring/products">new arrivals</a></li>
											<li><a href="/eStoreSpring/products">accessories</a></li>
											<li><a href="/eStoreSpring/products">brands</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>help</h4>
										<ul>
											<li><a href="/eStoreSpring/products">trends</a></li>
											<li><a href="/eStoreSpring/products">contacts</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>account</h4>
										<ul>
											<li><a href="/eStoreSpring/products">login</a></li>
											<li><a href="/eStoreSpring/registration">create an
													account</a></li>
											<li><a href="/eStoreSpring/login">my shopping bag</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col2"></div>
								<div class="col1"></div>
								<div class="col1"></div>
								<div class="col1"></div>
								<div class="col1"></div>
							</div>
						</div></li>
					<li class="grid"><a class="color3"
						href="/eStoreSpring/products?category=Japan">Japan</a>
						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>shop</h4>
										<ul>
											<li><a href="/eStoreSpring/products">new arrivals</a></li>
											<li><a href="/eStoreSpring/products">accessories</a></li>
											<li><a href="/eStoreSpring/products">brands</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>help</h4>
										<ul>
											<li><a href="/eStoreSpring/products">trends</a></li>
											<li><a href="/eStoreSpring/products">contacts</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>account</h4>
										<ul>
											<li><a href="/eStoreSpring/products">login</a></li>
											<li><a href="/eStoreSpring/registration">create an
													account</a></li>
											<li><a href="/eStoreSpring/login">my shopping bag</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col2"></div>
								<div class="col1"></div>
								<div class="col1"></div>
								<div class="col1"></div>
								<div class="col1"></div>
							</div>
						</div></li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="main">
			<div class="content">
				<div class="content_top">
					<div class="heading">
						<c:set var="order" value="0" />
						<div id="block">
							<c:forEach var="product" items="${sessionScope.CART}">
								<c:set var="order"
									value="${order+(product.key.price)*product.value}" />
							</c:forEach>
							<c:set var="order" value="${order}" />
						</div>

						<h3 id="sumOrder">Your Order: ${order}₴</h3>
					</div>

					<div class="clearfix"></div>
				</div>

				<ul class="products clearfix">

					<c:forEach var="product" items="${sessionScope.CART}">
						<li class="product-wrapper"><c:set var="countProducts"
								value="${product.value}" />
							<div class="product">

								<a href="/eStoreSpring/products?id=${product.key.id}"><div
										class="product-photo">

										<img height="${imgHeight}"
											src="<c:url value='/resources/images/${product.key.id}.jpg' />"
											alt="" />
									</div></a>
								<h2>${product.key.name}</h2>
								<p>${product.key.description}</p>
								<c:if test="${product.key.lenghtName <= 24}">
									<br>
								</c:if>
								<p>
									<span class="price">${product.key.price}₴</span>
								</p>
								<img width='25px' height='25px'
									src="<c:url value='/resources/images/minus.png' />"
									onclick='minus("${product.key.id}")' /> <input size='2'
									type='text' id='${product.key.id}' value='${countProducts}' />
								<img width='25px' height='25px'
									src="<c:url value='/resources/images/plus.png' />"
									onclick='plus("${product.key.id}")' /> <br> <input
									type='button' value='Details' style="color: blue;"
									onclick="javascript:location.href='/eStoreSpring/products?id=${product.key.id}'" />
								<br> <img align="left" height="30" width='30px'
									src="<c:url value='/resources/images/bin.png' />"
									onclick='deleteProduct("${product.key.id}")' />

							</div></li>
					</c:forEach>


				</ul>

				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="wrap">
			<div class="footer-middle">
				<div class="col-md-6 about-text text-right">
					<h4>About CoffeeShop</h4>
					<p>We are the best!</p>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="cards text-center">
				<img src="<c:url value='/resources/images/cards.jpg' />" alt="" />
			</div>
			<div class="copyright text-center">
				<p>Copyright &copy; 2020 All rights reserved | Template by
					ShitCoder</p>
			</div>

		</div>
	</div>

	<script>
		function plus(id) {
			var qnt = document.getElementById(id);
			qnt.value = +qnt.value + 1;
			updateCountProducts(id);
		}

		function minus(id) {
			var qnt = document.getElementById(id);
			if (qnt.value > 1) {
				qnt.value = +qnt.value - 1;
				updateCountProducts(id);
			}
		}

		function updateCountProducts(id) {
			var qnt = document.getElementById(id).value;
			$.ajax({
				url : 'cart',
				type : 'post',
				data : {
					productId : id,
					productCount : qnt,
				},
				success : function(data) { // вешаем свой обработчик на функцию success
					document.getElementById('sumOrder').innerHTML = data + "₴";
				}
			})
		}

		function getProductById(id) {
			$.ajax({
				url : 'products',
				type : 'post',
				data : {
					idProduct : id,
				},
				success : function(data) { // вешаем свой обработчик на функцию success
					window.location.href = '/eStoreSpring/products';
				}
			})
		}

		function deleteProduct(id) {
			$.ajax({
				url : 'cart',
				type : 'post',
				data : {
					productDeleteId : id,
				},
				success : function(data) { // вешаем свой обработчик на функцию success
					window.location.href = '/eStoreSpring/cart';
				}
			})
		}

		function logOut(input) {
			$.ajax({
				url : 'login',
				type : 'post',
				data : {
					loginOut : "loginOut",
				},
				success : function(data) {
					document.getElementById('userName').innerHTML = "";
					if (input.value == "LOG OUT") {
						input.value = "Sign In";
					} else {
						window.location.href = '/eStoreSpring/login';
					}
				}
			})
		}
	</script>

</body>
</html>