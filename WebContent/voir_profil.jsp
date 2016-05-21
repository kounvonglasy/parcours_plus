<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Profil étudiant" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div id="profil-etudiant">
					<c:choose>
						<c:when test="${profil.role == 'eleve'}">
							<h3>Profil de l'étudiant</h3>
						</c:when>
						<c:otherwise>
							<h3>Profil du responsable</h3>
						</c:otherwise>
					</c:choose>

					<div class="col-xs-2">
						<img src="DisplayBlob?id=${profil.id}" alt=""
							class="img-rounded img-responsive" /><br />
					</div>
					<table
						class="table table-bordered table-condensed table-striped table-hover ">
						<thead>
							<tr>
								<th>Nom</th>
								<th>Prénom</th>
								<th>Login</th>
								<th>Email</th>
								<c:if test="${profil.role == 'eleve'}">
									<th>Annee</th>
									<th>Promotion</th>
									<th>CV</th>
									<th>Lettre de motivation</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><c:out value="${profil.nom}" /></td>
								<td><c:out value="${profil.prenom}" /></td>
								<td><c:out value="${profil.login}" /></td>
								<td><c:out value="${profil.email}" /></td>
								<c:if test="${profil.role == 'eleve'}">
									<td><c:out value="${profil.promotion.annee}" /></td>
									<td><c:out value="${profil.promotion.promotion}" /></td>
									<td><c:if test="${!empty(profil.cv.filename)}">
											<a href="DownloadFile?file=CV&id=${profil.id}">Télécharger
												le CV</a>
										</c:if></td>
									<td><c:if test="${!empty(profil.lm.filename)}">
											<a href="DownloadFile?file=LM&id=${profil.id}">Télécharger
												la lettre de motivation</a>
										</c:if></td>
								</c:if>
							</tr>

						</tbody>
					</table>
					<div id="retour_liste_etudiants">
						<br />
						<c:if
							test="${sessionScope.session_utilisateur.role == 'eleve'}">
							<a
								href="AfficherEtudiants"
								class="btn btn-success btn btn-success"> Retour à la page
								liste des etudiants </a>
						</c:if>
						<c:if
							test="${sessionScope.session_utilisateur.role == 'prof' || sessionScope.session_utilisateur.role == 'administration'}">
							<a href="index.jsp" class="btn btn-success btn btn-success">
								Retour à la liste des responsables parcours </a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
