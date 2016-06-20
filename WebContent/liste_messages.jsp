<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Liste des messages" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div id="liste_message">
					<h3>Liste des messages</h3>
					<span class="erreur">${form.erreurs['suppression_message']}</span>
					<br>
					<table
						class="table table-bordered table-condensed table-striped table-hover ">
						<thead>
							<tr>
								<th></th>
								<th>Titre</th>
								<th>Status</th>
								<th>Date</th>
								<th>Expediteur</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${liste_messages}" var="message">
								<tr>
									<td><a title="Supprimer le message" href="SupprimerMessage?id=${message[3]}"><i
														class="glyphicon glyphicon-remove black"></i></a></td>
									<td><a href="VoirMessage?id=${message[3]}&id_expediteur=${message[5]}">${message[0]}</a></td>
									<td>${message[1]}</td>
									<td>${message[2]}</td>
									<td>${message[4]}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
