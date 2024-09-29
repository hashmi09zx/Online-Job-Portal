<!DOCTYPE html>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%
String referrer = ((HttpServletRequest) request).getHeader("referer");

if (referrer == null || !referrer.endsWith("CompanyLoginServlet")) {
    // If the referrer is null (direct access) or not the expected page, redirect to an error page or the main page.
    response.sendRedirect("CompanyLogin.jsp"); // You can create an "ErrorPage.jsp" for this purpose.
}
%>
<html>
<head>
    <title>File Status Update</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        h1 {
            background-color: #0074D9;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        input[type="text"],
        select {
            width: 95%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .center-button {
            text-align: center;
        }

        input[type="submit"] {
            background-color: #0074D9;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Update Job Status</h1>
    <form action="updateFileStatus" method="post">
        File Name: <input type="text" name="file_name" required><br>
        New Status:
        <select name="new_status" required>
            <option value="active">Active</option>
            <option value="inactive">Inactive</option>
        </select><br><br>
        <div class="center-button">
            <input type="submit" value="Update Status">
        </div>
<br>
        <a href="index.jsp" style="display: block; text-align: center; margin-top: 20px;">Click here to go to the home page</a>

    </form>
</body>
</html>
