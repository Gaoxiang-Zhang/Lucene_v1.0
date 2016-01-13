<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="model.DBPedia" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table width="100%" border="1" align="center">
	<tr bgcolor="#949494">
	<th>Name</th><th>Content</th>
	</tr>
	<%
		List<DBPedia> list = (List<DBPedia>)request.getAttribute("results");
		for(int i = 0; i < list.size(); i++){
			out.print("<tr><td><a href="+ list.get(i).getResourceAddress()+ ">" + list.get(i).getItemName() + "</a></td>\n");
			out.print("<td>" + list.get(i).getItemContent() + "</td></tr>\n");
 		}
	%>
	</table>
</body>
</html>