
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div class="row">
	<div class="col-sm-12">
		<nav style="color: black;" class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.jsp">Parcours +</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">

						<li><a href="AfficherParcours"><c:choose>
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
							test="${sessionScope.session_utilisateur.role == 'administration'}">
							<li><a href="ValiderParcours">Valider les parcours</a></li>
						</c:if>
						<c:if test="${sessionScope.session_utilisateur.role == 'eleve' && sessionScope.session_utilisateur.promotion.promotion == 'A1'}">
							<li><a href="ChoisirParcours">Choisir les parcours</a></li>
						</c:if>
						<c:if
							test="${sessionScope.session_utilisateur.role == 'prof' || sessionScope.session_utilisateur.role == 'administration'  }">
							<li><a href="SelectionMessageGroupe">Message groupé</a></li>
						</c:if>
						<c:if test="${!empty sessionScope.session_utilisateur}">
							<li><a href="AfficherMessages">Liste des messages</a></li>
						</c:if>
						<c:if
							test="${sessionScope.session_utilisateur.role == 'administration'}">
							<li><a href="EditerPromotion">Editer les promotions</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
		<div class="navbar navbar-tabs">

			<div style="float: right;">
				<c:if test="${!empty sessionScope.session_utilisateur}">
					<p class="succes">Vous êtes connecté(e) avec le login :
						${sessionScope.session_utilisateur.login}</p>
				</c:if>
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
	</div>
</div>
