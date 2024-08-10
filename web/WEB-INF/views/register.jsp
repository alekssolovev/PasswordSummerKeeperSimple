<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup||PassKeeper</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <!--Bootsrap CSS-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body>
<form action="register" method="post"> <!-- Form sends data to proc_login.jsp using HTTP POST method -->
    <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="row border rounder-5 p-3 bg-white shadow box-area">
            <div
                    class="col-md-6 rounder-4 d-flex justify-content-center align-items-center flex-column left-box " style="background: #ffff;">
            </div>
            <div class="col-md-6">
                <div class="row align-items-center">
                    <div class="header-text mb-4">
                        <h2>Hello!! Welcome to PassKeeper!!</h2>
                        <p>Please put your desired Username and Password</p>
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control form-control-lg bg-light fs-6" name="username" placeholder="Username" required>
                    </div>
                    <div class="input-group mb-3">
                        <input type="password" class="form-control form-control-lg bg-light fs-6" name="password" placeholder="Password" required>
                    </div>
                    <div class="input-group mb-3">
                        <input type="password" class="form-control form-control-lg bg-light fs-6" name="confirmpassword" placeholder="Confirm Password" required>
                    </div>
                    <div>
                        <small>Already have an account? <a href="/PassKeeper/login">Login</a></small>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type = "submit" class="btn btn-lg btn-primary w-100 fs-6" value = "Create Account" /> <!-- Submit button to submit the form -->
                    <c:if test="${not empty param.error}">
                        <!-- Display error message in red color -->
                        <p style="color: red;"><c:out value="${param.error}"/></p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</form>

</body>

</html>