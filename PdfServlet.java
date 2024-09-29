import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PdfServlet")
public class PdfServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int fileId = Integer.parseInt(request.getParameter("fileId"));
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hashmidb", "root", "Munger@123");

            String sql = "SELECT file_data, file_name FROM file WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, fileId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                byte[] fileData = result.getBytes("file_data");
                String fileName = result.getString("file_name");

                // Determine the content type based on the file extension (you can use your logic here)
                String contentType = "application/octet-stream"; // Default content type

                response.setContentType(contentType);
                response.setHeader("Content-Disposition", "inline; filename=" + fileName);

                OutputStream out = response.getOutputStream();
                out.write(fileData);
                out.flush();
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
    }
}
