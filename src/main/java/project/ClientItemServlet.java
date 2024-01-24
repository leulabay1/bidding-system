package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.Session;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;

public class ClientItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve authorId parameter from the request
        String authorIdParam = request.getParameter("authorId");

        if (authorIdParam != null && !authorIdParam.isEmpty()) {
            try {
                // Convert authorId parameter to an integer
                int authorId = Integer.parseInt(authorIdParam);

                // Retrieve items based on authorId from the database
                List<Item> items = getItemsByAuthor(authorId);

                // Forward the retrieved items to a JSP page for display
                request.setAttribute("itemsByAuthor", items);
                request.getRequestDispatcher("/displayItemsByAuthor.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Handle the case where authorId parameter is not a valid integer
                response.getWriter().write("Invalid authorId parameter");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately in your application
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            // Handle the case where authorId parameter is missing
            response.getWriter().write("Missing authorId parameter");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private List<Item> getItemsByAuthor(int authorId) throws SQLException {
        List<Item> items = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve items based on authorId
            String sql = "SELECT * FROM items WHERE authorId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, authorId);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Populate Item objects from the result set
                        Item item = new Item();
                        item.setId(resultSet.getInt("id"));
                        item.setAuthorId(resultSet.getInt("authorId"));
                        item.setEndDate(resultSet.getDate("end_date"));
                        item.setDescription(resultSet.getString("description"));
                        item.setTitle(resultSet.getString("title"));
                        item.setClosed(resultSet.getBoolean("closed"));

                        items.add(item);
                    }
                }
            }
        }

        return items;
    }

}
