import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javax.servlet.RequestDispatcher;

@WebServlet("/FileUploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private String url = "jdbc:mysql://localhost:3306/hashmidb";
    private String user = "root";
    private String password = "Munger@123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String status = request.getParameter("status");
        String websiteLink = request.getParameter("website"); // Get the website link from the form
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            // Insert file_name, status, website_link, and file_data into the database
            String insertQuery = "INSERT INTO file (file_name, status, website_link, file_data) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, fileName);
            preparedStatement.setString(2, status);
            preparedStatement.setString(3, websiteLink); // Set the website link
            InputStream fileInputStream = filePart.getInputStream();
            preparedStatement.setBlob(4, fileInputStream);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
               // out.println("<h3 style='color:green'>File uploaded and credentials stored successfully.</h3>");
                response.setContentType("text/html");
                out.print("<div style=\"background-color: #74B72E; color: white; text-align: center; padding: 1px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); margin: 0px; max-width: auto;\">\r\n" + //
                        "");
                out.print("<h3>File uploaded and credentials stored successfully.</h3>");
                out.print("</div>");

                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.include(request, response);
            } else {
                //out.println("<h3 style='color:red'>File upload and credential storage failed.</h3>");
                response.setContentType("text/html");
                out.println("<h3 style='color:red'>User not logged in due to some error.</h3> ");

                RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //out.println("<h3 style='color:red'>Error: " + e.getMessage() + "</h3>");
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

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "unknown";
    }
}
