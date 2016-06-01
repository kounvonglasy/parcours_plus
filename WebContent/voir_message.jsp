<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Message Privé" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div id="liste-message">
					<span class="erreur">${form.erreurs['nom_responsable']}</span>
					<form id="listeMessage" action="ListMessage" method="POST">
						<h3>Liste des messages</h3>
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

									<th>Titre</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${liste_message}" var="message">
									<tr>
										<c:url value="/EditerParcours" var="url_edition_parcours">
											<c:param name="id" value="${message[0]}" />
											<c:param name="nom_responsable" value="${message[2]}" />
										</c:url>
										<c:url value="/AfficherModule" var="url_affichage_modules">
											<c:param name="id_parcours" value="${message[0]}" />
											<c:param name="libelle_parcours" value="${message[1]}" />
										</c:url>
										<c:url value="/SupprimerParcours"
											var="url_suppression_parcours">
											<c:param name="id" value="${message[0]}" />
										</c:url>
									
										<td><c:out value="${message[0]}" /></td>
										<td><c:out value="${message[1]}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
