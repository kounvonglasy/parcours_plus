<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Voir le message" />
</jsp:include>
<%@ include file="navbar.jsp"%>
<body>
	<div class="container">
		<div class="row">
			<div id="content" class="span12">
				<div id="message">
					<h3>Titre:</h3>
					${message.titre}
					<h3>Message</h3>
					<pre>${message.message}</pre>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
