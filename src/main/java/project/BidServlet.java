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

@WebServlet("/bid")
public class BidServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemIdParam = request.getParameter("itemid");

        if (itemIdParam != null && !itemIdParam.isEmpty()) {
            try {
                // Convert the itemid parameter to an integer
                int itemId = Integer.parseInt(itemIdParam);

                // Retrieve bids based on itemid from the database
                List<Bid> bids = getBidsByItemId(itemId);

                // Forward the retrieved bids to a JSP page for display
                request.setAttribute("bids", bids);
                request.getRequestDispatcher("/displayBids.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Handle the case where itemid parameter is not a valid integer
                response.getWriter().write("Invalid itemid parameter");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            // Handle the case where itemid parameter is missing
            response.getWriter().write("Missing itemid parameter");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve bid details from the request
        String dateParam = request.getParameter("date");
        String amountParam = request.getParameter("amount");
        HttpSession session = request.getSession();
        int bidderId = (Integer) session.getAttribute("userId");;
        String itemIdParam = request.getParameter("item_id");
        String statementParam = request.getParameter("statement");

        try {
            // Parse form parameters to appropriate data types
            Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateParam);
            int amount = Integer.parseInt(amountParam);
            int itemId = Integer.parseInt(itemIdParam);
            boolean winner = false;
            boolean paidClient = false;
            boolean paidBidder = false;

            // Create a Bid object with the retrieved details
            Bid bid = new Bid();
            bid.setDate(date);
            bid.setAmount(amount);
            bid.setBidderId(bidderId);
            bid.setItemId(itemId);
            bid.setStatement(statementParam);
            bid.setWinner(winner);
            bid.setPaidClient(paidClient);
            bid.setPaidBidder(paidBidder);

            // Save the bid details to the database
            saveBidToDatabase(bid);

            response.getWriter().write("Bid submitted successfully");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ParseException | NumberFormatException e) {
            response.getWriter().write("Invalid form parameters");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void saveBidToDatabase(Bid bid) {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to insert a new bid into the database
            String sql = "INSERT INTO bids (date, amount, bidder_id, item_id, statement, winner, paid_client, paid_bidder) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, new java.sql.Date(bid.getDate().getTime()));
                statement.setInt(2, bid.getAmount());
                statement.setInt(3, bid.getBidderId());
                statement.setInt(4, bid.getItemId());
                statement.setString(5, bid.getStatement());
                statement.setBoolean(6, bid.isWinner());
                statement.setBoolean(7, bid.isPaidClient());
                statement.setBoolean(8, bid.isPaidBidder());

                // Execute the update
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }
    }

    private List<Bid> getBidsByItemId(int itemId) {
        List<Bid> bids = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to retrieve bids based on itemid
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
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }

        return bids;
    }

}