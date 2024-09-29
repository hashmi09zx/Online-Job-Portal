<!DOCTYPE html>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%
String referrer = ((HttpServletRequest) request).getHeader("referer");

if (referrer == null || !referrer.endsWith("CompanyLoginServlet")) {
    // If the referrer is null (direct access) or not the expected page, redirect to an error page or the main page.
    response.sendRedirect("CompanyLogin.jsp"); // You can create an "ErrorPage.jsp" for this purpose.
}
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>File Upload</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            text-align: center;
        }

        h1 {
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            text-align: left;
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="file"] {
            width: 95%;
            padding: 10px;
            margin: 6px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        select {
            width: 100%;
            padding: 10px;
            margin: 6px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="text"] {
            width: 95%;
            padding: 10px;
            margin: 6px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #007BFF;
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
    <h1>Upload Job</h1>
    <form action="FileUploadServlet" method="POST" enctype="multipart/form-data">
        <label for="file">Select a file or image:</label>
        <input type="file" id="file" name="file" accept=".pdf, .doc, .docx, .jpg, .jpeg, .png" required><br><br>
        
        <label for="status">Select Status:</label>
        <select id="status" name="status" required>
            <option value="active">Active</option>
            <option value="inactive">Inactive</option>
        </select>
        <br><br>

        <label for="website">Enter Form Link:</label>
        <input type="text" id="website" name="website" required><br><br>
        
        <input type="submit" value="Upload"><br><br>

        <a href="index.jsp" style="display: block; text-align: center; margin-top: 20px;">Click here to go to the home page</a>

    </form>
</body>
</html>
