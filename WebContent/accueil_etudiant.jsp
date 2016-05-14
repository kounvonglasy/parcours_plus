<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Accueil Etudiant" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<br />
	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div class="row">
					<div class="col-sm-4">
						<a href="Connexion" class="btn btn-primary btn-lg" role="button" style="text-decoration: none;">Afficher
							les responsables</a>
					</div>
					<div class="col-sm-4">
						<a href="AfficherParcours" class="btn btn-primary btn-lg" style="text-decoration: none;">Afficher
							les parcours</a>
					</div>
					<div class="col-sm-4">
						<a href="editer_profil.jsp" class="btn btn-primary btn-lg" style="text-decoration: none;">Editer
							profil</a>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>