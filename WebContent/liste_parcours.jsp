<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Liste des parcours" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div id="liste-parcours">
					<span class="erreur">${form.erreurs['nom_responsable']}</span>
					<form id="listeParcours" action="RechercherParcours" method="POST">
						<h3
							<c:if
							test="${sessionScope.session_utilisateur.role == 'responsable' && sessionScope.session_utilisateur.parcours == '[]'}"> onclick='afficher_description_icones();'</c:if>>Liste
							des parcours</h3>
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

										<c:url value="/EditerParcours" var="url_edition_parcours">
											<c:param name="id" value="${parcours[0]}" />
											<c:param name="nom_responsable" value="${parcours[2]}" />
										</c:url>
										<c:url value="/AfficherModule" var="url_affichage_modules">
											<c:param name="id_parcours" value="${parcours[0]}" />
											<c:param name="libelle_parcours" value="${parcours[1]}" />
										</c:url>
										<c:url value="/SupprimerParcours"
											var="url_suppression_parcours">
											<c:param name="id" value="${parcours[0]}" />
										</c:url>
										<c:choose>
											<c:when
												test="${sessionScope.session_utilisateur.role == 'responsable' && sessionScope.session_utilisateur.parcours == '[]'}">
												<td><a title="Editer parcours"
													href="${url_edition_parcours}"><i
														class="glyphicon glyphicon-pencil black"></i></a> <a
													title="Editer les modules" href="${url_affichage_modules}"><i
														class="glyphicon glyphicon-edit black"></i></a> <a
													title="Supprimer le parcours"
													href="${ url_suppression_parcours}"><i
														class="glyphicon glyphicon-remove black"></i></a></td>
											</c:when>
											<c:otherwise>
												<td><a title="Afficher les modules du parcours"
													href="${url_affichage_modules}"><i
														class="glyphicon glyphicon-eye-open black"></i></a></td>
											</c:otherwise>
										</c:choose>
										<td><c:out value="${parcours[1]}" /></td>
										<td><c:out value="${parcours[2]}" /></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td>&nbsp;</td>
									<td colspan="12"><div id="creerParcours">
											<c:if
												test="${sessionScope.session_utilisateur.role == 'responsable' && sessionScope.session_utilisateur.parcours == '[]'}">
												<a href="CreerParcours"> <i
													class="glyphicon glyphicon-plus black"></i>&nbsp;<b>Creer
														un parcours</b>
												</a>
											</c:if>
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
