<%@ page import="java.sql.*, java.io.*, javax.servlet.http.HttpServletRequest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
String referrer = ((HttpServletRequest) request).getHeader("referer");

if (referrer == null || !referrer.endsWith("LoginServlet")) {
    // If the referrer is null (direct access) or not the expected page, redirect to an error page or the main page.
    response.sendRedirect("login.jsp"); // You can create an "ErrorPage.jsp" for this purpose.
}
%>
<html>
<head>
    <title>Active Jobs</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            font-size: 36px;
            text-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1);
        }
        .file-list {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #fff;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .file-name a {
            text-decoration: none;
            color: blue;
        }
        .file-name {
            flex: 1;
            margin-right: 10px;
        }
        .apply-button button {
            padding: 8px 12px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .apply-button button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Active Job Listings</h1>
    <% 
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hashmidb", "root", "Munger@123");

            String sql = "SELECT id, file_name, website_link FROM file WHERE status = 'active'";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int fileId = result.getInt("id");
                String fileName = result.getString("file_name");
                String websiteLink = result.getString("website_link");

                %>
                <div class="file-list">
                    <div class="file-name">
                        <a href="PdfServlet?fileId=<%= fileId %>"><%= fileName %></a>
                    </div>
                    <% if (websiteLink != null && !websiteLink.isEmpty()) { %>
                        <div class="apply-button">
                            <button onclick="window.open('<%= websiteLink %>','_blank')">Apply</button>
                        </div>
                    <% } %>
                </div>
                <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    %>
</body>
</html>
