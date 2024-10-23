<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Room</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 20px;
        }
    </style>
</head>
<body>
<h1 class="text-center">Add New Room</h1>
<div class="container">
    <form action="${pageContext.request.contextPath}/room" method="post">
        <input type="hidden" name="action" value="add">

        <div class="form-group">
            <label for="renterName">Renter Name:</label>
            <input type="text" id="renterName" name="renterName" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="renterContact">Renter Contact:</label>
            <input type="text" id="renterContact" name="renterContact" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="moveInDate">Move-In Date:</label>
            <input type="date" id="moveInDate" name="moveInDate" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="payCycle">Pay Cycle:</label>
            <select id="payCycle" name="payCycle" class="form-control" required>
                <option value="1">Per Month</option>
                <option value="2">Per Quarter</option>
                <option value="3">Per Year</option>
            </select>
        </div>

        <div class="form-group">
            <label for="note">Note:</label>
            <textarea id="note" name="note" class="form-control"></textarea>
        </div>

        <button type="submit" class="btn btn-primary">Add Room</button>
    </form>

    <p style="color: red">Important note: <br> At "Pay cycle column <br> 1 stands for "pay per month" <br> 2 stands for "pay per quarter" <br> 3 stands for "pay per year"</p>
    <br>

    <a href="${pageContext.request.contextPath}/room?action=list" class="btn btn-secondary mt-2">Back to Room List</a>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>