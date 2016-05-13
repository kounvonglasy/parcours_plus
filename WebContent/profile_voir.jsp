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
<script>
	function afficher_description_icones() {
		$("#description_icones").toggle("blind");
	}
</script>
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
				<div id="liste-parcours">
					<span class="erreur">${form.erreurs['nom_responsable']}</span>
					<form id="listeParcours" action="RechercherParcours" method="POST">
						<h3 onclick='afficher_description_icones();'>Liste des
							parcours</h3>
						<span class="erreur">${form.erreurs['suppression_parcours']}</span>
						<div id="description_icones">
							<i class="glyphicon glyphicon-edit black"></i> Editer le parcours
							<br> <i class="glyphicon glyphicon-pencil black"></i>Editer
							les modules du parcours <br> <i
								class="glyphicon glyphicon-remove black"></i>Supprimer le
							parcours<br>
						</div>
						<br>
						<p class="hide">
							<button type="submit"></button>
						</p>
						<table
							class="table table-bordered table-condensed table-striped table-hover ">
							<thead>
								<tr>
									<th></th>
									<th><input type="text" class="form-control rounded"
										id="libelleFilter" name="libelleFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="responsableFilter" name="responsableFilter" value="%"></th>
								</tr>
								<tr>
									<th></th>
									<th>Libelle</th>
									<th>Responsable</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${liste_parcours}" var="parcours">
									<tr>
										<td><a title="Editer parcours"
											href="EditerParcours?id=<c:out value="${parcours[0]}" />&nom_responsable=<c:out value="${parcours[2]}" />"><i
												class="glyphicon glyphicon-pencil black"></i></a> <a
											title="Editer les modules"
											href="AfficherModule?id_parcours=<c:out value="${parcours[0]}" />&libelle_parcours=<c:out value="${parcours[1]}" />"><i
												class="glyphicon glyphicon-edit black"></i></a> <a
											title="Supprimer le parcours"
											href="SupprimerParcours?id=<c:out value="${parcours[0]}" />"><i
												class="glyphicon glyphicon-remove black"></i></a></td>
										<td><c:out value="${parcours[1]}" /></td>
										<td><c:out value="${parcours[2]}" /></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td>&nbsp;</td>
									<td colspan="12"><div id="creerParcours">
											<a href="CreerParcours"> <i
												class="glyphicon glyphicon-plus black"></i>&nbsp;<b>Creer
													un parcours</b>
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