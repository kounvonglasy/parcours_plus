<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Profil étudiant" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<script>
	function date_heure(id) {
		date = new Date;
		annee = date.getFullYear();
		moi = date.getMonth();
		mois = new Array('Janvier', 'F&eacute;vrier', 'Mars', 'Avril', 'Mai',
				'Juin', 'Juillet', 'Ao&ucirc;t', 'Septembre', 'Octobre',
				'Novembre', 'D&eacute;cembre');
		j = date.getDate();
		jour = date.getDay();
		jours = new Array('Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi',
				'Vendredi', 'Samedi');
		h = date.getHours();
		if (h < 10) {
			h = "0" + h;
		}
		m = date.getMinutes();
		if (m < 10) {
			m = "0" + m;
		}
		s = date.getSeconds();
		if (s < 10) {
			s = "0" + s;
		}
		resultat = jours[jour] + ' ' + j + ' ' + mois[moi] + ' ' + annee + ' '
				+ h + ':' + m + ':' + s;
		document.getElementById(id).innerHTML = resultat;
		setTimeout('date_heure("' + id + '");', '1000');
		return true;
	}
</script>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-5  toppad  pull-right col-md-offset-3 ">
				<p class=" text-info">
					<span id="date_heure"></span>
					<script type="text/javascript">
						window.onload = date_heure('date_heure');
					</script>
				</p>
			</div>
			<div
				class="col-xs-9 col-sm-9 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">

				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Profil</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<img src="DisplayBlob?id=${profil.id}" alt=""
									class="img-rounded img-responsive" />
							</div>

							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>Nom:</td>
											<td>${profil.nom}</td>
										</tr>
										<tr>
											<td>Prénom:</td>
											<td>${profil.prenom}</td>
										</tr>
										<tr>
											<td>Parcours:</td>
											<td><c:forEach items="${parcours_status}"
													var="parcours_status">
													<c:if test="${parcours_status.status.libelle == 'Accepté'}">
											${parcours_status.parcours.libelle}</c:if>
												</c:forEach> <c:if test="${empty(parcours_status)}">
													<c:choose>
														<c:when test="${profil.role == 'prof'}">
															<c:forEach items="${profil.parcours}" var="parcours">
															${parcours.libelle}</c:forEach>
														</c:when>
														<c:otherwise>
													Non</c:otherwise>
													</c:choose>
												</c:if></td>
										</tr>
										<tr>
											<td>Login:</td>
											<td>${profil.login}</td>
										</tr>

										<tr>
										<tr>
											<td>Email:</td>
											<td>${profil.email}</td>
										</tr>
										<tr>
											<td>Role:</td>
											<td>${profil.role}</td>
										</tr>
										<c:if test="${profil.role == 'prof'}">
											<tr>
												<td>Description:</td>
												<td>${profil.description}</td>
											</tr>
											<tr>
												<td>Bureau:</td>
												<td>${profil.bureau}</td>
											</tr>
										</c:if>
										<c:if test="${profil.role == 'eleve'}">
											<tr>
												<td>Année:</td>
												<td>${profil.promotion.annee}</td>
											</tr>
											<tr>
												<td>Promotion:</td>
												<td>${profil.promotion.promotion}</td>
											</tr>
											<tr>
												<td>Alternant</td>
												<td>${profil.alternant}</td>
											</tr>
											<tr>
												<td>CV:</td>
												<td><c:if test="${!empty(profil.cv.filename)}">
														<a href="DownloadFile?file=CV&id=${profil.id}">Télécharger
															le CV</a>
													</c:if></td>
											</tr>
											<tr>
												<td>LM:</td>
												<td><c:if test="${!empty(profil.lm.filename)}">
														<a href="DownloadFile?file=LM&id=${profil.id}">Télécharger
															la lettre de motivation</a>
													</c:if></td>
											</tr>
										</c:if>

									</tbody>
								</table>

							</div>
						</div>
					</div>
					<c:choose>
						<c:when test="${sessionScope.session_utilisateur.id != profil.id}">
							<div class="panel-footer">
								<a data-original-title="Broadcast Message" data-toggle="tooltip"
									type="button" class="btn btn-sm btn-primary"
									href="redac_mess.jsp?email_destinataire=${profil.email}&email_expediteur=${sessionScope.session_utilisateur.email}"><i
									class="glyphicon glyphicon-envelope"></i></a>
							</div>
						</c:when>
						<c:otherwise>
							<a href="EditerProfil" data-original-title="Edit this user"
								data-toggle="tooltip" type="button"
								class="btn btn-sm btn-warning"><i
								class="glyphicon glyphicon-edit"></i></a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<c:if test="${sessionScope.session_utilisateur.role == 'eleve'}">
				<div class="col-xs-3 col-sm-3">
					<div class="alert alert-info fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Notification!</strong> Vous avez recu
						<c:out value="${messages_non_lues}" />
						nouveau mail
					</div>
				</div>
			</c:if>
		</div>
	</div>
</body>