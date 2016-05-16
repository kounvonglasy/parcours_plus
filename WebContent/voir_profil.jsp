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
					<h3>Profil de l'étudiant</h3>
					<table
						class="table table-bordered table-condensed table-striped table-hover ">
						<thead>
							<tr>
								<th>Nom</th>
								<th>Prénom</th>
								<th>Login</th>
								<th>Promotion</th>
								<th>Email</th>
								<th>CV</th>
								<th>Lettre de motivation</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${liste_etudiants}" var="profil">
								<div class="col-xs-2">
									<img src="DisplayBlob?id=${profil[0]}" alt=""
										class="img-rounded img-responsive" /><br/>
								</div>
								<tr>
									<td><c:out value="${profil[1]}" /></td>
									<td><c:out value="${profil[2]}" /></td>
									<td><c:out value="${profil[3]}" /></td>
									<td><c:out value="${profil[4]}" /></td>
									<td><c:out value="${profil[5]}" /></td>
									<td><c:if test="${profil[7] != null}">
											<a href="DownloadFile?file=CV&id=${profil[0]}">Télécharger
												le CV</a>
										</c:if></td>
									<td><c:if test="${profil[8] != null}">
											<a href="DownloadFile?file=LM&id=${profil[0]}">Télécharger
												la lettre de motivation</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div id="retour_liste_etudiants">
						<br /> <a href="AfficherEtudiants"
							class="btn btn-success btn btn-success"> Retour à la liste
							des étudiants </a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
