package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
@WebServlet("/bidder-paid")
public class BidderPaid extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bidIdParam = request.getParameter("bidId");

        if (bidIdParam != null && !bidIdParam.isEmpty()) {
            try {
                // Convert bidId parameter to an integer
                int bidId = Integer.parseInt(bidIdParam);

                // Update the paid_bidder property of the bid in the database
                boolean updated = updateBidderPaid(bidId);

                if (updated) {
                    response.sendRedirect("items");
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
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            // Handle the case where bidId parameter is missing
            response.getWriter().write("Missing bidId parameter");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean updateBidderPaid(int bidId) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Prepare SQL statement to update the paid_bidder property of a bid
            String sql = "UPDATE bids SET paid_bidder = NOT paid_bidder WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bidId);

                // Execute the update
                int affectedRows = statement.executeUpdate();

                // Return true if the update was successful (1 row affected)
                return affectedRows == 1;
            }
        }
    }

    public static String sendPaymentRequest() throws Exception {
        // URL of the payment endpoint
        String paymentUrl = "https://api.chapa.co/v1/hosted/pay";

        // Form data
        String formData = "public_key=CHAPUBK_TEST-rUKme3vQurozLJLNOr450ZCLkhti7eer" +
                "&tx_ref=negade-tx-12345678123435" +
                "&amount=100" +
                "&currency=ETB" +
                "&email=israel@negade.et" +
                "&first_name=Israel" +
                "&last_name=Goytom" +
                "&title=Let us do this" +
                "&description=Paying with Confidence with cha" +
                "&logo=https://chapa.link/asset/images/chapa_swirl.svg" +
                "&callback_url=https://example.com/callbackurl" +
                "&return_url=http://localhost:8080/BiddingSystem/items" +
                "&meta[title]=test";

        // Create a URL object
        URL url = new URL(paymentUrl);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to POST
        connection.setRequestMethod("POST");

        // Enable input/output streams
        connection.setDoOutput(true);

        // Write the form data to the output stream
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = formData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Get the HTTP response code
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Read the response body from the input stream
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } finally {
            // Close the connection
            connection.disconnect();
        }
    }
}