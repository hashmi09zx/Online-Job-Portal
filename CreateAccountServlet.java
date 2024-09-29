import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/hashmidb"; // Change to your database URL
        String user = "root";
        String passwords = "Munger@123";

        // Form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String qualification = request.getParameter("qualification");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");

        Connection connection = null;

        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the database connection
            connection = DriverManager.getConnection(url, user, passwords);

            // Insert user data into the database
            String insertQuery = "INSERT INTO users (username, password, address, email, mobile, qualification, dob, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, mobile);
            preparedStatement.setString(6, qualification);
            preparedStatement.setString(7, dob);
            preparedStatement.setString(8, gender);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                response.setContentType("text/html");
                out.print(
                        "<div style=\" color: white;  padding: 1px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); margin: 0px; max-width: auto;\">\r\n"
                                + //
                                "");
                out.print("<h3>User logged in successfully.</h3>");
           

                 RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
                 rd.include(request, response);
            } else {
                response.setContentType("text/html");
                out.print(
                        "<div style=\" color: #f9004d;  padding: 1px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); margin: 0px; max-width: auto;\">\r\n"
                                + //
                                "");
                out.print("<h3>User not registered due to some error.</h3>");
                out.print("</div>");

                 RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
                rd.include(request, response);

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
                out.print(
                        "<div style=\" color: white;  padding: 1px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); margin: 0px; max-width: auto;\">\r\n"
                                + //
                                "");
                out.println("<h3 style='color:red'>User not registered due to some exception:" + e.getMessage() + "</h3> ");

          
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
