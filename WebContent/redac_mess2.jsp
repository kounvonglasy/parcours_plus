<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Envoi d'un message" />
	<jsp:param name="pageTitle" value="Responsables des parcours" />
</jsp:include>
<%@ include file="navbar.jsp"%>

<div class="container">
	<h2>Rédigez votre message ci-dessous</h2>
	<p>Votre déstinataire recevra un message de d'alerte</p>
	<form method="POST" action="EnvoyerMessage">
		<table class="table">
			<thead>
				<tr>
					<!-- titre des colonnes ==> ici on en n'a pas besoin  -->
				</tr>
			</thead>
			<tbody>
				<tr>
					<div class="form-group">
						<label for="titre">Titre :</label> <input type="text"
							class="form-control" id="titre" placeholder="Titre" name="titre" />
					</div>
				</tr>
				<tr>
					<div class="form-group">
						<label for="email">À :</label> <input
							class="form-control" id="email_destinataire"
							placeholder="Entrer email" name="email_destinataire"
							value="<%=request.getParameter("destinataire")%>" /> <input
							type="hidden" name='email_expediteur'
							value="${sessionScope.session_utilisateur.email}" />
					</div>
					<br />
					
					<!-- Liste déroulante des responsable-->
				<div class="dropdown">
					<button class="btn btn-default dropdown-toggle" type="button"
						data-toggle="dropdown">
						Liste des responsable de parcours <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<c:forEach items="${liste_responsables}" var="responsable">
							<li class="dropdown-header">Responsable <c:forEach
									items="${responsable.parcours}" var="parcours">
									<c:out value="${parcours.libelle}" />
								</c:forEach></li>
							<li><a href="#" onClick="ajax_loader('${responsable.nom}')"><c:out
										value="${responsable.nom}" /> <c:out
										value="${responsable.prenom}" /></a></li>
							<li class="divider"></li>
						</c:forEach>
					</ul>
				</div>
				
				<br />
				<br />
				</tr>
				<tr>
					<div class="form-group">
						<label for="email">Message :</label>
						<textarea style="height: 300px;" type="text" class="form-control"
							id="message" name="message"></textarea>
					</div>
				</tr>
			</tbody>
		</table>
		<div style="text-align: center;">
			<button class="btn btn-success" type="submit">Envoyer</button>
		</div>
	</form>
</div>
</body>
</html>
