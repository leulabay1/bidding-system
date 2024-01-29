package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Bid;
import model.Client;
import model.Item;
import model.User;
import utils.DatabaseConnectionManager;

import java.sql.*;

@WebServlet("/items")
public class ItemServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve items ordered chronologically with the most recent items first

            List<Item> recentItems;

            if (request.getParameter("search") == null) {
                recentItems = getRecentItems();
            } else {
                String search = request.getParameter("search");
                recentItems = getRecentItems();
                List<Item> matchingItems = new ArrayList<>();
                for (Item item : recentItems) {
                    if (item.getTitle().toLowerCase().contains(search.toLowerCase())) {
                        matchingItems.add(item);
                    }
                }
                recentItems = matchingItems;
            }

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            List<Bid> bids = getBidsByBidderId(user.getId());
            bids = getItemsByBids(bids);
            HashMap<String, Integer> bidInfo = new HashMap<String, Integer>();

            bidInfo.put("totalBids", bids.size());
            bidInfo.put("pendingBids", getTotalUnpaidBids(user.getId()));
            bidInfo.put("paidBids", getTotalPaidBids(user.getId()));
            bidInfo.put("expend", Math.round(getTotalSpent(user.getId())));

            request.setAttribute("items", recentItems);
            request.setAttribute("bids", bids);
            request.setAttribute("bidInfo", bidInfo);


            if (request.getParameter("search") != null) {
                request.getRequestDispatcher("item-list.jsp?search=" + request.getParameter("search")).forward(request, response);
            } else {
                request.getRequestDispatcher("item-list.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private List<Bid> getItemsByBids(List<Bid> bids) throws SQLException {
        List<Item> items = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve items based on bids
            String sql = "SELECT * FROM items WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (Bid bid : bids) {
                    statement.setInt(1, bid.getItemId());
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
                            bid.setItemTitle(item.getTitle());
                            bid.setImageUrl(item.getImageUrl());
                            items.add(item);
                        }
                    }
                }
            }
        }
        return bids;
    }

    private List<Item> getRecentItems() throws SQLException {
        List<Item> recentItems = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve recent items
            String sql = "SELECT * FROM items where closed = 0  ORDER BY end_date DESC";
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
                        item.setMaxAmount(resultSet.getFloat("max_amount"));
                        item.setTotalNo(resultSet.getInt("total_no"));
                        item.setImageUrl(resultSet.getString("image_url"));
                        recentItems.add(item);
                    }
                }
            }
        }

        return recentItems;
    }

    private Float getHighestBid(int itemId) throws SQLException {
        Float highestBid = (float) 0;
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve recent items
            String sql = "SELECT MAX(amount) AS highestBid FROM bids WHERE item_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, itemId);
                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve the maximum amount from the result set
                        highestBid = resultSet.getFloat("highestBid");
                    }
                }
            }
        }
        return highestBid;
    }

    private int getTotalPaidBids(int bidderId) throws SQLException {
        int totalPaidBids = 0;
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve recent items
            String sql = "SELECT COUNT(*) AS totalPaidBids FROM bids WHERE bidder_id = ? AND paid_bidder = 1";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bidderId);
                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve the count from the result set
                        totalPaidBids = resultSet.getInt("totalPaidBids");
                    }
                }
            }
        }
        return totalPaidBids;
    }

    private int getTotalUnpaidBids(int bidderId) throws SQLException {
        int totalUnpaidBids = 0;
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve recent items
            String sql = "SELECT COUNT(*) AS totalUnpaidBids FROM bids WHERE bidder_id = ? AND paid_bidder = 0 AND winner = 1";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bidderId);
                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve the count from the result set
                        totalUnpaidBids = resultSet.getInt("totalUnpaidBids");
                    }
                }
            }
        }
        return totalUnpaidBids;
    }

    private Float getTotalSpent(int bidderId) throws SQLException {
        Float totalSpent = (float) 0;
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve recent items
            String sql = "SELECT SUM(amount) AS totalSpent FROM bids WHERE bidder_id = ? AND paid_bidder = 1";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bidderId);
                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve the sum from the result set
                        totalSpent = resultSet.getFloat("totalSpent");
                    }
                }
            }
        }
        return totalSpent;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Retrieve item details from request parameters
            HttpSession session = request.getSession();
            String endDateParam = request.getParameter("endDate");
            System.out.println(endDateParam);
            String description = request.getParameter("description");
            String title = request.getParameter("title");
            String imageUrl = request.getParameter("imageUrl");

            Client user = (Client) session.getAttribute("user");

            // Convert endDate to Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Parse the String to java.util.Date
            java.util.Date utilDate = dateFormat.parse(endDateParam);

            // Convert to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Convert closed to boolean
            boolean closed = false;
            Float maxAmount = (float) 0;
            int totalNo = 0;

            // Create the item
            Item newItem = createItem(user.getId(), sqlDate, description, title, closed, maxAmount, totalNo, imageUrl);

            if (newItem != null ){

                response.sendRedirect("client-item");

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

    private Item createItem(int authorId, Date endDate, String description, String title, boolean closed, Float maxAmount, int totalNo, String imageUrl)
            throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to insert a new item
            String sql = "INSERT INTO items (authorId, end_date, description, title, closed, total_no, max_amount, image_url ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, authorId);
                statement.setDate(2, new java.sql.Date(endDate.getTime()));
                statement.setString(3, description);
                statement.setString(4, title);
                statement.setBoolean(5, closed);
                statement.setInt(6, totalNo);
                statement.setFloat(7, maxAmount);
                statement.setString(8, imageUrl);

                // Execute the update
                int affectedRows = statement.executeUpdate();

                if (affectedRows == 1) {
                    // Retrieve the generated item ID
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int itemId = generatedKeys.getInt(1);

                            // Create and return the Item object
                            return new Item(itemId, authorId, endDate, description, title, closed,maxAmount, totalNo, imageUrl);
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

    private List<Bid> getBidsByBidderId(int bidderId) {
        List<Bid> bids = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve bids based on bidderid
            String sql = "SELECT * FROM bids WHERE bidder_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bidderId);

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
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }

        return bids;
    }
}
