<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

  <body>
	Insert device Registration_id:
  	<form action="/receivePicture" method="post" enctype="multipart/form-data">
		<div><input name="image" type="file" id="image"></textarea></div>
	    <div><input type="submit" value="Submit" /></div>   
  	</form>
  </body>
</html>
