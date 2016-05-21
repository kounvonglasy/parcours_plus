<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Liste des parcours" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div id="liste-promotion">
					<form id="listePromotion" action="EditerPromotion"
						method="POST">
						<h3
							<c:if
							test="${sessionScope.session_utilisateur.role == 'administration'}"> onclick='afficher_description_icones();'</c:if>>Liste
							des promotions</h3>
						<span class="erreur">${form.erreurs['promotion']}</span>
						<br />
						<table
							class="table table-bordered table-condensed table-striped table-hover ">
							<thead>
								<tr>
									<th>Promotion</th>
									<th>Annee</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${liste_promotion}" var="promotion">
									<tr>
										<td><c:out value="${promotion.promotion}" /></td>
										<td><input type="text" name="${promotion.promotion}" value="<c:out value="${promotion.annee}"/>" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br /> <input type="submit">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
