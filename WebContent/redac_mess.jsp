<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Envoi d'un message" />
</jsp:include>
<%@ include file="navbar.jsp"%>

<div class="container">
	<h2>Rédigez votre message ci-dessous</h2>
	<p>Votre déstinataire recevra un message d'alerte</p>
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
						<label for="email">À :</label> <input type="text"
							class="form-control" id="email_destinataire"
							placeholder="Entrer email" name="email_destinataire"
							value="<%=request.getParameter("email_destinataire")%>" /> <input
							type="hidden" name='email_expediteur'
							value="${sessionScope.session_utilisateur.email}" />
					</div>
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
