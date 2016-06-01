<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title><%=request.getParameter("pageTitle")%></title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/style.css" rel="stylesheet">
<link href="css/parcours_manager.css" rel="stylesheet">
<link href="css/profil_etudiant.css" rel="stylesheet">
<link href="css/liste_module.css" rel="stylesheet">
<link href="css/valider_parcours.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script src="js/jquery-1.12.3.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/selection_responsable_parcours.js"></script>
<script src="js/jquery-scrollto.js"></script>
<script type="text/javascript" src="js/choix_parcours.js"></script>
<c:if test="${sessionScope.session_utilisateur.role == 'prof' || sessionScope.session_utilisateur.role == 'administration'}">
	<script>
		function afficher_description_icones() {
			$("#description_icones").toggle("blind");
		}
	</script>
</c:if>
</head>
<header>
	<!-- image de couverture -->
	<IMG src="Images/isep2.png" WIDTH='100%' HEIGHT='20%'>
</header>
