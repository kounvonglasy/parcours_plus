<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.Utilisateur"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="css/parcours_manager.css" rel="stylesheet">
<script src="js/jquery-1.12.3.js"></script>
<title>Liste des parcours</title>
</head>
<header> <!-- image de couverture --> <IMG
	src="Images/isep2.png" WIDTH='100%' HEIGHT='20%'> </header>
<body>
	<div class="row">
		<div class="col-sm-12">
			<div class="navbar navbar-tabs">
				<!-- Marque : Parcours + -->
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Parcours +</a>
				</div>
				<div class="navbar-collapse collapse">
					<!-- Liste des choix -->
					<ul class="nav navbar-nav">
						<li class="active"><a href="consulter_parcours.html">Consulter
								parcours</a></li>
						<li><a href="resp_parcours.jsp">Responsable des parcours</a></li>
						<c:if test="${!empty sessionScope.session_utilisateur}">
							<li><a href="editer_profil.html">Editer profils</a></li>
						</c:if>
					</ul>

					<!-- Barre de recherche -->
					<form class="navbar-form" method="POST" action="RechercherParcours">
						<div class="form-group" style="display: inline;">
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="What are searching for?"> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-search"></span> </span>
							</div>

						</div>

					</form>

					<c:if test="${!empty sessionScope.session_utilisateur}">
						<p class="succes">Vous êtes connecté(e) avec le login :
							${sessionScope.session_utilisateur.login}</p>
					</c:if>
				</div>
			</div>
			<c:if test="${!empty sessionScope.session_utilisateur}">
				<div class="col-sm-12">
					<form action="Deconnexion" name="login" role="form"
						class="form-horizontal" method="post" accept-charset="utf-8">
						<input class="btn btn-success btn btn-success" type="submit"
							value="Deconnection" />
					</form>
				</div>
			</c:if>

		</div>
	</div>

	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div id="liste-validation_parcours">
					<form id="validationParcours" action="ValidationParcours"
						method="POST">
						<h3 onclick='afficher_description_icones();'>Liste des
							choix de parcours</h3>
						<br>
						<table
							class="table table-bordered table-condensed table-striped table-hover ">
							<thead>
								<tr>
									<th><input type="text" class="form-control rounded"
										id="parcoursFilter" name="parcoursFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="choixFilter" name="choixFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="etudiantFilter" name="etudiantFilter" value="%"></th>
								</tr>
								<tr>
									<th>Nom du Parcours</th>
									<th>Choix de parcours de l'étudiant</th>
									<th>Nom de l'étudiant</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Systeme embarqué</td>
									<td>0</td>
									<td>Raj</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="12"><div id="validerParcours">
											<a href="validerParcours"> <i
												class="glyphicon glyphicon-save black"></i>&nbsp;<b>Valider
											</b>
											</a>
										</div></td>
								</tr>
							</tfoot>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
