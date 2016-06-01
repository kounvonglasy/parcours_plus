
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
					<li class="active"><a href="AfficherParcours"> <c:choose>
								<c:when
									test="${sessionScope.session_utilisateur.role == 'administration'}">
									<c:out value="Paramétrage des parcours" />
								</c:when>
								<c:otherwise>
									<c:out value="Consulter les parcours" />
								</c:otherwise>
							</c:choose></a></li>
					<li><a href="index.jsp">Responsable des parcours</a></li>
					<c:if test="${sessionScope.session_utilisateur != null}">
						<li><a href="AfficherEtudiants">Liste des étudiants</a></li>
					</c:if>
					<c:if test="${!empty sessionScope.session_utilisateur}">
						<li><a class="dropdown-toggle" data-toggle="dropdown"
							href="#">Profil <span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a
									href="AfficherProfil?id=${sessionScope.session_utilisateur.id}">Voir
										profil</a></li>
								<li><a href="EditerProfil">Editer profil</a></li>
							</ul></li>
					</c:if>
					<c:if
						test="${sessionScope.session_utilisateur.role == 'prof'}">
						<li><a href="ValiderParcours">Valider les parcours</a></li>
					</c:if>
					<c:if test="${sessionScope.session_utilisateur.role == 'eleve'}">
						<li><a href="ChoisirParcours">Choisir les parcours</a></li>
					</c:if>
					<c:if
						test="${sessionScope.session_utilisateur.role == 'prof' || sessionScope.session_utilisateur.role == 'administration'  }">
						<li><a href="selection_mail_groupe.jsp">Mail groupé</a></li>
					</c:if>
					<c:if
						test="${sessionScope.session_utilisateur.role == 'prof' || sessionScope.session_utilisateur.role == 'administration'  }">
						<li><a href="ListMessage">Voir Message</a></li>
					</c:if>
					<c:if
						test="${sessionScope.session_utilisateur.role == 'administration'}">
						<li><a href="EditerPromotion">Editer les promotions</a></li>
					</c:if>
				</ul>

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
<br />