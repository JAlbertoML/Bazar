<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">
<c:set var="client" value="${requestScope['client']}"></c:set>
<c:set var="openmodal" value="${requestScope['openmodal']}"></c:set>
<c:set var="message" value="${requestScope['message']}"></c:set>
<c:set var="editMessage" value="${requestScope['editMessage']}"></c:set>
<c:set var="arrayProducts" value="${requestScope['arrayProducts']}"></c:set>
<c:set var="editableProduct" value="${requestScope['editableProduct']}"></c:set>
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
							<a class="dropdown-item" href="AdminProducts"><i
								class="fa fa-shopping-cart mr-3"></i>Admin. Productos</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item" data-toggle="modal"
								data-target="#logout"><i class="fa fa-sign-out mr-3"></i>Cerrar
								sesión</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- CONTACT -->
	<section class="section" id="profile">
		<div class="container">
			<p class="error">${editMessage}</p>
			<div class="row">
				<a href="#" data-toggle="modal" data-target="#AddProduct"
					class="btn btn-primary col-md-12">Agregar un nuevo
					producto</a>
			</div>
			<br>
			<c:forEach items="${arrayProducts}" var="product">
				<div class="card mb-3" style="max-width: 100%;">
					<div class="row no-gutters">
						<div class="col-md-2">
							<img src="${product.photo}" class="card-img" alt="imagen">
						</div>
						<div class="col-md-10">
							<div class="card-body">
								<a href="EditProduct?idProduct=${product.id}"
									class="badge badge-success"><i class="fa fa-edit mr-3"></i>
									Editar producto</a> <a href="DeleteProduct?idProduct=${product.id}"
									class="badge badge-danger"><i class="fa fa-trash mr-3"></i>
									Eliminar producto</a>
								<h5 class="card-title">${product.name} (Cantidad: ${product.quantity})</h5>
								<p class="card-text">${product.description}</p>
								<c:if test="${(100 - (product.discount * 100) / product.price) == '0.00'}">
									<p class="card-text">
										<small class="text-muted"><strong><fmt:formatNumber
													value="${product.price}" type="currency" /></strong></small>
									</p>
								</c:if>
								<c:if test="${(100 - (product.discount * 100) / product.price) != '0.00'}">
									<p class="card-text">
										<small class="text-muted"><strong><fmt:formatNumber
													value="${product.discount}" type="currency" /></strong> <span
											style="text-decoration: line-through;"><fmt:formatNumber
													value="${product.price}" type="currency" /></span></small>
									</p>
								</c:if>
								<c:if test="${product.category == 0}">
									<span class="badge badge-pill badge-warning">Hombres</span>
								</c:if>
								<c:if test="${product.category == 1}">
									<span class="badge badge-pill badge-warning">Mujeres</span>
								</c:if>
								<c:if test="${product.category == 2}">
									<span class="badge badge-pill badge-warning">Otros</span>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>

	<!-- Edit product Modal -->
	<div class="modal fade" id="EditProduct" tabindex="-1" role="dialog"
		aria-labelledby="EditProduct" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="RegisterLabel">Editar Producto</h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p class="error">${message}</p>
					<p>
						Si el producto <b>NO</b> tiene descuento, deja ese campo el
						blanco.
					</p>
					<form class="membership-form webform text-center" role="form"
						method="POST" action="EditProduct" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-8">
								<div class="row">
									<div class="col-md-6">
										<input type="text" class="form-control" name="nameTxt"
											placeholder="Nombre(s)" value="${editableProduct.name}"
											required>
									</div>
									<div class="col-md-3">
										<input type="number" class="form-control" name="priceTxt"
											placeholder="Precio" value="${editableProduct.price}"
											required>
									</div>
									<div class="col-md-3">
										<c:if test="${editableProduct.discount == '0.00'}">
											<input type="text" class="form-control" name="discountTxt"
												placeholder="Descuento (%)">
										</c:if>
										<c:if test="${editableProduct.discount != '0.00'}">
											<input type="text" class="form-control" name="discountTxt"
												placeholder="Descuento" value="${(100 - (editableProduct.discount * 100) / editableProduct.price)}">
										</c:if>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<input type="number" class="form-control" name="quantityTxt"
											placeholder="Cantidad existente" required="required"
											value="${editableProduct.quantity}">
									</div>
									<div class="col-md-8">
										<select class="form-control" name="categoryTxt" required>
											<option selected="selected" disabled="disabled">Sin
												seleccionar</option>
											<c:if test="${editableProduct.category == 0}">
												<option value="0" selected="selected">Hombres</option>
												<option value="1">Mujeres</option>
												<option value="2">Otros</option>
											</c:if>
											<c:if test="${editableProduct.category == 1}">
												<option value="0">Hombres</option>
												<option value="1" selected="selected">Mujeres</option>
												<option value="2">Otros</option>
											</c:if>
											<c:if test="${editableProduct.category == 2}">
												<option value="0">Hombres</option>
												<option value="1">Mujeres</option>
												<option value="2" selected="selected">Otros</option>
											</c:if>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<textarea name="descriptionTxt" class="form-control" cols="20"
									rows="6" placeholder="Descripción" required="required">${editableProduct.description}</textarea>
							</div>
						</div>
						<div class="row justify-content-center">
							<label class="form-control" for="productImage">Agrega una
								imágen del producto</label> <input type="file" id="productImage"
								name="photoInput" accept="image/png, image/jpeg, image/jpg" />
						</div>
						<input type="hidden" value="${editableProduct.id}"
							name="idProduct">
						<button type="submit" class="form-control" id="submit-button">Editar
							producto</button>
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

	<!-- Add product Modal -->
	<div class="modal fade" id="AddProduct" tabindex="-1" role="dialog"
		aria-labelledby="RegisterLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="RegisterLabel">Agregar Producto</h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p class="error">${message}</p>
					<p>
						Si el producto <b>NO</b> tiene descuento, deja ese campo el
						blanco.
					</p>
					<form class="membership-form webform text-center" role="form"
						method="POST" action="AddProduct" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-8">
								<div class="row">
									<div class="col-md-6">
										<input type="text" class="form-control" name="nameTxt"
											placeholder="Nombre(s)" required>
									</div>
									<div class="col-md-3">
										<input type="number" class="form-control" name="priceTxt"
											placeholder="Precio" required>
									</div>
									<div class="col-md-3">
										<input type="number" class="form-control" name="discountTxt"
											placeholder="Descuento (%)">
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<input type="number" class="form-control" name="quantityTxt"
											placeholder="Cantidad existente" required="required">
									</div>
									<div class="col-md-8">
										<select class="form-control" name="categoryTxt" required>
											<option selected="selected" disabled="disabled">Sin
												seleccionar</option>
											<option value="0">Hombres</option>
											<option value="1">Mujeres</option>
											<option value="2">Otros</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<textarea name="descriptionTxt" class="form-control" cols="20"
									rows="6" placeholder="Descripción" required="required"></textarea>
							</div>
						</div>
						<div class="row justify-content-center">
							<label class="form-control" for="productImage">Agrega una
								imágen del producto</label> <input type="file" id="productImage"
								name="photoInput" required="required"
								accept="image/png, image/jpeg, image/jpg" />
						</div>
						<button type="submit" class="form-control" id="submit-button">Agregar
							producto</button>
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