<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Choix des parcours" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<c:if test="${succes_validation != null}">
		<c:out value="${succes_validation}" />
		</c:if>
		<h1></h1>
		<form method="POST" action="ChoisirParcours" id="choixParcours">
			<h3>Choix des parcours</h3>
			<span class="erreur">${form.erreurs['choix_parcours']}</span>
			<table class="table table-striped">
				<thead>
					<!-- Les colonnes du tableau -->
					<tr>
						<th>Liste des parcours</th>
						<th>Choix 1</th>
						<th>Choix 2</th>
						<th>Choix 3</th>
						<th>Choix 4</th>
						<th>Choix 5</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${liste_parcours}" var="parcours"
						varStatus="loop">
						<tr>
							<td><c:out value="${parcours[1]}" /></td>
							<td><input type="checkbox" class="choix${loop.index}"
								name="choix1" value="<c:out value="${parcours[0]}" />" /></td>
							<td><input type="checkbox" class="choix${loop.index}"
								name="choix2" value="<c:out value="${parcours[0]}" />" /></td>
							<td><input type="checkbox" class="choix${loop.index}"
								name="choix3" value="<c:out value="${parcours[0]}" />" /></td>
							<td><input type="checkbox" class="choix${loop.index}"
								name="choix4" value="<c:out value="${parcours[0]}" />" /></td>
							<td><input type="checkbox" class="choix${loop.index}"
								name="choix5" value="<c:out value="${parcours[0]}" />" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="submit" class="btn btn-success" name="submit"
				value="Valider mes
					choix" />
		</form>

	</div>
</body>
</html>