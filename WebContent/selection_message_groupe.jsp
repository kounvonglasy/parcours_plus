<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Mail groupé" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<c:if
			test="${sessionScope.session_utilisateur.role == 'prof' || sessionScope.session_utilisateur.role == 'administration' }">
			<h2>Envoyer un mail groupé</h2>
			<h4>Sélectionner les déstinataires puis cliquer sur Rédiger le
				message</h4>
			<form method="POST" action="#">
				<div>
					<button type="button" class="btn btn-info" data-toggle="collapse"
						data-target="#demo1">Etudiants A1</button>
					<div id="demo1" class="collapse">
						<table class="table table">
							<tbody>
								<tr>
									<td>Envoyer un mail grouper aux étudiants A1</td>
									<td><input type="checkbox" name="A1" value="A1"></td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>



				<div>
					<br />
					<button type="button" class="btn btn-info" data-toggle="collapse"
						data-target="#demo2">Etudiants A2</button>
					<div id="demo2" class="collapse">
						<table class="table table">
							<tbody>
								<tr>
									<td>Envoyer un mail grouper aux étudiants <b>A2
											alternants</b></td>
									<td><input type="checkbox" name="A2_alternant"
										value="A2_alternant"></td>
								</tr>
								<tr>
									<td>Envoyer un mail grouper aux étudiants <b>A2 non
											alternants</b></td>
									<td><input type="checkbox" name="A2" value="A2"></td>
								</tr>
								<tr>
									<td>
										<div>

											<button type="button" class="btn btn" data-toggle="collapse"
												data-target="#demo4">Par parcours</button>
											<div id="demo4" class="collapse">
												<table class="table table">
													<tbody>
														<c:forEach items="${liste_parcours}" var="parcours">
															<tr>
																<td>Envoyer un mail groupé aux étudiants de
																	parcours <b>${parcours[1]}</b>
																</td>
																<td><input type="checkbox" name="A2_${parcours[1]}"
																	value="${parcours[1]}"></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<br />

				<div>
					<button type="button" class="btn btn-info" data-toggle="collapse"
						data-target="#demo3">Etudiants A3</button>
					<div id="demo3" class="collapse">
						<table class="table table">
							<tbody>
								<tr>
									<td>Envoyer un mail grouper aux étudiants <b>A3
											alternants</b></td>
									<td><input type="checkbox" name="A3_alternant"
										value="A3_alternant"></td>
								</tr>
								<tr>
									<td>Envoyer un mail grouper aux étudiants <b>A3 non
											alternants</b></td>
									<td><input type="checkbox" name="A3" value="A3"></td>

								</tr>
								<tr>
									<td>
										<div>

											<button type="button" class="btn btn" data-toggle="collapse"
												data-target="#demo5">Par parcours</button>
											<div id="demo5" class="collapse">
												<table class="table table">
													<tbody>
														<c:forEach items="${liste_parcours}" var="parcours">
															<tr>
																<td>Envoyer un mail groupé aux étudiants de
																	parcours <b>${parcours[1]}</b>
																</td>
																<td><input type="checkbox" name="A3_${parcours[1]}"
																	value="${parcours[1]}"></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div>

					<br />
				</div>
				<br /> <br />
				<div style="text-align: center;">
					<button class="btn btn-danger" type="submit">Rédiger le
						message</button>
				</div>
			</form>
		</c:if>
	</div>
	<br />
	<br />
</body>
</html>
