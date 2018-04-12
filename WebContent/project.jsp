<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image Search Application</title>
</head>
<body>
<form method="post" action="UploadServlet" enctype="multipart/form-data">
<h1 align="center">Welcome to the MMDB-CBIR</h1>
Select File To Upload: &nbsp&nbsp&nbsp&nbsp&nbsp
<input type="file" name="dataFile" id="fileChooser"/><br>

<input type="submit" value="Upload"/>
<br><br><br>

<%
ArrayList<String> foundImgs =(ArrayList<String>)request.getAttribute("foundImgs");
if(foundImgs !=null){
for(String path:foundImgs){
%>
<img src="<%=path%>">&nbsp&nbsp&nbsp&nbsp&nbsp

<%
}
}
%>
</form>
</body>
</html>