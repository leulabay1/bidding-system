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
import model.Bid;
import model.Client;
import model.Item;
import model.User;
import utils.DatabaseConnectionManager;

@WebServlet("/user-item-detail")
public class UserItemDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve itemId parameter from the request
        String itemIdParam = request.getParameter("itemId");

        if (itemIdParam != null && !itemIdParam.isEmpty()) {
            try {
                // Convert itemId parameter to an integer
                int itemId = Integer.parseInt(itemIdParam);

                // Retrieve the item and its associated bids from the database
                Item item = getItemById(itemId);
                HashMap<String, Integer> clientInfo = new HashMap<String, Integer>();

                int authorId = item.getAuthorId();

                clientInfo.put("totalMoneyMade", getTotalMoney(authorId));
                clientInfo.put("totalPaidItems", getTotalPaidItems(authorId));
                clientInfo.put("totalPendingItems", getTotalPendingItems(authorId));
                clientInfo.put("totalItems",getTotalItemsByAuthorId(authorId));

                Client client = getClientById(authorId);

                // Forward the retrieved item and bids to a JSP page for display
                request.setAttribute("item", item);
                request.setAttribute("clientInfo", clientInfo);
                request.setAttribute("client", client);

                request.getRequestDispatcher("user-item-detail.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Handle the case where itemId parameter is not a valid integer
                response.sendRedirect("unauthorized.jsp");
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately in your application
            }
        } else {
            // Handle the case where itemId parameter is missing
            response.getWriter().write("Missing itemId parameter");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private Item getItemById(int itemId) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve an item by ID
            String sql = "SELECT * FROM items WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, itemId);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Populate Item object from the result set
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

                        return item;
                    }
                }
            }
        }

        // Return null if item with the specified ID is not found
        return null;
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

    private int getTotalItemsByAuthorId(int authorId) throws SQLException {
        int totalItems = 0;
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

    private Client getClientById(int clientId) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve an item by ID
            String sql = "SELECT * FROM clients WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, clientId);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Populate Item object from the result set
                        Client client = new Client();
                        client.setId(resultSet.getInt("id"));
                        client.setName(resultSet.getString("name"));
                        client.setPassword(resultSet.getString("password"));
                        client.setUsername(resultSet.getString("username"));
                        client.setAddress(resultSet.getString("address"));
                        client.setBankNum(resultSet.getInt("bank_num"));

                        return client;
                    }
                }
            }
        }

        // Return null if item with the specified ID is not found
        return null;
    }
}
