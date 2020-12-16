<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
				<form class="border border-primary rounded p-1" method="post"
					action="" enctype="multipart/form-data">
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
						<div class="col-3">

							<div class="input-group mb-3">
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="mimage" name="image">
									<label class="custom-file-label" for="mimage">...</label>
								</div>
							</div>

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
								<td>${fn:escapeXml(m.nom)}</td>
								<td>${fn:escapeXml(m.prix)}</td>
								<td>${fn:escapeXml(m.altitude)}</td>
								<td>${fn:substring(fn:escapeXml(m.description), 0, 80)}</td>
								<td>${fn:substring(fn:escapeXml(m.image), 0, 25)}</td>
								<td>${fn:escapeXml(m.chaine.nom)}</td>
								<td>${fn:escapeXml(m.type.nom)}</td>
								<td><c:url var="detailurl" value="">
										<c:param name="page" value="detail" />
										<c:param name="nommontagne" value="${fn:escapeXml(m.nom)}" />
									</c:url> <a class="btn btn-success btn-sm" href="${fn:escapeXml(detailurl)}">Detail</a>
								</td>
								<td>
									<form method="post" action="">
										<input type="hidden" name="form" value="suppr" /> <input
											type="hidden" name="nom" value="${fn:escapeXml(m.nom)}" /> <input
											type="submit" class="btn-danger btn-sm btn"
											onclick="return confirm('Confirmer la suppression de ${fn:escapeXml(m.nom)}');"
											value="supprimer" />
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>
	<script>
		$('#mimage').on('change', function() {
			var fileInput = document.getElementById('mimage');
			console.log(fileInput.files);
			if(fileInput.files[0]) {
				var filename = fileInput.files[0].name;
				//replace the "Choose a file" label
				$(this).next('.custom-file-label').text(filename);
			}
		})
	</script>

</body>
</html>