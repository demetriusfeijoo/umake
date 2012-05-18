<%@page import="br.com.umake.model.Application" %>
<%  
	Application app = (Application) request.getAttribute("application");
	app.getTemplate();
	
%>