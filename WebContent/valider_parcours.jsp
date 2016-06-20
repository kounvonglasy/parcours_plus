<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Validation des parcours" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body onload="$('#scrolly').ScrollTo();">
	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div id="liste-validation_parcours">
					<form id="validationParcours" action="ValiderParcours"
						method="POST">
						<h3>Validation des choix de parcours</h3>
						<br>
						<p class="hide">
							<button type="submit" name="rechercheParcours" value="rechercheParcours"></button>
						</p>
						<table
							class="table table-bordered table-condensed table-striped table-hover ">
							<thead>
								<tr>
									<th></th>
									<th><input type="text" class="form-control rounded"
										id="choixValidationFilter" name="choixValidationFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="etudiantFilter" name="etudiantFilter" value="%"></th>
									<th><input type="text" class="form-control rounded"
										id="prioriteFilter" name="prioriteFilter" value="%"></th>
								</tr>
								<tr>

									<th>Nom du Parcours</th>
									<th>Choix de validation du responsable</th>
									<th>Nom de l'étudiant</th>
									<th>Priorite des choix</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${liste_parcours_status}" var="parcours">
									<tr>
										<td><c:choose>
												<c:when
													test="${param.edit=='Edit' && param.id == parcours[0] && param.libelle_parcours == parcours[1]}">
													<input type="hidden" name="libelle_parcours"
														value="<c:out value="${parcours[1]}" />" />
													<c:out value="${parcours[1]}" />
												</c:when>
												<c:otherwise>
													<c:out value="${parcours[1]}" />
												</c:otherwise>
											</c:choose></td>
										<td><c:choose>
												<c:when
													test="${param.edit=='Edit' && param.id == parcours[0] && param.libelle_parcours == parcours[1]}">
													<SELECT name="status" size="1">
														<c:forEach items="${liste_status}" var="status">
															<c:if test="${status!=parcours[2]}">
																<OPTION>${status}
															</c:if>
														</c:forEach>
														<OPTION selected="selected"><c:out
																value="${parcours[2]}" />
													</SELECT>
													<div id="scrolly"></div>
													<input type="hidden" name="id"
														value="<c:out value='${parcours[0]}' />" />
												</c:when>
												<c:otherwise>
													<a
														href="ValiderParcours?edit=Edit&id=${parcours[0]}&libelle_parcours=${parcours[1]}">
														<c:out value='${parcours[2]}' />
													</a>
												</c:otherwise>
											</c:choose></td>
										<td><c:out value="${parcours[3]}" /></td>
										<td><c:out value="${parcours[4]}" /></td>
									</tr>
								</c:forEach>

							</tbody>
							<tfoot>
								<tr>
									<td colspan="12"><div id="validerParcours">
											<div class="inner-addon left-addon">
												<i class="glyphicon glyphicon-save black"></i><input
													type="submit" name="submit" value="Sauvegarder" />
											</div>
										</div></td>
								</tr>
							</tfoot>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
