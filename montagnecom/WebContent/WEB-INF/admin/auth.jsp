<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin login</title>
<jsp:include page="/WEB-INF/templates/bootstrap.jsp" />
</head>
<body>
	<h1 class="text-center m-4">Authentification</h1>
	<div class="container">
		<div class="row">
			<div class="col-4 offset-4">
				<form method="post" action="#">
					<input type="hidden" name="form" value="auth" />
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<label for="username" class="input-group-text">Login</label>
						</div>
						<input class="form-control" type="text" id="username"
							name="username" />
					</div>
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<label for="password" class="input-group-text">Mot de
								passe</label>
						</div>
						<input class="form-control" type="text" id="password"
							name="password" />
					</div>
					<div class=" text-center">
						<input class="btn btn-primary" type="submit" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>