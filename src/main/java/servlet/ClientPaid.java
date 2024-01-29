package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.*;

@WebServlet("/client-paid")
public class ClientPaid extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bidIdParam = request.getParameter("bidId");

        if (bidIdParam != null && !bidIdParam.isEmpty()) {
            try {
                // Convert bidId parameter to an integer
                int bidId = Integer.parseInt(bidIdParam);

                // Update the paid_client property of the bid in the database
                boolean updated = updateClientPaid(bidId);

                if (updated) {
                    // Forward to a success page or send a success response
                    response.getWriter().write("Client payment updated successfully");
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    // Handle the case where payment update fails
                    response.getWriter().write("Payment update failed");
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

    private boolean updateClientPaid(int bidId) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to update the paid_client property of a bid
            String sql = "UPDATE bids SET paid_client = NOT paid_client WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bidId);

                // Execute the update
                int affectedRows = statement.executeUpdate();

                // Return true if the update was successful (1 row affected)
                return affectedRows == 1;
            }
        }
    }
}