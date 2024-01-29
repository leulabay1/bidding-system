package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.*;

@WebServlet("/select-bid")
public class SelectBid extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bidIdParam = request.getParameter("bidId");
        String itemIdParam = request.getParameter("itemId");

        if (bidIdParam != null && !bidIdParam.isEmpty()) {
            try {
                // Convert bidId parameter to an integer
                int bidId = Integer.parseInt(bidIdParam);
                int itemId = Integer.parseInt(itemIdParam);

                // Update the winner property of the bid in the database
                boolean updated = updateBidWinner(bidId);
                boolean updated2 = updateItemStatus(itemId);

                if (updated && updated2) {
                    // Forward to a success page or send a success response
                    response.sendRedirect("client-item-detail?itemId=" + request.getParameter("itemId"));
                } else {
                    // Handle the case where bid update fails
                    response.getWriter().write("Bid update failed");
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            } catch (NumberFormatException e) {
                // Handle the case where bidId parameter is not a valid integer
                response.getWriter().write("Invalid bidId parameter");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately in your application
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            // Handle the case where bidId parameter is missing
            response.getWriter().write("Missing bidId parameter");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean updateBidWinner(int bidId) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to update the winner property of a bid
            String sql = "UPDATE bids SET winner = NOT winner WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bidId);

                // Execute the update
                int affectedRows = statement.executeUpdate();

                // Return true if the update was successful (1 row affected)
                return affectedRows == 1;
            }
        }
    }

    private boolean updateItemStatus(int itemId) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to update the status property of an item
            String sql = "UPDATE items SET closed = NOT closed WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, itemId);

                // Execute the update
                int affectedRows = statement.executeUpdate();

                // Return true if the update was successful (1 row affected)
                return affectedRows == 1;
            }
        }
    }
}