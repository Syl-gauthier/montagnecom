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
	<jsp:include page="/WEB-INF/templates/adminNavbar.jsp" />
	<div class="container mt-5">
		<form method="post" action="/montagnecom/admin">
			<input type="hidden" name="form" value="update" />
			<div class="form-row">

				<div class="form-group col-2 offset-1">
					<label for="mnom">Nom : </label> <input class="form-control"
						id="mnom" type="text" name="nom" value="${fn:escapeXml(m.nom)}" readonly />
				</div>
				<div class="form-group col-2 offset-2">
					<label for="mprix">Prix : </label> <input class="form-control"
						id="mprix" type="text" name="prix" value="${fn:escapeXml(m.prix)}" />
				</div>
				<div class="form-group  col-2 offset-2">
					<label for="maltitude">Altitude : </label> <input
						class="form-control" id="maltitude" type="text" name="altitude"
						value="${fn:escapeXml(m.altitude)}" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group  col-2 offset-1">
					<label for="mchaine">Chaine : </label> <input class="form-control"
						id="mchaine" type="text" name="chaine" value="${fn:escapeXml(m.chaine.nom)}" />
				</div>
				<div class="form-group col-2 offset-2">
					<label for="mtype">Type : </label> <input class="form-control"
						id="mtype" type="text" name="type" value="${fn:escapeXml(m.type.nom)}" />
				</div>
				<div class="form-group  col-2 offset-2">
					<label for="mimage">Image : </label> <input class="form-control"
						id="mimage" type="text" name="image" value="${fn:escapeXml(m.image)}" readonly />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-10 offset-1">
					<label for="mdescription">Description : </label>
					<textarea class="form-control" id="mdescription" name="description">${fn:escapeXml(m.description)}</textarea>
				</div>
			</div>
			<div class="form-col text-center">
				<input class="btn btn-primary" type="submit" value="update" />
			</div>
		</form>
	</div>
</body>
</html>