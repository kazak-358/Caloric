<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manufacturer Manager</title>
</head>
<body>
<div align="center">
    <h2>Manufacturer Manager</h2>
    <form method="get" action="/manufacturers/search">
        <input type="text" name="keyword" />  
        <input type="submit" value="Search" />
    </form>
    <h3><a href="/manufacturers/new">New Manufacturer</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Products</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listManufacturer}" var="manufacturer">
        <tr>
            <td>${manufacturer.id}</td>
            <td>${manufacturer.name}</td>
            <td>${manufacturer.products.size()}</td>
            <td>
                <a href="/manufacturers/edit?id=${manufacturer.id}">Edit</a>
                   
                <a href="/manufacturers/delete?id=${manufacturer.id}">Delete</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>   
</body>
</html>