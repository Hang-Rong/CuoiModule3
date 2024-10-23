<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 23/10/2024
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Room List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 20px;
        }
        table {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h1 class="text-center">Room List</h1>

<form action="${pageContext.request.contextPath}/room" method="get" class="form-inline mb-4">
    <input type="hidden" name="action" value="search">
    <input type="text" name="keyword" class="form-control mr-2" placeholder="Search by name or contact">
    <button type="submit" class="btn btn-primary">Search</button>
</form>

<form action="${pageContext.request.contextPath}/room" method="post">
    <input type="hidden" name="action" value="delete">
    <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
            <th>Select</th>
            <th>Room ID</th>
            <th>Renter Name</th>
            <th>Renter Contact</th>
            <th>Move-In Date</th>
            <th>Pay Cycle</th>
            <th>Note</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${rooms}">
            <tr>
                <td><input type="checkbox" name="roomIds" value="${room.roomId}"></td>
                <td>${room.roomId}</td>
                <td>${room.renterName}</td>
                <td>${room.renterContact}</td>
                <td>${room.moveInDate}</td>
                <td>${room.payCycle}</td>
                <td>${room.note}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/room?action=edit&roomId=${room.roomId}" class="btn btn-warning btn-sm">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>

    <p style="color: red">Important note: <br> At "Pay cycle column <br> 1 stands for "pay per month" <br> 2 stands for "pay per quarter" <br> 3 stands for "pay per year"</p>
    <br>
    <button type="submit" class="btn btn-danger">Delete Selected</button>
    <br>
</form>
<br>

<a href="${pageContext.request.contextPath}/room?action=add" class="btn btn-success">Add New Room</a>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>