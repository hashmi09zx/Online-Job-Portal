<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Company Registration</title>

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
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        textarea {
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
    <h1>Company Registration</h1>
    <form action="/CreateAccount/CompanyRegistrationServlet" method="POST">
        <label for="companyName">Company Name:</label>
        <input type="text" id="companyName" name="companyName" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="contactDetails">Contact Details:</label>
        <input type="text" id="contactDetails" name="contactDetails" required>

        <label for="fieldOfCompany">Field of Company:</label>
        <input type="text" id="fieldOfCompany" name="fieldOfCompany" required>

        <label for="address">Address:</label>
        <textarea id="address" name="address" rows="4" required></textarea>

        <input type="submit" value="Register">
        <br><br>
        <a href="CompanyLogin.jsp">Already have an account, Click here to log in.</a><br><br>
        <a href="index.jsp">Click here to go on home page.</a><br><br>
    </form>
</body>
</html>
