<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="beans.Utilisateur" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>Reponsable des parcours</title>
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="bootstrap/css/style.css" rel="stylesheet">
		<title>Liste des parcours</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
					<a class="navbar-brand" href="#">Parcours +</a>
				</div>
				<div class="navbar-collapse collapse">
					<!-- Liste des choix -->
					<ul class="nav navbar-nav">
						<li class="active"><a href="consulter_parcours.html">Consulter parcours</a></li>
						<li><a href="resp_parcours.html">Responsable des parcours</a></li>
						<li><a href="editer_profil.html">Editer profils</a></li>
					</ul>

					<!-- Barre de recherche -->
					<form class="navbar-form">
						<div class="form-group" style="display: inline;">
							<div class="input-group" >
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
			<form action="Deconnexion" name="login" role="form" class="form-horizontal" method="post" accept-charset="utf-8">
				<input class="btn btn-success btn btn-success" type="submit"
					   value="Deconnection" />
			</form>
			</div>
			</c:if>
		</div>

	</div>
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
	</br>
	</br>
	</br>
	</br>
	<div class="row">
		<div class="col-sm-3">
			<div class="col-sm-1">
			</div>
			<div class="col-sm-7">
				<!-- Miste déroulant des responsable-->
				<div class="dropdown">
					<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Liste des responsable de parcours
						<span class="caret"></span></button>
					<ul class="dropdown-menu">
						<li class="dropdown-header">Responsable Systeme d'information</li>
						<li><a href="#">Monsieur Bill Gates</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Résponsable Systéme d'embarquement</li>
						<li><a href="#">Monsieur Victor Hugo</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Résponsable Réseau</li>
						<li><a href="#">Madame Marie bulo</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Résponsable Business Intelligent</li>
						<li><a href="#">Madame Anne-laure Intelligis</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Résponsable Numérique et Santé</li>
						<li><a href="#">Madame Greys Kate</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-sm-6" id="nom_respo">
			<div class="container" >
				<div class="row" >
					<div class="col-xs-6">
						<!-- Cadre du profil repo parcours-->
						<div class="well well-sm" id="nom_respo2">
							<div class="row">
								<div class="col-sm-1 col-md-2">
									<img src="Images/olive.PNG" alt="" class="img-rounded img-responsive" />
								</div>
								<div class="col-sm-6 col-md-8">
									<h4>Monsieur Bill Gates</h4>
									<br/>
									<small><cite title="Systéme d'information"><i class="glyphicon glyphicon-user">
									</i>&nbsp; Responsable de la filiére systeme d'information</cite></small>
									<p>
									<c:if test="${!empty sessionScope.session_utilisateur}">
										<br/>
										<i class="glyphicon glyphicon-envelope"></i>&nbsp; bill.gates@isep.fr
										<br/>
									</c:if>
										<br/>
										<i class="glyphicon glyphicon-globe"></i><a href="http://www.isep.fr/parcours/">&nbsp; www.isep.fr/parcours/</a>
										<br/>
										<br/>
										<i class="glyphicon glyphicon-folder-close"></i>Valide vos choix de parcours, Professeur de base de donnée, chercheur
									</p>
									<c:if test="${!empty sessionScope.session_utilisateur}">
									<!-- Split button -->
									<div class="dropdown">
										<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Demande
											<span class="caret"></span></button>
										<ul class="dropdown-menu">
											<li><a href="#">Lettre recommandé</a></li>
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
				<strong>Notification!</strong> Votre Lettre de recommandation est prés
			</div>
		</div>
		<div class="col-sm-3">
			<div class="alert alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Notification!</strong> Votre demande des choix à été refusé
			</div>
		</c:when>
		<c:when test="${empty sessionScope.session_utilisateur}">
		<div class="col-sm-3">
		        <!-- Module de connextion-->
        <form action="Connexion" name="login" role="form" class="form-horizontal" method="post" accept-charset="utf-8">
            <label>Identifiant</label>
            <div class="form-group">
                <div class="col-md-8">
                    <input name="username" placeholder="Idenfiant" class="form-control" type="text" id="UserUsername" />
                </div>
            </div>
            <label>Mot de passe</label>
            <div class="form-group">
                <div class="col-md-8">
                    <input name="password" placeholder="Mot de passe"
                           class="form-control" type="password" id="UserPassword" />
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
	</div>
	</body>
</html>