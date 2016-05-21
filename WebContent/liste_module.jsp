<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Liste des modules" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div id="liste_module">
					<form id="listeModule" action="RechercherModule" method="POST">
						<h3
							<c:if
											test="${sessionScope.session_utilisateur.role == 'admnistration'}">onclick='afficher_description_icones();' </c:if>>Liste
							des modules</h3>
						<input type="hidden" name="libelle_parcours"
							value="${libelle_parcours}" /> <input type="hidden"
							name="id_parcours" value="${id_parcours}" /> <span
							class="erreur">${form.erreurs['suppression_module']}</span>
						<div id="description_icones">
							<i class="glyphicon glyphicon-edit black"></i> Editer le module <br>
							<i class="glyphicon glyphicon-remove black"></i>Supprimer le
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
									<th></th>
									<th><input type="text" class="form-control rounded"
										id="libelleFilter" name="libelleFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="aLaCarteFilter" name="aLaCarteFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="responsableFilter" name="responsableFilter" value="%"></th>
								</tr>
								<tr>
							
									<th></th>
									<th>Nom de parcours</th>
									<th>Libelle du module</th>
									<th>A la carte?</th>
									<th>Nom du responsable</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${liste_module}" var="module">
									<tr>
										<td><c:if
												test="${sessionScope.session_utilisateur.role == 'administration'}">
												<a title="Editer module"
													href="EditerModule?id=<c:out value="${module[0]}" />&id_parcours=${id_parcours}&libelle_parcours=${libelle_parcours}"><i
													class="glyphicon glyphicon-pencil black"></i></a>
												<a title="Supprimer le module"
													href="SupprimerModule?id=<c:out value="${module[0]}" />&id_parcours=${id_parcours}&libelle_parcours=${libelle_parcours}"><i
													class="glyphicon glyphicon-remove black"></i></a>
											</c:if></td>
										<td><c:out value="${module[2]}" /></td>
										<td><c:out value="${module[1]}" /></td>
										<td><c:out value="${module[3]}" /></td>
										<td><c:out value="${module[4]}" /></td>
										
									</tr>
								</c:forEach>

							</tbody>
							<tfoot>
								<tr>
									<td>&nbsp;</td>
									<td colspan="12"><div id="creerModule">
											<c:if
												test="${sessionScope.session_utilisateur.role == 'administration'}">
												<a
													href="CreerModule?id_parcours=${id_parcours}&libelle_parcours=${libelle_parcours}">
													<i class="glyphicon glyphicon-plus black"></i>&nbsp;<b>Creer
														un module</b>
												</a>
											</c:if>
										</div></td>
								</tr>
							</tfoot>
						</table>
					</form>
					<div id="retour_liste_module">
						<a href="AfficherParcours" class="btn btn-success btn btn-success">
							Retour à la liste </a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
