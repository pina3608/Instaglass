<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <body>
	Message:
  	<% if (request.getParameter("pictureName") != null) { %>
		<%= request.getParameter("pictureName")%>
	<% } %>
	<form action="/sendPicture" method="post">
		<div><input type="text" name="pictureName"/></div>
		<div><input type="submit" value="Submit" /></div>   
  	</form>
  </body>
</html>
