<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Editer le parcours" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="row">
		<div class="col-sm-12">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<div class="well well-sm">
							<div class="row">
								<div class="col-xs-2">
									<img src="Images/olive.PNG" alt=""
										class="img-rounded img-responsive" />
								</div>
								<div class="col-sm-6 col-md-8">
									<form action="EditerParcours?id=${id}" class="form-horizontal"
										method="post" accept-charset="utf-8" name="EditerParcours">
										<h3 class="titre_parcours">Editer le parcours</h3>
										<input name="id" class="form-control" type="hidden"
											value="${id}" /> <input name="libelle_parcours"
											placeholder="Libelle" class="form-control" type="text"
											id="libelle_parcours" /><span class="erreur">${form.erreurs['parcours']}</span>
										<input name="nom_responsable" placeholder="Nom du responsable"
											class="form-control" type="text" id="nom_responsable"
											value="${nom_responsable}" /> <span class="erreur">${form.erreurs['nom_responsable']}</span><br>
										<input class="btn btn-success btn btn-success" type="submit"
											value="Mettre à jour" />
									</form>
									<div id="retour_liste_parcours">
									<br/>
									 <a href="AfficherParcours"
										class="btn btn-success btn btn-success"> Retour à la liste
									</a></div>
									<br />
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