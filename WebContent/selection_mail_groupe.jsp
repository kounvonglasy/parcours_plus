<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Mail group�" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<c:if
			test="${sessionScope.session_utilisateur.role == 'prof' || sessionScope.session_utilisateur.role == 'administration' }">
			<h2>Envoyer un mail group�</h2>
			<h4>S�lectionner les d�stinataires puis cliquer sur R�diger le
				message</h4>

			<div>

				<button type="button" class="btn btn-info" data-toggle="collapse"
					data-target="#demo1">Etudiants A1</button>
				<div id="demo1" class="collapse">
					<table class="table table">
						<tbody>
							<tr>
								<td>Envoyer un mail grouper aux �tudiants A1</td>
								<td><input type="checkbox" id="myCheck"></td>
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
								<td>Envoyer un mail grouper aux �tudiants <b>A2
										alternants</b></td>
								<td><input type="checkbox" id="myCheck"></td>
							</tr>
							<tr>
								<td>Envoyer un mail grouper aux �tudiants <b>A2 non
										alternants</b></td>
								<td><input type="checkbox" id="myCheck"></td>
							</tr>

							<!-- envoie mail group� par parcours -->
							<tr>
								<td>
									<div>
										<button type="button" class="btn btn" data-toggle="collapse"
											data-target="#demo4">Par parcours</button>
										<div id="demo4" class="collapse">
											<table class="table table">
												<tbody>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Architecte des Syst�mes d'Information</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Architecte des Syst�mes d'Information - Finance</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Ing�nieur logiciel</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Ing�nieur en Business Intelligence</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Ing�nieur en conception de syst�mes embarqu�s</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Architecte r�seaux et services</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Architecte des Syst�mes de t�l�communications sans
																fil </b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Ing�nieur syst�mes avanc�s de radiocommunications</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Ing�nieur num�rique et sant�</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
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
								<td>Envoyer un mail grouper aux �tudiants <b>A3
										alternants</b></td>
								<td><input type="checkbox" id="myCheck"></td>
							</tr>
							<tr>
								<td>Envoyer un mail grouper aux �tudiants <b>A3 non
										alternants</b></td>
								<td><input type="checkbox" id="myCheck"></td>

							</tr>
							<!-- envoie mail group� par parcours -->
							<tr>
								<td>
									<div>
										<button type="button" class="btn btn" data-toggle="collapse"
											data-target="#demo5">Par parcours</button>
										<div id="demo5" class="collapse">
											<table class="table table">
												<tbody>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Architecte des Syst�mes d'Information</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Architecte des Syst�mes d'Information - Finance</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Ing�nieur logiciel</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Ing�nieur en Business Intelligence</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Ing�nieur en conception de syst�mes embarqu�s</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Architecte r�seaux et services</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Architecte des Syst�mes de t�l�communications sans
																fil </b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Ing�nieur syst�mes avanc�s de radiocommunications</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>
													<tr>
														<td>Envoyer un mail group� aux �tudiants de parcours
															<b>Ing�nieur num�rique et sant�</b>
														</td>
														<td><input type="checkbox" id="myCheck"></td>
													</tr>

												</tbody>
											</table>
										</div>
										<br />
									</div>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</c:if>
		<br /> <br />
		<div style="text-align: center;">
			<a href="redac_mess.html"><button class="btn btn-danger"
					type="submit">R�diger le message</button></a>
		</div>
	</div>
	<br />
	<br />
</body>
</html>
