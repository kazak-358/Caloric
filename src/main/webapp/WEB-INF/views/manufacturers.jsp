<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manufacturer Manager</title>
</head>
<body>
<div align="center">
    <h2>Manufacturer Manager</h2>
    <form method="get" action="/manufacturers/search">
        <input type="text" name="keyword" />  
        <input type="submit" value="Search" />
    </form>
    
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <c:forEach items="${listManufacturer}" var="manufacturer">
        <tr>
            <td>${manufacturer.id}</td>
            <td>${manufacturer.name}</td>
        </tr>
        </c:forEach>
    </table>
</div>   
</body>
</html>