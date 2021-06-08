<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">

<head>
<c:set var="isThereASession" value="${requestScope['isThereASession']}"></c:set>
<c:set var="client" value="${requestScope['client']}"></c:set>
<c:set var="arrayProducts" value="${requestScope['arrayProducts']}"></c:set>
<c:set var="cart" value="${requestScope['cart']}"></c:set>
<c:set var="sizeCart" value="${requestScope['sizeCart']}"></c:set>
<c:set var="openmodalcart" value="${requestScope['openmodalcart']}"></c:set>
<c:set var="subtotal" value="${requestScope['subtotal']}"></c:set>
<c:set var="shipping" value="80"></c:set>
<c:set var="openmodalpay" value="${requestScope['openmodalpay']}"></c:set>
<c:set var="productsInOrder" value="${requestScope['productsInOrder']}"></c:set>
<c:set var="alertOk" value="${requestScope['alertOk']}"></c:set>
<c:set var="quantityMessage" value="${requestScope['quantityMessage']}"></c:set>
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
	onload="${openmodalcart} ${openmodalpay}">

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
					<li class="nav-item"><a href="Index"
						class="nav-link smoothScroll">INICIO</a></li>
					<li class="nav-item"><a href="Index#philosophy"
						class="nav-link smoothScroll">FILOSOFIA</a></li>
					<li class="nav-item"><a href="Store"
						class="nav-link smoothScroll">TIENDA</a></li>
					<li class="nav-item"><a href="Index#contact"
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
								role="button" aria-haspopup="true" aria-expanded="false">PERFIL</a>
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

	<!-- STORE -->
	<section class="about section" id="store">
		<div class="container">
			<ul class="nav nav-tabs justify-content-center" id="myTab"
				role="tablist">
				<li class="nav-item categories" role="presentation"><a
					class="nav-link active" id="all-tab" data-toggle="tab" href="#all"
					role="tab" aria-controls="all" aria-selected="true">Todos los
						productos</a></li>
				<li class="nav-item categories" role="presentation"><a
					class="nav-link" id="men-tab" data-toggle="tab" href="#men"
					role="tab" aria-controls="men" aria-selected="false">Hombres</a></li>
				<li class="nav-item categories" role="presentation"><a
					class="nav-link" id="women-tab" data-toggle="tab"
					href="#women" role="tab" aria-controls="women"
					aria-selected="false">Mujeres</a></li>
				<li class="nav-item categories" role="presentation"><a
					class="nav-link" id="other-tab" data-toggle="tab" href="#other"
					role="tab" aria-controls="other" aria-selected="false">Otros
						productos</a></li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="all" role="tabpanel"
					aria-labelledby="all-tab">
					<br> <br>
					<div class="row">
						<c:forEach items="${arrayProducts}" var="product">
							<c:if test="${product.quantity > 0}">
								<div class="col-lg-3 col-md-3 col-12" data-aos="fade-up"
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
													<strong><fmt:formatNumber
															value="${product.discount}" type="currency" /></strong> <span
														style="text-decoration: line-through;"><fmt:formatNumber
															value="${product.price}" type="currency" /></span>
												</p>
											</c:if>
											<p>${product.description}</p>
											<c:if test="${isThereASession == false}">
												<a href="#" class="btn col-md-12 custom-btn bg-color mt-5"
													data-toggle="modal" data-target="#Login"><i
													class="fa fa-cart-plus mr-2"></i>Agregar</a>
											</c:if>
											<c:if test="${isThereASession == true}">
												<c:if test="${product.inCart == true}">
													<a
														href="RemoveFromCart?idProduct=${product.id}&user=${client.user.user}&page=Store"
														class="btn col-md-12 store-btn dark-color mt-5"><i
														class="fa fa-shopping-cart mr-2"></i>Quitar</a>
													<c:set var="done" value="true" />
												</c:if>
												<c:if test="${product.inCart == false}">
													<a
														href="AddToCart?idProduct=${product.id}&user=${client.user.user}&page=Store"
														class="btn col-md-12 custom-btn bg-color mt-5"><i
														class="fa fa-cart-plus mr-2"></i>Agregar</a>
												</c:if>
											</c:if>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="tab-pane fade" id="men" role="tabpanel"
					aria-labelledby="men-tab">
					<br> <br>
					<div class="row">
						<c:forEach items="${arrayProducts}" var="product">
							<c:if test="${product.category == 0 && product.quantity > 0}">
								<div class="col-lg-3 col-md-3 col-12" data-aos="fade-up"
									data-aos-delay="400">
									<div class="class-thumb">
										<a href=""><img src="${product.photo}" class="img-fluid"
											alt="Class"></a>
										<div class="class-info">
											<h3 class="mb-1">${product.name}</h3>
											<span><strong>Categoría</strong> - Hombres</span>
											<c:if test="${(100 - (product.discount * 100) / product.price) == '0.00'}">
												<p>
													<strong><fmt:formatNumber value="${product.price}"
															type="currency" /></strong>
												</p>
											</c:if>
											<c:if test="${(100 - (product.discount * 100) / product.price) != '0.00'}">
												<p>
													<strong><fmt:formatNumber
															value="${product.discount}" type="currency" /></strong> <span
														style="text-decoration: line-through;"><fmt:formatNumber
															value="${product.price}" type="currency" /></span>
												</p>
											</c:if>
											<p>${product.description}</p>
											<c:if test="${isThereASession == false}">
												<a href="#" class="btn col-md-12 custom-btn bg-color mt-5"
													data-toggle="modal" data-target="#Login"><i
													class="fa fa-cart-plus mr-2"></i>Agregar</a>
											</c:if>
											<c:if test="${isThereASession == true}">
												<c:if test="${product.inCart == true}">
													<a
														href="RemoveFromCart?idProduct=${product.id}&user=${client.user.user}&page=Store"
														class="btn col-md-12 store-btn dark-color mt-5"><i
														class="fa fa-shopping-cart mr-2"></i>Quitar</a>
													<c:set var="done" value="true" />
												</c:if>
												<c:if test="${product.inCart == false}">
													<a
														href="AddToCart?idProduct=${product.id}&user=${client.user.user}&page=Store"
														class="btn col-md-12 custom-btn bg-color mt-5"><i
														class="fa fa-cart-plus mr-2"></i>Agregar</a>
												</c:if>
											</c:if>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="tab-pane fade" id="women" role="tabpanel"
					aria-labelledby="women-tab">
					<br> <br>
					<div class="row">
						<c:forEach items="${arrayProducts}" var="product">
							<c:if test="${product.category == 1 && product.quantity > 0}">
								<div class="col-lg-3 col-md-3 col-12" data-aos="fade-up"
									data-aos-delay="400">
									<div class="class-thumb">
										<a href=""><img src="${product.photo}" class="img-fluid"
											alt="Class"></a>
										<div class="class-info">
											<h3 class="mb-1">${product.name}</h3>
											<span><strong>Categoría</strong> - Mujeres</span>
											<c:if test="${(100 - (product.discount * 100) / product.price) == '0.00'}">
												<p>
													<strong><fmt:formatNumber value="${product.price}"
															type="currency" /></strong>
												</p>
											</c:if>
											<c:if test="${(100 - (product.discount * 100) / product.price) != '0.00'}">
												<p>
													<strong><fmt:formatNumber
															value="${product.discount}" type="currency" /></strong> <span
														style="text-decoration: line-through;"><fmt:formatNumber
															value="${product.price}" type="currency" /></span>
												</p>
											</c:if>
											<p>${product.description}</p>
											<c:if test="${isThereASession == false}">
												<a href="#" class="btn col-md-12 custom-btn bg-color mt-5"
													data-toggle="modal" data-target="#Login"><i
													class="fa fa-cart-plus mr-2"></i>Agregar</a>
											</c:if>
											<c:if test="${isThereASession == true}">
												<c:if test="${product.inCart == true}">
													<a
														href="RemoveFromCart?idProduct=${product.id}&user=${client.user.user}&page=Store"
														class="btn col-md-12 store-btn dark-color mt-5"><i
														class="fa fa-shopping-cart mr-2"></i>Quitar</a>
													<c:set var="done" value="true" />
												</c:if>
												<c:if test="${product.inCart == false}">
													<a
														href="AddToCart?idProduct=${product.id}&user=${client.user.user}&page=Store"
														class="btn col-md-12 custom-btn bg-color mt-5"><i
														class="fa fa-cart-plus mr-2"></i>Agregar</a>
												</c:if>
											</c:if>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="tab-pane fade" id="other" role="tabpanel"
					aria-labelledby="other-tab">
					<br> <br>
					<div class="row">
						<c:forEach items="${arrayProducts}" var="product">
							<c:if test="${product.category == 2 && product.quantity > 0}">
								<div class="col-lg-3 col-md-3 col-12" data-aos="fade-up"
									data-aos-delay="400">
									<div class="class-thumb">
										<a href=""><img src="${product.photo}" class="img-fluid"
											alt="Class"></a>
										<div class="class-info">
											<h3 class="mb-1">${product.name}</h3>
											<span><strong>Categoría</strong> - Otros</span>
											<c:if test="${(100 - (product.discount * 100) / product.price) == '0.00'}">
												<p>
													<strong><fmt:formatNumber value="${product.price}"
															type="currency" /></strong>
												</p>
											</c:if>
											<c:if test="${(100 - (product.discount * 100) / product.price) != '0.00'}">
												<p>
													<strong><fmt:formatNumber
															value="${product.discount}" type="currency" /></strong> <span
														style="text-decoration: line-through;"><fmt:formatNumber
															value="${product.price}" type="currency" /></span>
												</p>
											</c:if>
											<p>${product.description}</p>
											<c:if test="${isThereASession == false}">
												<a href="#" class="btn col-md-12 custom-btn bg-color mt-5"
													data-toggle="modal" data-target="#Login"><i
													class="fa fa-cart-plus mr-2"></i>Agregar</a>
											</c:if>
											<c:if test="${isThereASession == true}">
												<c:if test="${product.inCart == true}">
													<a
														href="RemoveFromCart?idProduct=${product.id}&user=${client.user.user}&page=Store"
														class="btn col-md-12 store-btn dark-color mt-5"><i
														class="fa fa-shopping-cart mr-2"></i>Quitar</a>
													<c:set var="done" value="true" />
												</c:if>
												<c:if test="${product.inCart == false}">
													<a
														href="AddToCart?idProduct=${product.id}&user=${client.user.user}&page=Store"
														class="btn col-md-12 custom-btn bg-color mt-5"><i
														class="fa fa-cart-plus mr-2"></i>Agregar</a>
												</c:if>
											</c:if>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>

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

	<!-- Modal: modalPay -->
	<div class="modal fade" id="modalPay" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered"
			role="document">
			<div class="modal-content">
				<!--Header-->
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Tu órden</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<!--Body-->
				<form action="FinishPurchase" method="post"
					enctype="multipart/form-data">
					<div class="modal-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>Producto</th>
									<th>Precio</th>
									<th>Precio con descuento</th>
									<th>Cantidad</th>
									<th>Total</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${sizeCart == 0}">
									<p>No tienes elementos en tu órden</p>
								</c:if>
								<c:if test="${sizeCart > 0}">
									<c:set var="numero" value="1"></c:set>
									<c:forEach items="${productsInOrder}" var="pio">
										<tr>
											<th scope="row">${numero}</th>
											<td>${pio.product.name}</td>
											<td><fmt:formatNumber value="${pio.product.price}"
													type="currency" /></td>
											<td><fmt:formatNumber value="${pio.product.discount}"
													type="currency" /></td>
											<td>${pio.quantity}</td>
											<c:if test="${(100 - (pio.product.discount * 100) / pio.product.price) == '0.00'}">
												<td>${pio.quantity * pio.product.price}</td>
											</c:if>
											<c:if test="${(100 - (pio.product.discount * 100) / pio.product.price) != '0.00'}">
												<td>${pio.quantity * pio.product.discount}</td>
											</c:if>

										</tr>
										<c:set var="numero" value="${numero + 1}"></c:set>
									</c:forEach>
								</c:if>
								<tr>
									<th></th>
									<th>Subtotal</th>
									<th></th>
									<th></th>
									<th></th>
									<th><fmt:formatNumber value="${subtotal}" type="currency" /></th>
								</tr>
								<tr>
									<td></td>
									<td>Envío</td>
									<td></td>
									<td></td>
									<td></td>
									<td><fmt:formatNumber value="${shipping}" type="currency" /></td>
								</tr>
								<tr>
									<th></th>
									<th>Total</th>
									<th></th>
									<th></th>
									<th></th>
									<th><fmt:formatNumber value="${subtotal + shipping}"
											type="currency" /></th>
								</tr>
							</tbody>
						</table>
						<p>Para realizar tu pago puedes hacerlo por las siguientes
							vías:</p>
						<ul>
							<li>Depósito en OXXO o transferencia bancaria a la siguiente
								cuenta: 4766 8405 2708 0191</li>
							<li>Pago vía paypal en el siguiente enlace: <a
								href="https://www.paypal.me/Tacoale" target="_blank">https://www.paypal.me/Tacoale</a></li>
						</ul>
						<p>Una vez hecho el pago del total mostrado arriba sube una
							imágen de tu comprobante de pago y se te realizará el envío a la
							dirección con la que te registraste.</p>
						<p>En caso de que exista un error con la transacción nos
							comunicaremos contigo al correo o numero que proporcionaste.</p>
						<div style="text-align: center;">
							<p>Debes subir imagenes en formato png, jpg, o jpeg</p>
							<input type="file" name="payPhoto" required="required"
								accept="image/png, image/jpeg, image/jpg" />
						</div>
					</div>
					<!--Footer-->
					<div class="modal-footer">
						<c:if test="${sizeCart == 0}">
							<button class="btn col-md-12 custom-btn bg-color mt-5"
								disabled="disabled">Terminar compra</button>
						</c:if>
						<c:if test="${sizeCart > 0}">
							<button type="submit"
								class="btn col-md-12 custom-btn bg-color mt-5">Terminar
								compra</button>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal: modalPay -->

	<!-- Modal: modalCart -->
	<div class="modal fade" id="modalCart" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<!--Header-->
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Tu carrito</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<!--Body-->
				<p class="error">${alertOk}</p>
				<form action="Pay" method="POST">
					<div class="modal-body">
						<p class="error">${quantityMessage}</p>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>Producto</th>
									<th>Precio</th>
									<th style="width: 10%;">Cantidad</th>
									<th>Quitar</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${sizeCart == 0}">
									<p>No tienes elementos en tu carrito</p>
								</c:if>
								<c:if test="${sizeCart > 0}">
									<c:set var="numero" value="1"></c:set>
									<c:forEach items="${cart}" var="productInCart">
										<tr>
											<th scope="row">${numero}</th>
											<td>${productInCart.product.name}</td>
											<c:if test="${(100 - (productInCart.product.discount * 100) / productInCart.product.price) == '0.00'}">
												<td><fmt:formatNumber
														value="${productInCart.product.price}" type="currency" /></td>
											</c:if>
											<c:if test="${(100 - (productInCart.product.discount * 100) / productInCart.product.price) != '0.00'}">
												<td><fmt:formatNumber
														value="${productInCart.product.discount}" type="currency" /></td>
											</c:if>
											<td><input style="width: 100%;" type="number"
												name="quantityOf-${productInCart.product.id}"
												value="${productInCart.quantity}"></td>
											<td><a
												href="RemoveFromCart?idProduct=${productInCart.product.id}&user=${client.user.user}&page=Store"><i
													class="fa fa-times"></i></a></td>
										</tr>
										<c:set var="numero" value="${numero + 1}"></c:set>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					<!--Footer-->
					<div class="modal-footer">
						<c:if test="${sizeCart == 0}">
							<button class="btn col-md-12 custom-btn bg-color mt-5"
								disabled="disabled">Pagar</button>
						</c:if>
						<c:if test="${sizeCart > 0}">
							<button type="submit"
								class="btn col-md-12 custom-btn bg-color mt-5">Pagar</button>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal: modalCart -->

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
								<input type="text" class="form-control" name="phoneTxt"
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