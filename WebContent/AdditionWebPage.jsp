<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="AddServlet" method="get">
<h1>Enter first No:</h1>
<input type="text" name="firstTextBox"/>
<br>
<h1>Enter Second No:</h1>
<input type="text" name="secondTextBox"/>
<br><br>
<input type="submit" name="addButton" value="Add">
</form>
Result:<br>
<%=session.getAttribute("DisplayResult")%>

</body>
</html>