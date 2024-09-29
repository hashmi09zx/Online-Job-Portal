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

@WebServlet("/CompanyRegistrationServlet")
public class CompanyRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/hashmidb"; // Change to your database URL
        String user = "root";
        String password = "Munger@123";

        // Form data
        String companyName = request.getParameter("companyName");
        String email = request.getParameter("email");
        String passwordValue = request.getParameter("password");
        String contactDetails = request.getParameter("contactDetails");
        String fieldOfCompany = request.getParameter("fieldOfCompany");
        String address = request.getParameter("address");

        Connection connection = null;

        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the database connection
            connection = DriverManager.getConnection(url, user, password);

            // Insert company data into the database
            String insertQuery = "INSERT INTO company_registration (company_name, email, password, contact_details, field_of_company, address) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, companyName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, passwordValue);
            preparedStatement.setString(4, contactDetails);
            preparedStatement.setString(5, fieldOfCompany);
            preparedStatement.setString(6, address);
            
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Successful registration
                response.setContentType("text/html");
                out.println("<h3 style='color:green'>Company registered successfully.</h3> ");

                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.include(request, response);
            } else {
                // Registration failed
                response.setContentType("text/html");
                out.println("<h3 style='color:red'>Company not registered due to some error.</h3> ");

                RequestDispatcher rd = request.getRequestDispatcher("/CompanyRegistration.jsp");
                rd.include(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle database connection errors
            response.setContentType("text/html");
            out.println("<h3 style='color:red'>Company not registered due to some exception: " + e.getMessage() + "</h3> ");

            RequestDispatcher rd = request.getRequestDispatcher("/company_registration_form.jsp");
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
