<%@ page import = "javax.servlet.RequestDispatcher" %>
<%
RequestDispatcher rd = request.getRequestDispatcher("AfficherResponsables");
rd.forward(request, response);
%> 

