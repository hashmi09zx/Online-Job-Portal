import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/hashmidb"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Munger@123"; // Replace with your database password

        // Form data
        String email = request.getParameter("email"); // Change to "email" field
        String userPassword = request.getParameter("password");

        Connection connection = null;
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the database connection
            connection = DriverManager.getConnection(url, user, password);

            // Check user credentials using email
            String query = "SELECT * FROM users WHERE email = ? AND password = ?"; // Changed to use email
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email); // Change to use "email" field
            preparedStatement.setString(2, userPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User authenticated, redirect to index.jsp
                // response.sendRedirect("index.jsp");

                // response.setContentType("text/html");
                // out.println("<h3 style='color:green'>User logged in successfully.</h3> ");

                response.setContentType("text/html");
                out.print("<div style=\"background-color: #74B72E; color: white; text-align: center; padding: 1px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); margin: 0px; max-width: auto;\">\r\n" + //
                        "");
                out.print("<h3>User logged in successfully.</h3>");
                out.print("</div>");

                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.include(request, response);
            } else {
                // Authentication failed
                response.setContentType("text/html");
                out.println("<h3 style='color:red'>Login Faileded</h3> ");
               

                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                rd.include(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // response.getWriter().println("Database connection error.");

            response.setContentType("text/html");
            out.println("<h3 style='color:red'>User not logged in due to some exception: " + e.getMessage() + "</h3> ");

            RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
            rd.include(request, response);
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
