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
		<div class="col-sm-12" id="nom_respo">
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

										<input name="username" placeholder="Nom" class="form-control"
											type="text" id="UserUsername" value="${user.nom}" /> <br />
										<input name="userfname" placeholder="Prenom"
											class="form-control" type="text" id="UserFname"
											value="${user.prenom}" /> <br /> <input name="userlogin"
											placeholder="Login" class="form-control" type="text"
											id="UserLogin" value="${user.login}" /> <br /> <input
											name="userpwd" placeholder="Mot de Passe"
											class="form-control" type="text" id="UserPwd"
											value="${user.mdp}" /> <br /> <input name="userpromotion"
											placeholder="Promotion" class="form-control" type="text"
											id="UserUserPromotion" value="${user.promotion}" /> <br /> <input
											name="useremail" placeholder="Email" class="form-control"
											type="text" id="UserUserEmail" value="${user.email}" /> <br />
										<input name="userrole" placeholder="Role" class="form-control"
											type="text" id="UserUserRole" value="${user.role}" /> <br />
										<label>Image de Profil :</label> <input type="file" name="pic"
											accept="image/*"> <br /> <label>CV :</label> <input
											type="file" name="cv"> <br /> <label>Lettre
											de motivation :</label> <input type="file" name="lm"> <br />
										<input type="submit">
									</form>
									<div id="retour_liste_etudiants">
										<br /> <a href="AfficherEtudiants"
											class="btn btn-success btn btn-success"> Retour à la
											liste des étudiants </a>
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