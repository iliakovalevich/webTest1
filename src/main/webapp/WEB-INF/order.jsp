<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <style>
        table {
            align-content: center;
            bgcolor: aqua;
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 80%;
            border-color: darkslateblue;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>

<body bgcolor="#f0e68c">

<h2 style="text-align: center"><a href="<c:url value="/add-new-order"/>">Add new order</a></h2>
<table align="center" bgcolor="aqua">
    <tr>
        <th>Title</th>
        <th>Price</th>
        <th>Order number</th>
        <th>Action</th>
    </tr>
    <c:forEach var="order" items="${orderList}">
        <tr>
            <td>${order.title}</td>
            <td>${order.price}</td>
            <td>${order.id}</td>
            <td><a href="/delete/${order.id}">Delete this item</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>