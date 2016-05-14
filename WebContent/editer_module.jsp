<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Editer le module" />
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
									<form
										action="EditerModule?id=${id}&id_parcours=${id_parcours}&libelle_parcours=${libelle_parcours}"
										class="form-horizontal" method="post" accept-charset="utf-8">
										<h3 class="titre_module">Editer le module</h3>
										<input name="libelle_parcours" placeholder="Libelle Parcours"
											class="form-control" type="text" id="libelle_parcours"
											value="${libelle_parcours }" readonly /> <input name="id"
											class="form-control" type="hidden" value="${id}" /><input
											name="libelle_module" placeholder="Libelle Module"
											class="form-control" type="text" id="libelle_module" /><span
											class="erreur">${form.erreurs['module']}</span><input
											name="a_la_carte" placeholder="A la carte?"
											class="form-control" type="text" id="a_la_carte" /> <input
											name="nom_responsable" placeholder="Nom du Responsable"
											class="form-control" type="text" id="nom_responsable" /> <span
											class="erreur">${form.erreurs['nom_responsable']}</span> <br>
										<input class="btn btn-success btn btn-success" type="submit"
											value="Mettre à jour" />
									</form>
									<div id="retour_liste_module">
									<br/>
										<a
											href="AfficherModule?id=${id}&id_parcours=${id_parcours}&libelle_parcours=${libelle_parcours}"
											class="btn btn-success btn btn-success"> Retour à la
											liste des modules </a>
									</div>
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