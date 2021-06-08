<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">
<c:set var="isThereASession" value="${requestScope['isThereASession']}"></c:set>
<c:set var="client" value="${requestScope['client']}"></c:set>
<c:set var="loginMessage" value="${requestScope['loginMessage']}"></c:set>
<c:set var="openmodal" value="${requestScope['openmodal']}"></c:set>
<c:set var="message" value="${requestScope['message']}"></c:set>
<c:set var="person" value="${requestScope['person']}"></c:set>
<c:set var="arrayIndexProducts"
	value="${requestScope['arrayIndexProducts']}"></c:set>
<head>
<fmt:setLocale value="es_MX" />
<title>Merak Shopping</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="description" content="Taller mecánico Gómez">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="icon" type="image/png" href="images/logo.png">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="css/aos.css"> -->
<link rel="stylesheet" href="css/main.css">
</head>

<body data-spy="scroll" data-target="#navbarNav" data-offset="50"
	onload="${openmodal}">
	<!-- MENU BAR -->
	<nav class="navbar navbar-expand-lg fixed-top">
		<div class="container">
			<img src="images/logo.png" class="logo-img"><a
				class="navbar-brand" href="Index">Bazar Merak Shopping</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ml-lg-auto">
					<li class="nav-item"><a href="#home"
						class="nav-link smoothScroll">INICIO</a></li>
					<li class="nav-item"><a href="#philosophy"
						class="nav-link smoothScroll">FILOSOFIA</a></li>
					<li class="nav-item"><a href="#store"
						class="nav-link smoothScroll">TIENDA</a></li>
					<li class="nav-item"><a href="#contact"
						class="nav-link smoothScroll">CONTACTO</a></li>
					<c:if test="${isThereASession == false}">
						<li class="nav-item"><a href="#"
							class="nav-link smoothScroll" data-toggle="modal"
							data-target="#Login">INICIAR SESION</a></li>
					</c:if>
					<c:if test="${isThereASession == true}">
						<c:if test="${client.user.type == 1}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
								role="button" aria-haspopup="true" aria-expanded="false">Perfil</a>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="Profile"> <i
										class="fa fa-user mr-3"></i>Perfil
									</a> <a class="dropdown-item"
										href="Cart?user=${client.user.user}&page=Store"><i
										class="fa fa-shopping-cart mr-3"></i>Carrito</a>
									<div class="dropdown-divider"></div>
									<a href="#" class="dropdown-item" data-toggle="modal"
										data-target="#logout"><i class="fa fa-sign-out mr-3"></i>Cerrar
										sesión</a>
								</div></li>
						</c:if>
						<c:if test="${client.user.type == 0}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
								role="button" aria-haspopup="true" aria-expanded="false">Perfil</a>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="AdminProducts"><i
										class="fa fa-shopping-cart mr-3"></i>Admin. Productos</a>
									<div class="dropdown-divider"></div>
									<a href="#" class="dropdown-item" data-toggle="modal"
										data-target="#logout"><i class="fa fa-sign-out mr-3"></i>Cerrar
										sesión</a>
								</div></li>
						</c:if>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>

	<!-- HERO -->
	<section
		class="hero d-flex flex-column justify-content-center align-items-center"
		id="home">
		<div class="bg-overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto col-12">
					<div class="hero-text mt-5 text-center">
						<h1 class="text-white" data-aos="fade-up" data-aos-delay="500">¿Quiénes
							somos?</h1>
						<h6 data-aos="fade-up" data-aos-delay="300">Bazar de ropa
							Merak shopping es una PyME que ofrece ropa y accesorios para
							cualquier persona, desde infantes hasta adultos, esto dependiendo
							de la disponibilidad de los artículos.</h6>
						<a href="#philosophy" class="btn custom-btn mt-3"
							data-aos="fade-up" data-aos-delay="600">Filosofí­a</a>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- PHILOSOPHY -->
	<section class="feature" id="philosophy">
		<div class="container">
			<div class="row">
				<div
					class="d-flex flex-column justify-content-center ml-lg-auto mr-lg-6 col-lg-6 col-md-6 col-12">
					<h2 class="mb-3 text-white" data-aos="fade-up">Misión</h2>
					<p data-aos="fade-up" data-aos-delay="200">Dar productos de
						buena calidad a precios bajos pero con la seguridad de que el
						cliente haya comprado un producto legítimo.</p>
				</div>
				<div class="mr-lg-auto mt-6 col-lg-6 col-md-6 col-12">
					<div class="about-working-hours">
						<div>
							<h2 class="mb-3 text-white" data-aos="fade-up">Visión</h2>
							<p data-aos="fade-up" data-aos-delay="200">Ofrecer un
								servicio de compra en línea efectivo y confiable que haga crecer
								como empresa para contar con más productos y en un futuro lograr
								tener más alcance en las personas que les interese comprar ropa
								y accesorios de marca.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- STORE -->
	<section class="about section" id="store">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-12 text-center mb-5">
					<h6 data-aos="fade-up">Encuentra todos los productos que
						necesitas para construir tu estilo</h6>
					<h2 data-aos="fade-up" data-aos-delay="200">Nuestra tienda en
						lí­nea</h2>
				</div>
				<c:forEach items="${arrayIndexProducts}" var="product">
					<div class="col-lg-4 col-md-6 col-12" data-aos="fade-up"
						data-aos-delay="400">
						<div class="class-thumb">
							<img src="${product.photo}" class="img-fluid" alt="Class">
							<div class="class-info">
								<h3 class="mb-1">${product.name}</h3>
								<c:if test="${product.category == 0}">
									<span><strong>Categoría</strong> - Hombres</span>
								</c:if>
								<c:if test="${product.category == 1}">
									<span><strong>Categoría</strong> - Mujeres</span>
								</c:if>
								<c:if test="${product.category == 2}">
									<span><strong>Categoría</strong> - Otros</span>
								</c:if>
								<c:if test="${(100 - (product.discount * 100) / product.price) == '0.00'}">
									<p>
										<strong><fmt:formatNumber value="${product.price}"
												type="currency" /></strong>
									</p>
								</c:if>
								<c:if test="${(100 - (product.discount * 100) / product.price) != '0.00'}">
									<p>
										<strong><fmt:formatNumber value="${product.discount}"
												type="currency" /></strong> <span
											style="text-decoration: line-through;"><fmt:formatNumber
												value="${product.price}" type="currency" /></span>
									</p>
								</c:if>
								<c:if test="${isThereASession == false}">
									<a href="#" class="btn col-md-12 custom-btn bg-color mt-5"
										data-toggle="modal" data-target="#Login"><i
										class="fa fa-cart-plus mr-2"></i>Agregar al carrito</a>
								</c:if>
								<c:if test="${isThereASession == true}">
									<c:if test="${product.inCart == true}">
										<a
											href="RemoveFromCart?idProduct=${product.id}&user=${client.user.user}&page=Index#store"
											class="btn col-md-12 store-btn dark-color mt-5"><i
											class="fa fa-shopping-cart mr-2"></i>Quitar del carrito</a>
										<c:set var="done" value="true" />
									</c:if>
									<c:if test="${product.inCart == false}">
										<a
											href="AddToCart?idProduct=${product.id}&user=${client.user.user}&page=Index#store"
											class="btn col-md-12 custom-btn bg-color mt-5"><i
											class="fa fa-cart-plus mr-2"></i>Agregar al carrito</a>
									</c:if>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
				<a href="Store" class="btn col-md-12 store-btn dark-color mt-5">Ir
					a la tienda completa</a>
			</div>
		</div>
	</section>

	<!-- CONTACT -->
	<section class="contact section" id="contact">
		<div class="container">
			<div class="row">
				<div class="ml-auto col-lg-5 col-md-6 col-12">
					<h2 class="mb-4 pb-2" data-aos="fade-up" data-aos-delay="200">Contáctanos</h2>
					<strong class="mt-3 d-block" data-aos="fade-up"
						data-aos-delay="700">Lunes - Viernes</strong>
					<p data-aos="fade-up" data-aos-delay="800">10:00 AM - 07:00 PM</p>
					<strong class="mt-3 d-block" data-aos="fade-up"
						data-aos-delay="700">Sábado</strong>
					<p data-aos="fade-up" data-aos-delay="800">10:00 AM - 05:00 PM</p>
					<strong class="mt-3 d-block" data-aos="fade-up"
						data-aos-delay="700">Domingo</strong>
					<p data-aos="fade-up" data-aos-delay="800">12:00 PM - 4:00 PM</p>
					<p data-aos="fade-up" data-aos-delay="800">
						<i class="fa fa-envelope-o mr-1"></i>bazarmerak@gmail.com
					</p>
					<p data-aos="fade-up" data-aos-delay="800">
						<i class="fa fa-phone mr-1"></i>55 57 74 38 57
					</p>
					<p data-aos="fade-up" data-aos-delay="800">
						<i class="fa fa-whatsapp mr-1"></i>55 60 17 46 78
					</p>
				</div>
				<div class="mx-auto mt-4 mt-lg-0 mt-md-0 col-lg-5 col-md-6 col-12">
					<h2 class="mb-4" data-aos="fade-up" data-aos-delay="200">Instagram</h2>
					<p data-aos="fade-up" data-aos-delay="800">
						<i class="fa fa-instagram mr-1"></i><a href="https://www.instagram.com/merakshopping/">@merakshopping</a>
						<br><br><br><a href="https://www.instagram.com/merakshopping/"><img alt="instagram" src="images/instagram.png" width="100%"></a>
					</p>
				</div>
			</div>
		</div>
	</section>

	<!-- Login Modal -->
	<div class="modal fade" id="Login" tabindex="-1" role="dialog"
		aria-labelledby="LoginLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="LoginLabel">Iniciar sesión</h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p class="error">${loginMessage}</p>
					<p class="error">${message}</p>
					<form action="Login" class="membership-form webform text-center"
						role="form" method="POST">
						<input type="text" class="form-control" name="username"
							placeholder="Nombre de usuario"> <input type="password"
							class="form-control" name="password" placeholder="Contraseña">
						<button type="submit" class="form-control" id="submit-button">Iniciar
							sesión</button>
						<label class="text-small text-muted" for="signup-agree">Si
							no tienes una cuenta, <a href="#" data-toggle="modal"
							data-target="#Register">Regístrate</a>
						</label>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<!-- Logout Modal -->
	<div class="modal fade" id="logout" tabindex="-1" role="dialog"
		aria-labelledby="logoutLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="logoutLabel">Cerrar sesión</h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>¿Estás seguro que quieres cerrar sesión?</p>
					<a href="Logout" class="btn col-md-12 custom-btn bg-color">Cerrar
						sesión</a>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<!-- Register Modal -->
	<div class="modal fade" id="Register" tabindex="-1" role="dialog"
		aria-labelledby="RegisterLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="RegisterLabel">Regístrate</h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p class="error">${message}</p>
					<form class="membership-form webform text-center" role="form"
						method="POST" action="Register">
						<div class="row">
							<div class="col-md-4">
								<input type="text" class="form-control" name="nameTxt"
									placeholder="Nombre(s)" value="${person.name}" required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control" name="surnameTxt"
									placeholder="Primer Apellido" value="${person.surname}"
									required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control" name="lastnameTxt"
									placeholder="Segundo Apellido" value="${person.lastname}"
									required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<input type="text" class="form-control" name="usernameTxt"
									placeholder="Nombre de usuario" required>
							</div>
							<div class="col-md-4">
								<input type="password" class="form-control" name="passwordTxt"
									placeholder="Contraseña" required>
							</div>
							<div class="col-md-4">
								<input type="password" class="form-control"
									name="repeatPasswordTxt"
									placeholder="Confirmación de contraseña" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<input type="date" class="form-control" name="birthdayDate"
									placeholder="Fecha de nacimiento" value="${person.birthday}"
									required>
							</div>
							<div class="col-md-4">
								<input type="email" class="form-control" name="emailTxt"
									placeholder="Correo electrónico"
									value="${person.contact.email}" required>
							</div>
							<div class="col-md-4">
								<input type="tel" class="form-control" name="phoneTxt"
									placeholder="Numero de teléfono"
									value="${person.contact.phone}" pattern="[0-9]{10}" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<input type="text" class="form-control" name="streetTxt"
									placeholder="Calle" value="${person.direction.street}" required>
							</div>
							<div class="col-md-2">
								<input type="number" class="form-control" name="numberTxt"
									placeholder="Número" value="${person.direction.number}"
									required>
							</div>
							<div class="col-md-2">
								<input type="text" class="form-control" name="postalCodeTxt"
									placeholder="Código Postal"
									value="${person.direction.postalCode}" required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control" name="suburbTxt"
									placeholder="Colonia o población"
									value="${person.direction.suburb}" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<input type="text" class="form-control" name="townhallTxt"
									placeholder="Delegación o municipio"
									value="${person.direction.townhall}" required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control"
									value="${person.direction.state}" name="stateTxt"
									placeholder="Estado" required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control" name="countryTxt"
									placeholder="País" value="México" readonly="readonly">
							</div>
						</div>
						<button type="submit" class="form-control" id="submit-button">Terminar
							registro</button>
						<label class="text-small text-muted" for="signup-agree">Si
							ya tienes una cuenta, <a href="#">Inicia sesión</a>
						</label>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<!-- SCRIPTS -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/aos.js"></script>
	<script src="js/smoothscroll.js"></script>
	<script src="js/custom.js"></script>

</body>

</html>