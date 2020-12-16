<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Montagne.com</title>
<jsp:include page="/WEB-INF/templates/bootstrap.jsp" />
</head>
<body>
	<h1 class="text-center">Detail</h1>
	<div class="container">
		<div class="row">
			<div class="col">
				<img src="image?nom=${fn:escapeXml(m.image)}"
					class="img-thumbnail float-right" width="400px"
					alt="${fn:escapeXml(m.nom)}">
				<h2 class="card-title h5">${fn:escapeXml(m.nom)}</h2>
				<ul>
					<li>Altitude :${fn:escapeXml(m.altitude)}m</li>
					<li>Chaine:	${fn:escapeXml(m.chaine.nom)}</li>
					<li>Type: ${fn:escapeXml(m.type.nom)}</li>
					<li>Prix: ${fn:escapeXml(m.prix)}€</li>
				</ul>
				<p>${fn:escapeXml(m.description)}</p>
				<a class="btn btn-primary mr-4" href="/montagnecom">retour à l'accueil</a><a class="btn btn-success" href="#">Ajouter au panier</a>
			</div>
		</div>
	</div>
</body>
</html>