<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Responsables des parcours" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body onload="$('#scrolly').ScrollTo();">

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
							<li class="dropdown-header">Responsable <c:forEach
									items="${responsable.parcours}" var="parcours" varStatus="loop">
									<c:out value="${parcours.libelle}" />
									<c:if
										test="${loop.index != fn:length(responsable.parcours) - 1}"> / </c:if>
								</c:forEach></li>
							<li><a href="#" onClick="ajax_loader('${responsable.nom}')"><c:out
										value="${responsable.nom}" /> <c:out
										value="${responsable.prenom}" /></a></li>
							<li class="divider"></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>

		<div class="col-sm-6" id="nom_respo">
			<div class="col-xm-6">
				<!-- Cadre du profil repo parcours-->
				<div class="well well-sm" id="nom_respo2">
					<div class="row">
						<div class="col-sm-4 col-md-4">
							<div id="scrolly"></div>
							<c:choose>
								<c:when test="${fn:length(responsable_default.image) != 0}">
									<img id="image" src="DisplayBlob?id=${responsable_default.id}"
										alt="" class="img-rounded img-responsive" />
								</c:when>
								<c:otherwise>
									<img id="image" src="Images/olive.PNG" alt=""
										class="img-rounded img-responsive" />
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-sm-6 col-md-8">
							<h4>
								<span id="libelle">${responsable_default.nom}
									${responsable_default.prenom}</span>
							</h4>
							<br /> <small><cite
								title="${responsable_default.parcours}"><i
									class="glyphicon glyphicon-user"> </i>&nbsp; Responsable de la
									filière <span id="type_responsable"><c:forEach
											items="${responsable_default.parcours}" var="parcours"
											varStatus="loop">${parcours.libelle}<c:if
												test="${loop.index != fn:length(responsable_default.parcours) - 1}"> / </c:if>
										</c:forEach></span></cite></small>
							<p>
								<c:if test="${!empty sessionScope.session_utilisateur}">
									<br />
									<i class="glyphicon glyphicon-envelope"></i>&nbsp; <span
										id="email"> ${responsable_default.email} </span>
									<br />
									<br /><i class="glyphicon glyphicon-home"></i>&nbsp; Bureau:
									<span
										id="bureau"> ${responsable_default.bureau} </span>
									<br />
								</c:if>
								<br /> <i class="glyphicon glyphicon-globe"></i><a
									href="http://www.isep.fr/parcours/">&nbsp;
									www.isep.fr/parcours/</a> <br /> <br /> <i
									class="glyphicon glyphicon-folder-close"></i> <span
									id="description">${responsable_default.description}</span>
							</p>
							<c:if test="${!empty sessionScope.session_utilisateur}">
								<!-- Split button -->
								<div class="dropdown">
									<button class="btn btn-default dropdown-toggle" type="button"
										data-toggle="dropdown">
										Demande <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a id="envoi_message"
											href="redac_mess.jsp?email_destinataire=${responsable_default.email}">Envoyer
												un message</a></li>
									</ul>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>

		<c:choose>
			<c:when
				test="${sessionScope.session_utilisateur.role == 'prof' || sessionScope.session_utilisateur.role == 'administration'}">
				<div class="col-sm-3">
					<div class="alert alert-info fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Notification!</strong> Vous avez recu
						<c:out value="${messages_non_lues}" />
						nouveau mail
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
									value="Connexion" />
							</div>
						</div>
					</form>
				</div>
			</c:when>
		</c:choose>
	</div>
</body>
</html>