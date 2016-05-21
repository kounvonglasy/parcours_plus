<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Responsables des parcours" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>

	<div class="row">
		<div class="col-sm-12">
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<!-- titre-->
					<h3 class="titre_parcours">Responsables des parcours</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3">
			<div class="col-sm-1"></div>
			<div class="col-sm-7">
				<!-- Liste déroulante des responsable-->
				<div class="dropdown">
					<button class="btn btn-default dropdown-toggle" type="button"
						data-toggle="dropdown">
						Liste des responsable de parcours <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<c:forEach items="${liste_responsables}" var="responsable">	
						<li class="dropdown-header">Responsable <c:forEach items="${responsable.parcours}" var="parcours">	<c:out value="${parcours.libelle}"/></c:forEach></li>
						<li><a href="#" onClick="ajax_loader('${responsable.nom}')"><c:out value="${responsable.nom}"/> <c:out value="${responsable.prenom}"/></a></li>
						<li class="divider"></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-sm-6" id="nom_respo">
			<div class="container">
				<div class="row">
					<div class="col-xs-6">
						<!-- Cadre du profil repo parcours-->
						<div class="well well-sm" id="nom_respo2">
							<div class="row">
								<div class="col-sm-1 col-md-2">
									<img id ="image" src="DisplayBlob?id=5" alt=""
										class="img-rounded img-responsive" />
								</div>
								<div class="col-sm-6 col-md-8">
									<h4>
										<span id="libelle">Kazi Zakia</span>
									</h4>
									<br /> <small><cite title="Système d'information"><i
											class="glyphicon glyphicon-user"> </i>&nbsp; Responsable de
											la filière <span id="type_responsable">systeme
												d'information</span></cite></small>
									<p>
										<c:if test="${!empty sessionScope.session_utilisateur}">
											<br />
											<i class="glyphicon glyphicon-envelope"></i>&nbsp; <span
												id="email">zakia.kazi@isep.fr </span>
											<br />
										</c:if>
										<br /> <i class="glyphicon glyphicon-globe"></i><a
											href="http://www.isep.fr/parcours/">&nbsp;
											www.isep.fr/parcours/</a> <br /> <br /> <i
											class="glyphicon glyphicon-folder-close"></i>Valide vos choix
										de parcours, Professeur de base de donnée, chercheur
									</p>
									<c:if test="${!empty sessionScope.session_utilisateur}">
										<!-- Split button -->
										<div class="dropdown">
											<button class="btn btn-default dropdown-toggle" type="button"
												data-toggle="dropdown">
												Demande <span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li><a href="#">Lettre recommandée</a></li>
												<li class="divider"></li>
												<li><a href="#">Envoyer un message</a></li>
											</ul>
										</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:choose>
			<c:when test="${!empty sessionScope.session_utilisateur}">
				<div class="col-sm-3">
					<div class="alert alert-info fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Notification!</strong> Vous avez recu un mail
					</div>
				</div>
				<div class="col-sm-3">
					<div class="alert alert-success fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Notification!</strong> Votre Lettre de recommandation est
						prêt
					</div>
				</div>
				<div class="col-sm-3">
					<div class="alert alert-danger fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Notification!</strong> Votre demande des choix à été
						refusée
					</div>
				</div>
			</c:when>
			<c:when test="${empty sessionScope.session_utilisateur}">
				<div class="col-sm-3">
					<!-- Module de connextion-->
					<form action="Connexion" name="login" role="form"
						class="form-horizontal" method="post" accept-charset="utf-8">
						<label>Identifiant</label>
						<div class="form-group">
							<div class="col-md-8">
								<input name="username" placeholder="Idenfiant"
									class="form-control" type="text" id="UserUsername" /> <span
									class="erreur">${form.erreurs['username']}</span>
							</div>
						</div>
						<label>Mot de passe</label>
						<div class="form-group">
							<div class="col-md-8">
								<input name="password" placeholder="Mot de passe"
									class="form-control" type="password" id="UserPassword" /> <span
									class="erreur">${form.erreurs['password']}</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-0 col-md-8">
								<input class="btn btn-success btn btn-success" type="submit"
									value="Connection" />
							</div>
						</div>
					</form>
				</div>
			</c:when>
		</c:choose>
	</div>
</body>
</html>