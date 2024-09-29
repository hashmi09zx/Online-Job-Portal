import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileListServlet")
public class FileListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hashmidb", "root", "Munger@123");

            String sql = "SELECT id, file_name, website_link FROM file";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            // StringBuilder to construct HTML content for displaying file list
            StringBuilder fileList = new StringBuilder();
            while (result.next()) {
                int fileId = result.getInt("id");
                String fileName = result.getString("file_name");
                String websiteLink = result.getString("website_link");

                // Generate link for file name
                fileList.append("<a href='PdfServlet?fileId=").append(fileId).append("'>").append(fileName).append("</a>");

                // Generate Apply button for website link
                if (websiteLink != null && !websiteLink.isEmpty()) {
                    fileList.append("&nbsp;&nbsp;<a href='").append(websiteLink).append("' target='_blank'>Apply</a>");
                }

                fileList.append("<br>");
            }
            // Setting the response content type to HTML and writing the file list content
            response.setContentType("text/html");
            response.getWriter().write(fileList.toString());
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
