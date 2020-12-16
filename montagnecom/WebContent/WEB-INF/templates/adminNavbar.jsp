<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<nav class="navbar navbar-dark bg-dark">
	<a class="navbar-brand">Admin console</a>
	<div class="navbar-nav flex-grow-1 pl-5">
		<a class="nav-link" href="/montagnecom/admin">Home</a>
	</div>
	<form class="form-inline" method="post" action="">
		<input type="hidden" name="form" value="disconnect" /> <input
			class="form-control mr-sm-2" type="submit" value="Déconnexion"
			aria-label="Déconnexion" />
	</form>
</nav>