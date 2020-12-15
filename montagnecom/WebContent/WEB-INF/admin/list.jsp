<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin console</title>
<jsp:include page="/WEB-INF/templates/bootstrap.jsp" />
</head>
<body>
	<h1 class="text-center m-5">Admin console</h1>
	<div class="container-flud">
		<div class="row my-3">
			<div class="col-6 offset-3">
				<form class="border border-primary rounded p-1" method="post" action="">
					<h2 class="text-center">Ajouter une montagne</h2>
					<input type="hidden" name="form" value="ajout" />
					<div class="form-row m-2">
						<label for="mnom" class="col-1 col-form-label col-form-label-sm">Nom
							: </label>
						<div class="col-2">
							<input class="form-control form-control-sm" id="mnom" type="text"
								name="nom" />
						</div>

						<label for="mprix"
							class="col-1 offset-1 col-form-label col-form-label-sm">Prix
							: </label>
						<div class="col-2">
							<input class="form-control form-control-sm" id="mprix"
								type="text" name="prix" />
						</div>

						<label for="maltitude"
							class="col-1 offset-1 col-form-label col-form-label-sm">Altitude
							: </label>
						<div class="col-2">
							<input class="form-control form-control-sm" id="maltitude"
								type="text" name="altitude" />
						</div>

					</div>
					<div class="form-row m-2">
						<label for="mchaine"
							class="col-1 col-form-label col-form-label-sm">Chaine : </label>
						<div class="col-2">
							<input class="form-control form-control-sm" id="mchaine"
								type="text" name="chaine" />
						</div>
						<label for="mtype"
							class="col-1 offset-1 col-form-label col-form-label-sm">Type
							: </label>
						<div class="col-2">
							<input class="form-control form-control-sm" id="mtype"
								type="text" name="type" />
						</div>
						<label for="mimage"
							class="col-1 offset-1 col-form-label col-form-label-sm">Image
							: </label>
						<div class="col-2">
							<input class="form-control form-control-sm" id="mimage"
								type="text" name="image" />
						</div>
					</div>
					<div class="form-row m-2">
						<label for="mdescription"
							class="col-auto col-form-label col-form-label-sm">Description
							: </label>
						<div class="col-auto">
							<textarea class="form-control form-control-sm" id="mdescription"
								name="description" rows="2" cols="70"></textarea>
						</div>
						<div class="col-auto offset-1">
							<input class="btn btn-primary mt-2" type="submit" value="ajouter" />
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<table class="table">
					<thead>
						<tr class="text-center">
							<th>Nom</th>
							<th>Prix</th>
							<th>Altitude</th>
							<th>Description</th>
							<th>Image</th>
							<th>Chaine</th>
							<th>Type</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="m" items="${listMontagne}">
							<tr class="text-center">
								<td>${m.nom}</td>
								<td>${m.prix}</td>
								<td>${m.altitude}</td>
								<td>${m.description}</td>
								<td>${m.image}</td>
								<td>${m.chaine.nom}</td>
								<td>${m.type.nom}</td>
								<td><c:url var="detailurl" value="">
										<c:param name="page" value="detail" />
										<c:param name="nommontagne" value="${m.nom}" />
									</c:url> <a class="btn btn-success btn-sm" href="${detailurl}">Detail</a>
								</td>
								<td>
									<form method="post" action="">
										<input type="hidden" name="form" value="suppr" />
										<input type="hidden" name="nom" value="${m.nom}" />
										<input type="submit" class="btn-danger btn-sm btn" onclick="return confirm('Confirmer la suppression de ${m.nom}');" value="supprimer" />
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>