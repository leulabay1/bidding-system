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

public class BidderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the bidderid parameter from the request
        String bidderIdParam = request.getParameter("bidderid");

        if (bidderIdParam != null && !bidderIdParam.isEmpty()) {
            try {
                // Convert the bidderid parameter to an integer
                int bidderId = Integer.parseInt(bidderIdParam);

                // Retrieve bids based on bidderid from the database
                List<Bid> bids = getBidsByBidderId(bidderId);

                // Forward the retrieved bids to a JSP page for display
                request.setAttribute("bids", bids);
                request.getRequestDispatcher("/displayBids.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Handle the case where bidderid parameter is not a valid integer
                response.getWriter().write("Invalid bidderid parameter");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            // Handle the case where bidderid parameter is missing
            response.getWriter().write("Missing bidderid parameter");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
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
