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
import javax.servlet.http.HttpSession;

@WebServlet("/CompanyLoginServlet")
public class CompanyLoginServlet extends HttpServlet {
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
        String email = request.getParameter("email");
        String companyPassword = request.getParameter("password");

        Connection connection = null;
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the database connection
            connection = DriverManager.getConnection(url, user, password);

            // Check company credentials using email
            String companyQuery = "SELECT * FROM company_registration WHERE email = ? AND password = ?";
            PreparedStatement companyStatement = connection.prepareStatement(companyQuery);

            companyStatement.setString(1, email);
            companyStatement.setString(2, companyPassword);

            ResultSet companyResultSet = companyStatement.executeQuery();

            if (companyResultSet.next()) {
                // Company authenticated, redirect to company dashboard

                HttpSession session = request.getSession();
                session.setAttribute("authenticatedCompany", email);

                response.setContentType("text/html");
                out.println("<h3 style='color:green'>Company logged in successfully.</h3> ");
                // RequestDispatcher rd = request.getRequestDispatcher("/PostJob.jsp");
                // rd.include(request, response);

                RequestDispatcher rd = request.getRequestDispatcher("/Option_Edit_Post_Jobs.jsp");
                rd.forward(request, response);

            } else {
                // Authentication failed
                response.setContentType("text/html");
                out.println("<h3 style='color:red'>Company login failed. Please check your email and password.</h3> ");
                RequestDispatcher rd = request.getRequestDispatcher("/CompanyLogin.jsp");
                rd.include(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            out.println(
                    "<h3 style='color:red'>Company login failed due to some exception: " + e.getMessage() + "</h3> ");
            RequestDispatcher rd = request.getRequestDispatcher("/company_login.jsp");
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
