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
					<br>
					<table
						class="table table-bordered table-condensed table-striped table-hover ">
						<thead>
							<tr>
								<th>Titre</th>
								<th>Status</th>
								<th>Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${liste_messages}" var="message">
								<tr>
									<td><a href="VoirMessage?id=${message[3]}">${message[0]}</a></td>
									<td>${message[1]}</td>
									<td>${message[2]}</td>
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
