import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/updateFileStatus")
public class FileStatusUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from the form
        String fileName = request.getParameter("file_name");
        String newStatus = request.getParameter("new_status");

        // JDBC Database Connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/hashmidb"; // Update with your database URL
        String dbUser = "root";
        String dbPassword = "Munger@123";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Update the status in the database
            String sql = "UPDATE file SET status = ? WHERE file_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newStatus);
            statement.setString(2, fileName);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                // Status updated successfully
                out.println("<h1>Status Updated Successfully</h1>");

                response.sendRedirect("index.jsp");

            } else {
                // File not found or status not updated
                out.println("<h1>Status Not Updated</h1>");
                response.sendRedirect("index.jsp");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<h1>Error: Status Not Updated</h1>");
            response.sendRedirect("index.jsp");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
