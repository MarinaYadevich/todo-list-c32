<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #343a40;
        }

        .error-container {
            text-align: center;
            padding: 2rem;
            border-radius: 10px;
            background: white;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 90%;
        }

        h1 {
            color: #dc3545;
            margin-bottom: 1rem;
            font-size: 2.5rem;
        }

        .error-icon {
            font-size: 4rem;
            color: #dc3545;
            margin-bottom: 1rem;
        }

        p {
            margin-bottom: 2rem;
            font-size: 1.1rem;
            line-height: 1.6;
        }

        a {
            display: inline-block;
            padding: 0.75rem 1.5rem;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            font-weight: 500;
        }

        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    String backUrl = request.getContextPath() + "/login.html";

    if (errorMessage != null) {
        if (errorMessage.toLowerCase().contains("invalid")) {
            backUrl = request.getContextPath() + "/login.html";
        } else if (errorMessage.toLowerCase().contains("confirm")) {
            backUrl = request.getContextPath() + "/registration.html";
        }
    }
%>
<body>
<div class="error-container">
    <div class="error-icon">❌</div>
    <h1>Error</h1>
    <p><%= errorMessage != null ? errorMessage : "An error occurred" %></p>
    <a href="<%= backUrl %>">Go back</a>
</div>
</body>
</html>
