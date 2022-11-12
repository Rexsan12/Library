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
        	<a href="new">Add New Customer</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Customers</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${customer != null}">
			<form action="update" method="post">
        </c:if>
        <c:if test="${customer == null}">
			<form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${customer != null}">
            			Edit customer
            		</c:if>
            		<c:if test="${customer == null}">
            			Add New customer
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${customer != null}">
        			<input type="hidden" name="id" value="<c:out value='${customer.id}' />" />
        		</c:if>            
            <tr>
                <th>NAME: </th>
                <td>
                	<input type="text" name="name" size="45"
                			value="<c:out value='${customer.name}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Account No: </th>
                <td>
                	<input type="text" name="accountno" size="45"
                			value="<c:out value='${customer.accountno}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Balance : </th>
                <td>
                	<input type="text" name="balance" size="5"
                			value="<c:out value='${customer.balance}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
    </div>	
</body>
</html>
