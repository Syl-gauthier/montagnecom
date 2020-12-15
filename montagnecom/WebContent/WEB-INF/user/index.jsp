<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Montagne.com</title>
<jsp:include page="/WEB-INF/templates/bootstrap.jsp" />
</head>
<body>
	<h1 class="text-center">MONTAGNE.COM</h1>
	<h2 class="text-center">Achetez une montagne ou un pic (ou un cap
		ou un roc)</h2>
	<div class="container">
		<form method="get" action="#">
			<div class="row">
				<div class="col">
					<div class="input-group mb-2">
						<div class="input-group-prepend">
							<div class="input-group-text">Rechercher</div>
						</div>
						<input type="text" class="form-control" id="inlineFormInputGroup"
							placeholder="...">
					</div>
				</div>
				<div class="mb-3 col">
					<select class="custom-select">
						<option value="" disabled selected>Type</option>
						<c:forEach var="t" items="${listType}">
							<option value="$t.nom">${t.nom}</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-3 col">
					<select class="custom-select">
						<option value="" disabled selected>Chaine de montagne</option>
						<c:forEach var="c" items="${listChaine}">
							<option value="$t.nom">${c.nom}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
					<input type="submit" class="btn btn-primary" value="rechercher" />
				</div>

			</div>
		</form>
		<div class="row">
			<c:forEach var="m" items="${listMontagne}">
				<div class="col">
					<div class="card" style="width: 18rem;">
						<img src="image?nom=${m.image}" class="card-img-top" alt="${m.nom}">
						<div class="card-body">
							<p class="card-title h5">${m.nom}
								<span class="card-subtitle text-muted"> ${m.altitude}m</span>
							</p>
							<h6 class="card-subtitle mb-2 text-muted">
								<span class="mx-1">Chaine: ${m.getChaine().getNom()}</span> <span
									class="mx-1"> Type: ${m.getType().getNom()}</span>
							</h6>
							<div>Prix: ${m.getPrix()}€</div>
							<p class="card-text">${fn:substring(m.description, 0, 200)}</p>
							<a class="btn btn-success" href="#?type=detail&nom=${m.nom}">Détail</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>


</body>
</html>