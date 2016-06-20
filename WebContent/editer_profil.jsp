<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Editer profil" />
</jsp:include>
<body>
	<div class="row">
		<div class="col-sm-12">
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<!-- titre-->
					<h3 class="titre_parcours">Editer profil</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<!-- Cadre du profil repo parcours-->
						<div class="well well-sm" id="nom_respo2">
							<div class="row">
								<div class="col-xs-2">
									<img src="DisplayBlob?id=${user.id}" alt=""
										class="img-rounded img-responsive" />
								</div>
								<div class="col-sm-6 col-md-8">
									<form action="EditerProfil?id=${user.id}" method="post"
										enctype="multipart/form-data">
										<label for="UserUsername">Nom: </label> <input name="username"
											placeholder="Nom" class="form-control" type="text"
											id="UserUsername" value="${user.nom}" /> <br /> <label
											for="UserFname">Prenom: </label> <input name="userfname"
											placeholder="Prenom" class="form-control" type="text"
											id="UserFname" value="${user.prenom}" /> <br /> <label
											for="UserLogin">Login: </label> <input name="userlogin"
											placeholder="Login" class="form-control" type="text"
											id="UserLogin" value="${user.login}" /> <br />
										<c:if
											test="${sessionScope.session_utilisateur.role == 'eleve'}">
											<label for="UserUserAnnee">Promotion Année: </label>
											<input name="userannee" placeholder="Promotion Année"
												class="form-control" type="text" id="UserUserAnnee"
												value="${user.promotion.annee}" />
											<br />
											<label for="UserUserAlternant">Alternant: </label>
											<input name="useralternant" placeholder="Alternant?"
												class="form-control" type="text" id="UserUserAlternant"
												value="${user.alternant}" />
											<br />
										</c:if>
										<label for="UserUserEmail">Email: </label> <input
											name="useremail" placeholder="Email" class="form-control"
											type="email" id="UserUserEmail" value="${user.email}" /> <br />
										<c:if
											test="${sessionScope.session_utilisateur.role == 'prof'}">
											<label for="UserUserDescription">Description:</label>
											<input name="userdescription" placeholder="Description"
												class="form-control" type="text" id="UserUserDescription"
												value="${user.description}" />
											<br />
											<label for="UserUserBureau">Bureau:</label>
											<input name="userbureau" placeholder="Bureau"
												class="form-control" type="text" id="UserUserBureau"
												value="${user.bureau}" />
											<br />
										</c:if>

										<div id="nom_respo">
											<label>Image de Profil :</label> <input type="file"
												name="pic" accept="image/*">
											<c:if
												test="${sessionScope.session_utilisateur.role == 'eleve'}">
												<br />
												<label>CV :</label>
												<input type="file" name="cv">
												<br />
												<label>Lettre de motivation :</label>
												<input type="file" name="lm">
											</c:if>
											<br /> <input type="submit">
										</div>
									</form>

									<div id="retour_profil">
										<br /> <a
											href="AfficherProfil?id=${sessionScope.session_utilisateur.id}"
											class="btn btn-success btn btn-success"> Retour à la page
											profil </a>
									</div>
									<br /> <br />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
</body>
</html>