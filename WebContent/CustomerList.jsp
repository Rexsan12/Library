<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Bank Management Application</title>
</head>
<body>
	<center>
		<h1>Bank Management</h1>
        <h2>
        	<a href="new">Add New customer</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Customer</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Customer</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Account Number</th>
                <th>Balance</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="customer" items="${listCustomer}">
                <tr>
                    <td><c:out value="${customer.id}" /></td>
                    <td><c:out value="${customer.name}" /></td>
                    <td><c:out value="${customer.accountno}" /></td>
                    <td><c:out value="${customer.balance}" /></td>
                    <td>
                    	<a href="edit?id=<c:out value='${customer.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${customer.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
