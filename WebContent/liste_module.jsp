<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.Module"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="css/liste_module.css" rel="stylesheet">
<link href="css/module_manager.css" rel="stylesheet">
<script src="js/jquery-1.12.3.js"></script>
<title>Liste des modules</title>
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
				<div id="liste_module">
					<form id="listeModule" action="RechercherModule" method="POST">
						<h3 onclick='afficher_description_icones();'>Liste des
							modules</h3>
					    <span class="erreur">${form.erreurs['suppression_module']}</span>	
						<div id="description_icones">
							<i class="glyphicon glyphicon-edit black"></i> Editer le module
							<br> <i
								class="glyphicon glyphicon-remove black"></i>Supprimer le
							module<br>
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
										name="srch-term" id="srch-term" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="aLaCarteFilter" name="aLaCarteFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="responsableFilter" name="responsableFilter" value="%"></th>
								</tr>
								<tr>
								<th></th>
									<th>Libelle du module</th>
									<th>Nom de parcours</th>
									<th> A la carte? </th>
									<th> Nom du responsable </th>
								</tr>
							</thead>
							<tbody>

								
				
								<c:forEach items="${liste_module}" var="module">
								<tr>
									<td><a title="Editer module" href="EditerModule?id=<c:out value="${module[0]}" />&id_parcours=${id_parcours}&libelle_parcours=${libelle_parcours}"><i
											class="glyphicon glyphicon-pencil black"></i></a> <a
										title="Supprimer le module" href="SupprimerModule?id=<c:out value="${module[0]}" />&id_parcours=${id_parcours}&libelle_parcours=${libelle_parcours}"><i
											class="glyphicon glyphicon-remove black"></i></a></td>
									<td><c:out value="${module[1]}" /></td>
									<td><c:out value="${module[2]}" /></td>
									<td><c:out value="${module[3]}" /></td>
									<td><c:out value="${module[4]}" /><td>
								</tr></c:forEach>

							</tbody>
							<tfoot>
								<tr>
									<td>&nbsp;</td>
									<td colspan="12"><div id="creerModule">
											<a href="CreerModule?id_parcours=${id_parcours}&libelle_parcours=${libelle_parcours}"> <i
												class="glyphicon glyphicon-plus black"></i>&nbsp;<b>Creer
													un module</b>
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
