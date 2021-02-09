<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Manager</title>
</head>
<body>
<div align="center">
    <h2>Product Manager</h2>
    <form method="get" action="/products/search">
        <input type="text" name="keyword" />  
        <input type="submit" value="Search" />
    </form>
    <h3><a href="/products/new">New Product</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Manufacturer</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listProduct}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.manufacturerName}</td>
            <td>
                <a href="/products/edit?pid=${product.id}&mid=${product.manufacturerId}">Edit</a>
                   
                <a href="/products/delete?pid=${product.id}">Delete</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>   
</body>
</html>