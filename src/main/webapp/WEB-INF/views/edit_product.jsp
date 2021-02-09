<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Product</title>
</head>
<body>
    <div align="center">
        <h2>Edit Product</h2>
        <form:form action="save" modelAttribute="product">
            <table border="0" cellpadding="5">
                <tr>
                    <td>product ID: </td>
                    <td>${product.id}
                        <form:hidden path="id"/>
                    </td>
                </tr>        
                <tr>
                    <td>manufacturer ID: </td>
                    <td>${product.manufacturerId}
                        <form:hidden path="manufacturerId"/>
                    </td>
                </tr>        
                <tr>
                    <td>Name: </td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td>Caloric: </td>
                    <td><form:input path="caloric" /></td>
                </tr>
                <tr>
                    <td>Protein: </td>
                    <td><form:input path="protein" /></td>
                </tr>
                <tr>
                    <td>Fat: </td>
                    <td><form:input path="fat" /></td>
                </tr>
                <tr>
                    <td>Carbohydrates: </td>
                    <td><form:input path="carbohydrates" /></td>
                </tr>
                <tr>
                    <td>Manufacturer: </td>
                	<td><form:select path="manufacturerName" multiple="true" items="${manufacturerCache}" itemLabel="name" itemValue="name"/></td>
                </tr>
                
                <tr>
                    <td colspan="2"><input type="submit" value="Save"></td>
                </tr>                    
            </table>
        </form:form>
    </div>
</body>
</html>