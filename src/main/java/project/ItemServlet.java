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
public class ItemServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve items ordered chronologically with the most recent items first
            List<Item> recentItems = getRecentItems();

            // Forward the retrieved items to a JSP page for display
            request.setAttribute("recentItems", recentItems);
            request.getRequestDispatcher("/displayRecentItems.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    private List<Item> getRecentItems() throws SQLException {
        List<Item> recentItems = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve recent items
            String sql = "SELECT * FROM items ORDER BY end_date DESC";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
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

                        recentItems.add(item);
                    }
                }
            }
        }

        return recentItems;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve item details from request parameters
            String authorIdParam = request.getParameter("authorId");
            String endDateParam = request.getParameter("endDate");
            String description = request.getParameter("description");
            String title = request.getParameter("title");

            // Convert authorId to int
            int authorId = Integer.parseInt(authorIdParam);

            // Convert endDate to Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = (Date) dateFormat.parse(endDateParam);

            // Convert closed to boolean
            boolean closed = false;

            // Create the item
            Item newItem = createItem(authorId, endDate, description, title, closed);

            if (newItem != null ){
                request.setAttribute("createdItem", newItem);
                request.getRequestDispatcher("/displayCreatedItem.jsp").forward(request, response);

            }else {
                response.getWriter().write("Item creation failed");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (ParseException | NumberFormatException e) {
            // Handle the case where there is an issue with parameter conversion
            response.getWriter().write("Invalid parameter format");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Item createItem(int authorId, Date endDate, String description, String title, boolean closed)
            throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to insert a new item
            String sql = "INSERT INTO items (authorId, end_date, description, title, closed) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, authorId);
                statement.setDate(2, new java.sql.Date(endDate.getTime()));
                statement.setString(3, description);
                statement.setString(4, title);
                statement.setBoolean(5, closed);

                // Execute the update
                int affectedRows = statement.executeUpdate();

                if (affectedRows == 1) {
                    // Retrieve the generated item ID
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int itemId = generatedKeys.getInt(1);

                            // Create and return the Item object
                            return new Item(itemId, authorId, endDate, description, title, closed);
                        }
                    }
                } else {
                    // Item creation failed
                    return null;
                }
            }
        }

        // Return null if item creation fails
        return null;
    }
}
