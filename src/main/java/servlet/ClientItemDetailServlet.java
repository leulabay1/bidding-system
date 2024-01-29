package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Bid;
import model.Item;
import model.User;
import utils.DatabaseConnectionManager;

@WebServlet("/client-item-detail")
public class ClientItemDetailServlet extends HttpServlet {

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
                List<Bid> bids = getBidsByItemId(itemId);
                bids = getBiddersByBidId(bids);


                // Forward the retrieved item and bids to a JSP page for display
                request.setAttribute("item", item);
                request.setAttribute("bids", bids);

                request.getRequestDispatcher("item-detail.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Handle the case where itemId parameter is not a valid integer
                response.getWriter().write("Invalid itemId parameter");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately in your application
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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

    private List<Bid> getBidsByItemId(int itemId) throws SQLException {
        List<Bid> bids = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve bids based on item ID
            String sql = "SELECT * FROM bids WHERE item_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, itemId);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Populate Bid objects from the result set
                        Bid bid = new Bid();
                        bid.setId(resultSet.getInt("id"));
                        bid.setDate(resultSet.getDate("date"));
                        bid.setAmount(resultSet.getInt("amount"));
                        bid.setBidderId(resultSet.getInt("bidder_id"));
                        bid.setItemId(resultSet.getInt("item_id"));
                        bid.setStatement(resultSet.getString("statement"));
                        bid.setWinner(resultSet.getBoolean("winner"));
                        bid.setPaidClient(resultSet.getBoolean("paid_client"));
                        bid.setPaidBidder(resultSet.getBoolean("paid_bidder"));

                        bids.add(bid);
                    }
                }
            }
        }

        return bids;
    }

    // get bidders from bids
    private List<Bid> getBiddersByBidId(List<Bid> bids) throws SQLException {
        List<User> bidders = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve bids based on item ID
            String sql = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (Bid bid : bids) {
                    statement.setInt(1, bid.getBidderId());

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            // Populate Bid objects from the result set
                            User bidder = new User();
                            bidder.setId(resultSet.getInt("id"));
                            bidder.setName(resultSet.getString("name"));
                            bidder.setPassword(resultSet.getString("password"));
                            bidder.setUsername(resultSet.getString("username"));
                            bidder.setAddress(resultSet.getString("address"));
                            bidders.add(bidder);

                            bid.setBidderName(bidder.getName());

                        }
                    }
                }
            }
        }

        return bids;
    }
}
