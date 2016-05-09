<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.Utilisateur"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="css/parcours_manager.css" rel="stylesheet">
<link href="css/valider_parcours.css" rel="stylesheet">
<script src="js/jquery-1.12.3.js"></script>
<script src="js/valider_parcours.js"></script>
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
					<sql:setDataSource var="myDS" driver="com.mysql.jdbc.Driver"
						url="jdbc:mysql://localhost:3306/parcours_plus" user="root"
						password="" />

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

					<form id="validationParcours" action="ValiderParcours"
						method="POST">
						<h3 onclick='afficher_description_icones();'>Validation des choix
							de parcours</h3>
						<br>
						<table
							class="table table-bordered table-condensed table-striped table-hover ">
							<thead>
								<tr>
									<th><input type="text" class="form-control rounded"
										id="parcoursFilter" name="parcoursFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="parcoursFilter" name="parcoursFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="choixFilter" name="choixFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="etudiantFilter" name="etudiantFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="etudiantFilter" name="etudiantFilter" value="%"></th>
								</tr>
								<tr>
									<th></th>
									<th>Nom du Parcours</th>
									<th>Choix de parcours de l'étudiant</th>
									<th>Nom de l'étudiant</th>
									<th>Priorite des choix</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${liste_parcours_status}" var="parcours">
									<tr>
										<td><a
											href="ValiderParcours?edit=Edit&id=${parcours[0]}&libelle_parcours=${parcours[1]}"><input
												type="checkbox" name="id"
												value="<c:out value='${parcours[0]}'/>"
												<c:if test="${param.edit=='Edit' && param.id == parcours[0] && param.libelle_parcours == parcours[1]}">checked="checked" </c:if>
												style="margin: 0 0 0 4px" onclick="radio(this)" /></a></td>
										<td><c:choose>
												<c:when
													test="${param.edit=='Edit' && param.id == parcours[0] && param.libelle_parcours == parcours[1]}">
													<input type="hidden" name="libelle_parcours"
														value="<c:out value="${parcours[1]}" />" />
													<c:out value="${parcours[1]}" />
												</c:when>
												<c:otherwise>
													<c:out value="${parcours[1]}" />
												</c:otherwise>
											</c:choose></td>
										<td><c:choose>
												<c:when
													test="${param.edit=='Edit' && param.id == parcours[0] && param.libelle_parcours == parcours[1]}">
													<SELECT name="status" size="1">
														<c:forEach items="${liste_status}" var="status">
															<c:if test="${status!=parcours[2]}">
																<OPTION>${status}
															</c:if>
														</c:forEach>
														<OPTION selected="selected"><c:out
																value="${parcours[2]}" />
													</SELECT>
												</c:when>
												<c:otherwise>
													<c:out value="${parcours[2]}" />
												</c:otherwise>
											</c:choose></td>
										<td><c:out value="${parcours[3]}" /></td>
										<td><c:out value="${parcours[4]}" /></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="12"><div id="validerParcours">
											<div class="inner-addon left-addon">
												<i class="glyphicon glyphicon-save black"></i><input
													type="submit" name="submit" value="Sauvegarder" />
											</div>
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
