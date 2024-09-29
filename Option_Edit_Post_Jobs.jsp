
<!DOCTYPE html>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%
String referrer = ((HttpServletRequest) request).getHeader("referer");

if (referrer == null || !referrer.endsWith("CompanyLogin.jsp")) {
    // If the referrer is null (direct access) or not the expected page, redirect to an error page or the main page.
    response.sendRedirect("CompanyLogin.jsp"); 
}
%>
<html>
<head>
    <title>Job Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f5f5f5;
        }

        h1 {
            color: #333;
        }

        form {
            margin: 20px;
            display: inline-block;
        }

        button {
            background-color: #007BFF;
            color: #fff;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Job Management System</h1>

    <form method="post" action="PostJob.jsp">
        <input type="hidden" name="source" value="job_management_system">
        <button type="submit">Post Job</button>
    </form>
    

    <form method="post" action="EditJob.jsp">
        <button type="submit">Edit Job</button>
    </form>

    <br><br><br>
    <a href="index.jsp" style="display: block; text-align: center; margin-top: 20px;">Click here to go to the home page</a>




</body>
</html>
