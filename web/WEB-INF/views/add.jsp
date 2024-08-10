<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PassKeeper||Home</title>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        //dynamically change password field without refreshing the whole page
        function generatePassword() {
            fetch('/PassKeeper/generate-password')
                .then(response => response.text())
                .then(password => {
                    document.getElementById('password').value = password;
                });
        }
    </script>
</head>
<body class="tablebody">


<div class="welcome-message">
    Add Password
</div>

<form action="savePasswordInfo" method="post">
    <label>Platform:</label>
    <input type="text" name="name"/>
    <label>Username:</label>
    <input type="text" name="username"/>
    <label>Password:</label>
    <input type="text" id="password" name="password"/>
    <button type="button" onclick="generatePassword()">Generate Password</button>
    <input type="hidden" name="passwordOwner" value="${session_username}" />
    <button type="submit">Save</button>
</form>
<div class="btns">
    <a href="/PassKeeper/" class="addbtn">Back to Dashboard</a> <a href="/PassKeeper/logout" class="logoutbtn">Log Out</a>
</div>
</body>
</html>