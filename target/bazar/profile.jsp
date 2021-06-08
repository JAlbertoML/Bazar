<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">
<c:set var="client" value="${requestScope['client']}"></c:set>
<c:set var="openmodal" value="${requestScope['openmodal']}"></c:set>
<c:set var="message" value="${requestScope['message']}"></c:set>
<c:set var="disableRemoveButton"
	value="${requestScope['disableRemoveButton']}"></c:set>
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
	onclick="${openmodal}">

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
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
						role="button" aria-haspopup="true" aria-expanded="false">PERFIL</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#profile"><i
								class="fa fa-user mr-3"></i>Perfil</a> <a class="dropdown-item"
										href="Cart?user=${client.user.user}&page=Store"><i
										class="fa fa-shopping-cart mr-3"></i>Carrito</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#" data-toggle="modal"
								data-target="#logout"><i class="fa fa-sign-out mr-3"></i>Cerrar
								sesión</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- PROFILE -->
	<section class="section" id="profile">
		<div class="container">
			<div class="row">
				<div class="container-fluid">
					<div class="row my-2">
						<div class="col-lg-12 order-lg-2">
							<div class="tab-content py-4">
								<div class="tab-pane active" id="profile">
									<div class="col-lg-12 order-lg-1 text-center">
										<div class="row d-flex align-items-center">
											<div class="col-md-4"></div>
											<div class="col-md-2">
												<img src="${client.photo}"
													class="mx-auto img-fluid img-circle d-block" width="150px"
													style="border-radius: 50%" alt="avatar">
											</div>
											<div class="col-md-2 mt-3">
												<a class="btn col-md-12 custom-btn bg-color"
													style="color: white;" data-toggle="modal"
													data-target="#changePhoto">Cambiar foto</a> <a
													href="RemoveProfilePhoto?username=${client.user.user}"
													class="btn btn-secondary btn-icon-split col-md-12 mt-3 ${disableRemoveButton} "><span
													class="text">Eliminar foto</span> </a>
											</div>
											<div class="col-md-4"></div>
										</div>
									</div>
									<br>
									<h5 class="mb-3 text-center">
										<b>${client.user.user}</b>
									</h5>
									<div class="row">
										<div class="col-lg-6">
											<div class="card shadow mb-4">
												<div class="card-header py-3">
													<h6 class="m-0 font-weight-bold text-primary">Datos
														personales</h6>
												</div>
												<div class="card-body">
													<p>
														<b>Nombre: </b>${client.name} ${client.surname}
														${client.lastname}
													</p>
													<p>
														<b>Fecha de nacimiento: </b>${client.birthday}</p>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="card shadow mb-4">
												<div class="card-header py-3">
													<h6 class="m-0 font-weight-bold text-primary">Contacto</h6>
												</div>
												<div class="card-body">
													<p>
														<b>Correo electrónico: </b>${client.contact.email}</p>
													<p>
														<b>Teléfono: </b>${client.contact.phone }</p>
												</div>
											</div>
										</div>
										<div class="col-lg-12">
											<div class="card shadow mb-4">
												<div class="card-header py-3">
													<h6 class="m-0 font-weight-bold text-primary">Dirección</h6>
												</div>
												<div class="card-body">
													<div class="row">
														<div class="col-lg-4">
															<p>
																<b>Calle: </b>${client.direction.street}</p>
															<p>
																<b>Número: </b>${client.direction.number}</p>
														</div>
														<div class="col-lg-4">
															<p>
																<b>Colonia o poblado: </b>${client.direction.suburb}</p>
															<p>
																<b>Alcaldí­a o municipio: </b>${client.direction.townhall}</p>
															<p>
																<b>Código postal: </b>${client.direction.postalCode}</p>
														</div>
														<div class="col-lg-4">
															<p>
																<b>Estado: </b>${client.direction.state}</p>
															<p>
																<b>País: </b>${client.direction.country}</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<a class="btn col-md-12 custom-btn bg-color" href="#"
									data-toggle="modal" data-target="#EditProfile">Editar
									perfil</a>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->
		</div>
	</section>

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
				<div class="modal-body">
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
								<c:forEach items="${cart}" var="productInCart">
									<tr>
										<th scope="row">1</th>
										<td>${productInCart.product.name}</td>
										<c:if test="${productInCart.product.discount == '0.00'}">
											<td><fmt:formatNumber
													value="${productInCart.product.price}" type="currency" /></td>
										</c:if>
										<c:if test="${productInCart.product.discount != '0.00'}">
											<td><fmt:formatNumber
													value="${productInCart.product.discount}" type="currency" /></td>
										</c:if>
										<td><input style="width: 100%;" type="number"
											value="${productInCart.quantity}"></td>
										<td><a
											href="RemoveFromCart?idProduct=${productInCart.product.id}&user=${client.user.user}&page=Profile"><i
												class="fa fa-times"></i></a></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>

				</div>
				<!--Footer-->
				<div class="modal-footer">
					<c:if test="${sizeCart == 0}">
						<button class="btn col-md-12 custom-btn bg-color mt-5"
							disabled="disabled">Checkout</button>
					</c:if>
					<c:if test="${sizeCart > 0}">
						<button class="btn col-md-12 custom-btn bg-color mt-5">Checkout</button>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal: modalCart -->

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

	<!-- Change Photo modal -->
	<div class="modal fade" id="changePhoto" tabindex="-1" role="dialog"
		aria-labelledby="changePhotoLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form action="ChangeProfilePhoto" method="post"
					enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">Cambiar
							foto de perfil</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>Debes subir imagenes en formato png, jpg, o jpeg</p>
						<input type="file" name="photoInput"
							accept="image/png, image/jpeg, image/jpg" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancelar</button>
						<input type="submit" class="btn btn-primary" value="Guardar foto">
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Edit Profile Modal -->
	<div class="modal fade" id="EditProfile" tabindex="-1" role="dialog"
		aria-labelledby="RegisterLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="RegisterLabel">Editar perfil</h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p class="error">${message}</p>
					<p>
						Si <b>NO</b> deseas cambiar tu nombre de usuario y/o contraseña
						deja esos campos en blanco
					</p>
					<form class="membership-form webform text-center" role="form"
						method="POST" action="EditProfile">
						<div class="row">
							<div class="col-md-4">
								<input type="text" class="form-control" name="nameTxt"
									placeholder="Nombre(s)" value="${client.name}" required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control" name="surnameTxt"
									placeholder="Primer Apellido" value="${client.surname}"
									required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control" name="lastnameTxt"
									placeholder="Segundo Apellido" value="${client.lastname}"
									required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<input type="text" class="form-control" name="usernameTxt"
									placeholder="Nombre de usuario">
							</div>
							<div class="col-md-4">
								<input type="password" class="form-control" name="passwordTxt"
									placeholder="Contraseña">
							</div>
							<div class="col-md-4">
								<input type="password" class="form-control"
									name="repeatPasswordTxt"
									placeholder="Confirmación de contraseña">
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<input type="date" class="form-control" name="birthdayDate"
									placeholder="Fecha de nacimiento" value="${client.birthday}"
									required>
							</div>
							<div class="col-md-4">
								<input type="email" class="form-control" name="emailTxt"
									placeholder="Correo electrónico"
									value="${client.contact.email}" required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control" name="phoneTxt"
									placeholder="Numero de teléfono"
									value="${client.contact.phone}" pattern="[0-9]{10}" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<input type="text" class="form-control" name="streetTxt"
									placeholder="Calle" value="${client.direction.street}" required>
							</div>
							<div class="col-md-2">
								<input type="number" class="form-control" name="numberTxt"
									placeholder="Número" value="${client.direction.number}"
									required>
							</div>
							<div class="col-md-2">
								<input type="text" class="form-control" name="postalCodeTxt"
									placeholder="Código Postal"
									value="${client.direction.postalCode}" required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control" name="suburbTxt"
									placeholder="Colonia o población"
									value="${client.direction.suburb}" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<input type="text" class="form-control" name="townhallTxt"
									placeholder="Delegación o municipio"
									value="${client.direction.townhall}" required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control"
									value="${client.direction.state}" name="stateTxt"
									placeholder="Estado" required>
							</div>
							<div class="col-md-4">
								<input type="text" class="form-control" name="countryTxt"
									placeholder="País" value="México" readonly="readonly">
							</div>
						</div>
						<button type="submit" class="form-control" id="submit-button">Terminar
							edición del perfil</button>
						<label class="text-small text-muted" for="signup-agree"><a
							href="#" data-toggle="modal" data-target="#DeleteAccount">Eliminar
								cuenta</a> </label>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<!-- Delete account Modal -->
	<div class="modal fade" id="DeleteAccount" tabindex="-1" role="dialog"
		aria-labelledby="logoutLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="logoutLabel">Eliminar cuenta</h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>¿Estás seguro que quieres eliminar tu cuenta?</p>
					<a href="DeleteAccount" class="btn col-md-12 custom-btn bg-color">Eliminar
						cuenta</a>
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