<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PassKeeper||Home</title>
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body class="tablebody">
<div class="welcome-message">
    Update Password
</div>
<form action="../showFormForUpdate/${passwordInfo.passwordId}" method="post">
    <input type="hidden" name="id" value="${passwordInfo.passwordId}" />
    <label>Platform:</label>
    <input type="text" name="name" value="${passwordInfo.name}" />
    <label>Username:</label>
    <input type="text" name="username" value="${passwordInfo.username}" />
    <label>Password:</label>
    <input type="text" name="password" value="${passwordInfo.password}" />
    <input type="hidden" name="passwordOwner" value="${passwordInfo.passwordOwner}" />
    <button type="submit">Save</button>
</form>
<div class="btns">
    <a href="/PassKeeper/" class="addbtn">Back to Dashboard</a> <a href="/PassKeeper/logout" class="logoutbtn">Log Out</a>
</div>
</body>
</html>