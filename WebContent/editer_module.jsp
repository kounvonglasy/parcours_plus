<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Reponsable des parcours</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/style.css" rel="stylesheet">
<link href="css/module_manager.css" rel="stylesheet">
<title>Liste des parcours</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<header>
	<!-- image de couverture -->
	<IMG src="Images/isep2.png" WIDTH='100%' HEIGHT='20%'>
</header>
<body>
	<div class="row">
		<div class="col-sm-12">
			<div class="navbar navbar-tabs">
				<!-- Marque : Parcours + -->
				<div class="navbar-header">
					<a class="navbar-brand" href="#">PaRcours +</a>
				</div>
				<div class="navbar-collapse collapse">
					<!-- Liste des choix -->
					<ul class="nav navbar-nav">
						<li class="active"><a href="consulter_parcours.html">Consulter
								parcours</a></li>
						<li><a href="resp_parcours.jsp">Responsable des parcours</a></li>
						<li><a href="editer_profil.html">Editer profils</a></li>
					</ul>
					<!-- Barre de recherche -->
					<form class="navbar-form">
						<div class="form-group" style="display: inline;">
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="What are searching for?"> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-search"></span> </span>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<!-- titre-->
					<h3 class="titre_module">Editer module</h3>
				</div>
			</div>
		</div>
	</div>

	<div class="row">

		<div class="col-sm-12">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">

						<div class="well well-sm"">
							<div class="row">
								<div class="col-xs-2">
									<img src="Images/olive.PNG" alt=""
										class="img-rounded img-responsive" />
								</div>
								<div class="col-sm-6 col-md-8">
									<form
										action="EditerModule?id=${id}&id_parcours=${id_parcours}&libelle_parcours=${libelle_parcours}"
										class="form-horizontal" method="post" accept-charset="utf-8">
										<input name="id" class="form-control" type="hidden"
											value="${id}" /><input name="libelle_module"
											placeholder="Libelle Module" class="form-control" type="text"
											id="libelle_module" /><span class="erreur">${form.erreurs['module']}</span>
										<input name="libelle_parcours" placeholder="Libelle Parcours"
											class="form-control" type="text" id="libelle_parcours"
											value="${libelle_parcours }" readonly /><input
											name="a_la_carte" placeholder="A la carte?"
											class="form-control" type="text" id="a_la_carte" /> <input
											name="nom_responsable" placeholder="Nom du Responsable"
											class="form-control" type="text" id="nom_responsable" /> <span
											class="erreur">${form.erreurs['nom_responsable']}</span> <br>
										<input class="btn btn-success btn btn-success" type="submit"
											value="Mettre à jour" />
									</form>
									<div id="retour_liste_module">
										<a href="AfficherModule?id=${id}&id_parcours=${id_parcours}&libelle_parcours=${libelle_parcours}"
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