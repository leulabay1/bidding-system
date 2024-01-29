package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Client;
import model.Item;
import utils.DatabaseConnectionManager;

@WebServlet("/client-item")
public class ClientItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve authorId parameter from the request
        try {
            // Convert authorId parameter to an integer

            HttpSession session = request.getSession();
            Client user = (Client) session.getAttribute("user");
            int authorId = user.getId();
            HashMap<String, Integer> clientInfo = new HashMap<String, Integer>();
            List<Item> items = getItemsByAuthor(authorId);

            clientInfo.put("totalMoneyMade", getTotalMoney(authorId));
            clientInfo.put("totalPaidItems", getTotalPaidItems(authorId));
            clientInfo.put("totalPendingItems", getTotalPendingItems(authorId));
            clientInfo.put("totalItems",items.size());

            // Forward the retrieved items to a JSP page for display
            request.setAttribute("items", items);
            request.setAttribute("user", user);
            request.setAttribute("clientInfo", clientInfo);
            request.getRequestDispatcher("/client-dashboard.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle the case where authorId parameter is not a valid integer
            response.getWriter().write("Invalid authorId parameter");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
                        item.setMaxAmount(resultSet.getFloat("max_amount"));
                        item.setTotalNo(resultSet.getInt("total_no"));
                        item.setImageUrl(resultSet.getString("image_url"));
                        items.add(item);
                    }
                }
            }
        }

        return items;
    }

    private int getTotalMoney(int authorId) throws SQLException {
        int totalMoneyMade = 0;

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve items based on authorId
            String sql = "SELECT * FROM items WHERE authorId = ? && closed = true";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, authorId);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Populate Item objects from the result set
                        Item item = new Item();
                        item.setMaxAmount(resultSet.getFloat("max_amount"));

                        totalMoneyMade += (int) item.getMaxAmount();
                    }
                }
            }
        }

        return totalMoneyMade;
    }

    private int getTotalPaidItems(int authorId) throws SQLException {
        int totalItems = 0;

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve items based on authorId
            String sql = "SELECT * FROM items WHERE authorId = ? && closed = true";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, authorId);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Populate Item objects from the result set
                        Item item = new Item();
                        item.setMaxAmount(resultSet.getFloat("max_amount"));

                        totalItems += 1;
                    }
                }
            }
        }

        return totalItems;
    }
    private int getTotalPendingItems(int authorId) throws SQLException {
        int totalItems = 0;

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve items based on authorId
            String sql = "SELECT * FROM items WHERE authorId = ? && closed = false";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, authorId);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Populate Item objects from the result set
                        Item item = new Item();
                        item.setMaxAmount(resultSet.getFloat("max_amount"));

                        totalItems += 1;
                    }
                }
            }
        }

        return totalItems;
    }

}
