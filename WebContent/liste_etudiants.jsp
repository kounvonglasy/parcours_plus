<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Liste des étudiants" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div id="liste-etudiants">
					<form id="listeEtudiants" action="AfficherEtudiants" method="POST">
						<h3>Liste des étudiants</h3>
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
										id="nomFilter" name="nomFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="prenomFilter" name="prenomFilter" value="%"></th>
								</tr>
								<tr>
									<th></th>
									<th>Nom de l'étudiant</th>
									<th>Prénom de l'étudiant</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${liste_etudiants}" var="etudiant">
									<tr>
										<c:url value="/AfficherProfil"
											var="url_afficher_profile">
											<c:param name="id" value="${etudiant[0]}" />
										</c:url>
										<th><a title="Voir le profil de l'étudiant"
											href="${url_afficher_profile}"><i
												class="glyphicon glyphicon-eye-open black"></i></a></th>
										<td><c:out value="${etudiant[1]}" /></td>
										<td><c:out value="${etudiant[2]}" /></td>
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
