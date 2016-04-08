<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="parcours_plus.beans.Utilisateur"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Reponsable parcours</title>


<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/style.css" rel="stylesheet">
<title>Liste des parcours</title>
</head>
<header> <IMG src="Images/isep2.png" WIDTH='100%' HEIGHT='20%'>
</header>


<body>

	<div class="row">


		<div class="navbar navbar-tabs">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Parcours +</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="consulter_parcours.html">Consulter
							parcours</a></li>
					<li><a href="resp_parcours.html">Responsable des parcours</a></li>
					<li>&nbsp;</li>
				</ul>
				<form class="navbar-form">

					<div class="form-group" style="display: inline;">
						<div class="input-group">

							<input type="text" class="form-control"
								placeholder="What are searching for?"> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-search"></span> </span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>

	<div class="tab-content">
		<div id="home" class="tab-pane fade in active">

			<h3 class="titre_parcours">Responsabe des parcours</h3>


		</div>
	</div>
	</br>
	</br>
	</br>
	</br>




	<div class="row">
		<div class="col-sm-4">
			<FORM>
				<SELECT name="nom" size="1">
					<OPTION>Mme Tallon
					<OPTION>Me exemple
					<OPTION selected>A ajouter avec java
					<OPTION>Kevin la pute
					<OPTION>Kevin la tchoin
				</SELECT>
			</FORM>
		</div>
		<div class="col-sm-4">
			<h3>Mr Tallon</h3>
			<h3>Nom du parcours</h3>
		</div>



		<div class="col-sm-4">

			<form action="Connexion" name="login" role="form"
				class="form-horizontal" method="post" accept-charset="utf-8">
				<label>Identifiant</label>
				<div class="form-group">

					<div class="col-md-8">
						<input name="login" placeholder="Idenfiant" class="form-control"
							type="text" id="UserUsername" />
					</div>
				</div>
				<label>Mot de passe</label>
				<div class="form-group">
					<div class="col-md-8">
						<input name="password" placeholder="Mot de passe"
							class="form-control" type="password" id="UserPassword" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-offset-0 col-md-8">
						<input class="btn btn-success btn btn-success" type="submit"
							value="Connexion" />
					</div>
				</div>

			</form>
		</div>

		<p>
			<c:if test="${!empty sessionScope.session_utilisateur}">
				<p class="succes">Vous êtes connecté(e) avec le login :
					${sessionScope.session_utilisateur.login} et le mdp
					${sessionScope.session_utilisateur.mdp}</p>
			</c:if>

		</p>
</body>

</html>

